package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.ConflitoException;
import exception.EntidadeNaoEncontradaException;
import exception.ValidacaoException;
import model.Paciente;

public class PacienteController {
   
    private Map<Integer, Paciente> pacientes =
            new HashMap<>();

    public Paciente cadastrar(Paciente paciente)
            throws ValidacaoException, ConflitoException {

        if (paciente == null) {
            throw new ValidacaoException(
                "O paciente não pode ser nulo."
            );
        }

        if (pacientes.containsKey(paciente.getId())) {
            throw new ConflitoException(
                "Já existe um paciente com esse ID."
            );
        }

        if (cpfJaCadastrado(paciente.getCpf(), paciente.getId())) {
            throw new ConflitoException(
                "Já existe um paciente com esse CPF."
            );
        }

        pacientes.put(paciente.getId(), paciente);

        return paciente;
    }

    public Paciente buscarPorId(int id)
            throws EntidadeNaoEncontradaException {

        Paciente paciente = pacientes.get(id);

        if (paciente == null) {
            throw new EntidadeNaoEncontradaException(
                "Paciente não encontrado."
            );
        }

        return paciente;
    }

    public List<Paciente> listarTodos() {
        return new ArrayList<>(pacientes.values());
    }

    public List<Paciente> listarPorConsultorio(int consultorioId) {
        List<Paciente> resultado = new ArrayList<>();

        for (Paciente paciente : pacientes.values()) {
            if (paciente.getConsultorioId() == consultorioId) {
                resultado.add(paciente);
            }
        }

        return resultado;
    }

    public Paciente atualizar(Paciente paciente)
            throws ValidacaoException,
                   EntidadeNaoEncontradaException,
                   ConflitoException {

        if (paciente == null) {
            throw new ValidacaoException(
                "O paciente não pode ser nulo."
            );
        }

        if (!pacientes.containsKey(paciente.getId())) {
            throw new EntidadeNaoEncontradaException(
                "Paciente não encontrado."
            );
        }

        if (cpfJaCadastrado(paciente.getCpf(), paciente.getId())) {
            throw new ConflitoException(
                "Já existe outro paciente com esse CPF."
            );
        }

        pacientes.put(paciente.getId(), paciente);

        return paciente;
    }

    public boolean excluir(int id)
            throws EntidadeNaoEncontradaException {

        if (!pacientes.containsKey(id)) {
            throw new EntidadeNaoEncontradaException(
                "Paciente não encontrado."
            );
        }

        pacientes.remove(id);

        return true;
    }

    private boolean cpfJaCadastrado(String cpf, int idIgnorado) {
        for (Paciente paciente : pacientes.values()) {
            if (paciente.getCpf().equals(cpf)
                    && paciente.getId() != idIgnorado) {

                return true;
            }
        }

        return false;
    }
}
