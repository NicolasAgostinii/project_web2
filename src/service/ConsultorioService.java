package service;

import model.Paciente;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.crypto.Data;
import java.util.Date;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ConsultorioService {
    @WebMethod
    void adicionarPaciente(int idConsultorio, int idPaciente, String nome, String cpf, String telefone, String drtNascimento);

    @WebMethod
    void adicionarConsulta(int id, String nome, String cnpj, String telefone, String endereco);

    @WebMethod
    void removerCliente(int idConsultotrio, int idPacinte, String nome, String cpf, String telefone, String drtNascimento);

    @WebMethod
    void removerConsulta(int idConsulta);

    @WebMethod
    String listarClientes(int idConsultorio);

    @WebMethod
    String listarConsultas();

    @WebMethod
    void alterarNomeCliente(int idConsultorio, int idPaciente, String novoNome);

    @WebMethod
    void alterarConsulta(int id, String novoNome, String novoCnpj, String novoTelefone, String novoEndereco);
}
