
package co.edu.uniandes.csw.productocomp.persistence;

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


import co.edu.uniandes.csw.productocomp.logic.dto.ProductoCompDTO;
import co.edu.uniandes.csw.productocomp.persistence.api.IProductoCompPersistence;
import co.edu.uniandes.csw.productocomp.persistence.entity.ProductoCompEntity;

@RunWith(Arquillian.class)
public class ProductoCompPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ProductoCompPersistence.class.getPackage())
				.addPackage(ProductoCompEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IProductoCompPersistence productoCompPersistence;

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
		em.createQuery("delete from ProductoCompEntity").executeUpdate();
	}

	private List<ProductoCompEntity> data=new ArrayList<ProductoCompEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ProductoCompEntity entity=new ProductoCompEntity();
			entity.setTerminado(generateRandom(Integer.class));
			entity.setName(generateRandom(String.class));
			entity.setCantidadDisp(generateRandom(Integer.class));
			entity.setCanitdadEnProc(generateRandom(Integer.class));
			entity.setTiempoEspera(generateRandom(Integer.class));
			entity.setCostoPromedio(generateRandom(Double.class));
			entity.setCantidadMin(generateRandom(Integer.class));
			entity.setCantidadMax(generateRandom(Integer.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createProductoCompTest(){
		ProductoCompDTO dto=new ProductoCompDTO();
		dto.setTerminado(generateRandom(Integer.class));
		dto.setName(generateRandom(String.class));
		dto.setCantidadDisp(generateRandom(Integer.class));
		dto.setCanitdadEnProc(generateRandom(Integer.class));
		dto.setTiempoEspera(generateRandom(Integer.class));
		dto.setCostoPromedio(generateRandom(Double.class));
		dto.setCantidadMin(generateRandom(Integer.class));
		dto.setCantidadMax(generateRandom(Integer.class));
		
		
		ProductoCompDTO result=productoCompPersistence.createProductoComp(dto);
		
		Assert.assertNotNull(result);
		
		ProductoCompEntity entity=em.find(ProductoCompEntity.class, result.getId());
		
		Assert.assertEquals(dto.getTerminado(), entity.getTerminado());	
		Assert.assertEquals(dto.getName(), entity.getName());	
		Assert.assertEquals(dto.getCantidadDisp(), entity.getCantidadDisp());	
		Assert.assertEquals(dto.getCanitdadEnProc(), entity.getCanitdadEnProc());	
		Assert.assertEquals(dto.getTiempoEspera(), entity.getTiempoEspera());	
		Assert.assertEquals(dto.getCostoPromedio(), entity.getCostoPromedio());	
		Assert.assertEquals(dto.getCantidadMin(), entity.getCantidadMin());	
		Assert.assertEquals(dto.getCantidadMax(), entity.getCantidadMax());	
	}
	
	@Test
	public void getProductoCompsTest(){
		List<ProductoCompDTO> list=productoCompPersistence.getProductoComps();
		Assert.assertEquals(list.size(), data.size());
        for(ProductoCompDTO dto:list){
        	boolean found=false;
            for(ProductoCompEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getProductoCompTest(){
		ProductoCompEntity entity=data.get(0);
		ProductoCompDTO dto=productoCompPersistence.getProductoComp(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getTerminado(), dto.getTerminado());
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getCantidadDisp(), dto.getCantidadDisp());
		Assert.assertEquals(entity.getCanitdadEnProc(), dto.getCanitdadEnProc());
		Assert.assertEquals(entity.getTiempoEspera(), dto.getTiempoEspera());
		Assert.assertEquals(entity.getCostoPromedio(), dto.getCostoPromedio());
		Assert.assertEquals(entity.getCantidadMin(), dto.getCantidadMin());
		Assert.assertEquals(entity.getCantidadMax(), dto.getCantidadMax());
        
	}
	
	@Test
	public void deleteProductoCompTest(){
		ProductoCompEntity entity=data.get(0);
		productoCompPersistence.deleteProductoComp(entity.getId());
        ProductoCompEntity deleted=em.find(ProductoCompEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateProductoCompTest(){
		ProductoCompEntity entity=data.get(0);
		
		ProductoCompDTO dto=new ProductoCompDTO();
		dto.setId(entity.getId());
		dto.setTerminado(generateRandom(Integer.class));
		dto.setName(generateRandom(String.class));
		dto.setCantidadDisp(generateRandom(Integer.class));
		dto.setCanitdadEnProc(generateRandom(Integer.class));
		dto.setTiempoEspera(generateRandom(Integer.class));
		dto.setCostoPromedio(generateRandom(Double.class));
		dto.setCantidadMin(generateRandom(Integer.class));
		dto.setCantidadMax(generateRandom(Integer.class));
		
		
		productoCompPersistence.updateProductoComp(dto);
		
		
		ProductoCompEntity resp=em.find(ProductoCompEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getTerminado(), resp.getTerminado());	
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getCantidadDisp(), resp.getCantidadDisp());	
		Assert.assertEquals(dto.getCanitdadEnProc(), resp.getCanitdadEnProc());	
		Assert.assertEquals(dto.getTiempoEspera(), resp.getTiempoEspera());	
		Assert.assertEquals(dto.getCostoPromedio(), resp.getCostoPromedio());	
		Assert.assertEquals(dto.getCantidadMin(), resp.getCantidadMin());	
		Assert.assertEquals(dto.getCantidadMax(), resp.getCantidadMax());	
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