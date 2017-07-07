package apiRest;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/imcjaxrs")
public class ImcServices {

	
	@GET
	@Path("/imc")
	@Produces("application/text")
	public String getTexto(){
		
		return "Retorna um texto qualquer!!";
	}
	

}
