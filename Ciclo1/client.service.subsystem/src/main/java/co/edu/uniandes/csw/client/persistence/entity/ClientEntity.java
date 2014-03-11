
package co.edu.uniandes.csw.client.persistence.entity;

import javax.persistence.Entity;

@Entity
public class ClientEntity extends _ClientEntity {

    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email=email;
    }
 	
}