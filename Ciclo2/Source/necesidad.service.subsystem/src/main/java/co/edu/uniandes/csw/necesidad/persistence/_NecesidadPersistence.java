
package co.edu.uniandes.csw.necesidad.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.necesidad.logic.dto.NecesidadDTO;
import co.edu.uniandes.csw.necesidad.persistence.api._INecesidadPersistence;
import co.edu.uniandes.csw.necesidad.persistence.converter.NecesidadConverter;
import co.edu.uniandes.csw.necesidad.persistence.entity.NecesidadEntity;

public abstract class _NecesidadPersistence implements _INecesidadPersistence {

	@PersistenceContext(unitName="NecesidadPU")
	protected EntityManager entityManager;
	
	public NecesidadDTO createNecesidad(NecesidadDTO necesidad) {
		NecesidadEntity entity=NecesidadConverter.persistenceDTO2Entity(necesidad);
		entityManager.persist(entity);
		return NecesidadConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<NecesidadDTO> getNecesidads() {
		Query q = entityManager.createQuery("select u from NecesidadEntity u");
		return NecesidadConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public NecesidadDTO getNecesidad(Long id) {
		return NecesidadConverter.entity2PersistenceDTO(entityManager.find(NecesidadEntity.class, id));
	}

	public void deleteNecesidad(Long id) {
		NecesidadEntity entity=entityManager.find(NecesidadEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateNecesidad(NecesidadDTO detail) {
		NecesidadEntity entity=entityManager.merge(NecesidadConverter.persistenceDTO2Entity(detail));
		NecesidadConverter.entity2PersistenceDTO(entity);
	}

}