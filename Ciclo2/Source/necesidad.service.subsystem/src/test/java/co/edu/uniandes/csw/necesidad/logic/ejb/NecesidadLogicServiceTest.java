
package co.edu.uniandes.csw.necesidad.logic.ejb;

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


import co.edu.uniandes.csw.necesidad.logic.dto.NecesidadDTO;
import co.edu.uniandes.csw.necesidad.logic.api.INecesidadLogicService;
import co.edu.uniandes.csw.necesidad.persistence.NecesidadPersistence;
import co.edu.uniandes.csw.necesidad.persistence.api.INecesidadPersistence;
import co.edu.uniandes.csw.necesidad.persistence.entity.NecesidadEntity;

@RunWith(Arquillian.class)
public class NecesidadLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(NecesidadLogicService.class.getPackage())
				.addPackage(NecesidadPersistence.class.getPackage())
				.addPackage(NecesidadEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private INecesidadLogicService necesidadLogicService;
	
	@Inject
	private INecesidadPersistence necesidadPersistence;	

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
		List<NecesidadDTO> dtos=necesidadPersistence.getNecesidads();
		for(NecesidadDTO dto:dtos){
			necesidadPersistence.deleteNecesidad(dto.getId());
		}
	}

	private List<NecesidadDTO> data=new ArrayList<NecesidadDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			NecesidadDTO pdto=new NecesidadDTO();
			pdto.setCantidad(generateRandom(Integer.class));
			pdto.setName(generateRandom(String.class));
			pdto.setProductoId(generateRandom(Long.class));
			pdto=necesidadPersistence.createNecesidad(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createNecesidadTest(){
		NecesidadDTO ldto=new NecesidadDTO();
		ldto.setCantidad(generateRandom(Integer.class));
		ldto.setName(generateRandom(String.class));
		ldto.setProductoId(generateRandom(Long.class));
		
		
		NecesidadDTO result=necesidadLogicService.createNecesidad(ldto);
		
		Assert.assertNotNull(result);
		
		NecesidadDTO pdto=necesidadPersistence.getNecesidad(result.getId());
		
		Assert.assertEquals(ldto.getCantidad(), pdto.getCantidad());	
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getProductoId(), pdto.getProductoId());	
	}
	
	@Test
	public void getNecesidadsTest(){
		List<NecesidadDTO> list=necesidadLogicService.getNecesidads();
		Assert.assertEquals(list.size(), data.size());
        for(NecesidadDTO ldto:list){
        	boolean found=false;
            for(NecesidadDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getNecesidadTest(){
		NecesidadDTO pdto=data.get(0);
		NecesidadDTO ldto=necesidadLogicService.getNecesidad(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getCantidad(), ldto.getCantidad());
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getProductoId(), ldto.getProductoId());
        
	}
	
	@Test
	public void deleteNecesidadTest(){
		NecesidadDTO pdto=data.get(0);
		necesidadLogicService.deleteNecesidad(pdto.getId());
        NecesidadDTO deleted=necesidadPersistence.getNecesidad(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateNecesidadTest(){
		NecesidadDTO pdto=data.get(0);
		
		NecesidadDTO ldto=new NecesidadDTO();
		ldto.setId(pdto.getId());
		ldto.setCantidad(generateRandom(Integer.class));
		ldto.setName(generateRandom(String.class));
		ldto.setProductoId(generateRandom(Long.class));
		
		
		necesidadLogicService.updateNecesidad(ldto);
		
		
		NecesidadDTO resp=necesidadPersistence.getNecesidad(pdto.getId());
		
		Assert.assertEquals(ldto.getCantidad(), resp.getCantidad());	
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getProductoId(), resp.getProductoId());	
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