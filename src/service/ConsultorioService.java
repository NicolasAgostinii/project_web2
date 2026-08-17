package service;

import model.Consulta;
import model.Paciente;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.crypto.Data;
import java.util.Date;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ConsultorioService {
    @WebMethod void adicionarCliente();
    @WebMethod void adicionarConsultorio();
    @WebMethod void removerCliente();
    @WebMethod void removerConsultario();
    @WebMethod String listarClientes();
    @WebMethod String listarProdutos();
    @WebMethod void alterarNomeCliente();
    @WebMethod void alterarNomeConsultorio();
    @WebMethod Paciente listarPaciente(Paciente p);
    @WebMethod Consulta listarConsultas(Consulta c);
}
