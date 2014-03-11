package co.edu.uniandes.csw.inventario.master.logic.ejb;

import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.persistence.api.IFacturaPersistence;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.persistence.api.IProductoPersistence;
import co.edu.uniandes.csw.inventario.logic.dto.InventarioDTO;
import co.edu.uniandes.csw.inventario.master.logic.api._IInventarioMasterLogicService;
import co.edu.uniandes.csw.inventario.master.logic.dto.InventarioMasterDTO;
import co.edu.uniandes.csw.inventario.master.persistence.api.IInventarioMasterPersistence;
import co.edu.uniandes.csw.inventario.master.persistence.entity.InventarioFacturaEntity;
import co.edu.uniandes.csw.inventario.master.persistence.entity.InventarioProductoEntity;
import co.edu.uniandes.csw.inventario.persistence.api.IInventarioPersistence;
import javax.inject.Inject;

public abstract class _InventarioMasterLogicService implements _IInventarioMasterLogicService {

    @Inject
    protected IInventarioPersistence inventarioPersistance;
    @Inject
    protected IInventarioMasterPersistence inventarioMasterPersistance;
    @Inject
    protected IFacturaPersistence facturaPersistance;
    @Inject
    protected IProductoPersistence productoPersistance;

    public InventarioMasterDTO createMasterInventario(InventarioMasterDTO inventario) {
        InventarioDTO persistedInventarioDTO = inventarioPersistance.createInventario(inventario.getInventarioEntity());
        if (inventario.getCreateFactura() != null) {
            for (FacturaDTO facturaDTO : inventario.getCreateFactura()) {
                FacturaDTO persistedFacturaDTO = facturaPersistance.createFactura(facturaDTO);
                InventarioFacturaEntity inventarioFacturaEntity = new InventarioFacturaEntity(persistedInventarioDTO.getId(), persistedFacturaDTO.getId());
                inventarioMasterPersistance.createInventarioFactura(inventarioFacturaEntity);
            }
        }
        if (inventario.getCreateProducto() != null) {
            for (ProductoDTO productoDTO : inventario.getCreateProducto()) {
                ProductoDTO persistedProductoDTO = productoPersistance.createProducto(productoDTO);
                InventarioProductoEntity inventarioProductoEntity = new InventarioProductoEntity(persistedInventarioDTO.getId(), persistedProductoDTO.getId());
                inventarioMasterPersistance.createInventarioProducto(inventarioProductoEntity);
            }
        }
        return inventario;
    }

    public InventarioMasterDTO getMasterInventario(Long id) {
        return inventarioMasterPersistance.getInventario(id);
    }

    public void deleteMasterInventario(Long id) {
        inventarioPersistance.deleteInventario(id);
    }

    public void updateMasterInventario(InventarioMasterDTO inventario) {
        inventarioPersistance.updateInventario(inventario.getInventarioEntity());

        //---- FOR RELATIONSHIP
        // persist new factura
        if (inventario.getCreateFactura() != null) {
            for (FacturaDTO facturaDTO : inventario.getCreateFactura()) {
                FacturaDTO persistedFacturaDTO = facturaPersistance.createFactura(facturaDTO);
                InventarioFacturaEntity inventarioFacturaEntity = new InventarioFacturaEntity(inventario.getInventarioEntity().getId(), persistedFacturaDTO.getId());
                inventarioMasterPersistance.createInventarioFactura(inventarioFacturaEntity);
            }
        }
        // update factura
        if (inventario.getUpdateFactura() != null) {
            for (FacturaDTO facturaDTO : inventario.getUpdateFactura()) {
                facturaPersistance.updateFactura(facturaDTO);
            }
        }
        // delete factura
        if (inventario.getDeleteFactura() != null) {
            for (FacturaDTO facturaDTO : inventario.getDeleteFactura()) {
                inventarioMasterPersistance.deleteInventarioFactura(inventario.getInventarioEntity().getId(), facturaDTO.getId());
                facturaPersistance.deleteFactura(facturaDTO.getId());
            }
        }
        // persist new producto
        if (inventario.getCreateProducto() != null) {
            for (ProductoDTO productoDTO : inventario.getCreateProducto()) {
                ProductoDTO persistedProductoDTO = productoPersistance.createProducto(productoDTO);
                InventarioProductoEntity inventarioProductoEntity = new InventarioProductoEntity(inventario.getInventarioEntity().getId(), persistedProductoDTO.getId());
                inventarioMasterPersistance.createInventarioProducto(inventarioProductoEntity);
            }
        }
        // update producto
        if (inventario.getUpdateProducto() != null) {
            for (ProductoDTO productoDTO : inventario.getUpdateProducto()) {
                productoPersistance.updateProducto(productoDTO);
            }
        }
        // delete producto
        if (inventario.getDeleteProducto() != null) {
            for (ProductoDTO productoDTO : inventario.getDeleteProducto()) {
                inventarioMasterPersistance.deleteInventarioProducto(inventario.getInventarioEntity().getId(), productoDTO.getId());
                productoPersistance.deleteProducto(productoDTO.getId());
            }
        }
    }
}
