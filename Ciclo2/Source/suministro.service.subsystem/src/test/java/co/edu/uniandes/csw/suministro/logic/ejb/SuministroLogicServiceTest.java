
package co.edu.uniandes.csw.suministro.logic.ejb;

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


import co.edu.uniandes.csw.suministro.logic.dto.SuministroDTO;
import co.edu.uniandes.csw.suministro.logic.api.ISuministroLogicService;
import co.edu.uniandes.csw.suministro.persistence.SuministroPersistence;
import co.edu.uniandes.csw.suministro.persistence.api.ISuministroPersistence;
import co.edu.uniandes.csw.suministro.persistence.entity.SuministroEntity;

@RunWith(Arquillian.class)
public class SuministroLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(SuministroLogicService.class.getPackage())
				.addPackage(SuministroPersistence.class.getPackage())
				.addPackage(SuministroEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private ISuministroLogicService suministroLogicService;
	
	@Inject
	private ISuministroPersistence suministroPersistence;	

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
		List<SuministroDTO> dtos=suministroPersistence.getSuministros();
		for(SuministroDTO dto:dtos){
			suministroPersistence.deleteSuministro(dto.getId());
		}
	}

	private List<SuministroDTO> data=new ArrayList<SuministroDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			SuministroDTO pdto=new SuministroDTO();
			pdto.setName(generateRandom(String.class));
			pdto.setCantidadDisp(generateRandom(Integer.class));
			pdto.setCanitdadEnProc(generateRandom(Integer.class));
			pdto.setTiempoEspera(generateRandom(Integer.class));
			pdto.setCostoPromedio(generateRandom(Double.class));
			pdto.setLugar(generateRandom(String.class));
			pdto.setCantidadMin(generateRandom(Integer.class));
			pdto.setCantidadMax(generateRandom(Integer.class));
			pdto=suministroPersistence.createSuministro(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createSuministroTest(){
		SuministroDTO ldto=new SuministroDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setCantidadDisp(generateRandom(Integer.class));
		ldto.setCanitdadEnProc(generateRandom(Integer.class));
		ldto.setTiempoEspera(generateRandom(Integer.class));
		ldto.setCostoPromedio(generateRandom(Double.class));
		ldto.setLugar(generateRandom(String.class));
		ldto.setCantidadMin(generateRandom(Integer.class));
		ldto.setCantidadMax(generateRandom(Integer.class));
		
		
		SuministroDTO result=suministroLogicService.createSuministro(ldto);
		
		Assert.assertNotNull(result);
		
		SuministroDTO pdto=suministroPersistence.getSuministro(result.getId());
		
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
	public void getSuministrosTest(){
		List<SuministroDTO> list=suministroLogicService.getSuministros();
		Assert.assertEquals(list.size(), data.size());
        for(SuministroDTO ldto:list){
        	boolean found=false;
            for(SuministroDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getSuministroTest(){
		SuministroDTO pdto=data.get(0);
		SuministroDTO ldto=suministroLogicService.getSuministro(pdto.getId());
        Assert.assertNotNull(ldto);
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
	public void deleteSuministroTest(){
		SuministroDTO pdto=data.get(0);
		suministroLogicService.deleteSuministro(pdto.getId());
        SuministroDTO deleted=suministroPersistence.getSuministro(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateSuministroTest(){
		SuministroDTO pdto=data.get(0);
		
		SuministroDTO ldto=new SuministroDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setCantidadDisp(generateRandom(Integer.class));
		ldto.setCanitdadEnProc(generateRandom(Integer.class));
		ldto.setTiempoEspera(generateRandom(Integer.class));
		ldto.setCostoPromedio(generateRandom(Double.class));
		ldto.setLugar(generateRandom(String.class));
		ldto.setCantidadMin(generateRandom(Integer.class));
		ldto.setCantidadMax(generateRandom(Integer.class));
		
		
		suministroLogicService.updateSuministro(ldto);
		
		
		SuministroDTO resp=suministroPersistence.getSuministro(pdto.getId());
		
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