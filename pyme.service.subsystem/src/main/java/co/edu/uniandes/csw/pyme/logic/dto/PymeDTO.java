
package co.edu.uniandes.csw.pyme.logic.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class PymeDTO extends _PymeDTO {
    private String email, dirContacto;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDirContacto() {
        return dirContacto;
    }

    public void setDirContacto(String dirContacto) {
        this.dirContacto = dirContacto;
    }
    
}