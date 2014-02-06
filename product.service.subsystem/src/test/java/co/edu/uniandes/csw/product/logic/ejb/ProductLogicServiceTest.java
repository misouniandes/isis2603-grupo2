
package co.edu.uniandes.csw.product.logic.ejb;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;


import co.edu.uniandes.csw.product.logic.dto.ProductDTO;
import co.edu.uniandes.csw.product.logic.api.IProductLogicService;
import co.edu.uniandes.csw.product.persistence.ProductPersistence;
import co.edu.uniandes.csw.product.persistence.api.IProductPersistence;
import co.edu.uniandes.csw.product.persistence.entity.ProductEntity;

@RunWith(Arquillian.class)
public class ProductLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ProductLogicService.class.getPackage())
				.addPackage(ProductPersistence.class.getPackage())
				.addPackage(ProductEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IProductLogicService productLogicService;
	
	@Inject
	private IProductPersistence productPersistence;	

	@Before
	public void configTest() {
		try {
			clearData();
			insertData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearData() {
		List<ProductDTO> dtos=productPersistence.getProducts();
		for(ProductDTO dto:dtos){
			productPersistence.deleteProduct(dto.getId());
		}
	}

	private List<ProductDTO> data=new ArrayList<ProductDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ProductDTO pdto=new ProductDTO();
			pdto.setName(generateRandom(String.class));
			pdto.setValue(generateRandom(Long.class));
			pdto=productPersistence.createProduct(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createProductTest(){
		ProductDTO ldto=new ProductDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setValue(generateRandom(Long.class));
		
		
		ProductDTO result=productLogicService.createProduct(ldto);
		
		Assert.assertNotNull(result);
		
		ProductDTO pdto=productPersistence.getProduct(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getValue(), pdto.getValue());	
	}
	
	@Test
	public void getProductsTest(){
		List<ProductDTO> list=productLogicService.getProducts();
		Assert.assertEquals(list.size(), data.size());
        for(ProductDTO ldto:list){
        	boolean found=false;
            for(ProductDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getProductTest(){
		ProductDTO pdto=data.get(0);
		ProductDTO ldto=productLogicService.getProduct(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getValue(), ldto.getValue());
        
	}
	
	@Test
	public void deleteProductTest(){
		ProductDTO pdto=data.get(0);
		productLogicService.deleteProduct(pdto.getId());
        ProductDTO deleted=productPersistence.getProduct(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateProductTest(){
		ProductDTO pdto=data.get(0);
		
		ProductDTO ldto=new ProductDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setValue(generateRandom(Long.class));
		
		
		productLogicService.updateProduct(ldto);
		
		
		ProductDTO resp=productPersistence.getProduct(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getValue(), resp.getValue());	
	}
	
	public <T> T generateRandom(Class<T> objectClass){
		Random r=new Random();
		if(objectClass.isInstance(String.class)){
			String s="";
			for(int i=0;i<10;i++){
				char c=(char)(r.nextInt()/('Z'-'A')+'A');
				s=s+c;
			}
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Integer.class)){
			Integer s=r.nextInt();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Long.class)){
			Long s=r.nextLong();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(java.util.Date.class)){
			java.util.Calendar c=java.util.Calendar.getInstance();
			c.set(java.util.Calendar.MONTH, r.nextInt()/12);
			c.set(java.util.Calendar.DAY_OF_MONTH,r.nextInt()/30);
			c.setLenient(false);
			return objectClass.cast(c.getTime());
		} 
		return null;
	}
	
}