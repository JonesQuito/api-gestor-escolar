package apiRest;

import java.sql.Connection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import apiRest.conexao.ConnectionFactory;
import apiRest.modelo.aluno.Aluno;
import apiRest.modelo.aluno.AlunoDao;

@Path("/aluno")
public class AlunoService {
	
	@GET
	@Path("/texto")
	@Produces("application/text")
	public String getText(){
		return "Teste de retorno da api reste";
	}
	
	@GET
	@Path("list")
	@Produces("application/json")
	public String getList(){
		Connection connection = new ConnectionFactory().getConnection();
		List<Aluno> alunos = new AlunoDao(connection).getLista();
		
		Gson g = new Gson();
		
		
		return g.toJson(alunos);
	}

}
