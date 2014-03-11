
package co.edu.uniandes.csw.productocomp.logic.ejb;

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
import co.edu.uniandes.csw.productocomp.logic.api.IProductoCompLogicService;
import co.edu.uniandes.csw.productocomp.persistence.ProductoCompPersistence;
import co.edu.uniandes.csw.productocomp.persistence.api.IProductoCompPersistence;
import co.edu.uniandes.csw.productocomp.persistence.entity.ProductoCompEntity;

@RunWith(Arquillian.class)
public class ProductoCompLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ProductoCompLogicService.class.getPackage())
				.addPackage(ProductoCompPersistence.class.getPackage())
				.addPackage(ProductoCompEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IProductoCompLogicService productoCompLogicService;
	
	@Inject
	private IProductoCompPersistence productoCompPersistence;	

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
		List<ProductoCompDTO> dtos=productoCompPersistence.getProductoComps();
		for(ProductoCompDTO dto:dtos){
			productoCompPersistence.deleteProductoComp(dto.getId());
		}
	}

	private List<ProductoCompDTO> data=new ArrayList<ProductoCompDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ProductoCompDTO pdto=new ProductoCompDTO();
			pdto.setTerminado(generateRandom(Integer.class));
			pdto.setName(generateRandom(String.class));
			pdto.setCantidadDisp(generateRandom(Integer.class));
			pdto.setCanitdadEnProc(generateRandom(Integer.class));
			pdto.setTiempoEspera(generateRandom(Integer.class));
			pdto.setCostoPromedio(generateRandom(Double.class));
			pdto.setLugar(generateRandom(String.class));
			pdto.setCantidadMin(generateRandom(Integer.class));
			pdto.setCantidadMax(generateRandom(Integer.class));
			pdto=productoCompPersistence.createProductoComp(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createProductoCompTest(){
		ProductoCompDTO ldto=new ProductoCompDTO();
		ldto.setTerminado(generateRandom(Integer.class));
		ldto.setName(generateRandom(String.class));
		ldto.setCantidadDisp(generateRandom(Integer.class));
		ldto.setCanitdadEnProc(generateRandom(Integer.class));
		ldto.setTiempoEspera(generateRandom(Integer.class));
		ldto.setCostoPromedio(generateRandom(Double.class));
		ldto.setLugar(generateRandom(String.class));
		ldto.setCantidadMin(generateRandom(Integer.class));
		ldto.setCantidadMax(generateRandom(Integer.class));
		
		
		ProductoCompDTO result=productoCompLogicService.createProductoComp(ldto);
		
		Assert.assertNotNull(result);
		
		ProductoCompDTO pdto=productoCompPersistence.getProductoComp(result.getId());
		
		Assert.assertEquals(ldto.getTerminado(), pdto.getTerminado());	
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getCantidadDisp(), pdto.getCantidadDisp());	
		Assert.assertEquals(ldto.getCanitdadEnProc(), pdto.getCanitdadEnProc());	
		Assert.assertEquals(ldto.getTiempoEspera(), pdto.getTiempoEspera());	
		Assert.assertEquals(ldto.getCostoPromedio(), pdto.getCostoPromedio());	
		Assert.assertEquals(ldto.getLugar(), pdto.getLugar());	
		Assert.assertEquals(ldto.getCantidadMin(), pdto.getCantidadMin());	
		Assert.assertEquals(ldto.getCantidadMax(), pdto.getCantidadMax());	
	}
	
	@Test
	public void getProductoCompsTest(){
		List<ProductoCompDTO> list=productoCompLogicService.getProductoComps();
		Assert.assertEquals(list.size(), data.size());
        for(ProductoCompDTO ldto:list){
        	boolean found=false;
            for(ProductoCompDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getProductoCompTest(){
		ProductoCompDTO pdto=data.get(0);
		ProductoCompDTO ldto=productoCompLogicService.getProductoComp(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getTerminado(), ldto.getTerminado());
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getCantidadDisp(), ldto.getCantidadDisp());
		Assert.assertEquals(pdto.getCanitdadEnProc(), ldto.getCanitdadEnProc());
		Assert.assertEquals(pdto.getTiempoEspera(), ldto.getTiempoEspera());
		Assert.assertEquals(pdto.getCostoPromedio(), ldto.getCostoPromedio());
		Assert.assertEquals(pdto.getLugar(), ldto.getLugar());
		Assert.assertEquals(pdto.getCantidadMin(), ldto.getCantidadMin());
		Assert.assertEquals(pdto.getCantidadMax(), ldto.getCantidadMax());
        
	}
	
	@Test
	public void deleteProductoCompTest(){
		ProductoCompDTO pdto=data.get(0);
		productoCompLogicService.deleteProductoComp(pdto.getId());
        ProductoCompDTO deleted=productoCompPersistence.getProductoComp(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateProductoCompTest(){
		ProductoCompDTO pdto=data.get(0);
		
		ProductoCompDTO ldto=new ProductoCompDTO();
		ldto.setId(pdto.getId());
		ldto.setTerminado(generateRandom(Integer.class));
		ldto.setName(generateRandom(String.class));
		ldto.setCantidadDisp(generateRandom(Integer.class));
		ldto.setCanitdadEnProc(generateRandom(Integer.class));
		ldto.setTiempoEspera(generateRandom(Integer.class));
		ldto.setCostoPromedio(generateRandom(Double.class));
		ldto.setLugar(generateRandom(String.class));
		ldto.setCantidadMin(generateRandom(Integer.class));
		ldto.setCantidadMax(generateRandom(Integer.class));
		
		
		productoCompLogicService.updateProductoComp(ldto);
		
		
		ProductoCompDTO resp=productoCompPersistence.getProductoComp(pdto.getId());
		
		Assert.assertEquals(ldto.getTerminado(), resp.getTerminado());	
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getCantidadDisp(), resp.getCantidadDisp());	
		Assert.assertEquals(ldto.getCanitdadEnProc(), resp.getCanitdadEnProc());	
		Assert.assertEquals(ldto.getTiempoEspera(), resp.getTiempoEspera());	
		Assert.assertEquals(ldto.getCostoPromedio(), resp.getCostoPromedio());	
		Assert.assertEquals(ldto.getLugar(), resp.getLugar());	
		Assert.assertEquals(ldto.getCantidadMin(), resp.getCantidadMin());	
		Assert.assertEquals(ldto.getCantidadMax(), resp.getCantidadMax());	
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