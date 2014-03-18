
package co.edu.uniandes.csw.suministro.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.suministro.logic.dto.SuministroDTO;
import co.edu.uniandes.csw.suministro.persistence.api._ISuministroPersistence;
import co.edu.uniandes.csw.suministro.persistence.converter.SuministroConverter;
import co.edu.uniandes.csw.suministro.persistence.entity.SuministroEntity;

public abstract class _SuministroPersistence implements _ISuministroPersistence {

	@PersistenceContext(unitName="SuministroPU")
	protected EntityManager entityManager;
	
	public SuministroDTO createSuministro(SuministroDTO suministro) {
		SuministroEntity entity=SuministroConverter.persistenceDTO2Entity(suministro);
		entityManager.persist(entity);
		return SuministroConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<SuministroDTO> getSuministros() {
		Query q = entityManager.createQuery("select u from SuministroEntity u");
		return SuministroConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public SuministroDTO getSuministro(Long id) {
		return SuministroConverter.entity2PersistenceDTO(entityManager.find(SuministroEntity.class, id));
	}

	public void deleteSuministro(Long id) {
		SuministroEntity entity=entityManager.find(SuministroEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateSuministro(SuministroDTO detail) {
		SuministroEntity entity=entityManager.merge(SuministroConverter.persistenceDTO2Entity(detail));
		SuministroConverter.entity2PersistenceDTO(entity);
	}

}