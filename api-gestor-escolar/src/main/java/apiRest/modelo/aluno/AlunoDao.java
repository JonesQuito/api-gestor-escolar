
package apiRest.modelo.aluno;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import apiRest.modelo.endereco.Endereco;

public class AlunoDao {

	private Connection connection;

	public AlunoDao(Connection connection) {
		this.connection = connection;
	}

	public void inserir(Aluno aluno) {

		String sql = "insert into aluno "
				+ "(Nome, DataNascimento, Rg, Cpf, NomeMae, Cidade, Bairro, Logradouro, Cep, Numero) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Date dataNascimento = new Date(aluno.getDataNascimento().getTimeInMillis());

		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, aluno.getNome());
			stmt.setDate(2, dataNascimento);
			stmt.setString(3, aluno.getRg());
			stmt.setString(4, aluno.getCpf());
			stmt.setString(5, aluno.getNomeMae());
			stmt.setString(6, aluno.getEndereco().getCidade());
			stmt.setString(7, aluno.getEndereco().getBairro());
			stmt.setString(8, aluno.getEndereco().getLogradouro());
			stmt.setString(9, aluno.getEndereco().getCep());
			stmt.setString(10, aluno.getEndereco().getNumero());

			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}


	public List<Aluno> pesquisarPorNome(String nome) {
		try {
			List<Aluno> alunos = new ArrayList<Aluno>();
			
			String sql = "select * from aluno where Nome like '%' ? '%' or MatrAluno like ? '%'";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setString(2, nome);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				Aluno aluno = new Aluno();
				aluno.setEndereco(new Endereco());

				// Seta os valores retornados na pesquisa para os atributos de aluno
				aluno.setMatricula(rs.getInt("MatrAluno"));
				aluno.setNome(rs.getString("Nome"));
				aluno.setNomeMae(rs.getString("NomeMae"));
				aluno.setRg(rs.getString("Rg"));
				aluno.setCpf(rs.getString("Cpf"));
				aluno.getEndereco().setCidade(rs.getString("Cidade"));
				aluno.getEndereco().setBairro(rs.getString("Bairro"));
				aluno.getEndereco().setLogradouro(rs.getString("Logradouro"));
				aluno.getEndereco().setCep(rs.getString("Cep"));
				aluno.getEndereco().setNumero(rs.getString("Numero"));

				// Convete a data retornada do banco para o tipo Calendar
				Calendar dataNascimento = Calendar.getInstance();
				dataNascimento.setTime(rs.getDate("DataNascimento"));

				// Seta a data de nascimento do aluno
				aluno.setDataNascimento(dataNascimento);
				
				alunos.add(aluno);
			}
			return alunos;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	
	//PESQUISAR POR MATRICULA
	public Aluno pesquisarPorMatricula(String matricula) {
		Aluno aluno = null;
		Endereco endereco = new Endereco();
		try {
			String sql = "select * from aluno where MatrAluno=?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, matricula);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				aluno = new Aluno();
				aluno.setEndereco(endereco);

				// Seta os valores retornados na pesquisa para os atributos de aluno
				aluno.setMatricula(rs.getInt("MatrAluno"));
				aluno.setNome(rs.getString("Nome"));
				aluno.setNomeMae(rs.getString("NomeMae"));
				aluno.setRg(rs.getString("Rg"));
				aluno.setCpf(rs.getString("Cpf"));
				aluno.getEndereco().setCidade(rs.getString("Cidade"));
				aluno.getEndereco().setBairro(rs.getString("Bairro"));
				aluno.getEndereco().setLogradouro(rs.getString("Logradouro"));
				aluno.getEndereco().setCep(rs.getString("Cep"));
				aluno.getEndereco().setNumero(rs.getString("Numero"));

				// Convete a data retornada do banco para o tipo Calendar
				Calendar dataNascimento = Calendar.getInstance();
				dataNascimento.setTime(rs.getDate("DataNascimento"));

				// Seta a data de nascimento do aluno
				aluno.setDataNascimento(dataNascimento);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return aluno;
	}
	
	

	public List<Aluno> getLista() {		
		try{
			List<Aluno> alunos = new ArrayList<Aluno>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from aluno");
			ResultSet rs = stmt.executeQuery();
			
			
			while(rs.next()){
				Aluno aluno = new Aluno();
				aluno.setEndereco(new Endereco());
				// Seta os valores retornados na pesquisa para os atributos de aluno
				aluno.setMatricula(rs.getInt("MatrAluno"));
				aluno.setNome(rs.getString("Nome"));
				aluno.setNomeMae(rs.getString("NomeMae"));
				aluno.setRg(rs.getString("Rg"));
				aluno.setCpf(rs.getString("Cpf"));
				aluno.getEndereco().setCidade(rs.getString("Cidade"));
				aluno.getEndereco().setBairro(rs.getString("Bairro"));
				aluno.getEndereco().setLogradouro(rs.getString("Logradouro"));
				aluno.getEndereco().setCep(rs.getString("Cep"));
				aluno.getEndereco().setNumero(rs.getString("Numero"));
				
				//Convertendo data para Calendar e setando como atributo de aluno
				Calendar dataNascimento = Calendar.getInstance();
				dataNascimento.setTime(rs.getDate("DataNascimento"));
				
				aluno.setDataNascimento(dataNascimento);
				
				//Adicionando aluno na lista
				alunos.add(aluno);
				
			}
			return alunos;
		}catch(SQLException e){
			throw new RuntimeException("Erro na listagem de alunos: " + e);
		}	
	}

	public boolean editar(Aluno aluno) throws SQLException {
		String sql = "update aluno set Nome=?, NomeMae=?, DataNascimento=?, Rg=?, Cpf=?, Cidade=?, "
					+ "Bairro=?, Logradouro=?, Cep=?, Numero=? where MatrAluno=?";	
		
		PreparedStatement stmt = this.connection.prepareStatement(sql);
		stmt.setString(1, aluno.getNome());
		stmt.setString(2, aluno.getNomeMae());
		stmt.setDate(3, new Date(aluno.getDataNascimento().getTimeInMillis()));
		stmt.setString(4, aluno.getRg());
		stmt.setString(5, aluno.getCpf());
		stmt.setString(6, aluno.getEndereco().getCidade());
		stmt.setString(7, aluno.getEndereco().getBairro());
		stmt.setString(8, aluno.getEndereco().getLogradouro());
		stmt.setString(9, aluno.getEndereco().getCep());
		stmt.setString(10, aluno.getEndereco().getNumero());
		stmt.setInt(11, aluno.getMatricula());
		
		return stmt.execute();
		
		
	}

	public boolean excluir(int matricula) throws SQLException {
		String sql = "delete from aluno where MatrAluno=?";
		PreparedStatement stmt = this.connection.prepareStatement(sql);
		stmt.setInt(1, matricula);
		return stmt.execute();
	}

}
