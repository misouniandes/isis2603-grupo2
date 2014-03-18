package co.edu.uniandes.csw.inventario.master.persistence.api;

import co.edu.uniandes.csw.inventario.master.persistence.entity.InventarioFacturaEntity;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.inventario.master.persistence.entity.InventarioProductoEntity;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.inventario.master.logic.dto.InventarioMasterDTO;
import java.util.List;

public interface _IInventarioMasterPersistence {

    public InventarioFacturaEntity createInventarioFactura(InventarioFacturaEntity entity);

    public void deleteInventarioFactura(Long inventarioId, Long facturaId);
    
    public List<FacturaDTO> getFacturaListForInventario(Long inventarioId);
    public InventarioProductoEntity createInventarioProducto(InventarioProductoEntity entity);

    public void deleteInventarioProducto(Long inventarioId, Long productoId);
    
    public List<ProductoDTO> getProductoListForInventario(Long inventarioId);
    
    public InventarioMasterDTO getInventario(Long inventarioId);

}
