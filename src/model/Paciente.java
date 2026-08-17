package model;

public class Paciente {
	private int id;
	private String nome;
	private String cpf;
	private String telefone;
	private String dataNascimento;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if(nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("O nome do Paciente é obrigatório");
		}
		
		this.nome = nome;
	}
	
	public String getCpf() {
		
		return cpf;
	}
	
	public void setCpf(String cpf) {
		if(cpf == null || cpf.isBlank()) {
			throw new IllegalArgumentException("O CPF do cliente é obrigatório");
		}
		
		else if(!cpf.matches("\\d{11}")) {
			throw new IllegalArgumentException("CPF incompleto");
		}
		
		this.cpf = cpf;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		
		if(telefone == null || telefone.isBlank()) {
			throw new IllegalArgumentException("O telefone do paciente é obrigatório.");
		}
		
	    String numero = telefone.replaceAll("\\D", "");
	    
	    if(numero.length() != 10 && numero.length() != 11) {
	    	throw new IllegalArgumentException("O telefone deve conter 10 ou 11 números, incluindo o DDD!");
	    }
		
		this.telefone = telefone;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
}
