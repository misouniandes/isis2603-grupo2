
package co.edu.uniandes.csw.materiaprima.persistence;

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


import co.edu.uniandes.csw.materiaprima.logic.dto.MateriaPrimaDTO;
import co.edu.uniandes.csw.materiaprima.persistence.api.IMateriaPrimaPersistence;
import co.edu.uniandes.csw.materiaprima.persistence.entity.MateriaPrimaEntity;

@RunWith(Arquillian.class)
public class MateriaPrimaPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(MateriaPrimaPersistence.class.getPackage())
				.addPackage(MateriaPrimaEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IMateriaPrimaPersistence materiaPrimaPersistence;

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
		em.createQuery("delete from MateriaPrimaEntity").executeUpdate();
	}

	private List<MateriaPrimaEntity> data=new ArrayList<MateriaPrimaEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			MateriaPrimaEntity entity=new MateriaPrimaEntity();
			entity.setName(generateRandom(String.class));
			entity.setCantidadDisp(generateRandom(Integer.class));
			entity.setCanitdadEnProc(generateRandom(Integer.class));
			entity.setTiempoEspera(generateRandom(Integer.class));
			entity.setCostoPromedio(generateRandom(Double.class));
			entity.setLugar(generateRandom(String.class));
			entity.setCantidadMin(generateRandom(Integer.class));
			entity.setCantidadMax(generateRandom(Integer.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createMateriaPrimaTest(){
		MateriaPrimaDTO dto=new MateriaPrimaDTO();
		dto.setName(generateRandom(String.class));
		dto.setCantidadDisp(generateRandom(Integer.class));
		dto.setCanitdadEnProc(generateRandom(Integer.class));
		dto.setTiempoEspera(generateRandom(Integer.class));
		dto.setCostoPromedio(generateRandom(Double.class));
		dto.setLugar(generateRandom(String.class));
		dto.setCantidadMin(generateRandom(Integer.class));
		dto.setCantidadMax(generateRandom(Integer.class));
		
		
		MateriaPrimaDTO result=materiaPrimaPersistence.createMateriaPrima(dto);
		
		Assert.assertNotNull(result);
		
		MateriaPrimaEntity entity=em.find(MateriaPrimaEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());	
		Assert.assertEquals(dto.getCantidadDisp(), entity.getCantidadDisp());	
		Assert.assertEquals(dto.getCanitdadEnProc(), entity.getCanitdadEnProc());	
		Assert.assertEquals(dto.getTiempoEspera(), entity.getTiempoEspera());	
		Assert.assertEquals(dto.getCostoPromedio(), entity.getCostoPromedio());	
		Assert.assertEquals(dto.getLugar(), entity.getLugar());	
		Assert.assertEquals(dto.getCantidadMin(), entity.getCantidadMin());	
		Assert.assertEquals(dto.getCantidadMax(), entity.getCantidadMax());	
	}
	
	@Test
	public void getMateriaPrimasTest(){
		List<MateriaPrimaDTO> list=materiaPrimaPersistence.getMateriaPrimas();
		Assert.assertEquals(list.size(), data.size());
        for(MateriaPrimaDTO dto:list){
        	boolean found=false;
            for(MateriaPrimaEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getMateriaPrimaTest(){
		MateriaPrimaEntity entity=data.get(0);
		MateriaPrimaDTO dto=materiaPrimaPersistence.getMateriaPrima(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getCantidadDisp(), dto.getCantidadDisp());
		Assert.assertEquals(entity.getCanitdadEnProc(), dto.getCanitdadEnProc());
		Assert.assertEquals(entity.getTiempoEspera(), dto.getTiempoEspera());
		Assert.assertEquals(entity.getCostoPromedio(), dto.getCostoPromedio());
		Assert.assertEquals(entity.getLugar(), dto.getLugar());
		Assert.assertEquals(entity.getCantidadMin(), dto.getCantidadMin());
		Assert.assertEquals(entity.getCantidadMax(), dto.getCantidadMax());
        
	}
	
	@Test
	public void deleteMateriaPrimaTest(){
		MateriaPrimaEntity entity=data.get(0);
		materiaPrimaPersistence.deleteMateriaPrima(entity.getId());
        MateriaPrimaEntity deleted=em.find(MateriaPrimaEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateMateriaPrimaTest(){
		MateriaPrimaEntity entity=data.get(0);
		
		MateriaPrimaDTO dto=new MateriaPrimaDTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		dto.setCantidadDisp(generateRandom(Integer.class));
		dto.setCanitdadEnProc(generateRandom(Integer.class));
		dto.setTiempoEspera(generateRandom(Integer.class));
		dto.setCostoPromedio(generateRandom(Double.class));
		dto.setLugar(generateRandom(String.class));
		dto.setCantidadMin(generateRandom(Integer.class));
		dto.setCantidadMax(generateRandom(Integer.class));
		
		
		materiaPrimaPersistence.updateMateriaPrima(dto);
		
		
		MateriaPrimaEntity resp=em.find(MateriaPrimaEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getCantidadDisp(), resp.getCantidadDisp());	
		Assert.assertEquals(dto.getCanitdadEnProc(), resp.getCanitdadEnProc());	
		Assert.assertEquals(dto.getTiempoEspera(), resp.getTiempoEspera());	
		Assert.assertEquals(dto.getCostoPromedio(), resp.getCostoPromedio());	
		Assert.assertEquals(dto.getLugar(), resp.getLugar());	
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