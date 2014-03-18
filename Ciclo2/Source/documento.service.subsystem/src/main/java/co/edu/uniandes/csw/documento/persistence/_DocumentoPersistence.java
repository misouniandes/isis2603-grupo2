
package co.edu.uniandes.csw.documento.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.documento.logic.dto.DocumentoDTO;
import co.edu.uniandes.csw.documento.persistence.api._IDocumentoPersistence;
import co.edu.uniandes.csw.documento.persistence.converter.DocumentoConverter;
import co.edu.uniandes.csw.documento.persistence.entity.DocumentoEntity;

public abstract class _DocumentoPersistence implements _IDocumentoPersistence {

	@PersistenceContext(unitName="DocumentoPU")
	protected EntityManager entityManager;
	
	public DocumentoDTO createDocumento(DocumentoDTO documento) {
		DocumentoEntity entity=DocumentoConverter.persistenceDTO2Entity(documento);
		entityManager.persist(entity);
		return DocumentoConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<DocumentoDTO> getDocumentos() {
		Query q = entityManager.createQuery("select u from DocumentoEntity u");
		return DocumentoConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public DocumentoDTO getDocumento(Long id) {
		return DocumentoConverter.entity2PersistenceDTO(entityManager.find(DocumentoEntity.class, id));
	}

	public void deleteDocumento(Long id) {
		DocumentoEntity entity=entityManager.find(DocumentoEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateDocumento(DocumentoDTO detail) {
		DocumentoEntity entity=entityManager.merge(DocumentoConverter.persistenceDTO2Entity(detail));
		DocumentoConverter.entity2PersistenceDTO(entity);
	}

}