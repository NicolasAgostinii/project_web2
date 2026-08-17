package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.ConflitoException;
import exception.EntidadeNaoEncontradaException;
import exception.ValidacaoException;
import model.Consulta;
import model.StatusConsulta;

public class ConsultaController {
    
    private  Map<Integer, Consulta> consultas = new HashMap<>();

    public Consulta cadastrar(Consulta consulta)
            throws ValidacaoException, ConflitoException {

        if (consulta == null) {
            throw new ValidacaoException(
                "A consulta não pode ser nula."
            );
        }

        if (consultas.containsKey(consulta.getId())) {
            throw new ConflitoException(
                "Já existe uma consulta com esse ID."
            );
        }

        if (horarioConflitante(
                consulta.getConsultorioId(),
                consulta.getDataHora(),
                consulta.getId())) {

            throw new ConflitoException(
                "Já existe uma consulta agendada para esse consultório nesse horário."
            );
        }

        consultas.put(consulta.getId(), consulta);

        return consulta;
    }

    public Consulta buscarPorId(int id)
            throws EntidadeNaoEncontradaException {

        Consulta consulta = consultas.get(id);

        if (consulta == null) {
            throw new EntidadeNaoEncontradaException(
                "Consulta não encontrada."
            );
        }

        return consulta;
    }

    public List<Consulta> listarTodos() {
        return new ArrayList<>(consultas.values());
    }

    public List<Consulta> listarPorPaciente(int pacienteId) {
        List<Consulta> resultado = new ArrayList<>();

        for (Consulta consulta : consultas.values()) {
            if (consulta.getPacienteId() == pacienteId) {
                resultado.add(consulta);
            }
        }

        return resultado;
    }

    public List<Consulta> listarPorConsultorio(int consultorioId) {
        List<Consulta> resultado = new ArrayList<>();

        for (Consulta consulta : consultas.values()) {
            if (consulta.getConsultorioId() == consultorioId) {
                resultado.add(consulta);
            }
        }

        return resultado;
    }

    public Consulta atualizar(Consulta consulta)
            throws ValidacaoException,
                   EntidadeNaoEncontradaException,
                   ConflitoException {

        if (consulta == null) {
            throw new ValidacaoException(
                "A consulta não pode ser nula."
            );
        }

        if (!consultas.containsKey(consulta.getId())) {
            throw new EntidadeNaoEncontradaException(
                "Consulta não encontrada."
            );
        }

        if (horarioConflitante(
                consulta.getConsultorioId(),
                consulta.getDataHora(),
                consulta.getId())) {

            throw new ConflitoException(
                "Já existe outra consulta agendada para esse consultório nesse horário."
            );
        }

        consultas.put(consulta.getId(), consulta);

        return consulta;
    }

    public Consulta cancelar(int id)
            throws EntidadeNaoEncontradaException {

        Consulta consulta = buscarPorId(id);
        consulta.setStatus(StatusConsulta.CANCELADA);

        return consulta;
    }

    public Consulta marcarComoRealizada(int id)
            throws EntidadeNaoEncontradaException {

        Consulta consulta = buscarPorId(id);
        consulta.setStatus(StatusConsulta.REALIZADA);

        return consulta;
    }

    public boolean excluir(int id)
            throws EntidadeNaoEncontradaException {

        if (!consultas.containsKey(id)) {
            throw new EntidadeNaoEncontradaException(
                "Consulta não encontrada."
            );
        }

        consultas.remove(id);

        return true;
    }

    private boolean horarioConflitante(int consultorioId, String dataHora, int idIgnorado) {
        for (Consulta consulta : consultas.values()) {
            boolean mesmoConsultorio = consulta.getConsultorioId() == consultorioId;
            boolean mesmoHorario = consulta.getDataHora().equals(dataHora);
            boolean naoCancelada = consulta.getStatus() != StatusConsulta.CANCELADA;
            boolean idDiferente = consulta.getId() != idIgnorado;

            if (mesmoConsultorio && mesmoHorario && naoCancelada && idDiferente) {
                return true;
            }
        }

        return false;
    }
}
