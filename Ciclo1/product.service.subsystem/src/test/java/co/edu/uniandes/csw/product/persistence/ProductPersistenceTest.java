
package co.edu.uniandes.csw.product.persistence;

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
import co.edu.uniandes.csw.product.persistence.api.IProductPersistence;
import co.edu.uniandes.csw.product.persistence.entity.ProductEntity;

@RunWith(Arquillian.class)
public class ProductPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ProductPersistence.class.getPackage())
				.addPackage(ProductEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IProductPersistence productPersistence;

	@PersistenceContext
	private EntityManager em;

	@Inject
	UserTransaction utx;

	@Before
	public void configTest() {
		System.out.println("em: " + em);
		try {
			utx.begin();
			clearData();
			insertData();
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void clearData() {
		em.createQuery("delete from ProductEntity").executeUpdate();
	}

	private List<ProductEntity> data=new ArrayList<ProductEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ProductEntity entity=new ProductEntity();
			entity.setName(generateRandom(String.class));
			entity.setValue(generateRandom(Long.class));
                        entity.setImagen(generateRandom(String.class));
                        entity.setDescripcion(generateRandom(String.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createProductTest(){
		ProductDTO dto=new ProductDTO();
		dto.setName(generateRandom(String.class));
		dto.setValue(generateRandom(Long.class));
		dto.setImagen(generateRandom(String.class));
                dto.setDescripcion(generateRandom(String.class));
		
		ProductDTO result=productPersistence.createProduct(dto);
		
		Assert.assertNotNull(result);
		
		ProductEntity entity=em.find(ProductEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());	
		Assert.assertEquals(dto.getValue(), entity.getValue());	
                Assert.assertEquals(dto.getImagen(), entity.getImagen());	
                Assert.assertEquals(dto.getDescripcion(), entity.getDescripcion());	
	}
	
	@Test
	public void getProductsTest(){
		List<ProductDTO> list=productPersistence.getProducts();
		Assert.assertEquals(list.size(), data.size());
        for(ProductDTO dto:list){
        	boolean found=false;
            for(ProductEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getProductTest(){
		ProductEntity entity=data.get(0);
		ProductDTO dto=productPersistence.getProduct(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getValue(), dto.getValue());
                Assert.assertEquals(entity.getImagen(), dto.getImagen());
                Assert.assertEquals(entity.getDescripcion(), dto.getDescripcion());
        
	}

        
	@Test
	public void deleteProductTest(){
		ProductEntity entity=data.get(0);
		productPersistence.deleteProduct(entity.getId());
        ProductEntity deleted=em.find(ProductEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateProductTest(){
		ProductEntity entity=data.get(0);
		
		ProductDTO dto=new ProductDTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		dto.setImagen(generateRandom(String.class));
                dto.setDescripcion(generateRandom(String.class));
		
		
		productPersistence.updateProduct(dto);
		
		
		ProductEntity resp=em.find(ProductEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getValue(), resp.getValue());	
                Assert.assertEquals(dto.getImagen(),resp.getImagen());
                Assert.assertEquals(dto.getDescripcion(),resp.getDescripcion());
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