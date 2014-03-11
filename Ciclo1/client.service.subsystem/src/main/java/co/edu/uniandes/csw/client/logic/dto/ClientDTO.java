
package co.edu.uniandes.csw.client.logic.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class ClientDTO extends _ClientDTO {
        
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
            this.email = email;
        }
        

}