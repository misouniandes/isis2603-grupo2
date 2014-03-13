
package co.edu.uniandes.csw.materiaprima.logic.ejb;

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
import co.edu.uniandes.csw.materiaprima.logic.api.IMateriaPrimaLogicService;
import co.edu.uniandes.csw.materiaprima.persistence.MateriaPrimaPersistence;
import co.edu.uniandes.csw.materiaprima.persistence.api.IMateriaPrimaPersistence;
import co.edu.uniandes.csw.materiaprima.persistence.entity.MateriaPrimaEntity;

@RunWith(Arquillian.class)
public class MateriaPrimaLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(MateriaPrimaLogicService.class.getPackage())
				.addPackage(MateriaPrimaPersistence.class.getPackage())
				.addPackage(MateriaPrimaEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IMateriaPrimaLogicService materiaPrimaLogicService;
	
	@Inject
	private IMateriaPrimaPersistence materiaPrimaPersistence;	

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
		List<MateriaPrimaDTO> dtos=materiaPrimaPersistence.getMateriaPrimas();
		for(MateriaPrimaDTO dto:dtos){
			materiaPrimaPersistence.deleteMateriaPrima(dto.getId());
		}
	}

	private List<MateriaPrimaDTO> data=new ArrayList<MateriaPrimaDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			MateriaPrimaDTO pdto=new MateriaPrimaDTO();
			pdto.setName(generateRandom(String.class));
			pdto.setCantidadDisp(generateRandom(Integer.class));
			pdto.setCanitdadEnProc(generateRandom(Integer.class));
			pdto.setTiempoEspera(generateRandom(Integer.class));
			pdto.setCostoPromedio(generateRandom(Double.class));
			pdto.setCantidadMin(generateRandom(Integer.class));
			pdto.setCantidadMax(generateRandom(Integer.class));
			pdto=materiaPrimaPersistence.createMateriaPrima(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createMateriaPrimaTest(){
		MateriaPrimaDTO ldto=new MateriaPrimaDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setCantidadDisp(generateRandom(Integer.class));
		ldto.setCanitdadEnProc(generateRandom(Integer.class));
		ldto.setTiempoEspera(generateRandom(Integer.class));
		ldto.setCostoPromedio(generateRandom(Double.class));
		ldto.setCantidadMin(generateRandom(Integer.class));
		ldto.setCantidadMax(generateRandom(Integer.class));
		
		
		MateriaPrimaDTO result=materiaPrimaLogicService.createMateriaPrima(ldto);
		
		Assert.assertNotNull(result);
		
		MateriaPrimaDTO pdto=materiaPrimaPersistence.getMateriaPrima(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getCantidadDisp(), pdto.getCantidadDisp());	
		Assert.assertEquals(ldto.getCanitdadEnProc(), pdto.getCanitdadEnProc());	
		Assert.assertEquals(ldto.getTiempoEspera(), pdto.getTiempoEspera());	
		Assert.assertEquals(ldto.getCostoPromedio(), pdto.getCostoPromedio());	
		Assert.assertEquals(ldto.getCantidadMin(), pdto.getCantidadMin());	
		Assert.assertEquals(ldto.getCantidadMax(), pdto.getCantidadMax());	
	}
	
	@Test
	public void getMateriaPrimasTest(){
		List<MateriaPrimaDTO> list=materiaPrimaLogicService.getMateriaPrimas();
		Assert.assertEquals(list.size(), data.size());
        for(MateriaPrimaDTO ldto:list){
        	boolean found=false;
            for(MateriaPrimaDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getMateriaPrimaTest(){
		MateriaPrimaDTO pdto=data.get(0);
		MateriaPrimaDTO ldto=materiaPrimaLogicService.getMateriaPrima(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getCantidadDisp(), ldto.getCantidadDisp());
		Assert.assertEquals(pdto.getCanitdadEnProc(), ldto.getCanitdadEnProc());
		Assert.assertEquals(pdto.getTiempoEspera(), ldto.getTiempoEspera());
		Assert.assertEquals(pdto.getCostoPromedio(), ldto.getCostoPromedio());
		Assert.assertEquals(pdto.getCantidadMin(), ldto.getCantidadMin());
		Assert.assertEquals(pdto.getCantidadMax(), ldto.getCantidadMax());
        
	}
	
	@Test
	public void deleteMateriaPrimaTest(){
		MateriaPrimaDTO pdto=data.get(0);
		materiaPrimaLogicService.deleteMateriaPrima(pdto.getId());
        MateriaPrimaDTO deleted=materiaPrimaPersistence.getMateriaPrima(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateMateriaPrimaTest(){
		MateriaPrimaDTO pdto=data.get(0);
		
		MateriaPrimaDTO ldto=new MateriaPrimaDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setCantidadDisp(generateRandom(Integer.class));
		ldto.setCanitdadEnProc(generateRandom(Integer.class));
		ldto.setTiempoEspera(generateRandom(Integer.class));
		ldto.setCostoPromedio(generateRandom(Double.class));
		ldto.setCantidadMin(generateRandom(Integer.class));
		ldto.setCantidadMax(generateRandom(Integer.class));
		
		
		materiaPrimaLogicService.updateMateriaPrima(ldto);
		
		
		MateriaPrimaDTO resp=materiaPrimaPersistence.getMateriaPrima(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getCantidadDisp(), resp.getCantidadDisp());	
		Assert.assertEquals(ldto.getCanitdadEnProc(), resp.getCanitdadEnProc());	
		Assert.assertEquals(ldto.getTiempoEspera(), resp.getTiempoEspera());	
		Assert.assertEquals(ldto.getCostoPromedio(), resp.getCostoPromedio());	
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