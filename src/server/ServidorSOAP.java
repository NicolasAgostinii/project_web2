package server;

import javax.xml.ws.Endpoint;

import controller.ConsultorioController;

public class ServidorSOAP {
      public static void main(String[] args)
  {
    Endpoint.publish("http://localhost:8080/ws/consultorio?wsdl",
    new ConsultorioController());
  }
}
