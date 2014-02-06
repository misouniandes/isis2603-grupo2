
package co.edu.uniandes.csw.client.persistence;

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


import co.edu.uniandes.csw.client.logic.dto.ClientDTO;
import co.edu.uniandes.csw.client.persistence.api.IClientPersistence;
import co.edu.uniandes.csw.client.persistence.entity.ClientEntity;

@RunWith(Arquillian.class)
public class ClientPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ClientPersistence.class.getPackage())
				.addPackage(ClientEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IClientPersistence clientPersistence;

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
		em.createQuery("delete from ClientEntity").executeUpdate();
	}

	private List<ClientEntity> data=new ArrayList<ClientEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ClientEntity entity=new ClientEntity();
			entity.setName(generateRandom(String.class));
			entity.setCc(generateRandom(String.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createClientTest(){
		ClientDTO dto=new ClientDTO();
		dto.setName(generateRandom(String.class));
		dto.setCc(generateRandom(String.class));
		
		
		ClientDTO result=clientPersistence.createClient(dto);
		
		Assert.assertNotNull(result);
		
		ClientEntity entity=em.find(ClientEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());	
		Assert.assertEquals(dto.getCc(), entity.getCc());	
	}
	
	@Test
	public void getClientsTest(){
		List<ClientDTO> list=clientPersistence.getClients();
		Assert.assertEquals(list.size(), data.size());
        for(ClientDTO dto:list){
        	boolean found=false;
            for(ClientEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getClientTest(){
		ClientEntity entity=data.get(0);
		ClientDTO dto=clientPersistence.getClient(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getCc(), dto.getCc());
        
	}
	
	@Test
	public void deleteClientTest(){
		ClientEntity entity=data.get(0);
		clientPersistence.deleteClient(entity.getId());
        ClientEntity deleted=em.find(ClientEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateClientTest(){
		ClientEntity entity=data.get(0);
		
		ClientDTO dto=new ClientDTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		dto.setCc(generateRandom(String.class));
		
		
		clientPersistence.updateClient(dto);
		
		
		ClientEntity resp=em.find(ClientEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getCc(), resp.getCc());	
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