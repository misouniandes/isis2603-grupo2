package co.edu.uniandes.csw.inventario.master.persistence.converter;
import co.edu.uniandes.csw.inventario.master.persistence.entity.InventarioFacturaEntity;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.persistence.converter.FacturaConverter;
import co.edu.uniandes.csw.inventario.master.persistence.entity.InventarioProductoEntity;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.persistence.converter.ProductoConverter;
import co.edu.uniandes.csw.inventario.logic.dto.InventarioDTO;
import co.edu.uniandes.csw.inventario.master.logic.dto.InventarioMasterDTO;
import co.edu.uniandes.csw.inventario.persistence.converter.InventarioConverter;
import co.edu.uniandes.csw.inventario.persistence.entity.InventarioEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _InventarioMasterConverter {

    public static InventarioMasterDTO entity2PersistenceDTO(InventarioEntity inventarioEntity 
    ,List<InventarioFacturaEntity> inventarioFacturaEntity 
    ,List<InventarioProductoEntity> inventarioProductoEntity 
    ) {
        InventarioDTO inventarioDTO = InventarioConverter.entity2PersistenceDTO(inventarioEntity);
        ArrayList<FacturaDTO> facturaEntities = new ArrayList<FacturaDTO>(inventarioFacturaEntity.size());
        for (InventarioFacturaEntity inventarioFactura : inventarioFacturaEntity) {
            facturaEntities.add(FacturaConverter.entity2PersistenceDTO(inventarioFactura.getFacturaEntity()));
        }
        ArrayList<ProductoDTO> productoEntities = new ArrayList<ProductoDTO>(inventarioProductoEntity.size());
        for (InventarioProductoEntity inventarioProducto : inventarioProductoEntity) {
            productoEntities.add(ProductoConverter.entity2PersistenceDTO(inventarioProducto.getProductoEntity()));
        }
        InventarioMasterDTO respDTO  = new InventarioMasterDTO();
        respDTO.setInventarioEntity(inventarioDTO);
        respDTO.setListFactura(facturaEntities);
        respDTO.setListProducto(productoEntities);
        return respDTO;
    }

}