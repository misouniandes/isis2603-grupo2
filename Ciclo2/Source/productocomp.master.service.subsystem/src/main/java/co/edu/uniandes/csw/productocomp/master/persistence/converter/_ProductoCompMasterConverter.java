package co.edu.uniandes.csw.productocomp.master.persistence.converter;
import co.edu.uniandes.csw.productocomp.master.persistence.entity.ProductoCompNecesidadEntity;
import co.edu.uniandes.csw.necesidad.logic.dto.NecesidadDTO;
import co.edu.uniandes.csw.necesidad.persistence.converter.NecesidadConverter;
import co.edu.uniandes.csw.productocomp.logic.dto.ProductoCompDTO;
import co.edu.uniandes.csw.productocomp.master.logic.dto.ProductoCompMasterDTO;
import co.edu.uniandes.csw.productocomp.persistence.converter.ProductoCompConverter;
import co.edu.uniandes.csw.productocomp.persistence.entity.ProductoCompEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _ProductoCompMasterConverter {

    public static ProductoCompMasterDTO entity2PersistenceDTO(ProductoCompEntity productocompEntity 
    ,List<ProductoCompNecesidadEntity> productocompNecesidadEntity 
    ) {
        ProductoCompDTO productocompDTO = ProductoCompConverter.entity2PersistenceDTO(productocompEntity);
        ArrayList<NecesidadDTO> necesidadEntities = new ArrayList<NecesidadDTO>(productocompNecesidadEntity.size());
        for (ProductoCompNecesidadEntity productocompNecesidad : productocompNecesidadEntity) {
            necesidadEntities.add(NecesidadConverter.entity2PersistenceDTO(productocompNecesidad.getNecesidadEntity()));
        }
        ProductoCompMasterDTO respDTO  = new ProductoCompMasterDTO();
        respDTO.setProductoCompEntity(productocompDTO);
        respDTO.setListNecesidad(necesidadEntities);
        return respDTO;
    }

}