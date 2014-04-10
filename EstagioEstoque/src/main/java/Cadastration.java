import java.math.BigDecimal;

import com.ideais.stock.dao.AdminDao;
import com.ideais.stock.dao.CategoryDao;
import com.ideais.stock.dao.ImageDao;
import com.ideais.stock.dao.ItemDao;
import com.ideais.stock.dao.ProductDao;
import com.ideais.stock.dao.SubcategoryDao;
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
			
//			ImageDao imageDao = new ImageDao();
//			CategoryDao categoryDao = new CategoryDao();
//			SubcategoryDao subcategoryDao = new SubcategoryDao();
//			ProductDao productDao = new ProductDao();
//			ItemDao itemDao = new ItemDao();
//			Image image = new Image();
//			Item item = new Item();
//			Product product = new Product();
//			Subcategory subcategory = new Subcategory();
//			Category category = new Category();
//			Dimensions dimensions = new Dimensions();
//			
//			for (int i = 0; i < 10; i++) {
//				
//				category.setName("Esportes " + i);
//				categoryDao.save(category);
//				
//				for (int j = 0; j < 3; j++) {
//					
//					subcategory.setName("Luvas " + j);
//					subcategory.setCategory(category);
//					subcategoryDao.create(subcategory);
//					
//					for (int j2 = 0; j2 < 60; j2++) {
//						
//						product.setCategory(category);
//						product.setSubcategory(subcategory);
//						dimensions.setDepth(10.);
//						dimensions.setHeight(20.);
//						dimensions.setWidth(30.);
//						
//						product.setName("Luva de boxe " + j2);
//						product.setActive(true);
//						product.setLongDescription("Uma descrição longa. " + j2);
//						product.setShortDescription("Uma descrição curta. " + j2);
//						product.setDimensions(dimensions);
//						product.setWeight(500 + j2);
//						product.setWarranty(36);
//						product.setBrand("Paco Ideais " + j2);
//						product.setModel("XTVZB-4435 " + j2);
//						
//						productDao.create(product);
//						
//						for (int k = 0; k < 3; k++) {
//							
//							item.setSku(01L +k);
//							item.setActive(true);
//							item.setOptionName("Cor");
//							item.setOptionValue("Branca");
//							item.setPriceFrom(new BigDecimal (25.90+k));
//							item.setPriceFor(new BigDecimal (19.90+k));
//							item.setStock(5+k);
//							item.setRank(0+k-j2);
//							item.setProduct(product);
//							
//							itemDao.create(item);
//							
//							for (int k2 = 0; k2 < 2; k2++) {
//								
//								image.setMain(true);
//								image.setShoppingCartUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
//								image.setProductUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
//								image.setAndroidProductUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
//								image.setAndroidShowcaseUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
//								image.setShowcaseUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
//								image.setSuperzoomUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
//								image.setItem(item);
//								
//								imageDao.create(image);
//							}
//						}	
//					}	
//				}
//			}
	}
}
