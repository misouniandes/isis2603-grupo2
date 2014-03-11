
package co.edu.uniandes.csw.pyme.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.pyme.logic.api.IPymeLogicService;
import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;
import java.util.List;

@Default
@Stateless
@LocalBean
public class PymeLogicService extends _PymeLogicService implements IPymeLogicService {

    public List<PymeDTO> searchPyme(PymeDTO pyme) {
       return persistance.searchPyme(pyme);
    }

}