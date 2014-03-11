package co.edu.uniandes.csw.pyme.service;

import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Pyme")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PymeService extends _PymeService {

    @POST
    @Path("/search")
    public List<PymeDTO> searchpyme(PymeDTO pyme){
        return this.pymeLogicService.searchPyme(pyme);
    }
}