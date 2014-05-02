package com.ideais.stock.action;

import java.util.ArrayList;
import java.util.List;

import com.ideais.stock.domain.Image;
import com.ideais.stock.domain.Item;
import com.ideais.stock.json.internal.InternalImageJSON;
import com.ideais.stock.json.internal.ResponseJSON;
import com.ideais.stock.util.Validade;

public class ImageAction extends AbstractAction<Image, InternalImageJSON> {

	private static final long serialVersionUID = 1L;
	
	private Image image = new Image();

	public String save() {
		if (Validade.isValid(id)) {
			image.setId(Long.valueOf(id));
		}

		if (image.getMain() == null) {
			image.setMain(false);
		} 
		
		Item item = itemService.findById(itemId);
		image.setItem(item);
		
		Image savedImage = imageService.save(image, image.getMain());

		if (savedImage == null) {
			responseOutput = new ResponseJSON<InternalImageJSON>("ERROR",
					"Erro ao salvar categoria.");
			return ERROR;
		}

		responseOutput = new ResponseJSON<InternalImageJSON>("OK",
				new InternalImageJSON(savedImage));
		return SUCCESS;
	}


	public String delete() {
		image = imageService.findById(Long.valueOf(id));
		imageService.delete(image);
		responseOutput = new ResponseJSON<InternalImageJSON>("OK", new InternalImageJSON(image));
		return SUCCESS;
	}

	public String getImagesByItemId() {
		Item item = new Item();
		item.setId(itemId);
		
		List<Image> images = imageService.findByItemId(item);
		List<InternalImageJSON> imagesJSON = new ArrayList<InternalImageJSON>();
		
		for (Image image : images) {
			imagesJSON.add(new InternalImageJSON(image));
		}
		
		responseOutput = new ResponseJSON<InternalImageJSON>("OK", imagesJSON);
		return SUCCESS;
	}

	public Image getImage() {
		return image;
	}


	public void setImage(Image image) {
		this.image = image;
	}

}
