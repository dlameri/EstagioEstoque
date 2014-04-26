import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.stock.domain.Admin;
import com.ideais.stock.domain.Category;
import com.ideais.stock.domain.Dimensions;
import com.ideais.stock.domain.Image;
import com.ideais.stock.domain.Item;
import com.ideais.stock.domain.Product;
import com.ideais.stock.domain.Subcategory;
import com.ideais.stock.service.AdminService;
import com.ideais.stock.service.CategoryService;
import com.ideais.stock.service.ImageService;
import com.ideais.stock.service.ItemService;
import com.ideais.stock.service.ProductService;
import com.ideais.stock.service.SubcategoryService;

@ContextConfiguration({"classpath:spring/applicationContext.xml", "classpath:spring/dataSource.xml"})
@TransactionConfiguration(defaultRollback=false)
@Transactional(propagation=Propagation.REQUIRED)
@RunWith(SpringJUnit4ClassRunner.class)
public class Cadastration extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ItemService itemDao;
	
	@Autowired
	private SubcategoryService subcategoryDao;
	
	@Autowired
	private ImageService imageDao;
	
	@Autowired
	private ProductService productDao;
	
	@Test
	public void main() {

			Admin admin = new Admin();
			
			admin.setEmail("admin2@teste.com");
			admin.setName("Admin Teste2");
			admin.setPassword("123");
			adminService.save(admin);
			
			for (int i = 1; i <= 3; i++) {
				Category category = new Category();
				
				category.setName("Esportes " + i);
				categoryService.save(category);
				
				for (int j = 1; j <= 3; j++) {
					
					Subcategory subcategory = new Subcategory();
					subcategory.setName("Luvas " + j);
					subcategory.setCategory(category);
					subcategoryDao.save(subcategory);
					
					for (int j2 = 0; j2 < 60; j2++) {
						
						Product product = new Product();
						Dimensions dimensions = new Dimensions();
						
						product.setCategory(category);
						product.setSubcategory(subcategory);
						dimensions.setDepth(10.);
						dimensions.setHeight(20.);
						dimensions.setWidth(30.);
						
						product.setName("Luva de boxe " + j2);
						product.setActive(true);
						product.setLongDescription("Uma descrição longa. " + j2);
						product.setShortDescription("Uma descrição curta. " + j2);
						product.setDimensions(dimensions);
						product.setWeight(500 + j2);
						product.setWarranty(36);
						product.setBrand("Paco Ideais " + j2);
						product.setModel("XTVZB-4435 " + j2);
						
						productDao.save(product);
						
						for (int k = 0; k < 3; k++) {
							Item item = new Item();
							
							item.setSku(01L +k);
							item.setActive(true);
							item.setOptionName("Cor");
							item.setOptionValue("Branca");
							item.setPriceFrom(new BigDecimal (25.90+k));
							item.setPriceFor(new BigDecimal (19.90+k));
							item.setStock(5+k);
							item.setRank(0+k-j2);
							item.setProduct(product);
							
							if (j2 == 3) {
								item.setPromo(true);
							}
							
							
							itemDao.save(item);
							
							for (int k2 = 0; k2 < 2; k2++) {
								Image image = new Image();
								
								Boolean main = false;
								
								if (k2 == 1) {
									image.setShoppingCartUrl("http://www.sporttech.com.br/cache/com_zoo/images/luva_boxe_everlast1_f3b02da1771357e51bf7fffffea463ed.jpg");
									image.setProductUrl("http://www.sporttech.com.br/cache/com_zoo/images/luva_boxe_everlast1_f3b02da1771357e51bf7fffffea463ed.jpg");
									image.setAndroidProductUrl("http://www.sporttech.com.br/cache/com_zoo/images/luva_boxe_everlast1_f3b02da1771357e51bf7fffffea463ed.jpg");
									image.setAndroidShowcaseUrl("http://www.sporttech.com.br/cache/com_zoo/images/luva_boxe_everlast1_f3b02da1771357e51bf7fffffea463ed.jpg");
									image.setShowcaseUrl("http://www.sporttech.com.br/cache/com_zoo/images/luva_boxe_everlast1_f3b02da1771357e51bf7fffffea463ed.jpg");
									image.setSuperzoomUrl("http://www.sporttech.com.br/cache/com_zoo/images/luva_boxe_everlast1_f3b02da1771357e51bf7fffffea463ed.jpg");
									image.setPromo("http://www.hiperativo.com/wp-content/uploads/2013/06/Gusttavo-Lima-Ao-Vivo-e-S%C3%A3o-Paulo.jpg");
									main = true;
								} else {
									image.setShoppingCartUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
									image.setProductUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
									image.setAndroidProductUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
									image.setAndroidShowcaseUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
									image.setShowcaseUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
									image.setSuperzoomUrl("http://img1.mlstatic.com/s_MLB_v_O_f_4208129728_042013.jpg");
									image.setPromo("http://www.hiperativo.com/wp-content/uploads/2013/06/Gusttavo-Lima-Ao-Vivo-e-S%C3%A3o-Paulo.jpg");
								}
								
								image.setItem(item);
								imageDao.save(image, main);
							}
						}	
					}	
				}
			}
	}
}
