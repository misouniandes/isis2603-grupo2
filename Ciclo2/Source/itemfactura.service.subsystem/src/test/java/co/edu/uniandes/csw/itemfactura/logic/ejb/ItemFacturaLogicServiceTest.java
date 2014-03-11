
package co.edu.uniandes.csw.itemfactura.logic.ejb;

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


import co.edu.uniandes.csw.itemfactura.logic.dto.ItemFacturaDTO;
import co.edu.uniandes.csw.itemfactura.logic.api.IItemFacturaLogicService;
import co.edu.uniandes.csw.itemfactura.persistence.ItemFacturaPersistence;
import co.edu.uniandes.csw.itemfactura.persistence.api.IItemFacturaPersistence;
import co.edu.uniandes.csw.itemfactura.persistence.entity.ItemFacturaEntity;

@RunWith(Arquillian.class)
public class ItemFacturaLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ItemFacturaLogicService.class.getPackage())
				.addPackage(ItemFacturaPersistence.class.getPackage())
				.addPackage(ItemFacturaEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IItemFacturaLogicService itemFacturaLogicService;
	
	@Inject
	private IItemFacturaPersistence itemFacturaPersistence;	

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
		List<ItemFacturaDTO> dtos=itemFacturaPersistence.getItemFacturas();
		for(ItemFacturaDTO dto:dtos){
			itemFacturaPersistence.deleteItemFactura(dto.getId());
		}
	}

	private List<ItemFacturaDTO> data=new ArrayList<ItemFacturaDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ItemFacturaDTO pdto=new ItemFacturaDTO();
			pdto.setCantidad(generateRandom(Integer.class));
			pdto.setName(generateRandom(String.class));
			pdto.setProductoId(generateRandom(Long.class));
			pdto=itemFacturaPersistence.createItemFactura(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createItemFacturaTest(){
		ItemFacturaDTO ldto=new ItemFacturaDTO();
		ldto.setCantidad(generateRandom(Integer.class));
		ldto.setName(generateRandom(String.class));
		ldto.setProductoId(generateRandom(Long.class));
		
		
		ItemFacturaDTO result=itemFacturaLogicService.createItemFactura(ldto);
		
		Assert.assertNotNull(result);
		
		ItemFacturaDTO pdto=itemFacturaPersistence.getItemFactura(result.getId());
		
		Assert.assertEquals(ldto.getCantidad(), pdto.getCantidad());	
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getProductoId(), pdto.getProductoId());	
	}
	
	@Test
	public void getItemFacturasTest(){
		List<ItemFacturaDTO> list=itemFacturaLogicService.getItemFacturas();
		Assert.assertEquals(list.size(), data.size());
        for(ItemFacturaDTO ldto:list){
        	boolean found=false;
            for(ItemFacturaDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getItemFacturaTest(){
		ItemFacturaDTO pdto=data.get(0);
		ItemFacturaDTO ldto=itemFacturaLogicService.getItemFactura(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getCantidad(), ldto.getCantidad());
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getProductoId(), ldto.getProductoId());
        
	}
	
	@Test
	public void deleteItemFacturaTest(){
		ItemFacturaDTO pdto=data.get(0);
		itemFacturaLogicService.deleteItemFactura(pdto.getId());
        ItemFacturaDTO deleted=itemFacturaPersistence.getItemFactura(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateItemFacturaTest(){
		ItemFacturaDTO pdto=data.get(0);
		
		ItemFacturaDTO ldto=new ItemFacturaDTO();
		ldto.setId(pdto.getId());
		ldto.setCantidad(generateRandom(Integer.class));
		ldto.setName(generateRandom(String.class));
		ldto.setProductoId(generateRandom(Long.class));
		
		
		itemFacturaLogicService.updateItemFactura(ldto);
		
		
		ItemFacturaDTO resp=itemFacturaPersistence.getItemFactura(pdto.getId());
		
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