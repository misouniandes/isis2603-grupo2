
package co.edu.uniandes.csw.pyme.persistence;

import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.pyme.persistence.api.IPymePersistence;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class PymePersistence extends _PymePersistence  implements IPymePersistence {

    public List<PymeDTO> searchPyme(PymeDTO pyme) {
        List<PymeDTO> data=getPymes();
        List<PymeDTO> r = new ArrayList<PymeDTO>();
        for(PymeDTO d:data)
        {
            if(d.getName().contains(pyme.getName()))
                r.add(d);
        }
        return r;
    }

}