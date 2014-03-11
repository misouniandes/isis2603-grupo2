
package co.edu.uniandes.csw.pyme.persistence.api;

import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;
import java.util.List;

public interface IPymePersistence extends _IPymePersistence {

    public List<PymeDTO> searchPyme(PymeDTO pyme);

}