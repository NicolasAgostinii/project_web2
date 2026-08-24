package teste;

import model.Consultorio;

public class TesteConsultorio {

    public static void main(String[] args) {

        System.out.println("=== TESTES DA CLASSE CONSULTÓRIO ===");

        testarConsultorioValido();
        testarIdInvalido();
        testarNomeInvalido();
        testarCnpjInvalido();
        testarTelefoneInvalido();
        testarEnderecoInvalido();
    }

    private static void testarConsultorioValido() {
        try {
            Consultorio consultorio = new Consultorio(
                1,
                "Consultório Central",
                "12345678000199",
                "(47) 3333-4444",
                "Rua das Flores, 100"
            );

            System.out.println("\n[PASSOU] Consultório válido");
            System.out.println(consultorio);

        } catch (IllegalArgumentException e) {
            System.out.println("\n[FALHOU] Consultório válido");
            System.out.println(e.getMessage());
        }
    }

    private static void testarIdInvalido() {
        try {
            new Consultorio(
                0,
                "Consultório Central",
                "12345678000199",
                "4733334444",
                "Rua das Flores, 100"
            );

            System.out.println("\n[FALHOU] Aceitou ID inválido");

        } catch (IllegalArgumentException e) {
            System.out.println("\n[PASSOU] Rejeitou ID inválido");
            System.out.println(e.getMessage());
        }
    }

    private static void testarNomeInvalido() {
        try {
            new Consultorio(
                1,
                "   ",
                "12345678000199",
                "4733334444",
                "Rua das Flores, 100"
            );

            System.out.println("\n[FALHOU] Aceitou nome vazio");

        } catch (IllegalArgumentException e) {
            System.out.println("\n[PASSOU] Rejeitou nome vazio");
            System.out.println(e.getMessage());
        }
    }

    private static void testarCnpjInvalido() {
        try {
            new Consultorio(
                1,
                "Consultório Central",
                "123456789",
                "4733334444",
                "Rua das Flores, 100"
            );

            System.out.println("\n[FALHOU] Aceitou CNPJ inválido");

        } catch (IllegalArgumentException e) {
            System.out.println("\n[PASSOU] Rejeitou CNPJ inválido");
            System.out.println(e.getMessage());
        }
    }

    private static void testarTelefoneInvalido() {
        try {
            new Consultorio(
                1,
                "Consultório Central",
                "12345678000199",
                "12345",
                "Rua das Flores, 100"
            );

            System.out.println("\n[FALHOU] Aceitou telefone inválido");

        } catch (IllegalArgumentException e) {
            System.out.println("\n[PASSOU] Rejeitou telefone inválido");
            System.out.println(e.getMessage());
        }
    }

    private static void testarEnderecoInvalido() {
        try {
            new Consultorio(
                1,
                "Consultório Central",
                "12345678000199",
                "4733334444",
                "   "
            );

            System.out.println("\n[FALHOU] Aceitou endereço vazio");

        } catch (IllegalArgumentException e) {
            System.out.println("\n[PASSOU] Rejeitou endereço vazio");
            System.out.println(e.getMessage());
        }
    }
}