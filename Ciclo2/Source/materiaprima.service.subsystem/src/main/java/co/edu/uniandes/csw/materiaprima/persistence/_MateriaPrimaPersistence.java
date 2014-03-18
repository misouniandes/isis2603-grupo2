
package co.edu.uniandes.csw.materiaprima.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.materiaprima.logic.dto.MateriaPrimaDTO;
import co.edu.uniandes.csw.materiaprima.persistence.api._IMateriaPrimaPersistence;
import co.edu.uniandes.csw.materiaprima.persistence.converter.MateriaPrimaConverter;
import co.edu.uniandes.csw.materiaprima.persistence.entity.MateriaPrimaEntity;

public abstract class _MateriaPrimaPersistence implements _IMateriaPrimaPersistence {

	@PersistenceContext(unitName="MateriaPrimaPU")
	protected EntityManager entityManager;
	
	public MateriaPrimaDTO createMateriaPrima(MateriaPrimaDTO materiaPrima) {
		MateriaPrimaEntity entity=MateriaPrimaConverter.persistenceDTO2Entity(materiaPrima);
		entityManager.persist(entity);
		return MateriaPrimaConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<MateriaPrimaDTO> getMateriaPrimas() {
		Query q = entityManager.createQuery("select u from MateriaPrimaEntity u");
		return MateriaPrimaConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public MateriaPrimaDTO getMateriaPrima(Long id) {
		return MateriaPrimaConverter.entity2PersistenceDTO(entityManager.find(MateriaPrimaEntity.class, id));
	}

	public void deleteMateriaPrima(Long id) {
		MateriaPrimaEntity entity=entityManager.find(MateriaPrimaEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateMateriaPrima(MateriaPrimaDTO detail) {
		MateriaPrimaEntity entity=entityManager.merge(MateriaPrimaConverter.persistenceDTO2Entity(detail));
		MateriaPrimaConverter.entity2PersistenceDTO(entity);
	}

}