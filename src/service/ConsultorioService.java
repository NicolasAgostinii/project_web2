package service;

import model.Paciente;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.crypto.Data;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ConsultorioService {
    @WebMethod void adicionarCliente();
    @WebMethod void removerCliente();
    @WebMethod Data data();
    @WebMethod boolean clientePresente();
    @WebMethod String listarClientes();
    @WebMethod void data1();
    @WebMethod void alterarNomeCliente();
    @WebMethod boolean paceinteExiste(Paciente p );
    @WebMethod Paciente retornarPaciente(Paciente p);

}
