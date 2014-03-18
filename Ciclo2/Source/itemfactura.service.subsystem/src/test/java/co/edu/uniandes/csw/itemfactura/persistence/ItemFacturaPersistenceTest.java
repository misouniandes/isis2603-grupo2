
package co.edu.uniandes.csw.itemfactura.persistence;

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
import co.edu.uniandes.csw.itemfactura.persistence.api.IItemFacturaPersistence;
import co.edu.uniandes.csw.itemfactura.persistence.entity.ItemFacturaEntity;

@RunWith(Arquillian.class)
public class ItemFacturaPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ItemFacturaPersistence.class.getPackage())
				.addPackage(ItemFacturaEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IItemFacturaPersistence itemFacturaPersistence;

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
		em.createQuery("delete from ItemFacturaEntity").executeUpdate();
	}

	private List<ItemFacturaEntity> data=new ArrayList<ItemFacturaEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ItemFacturaEntity entity=new ItemFacturaEntity();
			entity.setCantidad(generateRandom(Integer.class));
			entity.setName(generateRandom(String.class));
			entity.setProductoId(generateRandom(Long.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createItemFacturaTest(){
		ItemFacturaDTO dto=new ItemFacturaDTO();
		dto.setCantidad(generateRandom(Integer.class));
		dto.setName(generateRandom(String.class));
		dto.setProductoId(generateRandom(Long.class));
		
		
		ItemFacturaDTO result=itemFacturaPersistence.createItemFactura(dto);
		
		Assert.assertNotNull(result);
		
		ItemFacturaEntity entity=em.find(ItemFacturaEntity.class, result.getId());
		
		Assert.assertEquals(dto.getCantidad(), entity.getCantidad());	
		Assert.assertEquals(dto.getName(), entity.getName());	
		Assert.assertEquals(dto.getProductoId(), entity.getProductoId());	
	}
	
	@Test
	public void getItemFacturasTest(){
		List<ItemFacturaDTO> list=itemFacturaPersistence.getItemFacturas();
		Assert.assertEquals(list.size(), data.size());
        for(ItemFacturaDTO dto:list){
        	boolean found=false;
            for(ItemFacturaEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getItemFacturaTest(){
		ItemFacturaEntity entity=data.get(0);
		ItemFacturaDTO dto=itemFacturaPersistence.getItemFactura(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getCantidad(), dto.getCantidad());
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getProductoId(), dto.getProductoId());
        
	}
	
	@Test
	public void deleteItemFacturaTest(){
		ItemFacturaEntity entity=data.get(0);
		itemFacturaPersistence.deleteItemFactura(entity.getId());
        ItemFacturaEntity deleted=em.find(ItemFacturaEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateItemFacturaTest(){
		ItemFacturaEntity entity=data.get(0);
		
		ItemFacturaDTO dto=new ItemFacturaDTO();
		dto.setId(entity.getId());
		dto.setCantidad(generateRandom(Integer.class));
		dto.setName(generateRandom(String.class));
		dto.setProductoId(generateRandom(Long.class));
		
		
		itemFacturaPersistence.updateItemFactura(dto);
		
		
		ItemFacturaEntity resp=em.find(ItemFacturaEntity.class, entity.getId());
		
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