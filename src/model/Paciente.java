package model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


public class Paciente {
	private int id;
	private String nome;
	private String cpf;
	private String telefone;
	private String dataNascimento;
	
	public Paciente() {
	}

	public Paciente(
	        int id,
	        String nome,
	        String cpf,
	        String telefone,
	        String dataNascimento) {

	    setId(id);
	    setNome(nome);
	    setCpf(cpf);
	    setTelefone(telefone);
	    setDataNascimento(dataNascimento);
	}
	
	private static final DateTimeFormatter FORMATO_DATA =
	        DateTimeFormatter.ofPattern("dd/MM/uuuu")
	                         .withResolverStyle(ResolverStyle.STRICT);
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		if(id <= 0) {
			throw new IllegalArgumentException("O ID deve ser maior que zero.");
		}
		
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
			throw new IllegalArgumentException("O CPF do paciente é obrigatório");
		}
		
		if(!cpf.matches("\\d{11}")) {
			throw new IllegalArgumentException("O CPF deve conter exatamente 11 números.");
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
	    
	    if(numero.length() == 11 && numero.charAt(2) != '9') {
	    	throw new IllegalArgumentException("Um número de celular deve começar com 9 após o DDD.");
	    }
		
		this.telefone = numero;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(String dataNascimento) {
		
		if(dataNascimento == null || dataNascimento.isBlank()) {
			throw new IllegalArgumentException("Data de nascimento é obrigatória");
		}
		LocalDate dataConvertida;
		
		try {
		    dataConvertida = LocalDate.parse(dataNascimento, FORMATO_DATA);
		} catch (DateTimeParseException e) {
		    throw new IllegalArgumentException(
		        "A data de nascimento deve estar no formato dd/MM/aaaa e ser válida."
		    );
		}
		
		if (dataConvertida.isAfter(LocalDate.now())) {
		    throw new IllegalArgumentException(
		        "A data de nascimento não pode estar no futuro."
		    );
		}
		
		this.dataNascimento = dataConvertida.format(FORMATO_DATA);
		
	}
	
	@Override
	public String toString() {
	    return "Paciente {" +
	            "id=" + id +
	            ", nome='" + nome + '\'' +
	            ", cpf='" + cpf + '\'' +
	            ", telefone='" + telefone + '\'' +
	            ", dataNascimento='" + dataNascimento + '\'' +
	            '}';
	}
	
}
