
package co.edu.uniandes.csw.client.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.client.logic.dto.ClientDTO;
import co.edu.uniandes.csw.client.persistence.api._IClientPersistence;
import co.edu.uniandes.csw.client.persistence.converter.ClientConverter;
import co.edu.uniandes.csw.client.persistence.entity.ClientEntity;

public abstract class _ClientPersistence implements _IClientPersistence {

	@PersistenceContext(unitName="ClientPU")
	protected EntityManager entityManager;
	
	public ClientDTO createClient(ClientDTO client) {
		ClientEntity entity=ClientConverter.persistenceDTO2Entity(client);
		entityManager.persist(entity);
		return ClientConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<ClientDTO> getClients() {
		Query q = entityManager.createQuery("select u from ClientEntity u");
		return ClientConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public ClientDTO getClient(Long id) {
		return ClientConverter.entity2PersistenceDTO(entityManager.find(ClientEntity.class, id));
	}

	public void deleteClient(Long id) {
		ClientEntity entity=entityManager.find(ClientEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateClient(ClientDTO detail) {
		ClientEntity entity=entityManager.merge(ClientConverter.persistenceDTO2Entity(detail));
		ClientConverter.entity2PersistenceDTO(entity);
	}

}