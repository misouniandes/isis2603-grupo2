
package co.edu.uniandes.csw.productocomp.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.productocomp.logic.dto.ProductoCompDTO;
import co.edu.uniandes.csw.productocomp.logic.api._IProductoCompLogicService;

public abstract class _ProductoCompMockLogicService implements _IProductoCompLogicService {

	private Long id= new Long(1);
	protected List<ProductoCompDTO> data=new ArrayList<ProductoCompDTO>();

	public ProductoCompDTO createProductoComp(ProductoCompDTO productoComp){
		id++;
		productoComp.setId(id);
		return productoComp;
    }

	public List<ProductoCompDTO> getProductoComps(){
		return data; 
	}

	public ProductoCompDTO getProductoComp(Long id){
		for(ProductoCompDTO d:data){
			if(d.getId().equals(id)){
				return d;
			}
		}
		return null;
	}

	public void deleteProductoComp(Long id){
	    ProductoCompDTO delete=null;
		for(ProductoCompDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateProductoComp(ProductoCompDTO productoComp){
	    ProductoCompDTO delete=null;
		for(ProductoCompDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(productoComp);
		} 
	}	
}