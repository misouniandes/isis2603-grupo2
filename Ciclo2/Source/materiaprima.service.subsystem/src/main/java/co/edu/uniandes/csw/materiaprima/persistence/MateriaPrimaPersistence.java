
package co.edu.uniandes.csw.materiaprima.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.materiaprima.persistence.api.IMateriaPrimaPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class MateriaPrimaPersistence extends _MateriaPrimaPersistence  implements IMateriaPrimaPersistence {

}