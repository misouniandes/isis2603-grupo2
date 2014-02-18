
package co.edu.uniandes.csw.pyme.logic.api;

import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;
import java.util.List;

public interface IPymeLogicService extends _IPymeLogicService {

    public List<PymeDTO> searchPyme(PymeDTO pyme);

}