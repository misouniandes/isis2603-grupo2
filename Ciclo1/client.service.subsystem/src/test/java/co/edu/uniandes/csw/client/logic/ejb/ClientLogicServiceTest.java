
package co.edu.uniandes.csw.client.logic.ejb;

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
import co.edu.uniandes.csw.client.logic.api.IClientLogicService;
import co.edu.uniandes.csw.client.persistence.ClientPersistence;
import co.edu.uniandes.csw.client.persistence.api.IClientPersistence;
import co.edu.uniandes.csw.client.persistence.entity.ClientEntity;

@RunWith(Arquillian.class)
public class ClientLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ClientLogicService.class.getPackage())
				.addPackage(ClientPersistence.class.getPackage())
				.addPackage(ClientEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IClientLogicService clientLogicService;
	
	@Inject
	private IClientPersistence clientPersistence;	

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
		List<ClientDTO> dtos=clientPersistence.getClients();
		for(ClientDTO dto:dtos){
			clientPersistence.deleteClient(dto.getId());
		}
	}

	private List<ClientDTO> data=new ArrayList<ClientDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ClientDTO pdto=new ClientDTO();
			pdto.setName(generateRandom(String.class));
			pdto.setCc(generateRandom(String.class));
                        pdto.setEmail(generateRandom(String.class)+"@gmail.com");
                        pdto.setPassword(generateRandom(String.class));
			pdto=clientPersistence.createClient(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createClientTest(){
		ClientDTO ldto=new ClientDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setCc(generateRandom(String.class));
		ldto.setEmail(generateRandom(String.class)+"@gmail.com");
                ldto.setPassword(generateRandom(String.class));
		
		ClientDTO result=clientLogicService.createClient(ldto);
		
		Assert.assertNotNull(result);
		
		ClientDTO pdto=clientPersistence.getClient(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getCc(), pdto.getCc());	
                Assert.assertEquals(ldto.getEmail(), pdto.getEmail());
                Assert.assertEquals(ldto.getPassword(), pdto.getPassword());
	}
	
	@Test
	public void getClientsTest(){
		List<ClientDTO> list=clientLogicService.getClients();
		Assert.assertEquals(list.size(), data.size());
        for(ClientDTO ldto:list){
        	boolean found=false;
            for(ClientDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
    
	
	@Test
	public void getClientTest(){
		ClientDTO pdto=data.get(0);
		ClientDTO ldto=clientLogicService.getClient(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getCc(), ldto.getCc());
                Assert.assertEquals(pdto.getEmail(), ldto.getEmail());
                Assert.assertEquals(pdto.getPassword(), ldto.getPassword());
        
	}
	
	@Test
	public void deleteClientTest(){
		ClientDTO pdto=data.get(0);
		clientLogicService.deleteClient(pdto.getId());
        ClientDTO deleted=clientPersistence.getClient(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateClientTest(){
		ClientDTO pdto=data.get(0);
		
		ClientDTO ldto=new ClientDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setCc(generateRandom(String.class));
                ldto.setEmail(generateRandom(String.class)+"@gmail.com");
                ldto.setPassword(generateRandom(String.class));
		
		clientLogicService.updateClient(ldto);
		
		
		ClientDTO resp=clientPersistence.getClient(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getCc(), resp.getCc());	
                Assert.assertEquals(ldto.getEmail(), resp.getEmail());	
                Assert.assertEquals(ldto.getPassword(), resp.getPassword());	
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