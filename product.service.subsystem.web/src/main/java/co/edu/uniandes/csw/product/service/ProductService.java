package co.edu.uniandes.csw.product.service;

import co.edu.uniandes.csw.product.logic.dto.ProductDTO;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Product")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductService extends _ProductService {

    @POST
    @Path("/search")
    public List<ProductDTO> searchproduct(ProductDTO product){
        return this.productLogicService.searchProduct(product);
    }
    
}