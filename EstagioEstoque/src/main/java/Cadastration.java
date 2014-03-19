import java.math.BigDecimal;

import com.ideais.stock.dao.AdminDao;
import com.ideais.stock.dao.ImageDao;
import com.ideais.stock.domain.Admin;
import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Dimensions;
import com.ideais.stock.domain.Image;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;

public class Cadastration {

	public static void main(String[] args) {
		
			AdminDao adminDao = new AdminDao();
			Admin admin = new Admin();
			
			admin.setEmail("admin@teste.com");
			admin.setName("Admin Teste");
			admin.setPassword("123");
			adminDao.create(admin);
			
			ImageDao imageDao = new ImageDao();
		
			Image image = new Image();
			Item item = new Item();
			Product product = new Product();
			Subcategory subcategory = new Subcategory();
			Category category = new Category();
			Dimensions dimensions = new Dimensions();
			
			dimensions.setDepth(10.);
			dimensions.setHeight(20.);
			dimensions.setWidth(30.);
			
			product.setName("Luva de boxe");
			product.setActive(true);
			product.setLongDescription("Uma descrição longa.");
			product.setShortDescription("Uma descrição curta.");
			product.setDimensions(dimensions);
			product.setWeight(500000);
			product.setWarranty(36);
			product.setBrand("Paco Ideais");
			product.setModel("XTVZB-4435");
			
			item.setSku(01L);
			item.setActive(true);
			item.setOptionName("Cor");
			item.setOptionValue("Branca");
			item.setPriceFrom(new BigDecimal (25.90));
			item.setPriceFor(new BigDecimal (19.90));
			item.setStock(5);
			item.setRank(0);
			
			subcategory.setName("Luvas");
			
			category.setName("Esportes");
			
			subcategory.setCategory(category);
			product.setCategory(category);
			product.setSubcategory(subcategory);
			item.setProduct(product);
		
			image.setMain(true);
			image.setShoppingCartUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
			image.setProductUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
			image.setAndroidProductUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
			image.setAndroidShowcaseUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
			image.setShowcaseUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
			image.setSuperzoomUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
			image.setItem(item);
			
			imageDao.create(image);
		
	}
}
