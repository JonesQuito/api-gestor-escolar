package apiRest.modelo.aluno;

import java.util.Calendar;

import apiRest.modelo.endereco.Endereco;

/**
 * @author Hitallo, Jones, Marcello e Reinaldo.
 */

public class Aluno {

	// Atributos de um objeto do tipo Aluno
	private int matricula = 0;
	private String nome = null;
	private Calendar dataNascimento = null;
	private String rg = null;
	private String cpf = null;
	private String nomeMae = null;
	private Endereco endereco = null;
	
	
	// Métodos acessores dos atributos de um objeto Aluno
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNomeMae() {
		return nomeMae;
	}
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
