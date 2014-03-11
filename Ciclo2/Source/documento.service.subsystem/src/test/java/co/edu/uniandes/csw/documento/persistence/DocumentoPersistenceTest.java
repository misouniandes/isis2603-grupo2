
package co.edu.uniandes.csw.documento.persistence;

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


import co.edu.uniandes.csw.documento.logic.dto.DocumentoDTO;
import co.edu.uniandes.csw.documento.persistence.api.IDocumentoPersistence;
import co.edu.uniandes.csw.documento.persistence.entity.DocumentoEntity;

@RunWith(Arquillian.class)
public class DocumentoPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(DocumentoPersistence.class.getPackage())
				.addPackage(DocumentoEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IDocumentoPersistence documentoPersistence;

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
		em.createQuery("delete from DocumentoEntity").executeUpdate();
	}

	private List<DocumentoEntity> data=new ArrayList<DocumentoEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			DocumentoEntity entity=new DocumentoEntity();
			entity.setType(generateRandom(String.class));
			entity.setFecha(generateRandom(Date.class));
			entity.setAutor(generateRandom(String.class));
			entity.setDescripcion(generateRandom(String.class));
			entity.setName(generateRandom(String.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createDocumentoTest(){
		DocumentoDTO dto=new DocumentoDTO();
		dto.setType(generateRandom(String.class));
		dto.setFecha(generateRandom(Date.class));
		dto.setAutor(generateRandom(String.class));
		dto.setDescripcion(generateRandom(String.class));
		dto.setName(generateRandom(String.class));
		
		
		DocumentoDTO result=documentoPersistence.createDocumento(dto);
		
		Assert.assertNotNull(result);
		
		DocumentoEntity entity=em.find(DocumentoEntity.class, result.getId());
		
		Assert.assertEquals(dto.getType(), entity.getType());	
		Assert.assertEquals(dto.getFecha(), entity.getFecha());	
		Assert.assertEquals(dto.getAutor(), entity.getAutor());	
		Assert.assertEquals(dto.getDescripcion(), entity.getDescripcion());	
		Assert.assertEquals(dto.getName(), entity.getName());	
	}
	
	@Test
	public void getDocumentosTest(){
		List<DocumentoDTO> list=documentoPersistence.getDocumentos();
		Assert.assertEquals(list.size(), data.size());
        for(DocumentoDTO dto:list){
        	boolean found=false;
            for(DocumentoEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getDocumentoTest(){
		DocumentoEntity entity=data.get(0);
		DocumentoDTO dto=documentoPersistence.getDocumento(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getType(), dto.getType());
		Assert.assertEquals(entity.getFecha(), dto.getFecha());
		Assert.assertEquals(entity.getAutor(), dto.getAutor());
		Assert.assertEquals(entity.getDescripcion(), dto.getDescripcion());
		Assert.assertEquals(entity.getName(), dto.getName());
        
	}
	
	@Test
	public void deleteDocumentoTest(){
		DocumentoEntity entity=data.get(0);
		documentoPersistence.deleteDocumento(entity.getId());
        DocumentoEntity deleted=em.find(DocumentoEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateDocumentoTest(){
		DocumentoEntity entity=data.get(0);
		
		DocumentoDTO dto=new DocumentoDTO();
		dto.setId(entity.getId());
		dto.setType(generateRandom(String.class));
		dto.setFecha(generateRandom(Date.class));
		dto.setAutor(generateRandom(String.class));
		dto.setDescripcion(generateRandom(String.class));
		dto.setName(generateRandom(String.class));
		
		
		documentoPersistence.updateDocumento(dto);
		
		
		DocumentoEntity resp=em.find(DocumentoEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getType(), resp.getType());	
		Assert.assertEquals(dto.getFecha(), resp.getFecha());	
		Assert.assertEquals(dto.getAutor(), resp.getAutor());	
		Assert.assertEquals(dto.getDescripcion(), resp.getDescripcion());	
		Assert.assertEquals(dto.getName(), resp.getName());	
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