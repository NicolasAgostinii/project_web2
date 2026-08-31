package client;

import service.ConsultorioService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class ClienteSOAP {

    public static void main(String[] args) throws MalformedURLException {

        URL wsdlUrl = new URL("http://localhost:8080/ws/consultorio?wsdl");

        QName qname = new QName("http://service/", "ConsultorioControllerService");

        Service service = Service.create(wsdlUrl, qname);
        ConsultorioService consultorio = service.getPort(ConsultorioService.class);

        consultorio.adicionarConsulta(1, "Consultório Central", "12345678000199", "4733334444", "Rua das Missões, 100");
        System.out.println("Consultório criado.");

        consultorio.adicionarPaciente(1, 10, "Marco", "12345678901", "47999998888", "16/01/2007");
        System.out.println("Paciente adicionado.");

        System.out.println(consultorio.listarClientes(1));
    }
}