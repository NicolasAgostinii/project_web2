package teste;

import model.Consulta;
import model.StatusConsulta;

public class TesteConsulta {

    public static void main(String[] args) {

        System.out.println("=== TESTES DA CLASSE CONSULTA ===");

        testarConsultaValida();
        testarIdInvalido();
        testarPacienteInvalido();
        testarConsultorioInvalido();
        testarDataInvalida();
        testarEspecialidadeInvalida();
        testarStatusInvalido();
    }

    private static void testarConsultaValida() {
        try {
            Consulta consulta = new Consulta(
                1,
                1,
                1,
                "18/08/2026 14:30",
                "Clínico Geral",
                StatusConsulta.AGENDADA
            );

            System.out.println("\n[PASSOU] Consulta válida");
            System.out.println(consulta);

        } catch (IllegalArgumentException e) {
            System.out.println("\n[FALHOU] Consulta válida");
            System.out.println(e.getMessage());
        }
    }

    private static void testarIdInvalido() {
        try {
            new Consulta(
                0,
                1,
                1,
                "18/08/2026 14:30",
                "Clínico Geral",
                StatusConsulta.AGENDADA
            );

            System.out.println("\n[FALHOU] Aceitou ID inválido");

        } catch (IllegalArgumentException e) {
            System.out.println("\n[PASSOU] Rejeitou ID inválido");
            System.out.println(e.getMessage());
        }
    }

    private static void testarPacienteInvalido() {
        try {
            new Consulta(
                1,
                0,
                1,
                "18/08/2026 14:30",
                "Clínico Geral",
                StatusConsulta.AGENDADA
            );

            System.out.println("\n[FALHOU] Aceitou paciente inválido");

        } catch (IllegalArgumentException e) {
            System.out.println("\n[PASSOU] Rejeitou paciente inválido");
            System.out.println(e.getMessage());
        }
    }

    private static void testarConsultorioInvalido() {
        try {
            new Consulta(
                1,
                1,
                0,
                "18/08/2026 14:30",
                "Clínico Geral",
                StatusConsulta.AGENDADA
            );

            System.out.println("\n[FALHOU] Aceitou consultório inválido");

        } catch (IllegalArgumentException e) {
            System.out.println("\n[PASSOU] Rejeitou consultório inválido");
            System.out.println(e.getMessage());
        }
    }

    private static void testarDataInvalida() {
        try {
            new Consulta(
                1,
                1,
                1,
                "31/02/2026 14:30",
                "Clínico Geral",
                StatusConsulta.AGENDADA
            );

            System.out.println("\n[FALHOU] Aceitou data inválida");

        } catch (IllegalArgumentException e) {
            System.out.println("\n[PASSOU] Rejeitou data inválida");
            System.out.println(e.getMessage());
        }
    }

    private static void testarEspecialidadeInvalida() {
        try {
            new Consulta(
                1,
                1,
                1,
                "18/08/2026 14:30",
                "   ",
                StatusConsulta.AGENDADA
            );

            System.out.println("\n[FALHOU] Aceitou especialidade vazia");

        } catch (IllegalArgumentException e) {
            System.out.println("\n[PASSOU] Rejeitou especialidade vazia");
            System.out.println(e.getMessage());
        }
    }

    private static void testarStatusInvalido() {
        try {
            new Consulta(
                1,
                1,
                1,
                "18/08/2026 14:30",
                "Clínico Geral",
                null
            );

            System.out.println("\n[FALHOU] Aceitou status nulo");

        } catch (IllegalArgumentException e) {
            System.out.println("\n[PASSOU] Rejeitou status nulo");
            System.out.println(e.getMessage());
        }
    }
}