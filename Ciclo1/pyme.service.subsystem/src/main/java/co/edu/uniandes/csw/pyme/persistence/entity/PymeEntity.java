
package co.edu.uniandes.csw.pyme.persistence.entity;

import javax.persistence.Entity;

@Entity
public class PymeEntity extends _PymeEntity {
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