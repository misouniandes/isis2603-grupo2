package co.edu.uniandes.csw.productocomp.master.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.productocomp.master.logic.api.IProductoCompMasterLogicService;
import co.edu.uniandes.csw.productocomp.master.logic.dto.ProductoCompMasterDTO;

public abstract class _ProductoCompMasterService {

    @Inject
    protected IProductoCompMasterLogicService productocompLogicService;

    @POST
    public ProductoCompMasterDTO createProductoComp(ProductoCompMasterDTO productocomp) {
        return productocompLogicService.createMasterProductoComp(productocomp);
    }

    @DELETE
    @Path("{id}")
    public void deleteProductoComp(@PathParam("id") Long id) {
        productocompLogicService.deleteMasterProductoComp(id);
    }
    
    @GET
    @Path("{id}")
    public ProductoCompMasterDTO getProductoComp(@PathParam("id") Long id) {
        return productocompLogicService.getMasterProductoComp(id);
    }

    @PUT
    @Path("{id}")
    public void updateProductoComp(@PathParam("id") Long id, ProductoCompMasterDTO productocomp) {
        productocompLogicService.updateMasterProductoComp(productocomp);
    }

}
