package model;

public class Consultorio {
    private int id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String endereco;

    public Consultorio() {

    }

    public Consultorio(
            int id,
            String nome,
            String cnpj,
            String telefone,
            String endereco) {

        setId(id);
        setNome(nome);
        setCnpj(cnpj);
        setTelefone(telefone);
        setEndereco(endereco);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que 0");
        }

        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório");
        }

        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if (cnpj == null || cnpj.isBlank()) {
            throw new IllegalArgumentException("O CNPJ do consultorio é obrigatório");
        }

        if (!cnpj.matches("\\d{14}")) {
            throw new IllegalArgumentException("O CNPJ deve conter exatamente 14 números.");
        }

        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {

        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("O telefone do consultório é obrigatório.");
        }

        String numero = telefone.replaceAll("\\D", "");

        if (numero.length() != 10 && numero.length() != 11) {
            throw new IllegalArgumentException("O telefone deve conter 10 ou 11 números, incluindo o DDD!");
        }

        if (numero.length() == 11 && numero.charAt(2) != '9') {
            throw new IllegalArgumentException("Um número de celular deve começar com 9 após o DDD.");
        }

        this.telefone = numero;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        if (endereco == null || endereco.isBlank()) {
            throw new IllegalArgumentException("Endereço é obrigatório.");
        }

        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Consultorio {" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}