package com.ideais.stock.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.ideais.stock.domain.Image;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Pagination;
import com.ideais.stock.json.ImageJSON;
import com.ideais.stock.json.ItemJSON;
import com.ideais.stock.service.ItemService;
import com.ideais.stock.webservice.domain.CartItemWS;
import com.ideais.stock.webservice.domain.CartWS;

@Path("/item")
public class ItemWS {

	@Autowired
	ItemService itemService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ItemJSON> getItems(
			@QueryParam("orderColumn") @DefaultValue("rank") String orderColumn,
			@QueryParam("order") @DefaultValue("desc") String order,
			@QueryParam("active") @DefaultValue("true") Boolean active,
			@QueryParam("firstResult") @DefaultValue("0") String firstResult,
			@QueryParam("maxResults") @DefaultValue("20") String maxResults) {
		List<ItemJSON> itemJSONs = new ArrayList<ItemJSON>();
		Pagination pagination = new Pagination(orderColumn, order, firstResult,
				maxResults);

		for (Item item : itemService.findAllWithPagination(active, pagination)) {
			itemJSONs.add(new ItemJSON(item));
		}

		return itemJSONs;
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ItemJSON getItemById(@PathParam("id") Long id) {
		return new ItemJSON(itemService.findById(id));
	}

	@Path("/{id}/image")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ImageJSON> getImagesByItemId(@PathParam("id") Long id) {
		List<ImageJSON> imageJSONs = new ArrayList<ImageJSON>();

		for (Image image : itemService.findById(id).getImages()) {
			imageJSONs.add(new ImageJSON(image));
		}

		return imageJSONs;
	}

	@Path("/updatestock")
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updatestock(CartWS cart) {
		String output = cart.getCartItems().toString();

		for (int i = 0; i < cart.getCartItems().size(); i++) {
			CartItemWS itemWS = cart.getCartItems().get(i);
			Item item = itemService.findById(itemWS.getCartItemId());
			Integer newItemStock = item.getStock() - itemWS.getQuantity();

			if (newItemStock < 0 || itemWS.getQuantity() <= 0) {
				return Response.status(400).entity(output).build();
			}

			item.setStock(newItemStock);
			item.setRank(item.getRank() + itemWS.getQuantity());
			item.getProduct().setRank(
					item.getProduct().getRank() + itemWS.getQuantity());
			itemService.save(item);
		}

		return Response.status(201).entity(output).build();
	}
}
