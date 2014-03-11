
package co.edu.uniandes.csw.necesidad.persistence;

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
import co.edu.uniandes.csw.necesidad.persistence.api.INecesidadPersistence;
import co.edu.uniandes.csw.necesidad.persistence.entity.NecesidadEntity;

@RunWith(Arquillian.class)
public class NecesidadPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(NecesidadPersistence.class.getPackage())
				.addPackage(NecesidadEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private INecesidadPersistence necesidadPersistence;

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
		em.createQuery("delete from NecesidadEntity").executeUpdate();
	}

	private List<NecesidadEntity> data=new ArrayList<NecesidadEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			NecesidadEntity entity=new NecesidadEntity();
			entity.setCantidad(generateRandom(Integer.class));
			entity.setName(generateRandom(String.class));
			entity.setProductoId(generateRandom(Long.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createNecesidadTest(){
		NecesidadDTO dto=new NecesidadDTO();
		dto.setCantidad(generateRandom(Integer.class));
		dto.setName(generateRandom(String.class));
		dto.setProductoId(generateRandom(Long.class));
		
		
		NecesidadDTO result=necesidadPersistence.createNecesidad(dto);
		
		Assert.assertNotNull(result);
		
		NecesidadEntity entity=em.find(NecesidadEntity.class, result.getId());
		
		Assert.assertEquals(dto.getCantidad(), entity.getCantidad());	
		Assert.assertEquals(dto.getName(), entity.getName());	
		Assert.assertEquals(dto.getProductoId(), entity.getProductoId());	
	}
	
	@Test
	public void getNecesidadsTest(){
		List<NecesidadDTO> list=necesidadPersistence.getNecesidads();
		Assert.assertEquals(list.size(), data.size());
        for(NecesidadDTO dto:list){
        	boolean found=false;
            for(NecesidadEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getNecesidadTest(){
		NecesidadEntity entity=data.get(0);
		NecesidadDTO dto=necesidadPersistence.getNecesidad(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getCantidad(), dto.getCantidad());
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getProductoId(), dto.getProductoId());
        
	}
	
	@Test
	public void deleteNecesidadTest(){
		NecesidadEntity entity=data.get(0);
		necesidadPersistence.deleteNecesidad(entity.getId());
        NecesidadEntity deleted=em.find(NecesidadEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateNecesidadTest(){
		NecesidadEntity entity=data.get(0);
		
		NecesidadDTO dto=new NecesidadDTO();
		dto.setId(entity.getId());
		dto.setCantidad(generateRandom(Integer.class));
		dto.setName(generateRandom(String.class));
		dto.setProductoId(generateRandom(Long.class));
		
		
		necesidadPersistence.updateNecesidad(dto);
		
		
		NecesidadEntity resp=em.find(NecesidadEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getCantidad(), resp.getCantidad());	
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getProductoId(), resp.getProductoId());	
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