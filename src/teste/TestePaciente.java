package teste;

import model.Paciente;

public class TestePaciente {

    public static void main(String[] args) {

        try {
            Paciente paciente = new Paciente(
                1,
                "Marco",
                "12345678901",
                "(47) 99999-8888",
                "16/01/2007"
            );

            System.out.println("Teste válido passou!");
            System.out.println(paciente);

        } catch (IllegalArgumentException e) {
            System.out.println("Teste válido falhou: " + e.getMessage());
        }
    }
}