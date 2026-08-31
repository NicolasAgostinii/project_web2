package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.ConflitoException;
import exception.EntidadeNaoEncontradaException;
import exception.ValidacaoException;
import model.Consultorio;
import model.Paciente;
import service.ConsultorioService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import exception.ConflitoException;


@WebService
public class ConsultorioController implements ConsultorioService {

    private final Map<Integer, Consultorio> consultorios = new HashMap<>();

    @Override
    public void adicionarPaciente(int idConsultorio, int idPaciente, String nome, String cpf, String telefone, String drtNascimento) {
        Paciente paciente = new Paciente(idPaciente, idConsultorio, nome, cpf, telefone, drtNascimento);
        if (consultorios.containsKey(idConsultorio)) {
            consultorios.get(idConsultorio).addPaciente(paciente);
        } else {
            throw new NullPointerException("O consultorio nao existe");
        }
    }

    @Override
    public void adicionarConsulta(int id, String nome, String cnpj, String telefone, String endereco) {
        Consultorio consultorio = new Consultorio(id, nome, cnpj, telefone, endereco);

        if (consultorios.containsKey(id)) {
            throw new IllegalArgumentException("Esse consultorio já existe");
        } else {
            consultorios.put(id, consultorio);
        }
    }

    @Override
    public void removerCliente(int idConsultotrio, int idPacinte, String nome, String cpf, String telefone, String drtNascimento) {
        Paciente paciente = new Paciente(idPacinte, idConsultotrio, nome, cpf, telefone, drtNascimento);

        if (consultorios.containsKey(idConsultotrio)) {
            if (consultorios.get(idConsultotrio).isExist(idPacinte)) {
                consultorios.get(idConsultotrio).removerPaciente(paciente);
            }
        } else {
            throw new NullPointerException("O consultorio nao existe");
        }
    }

    @Override
    public void removerConsulta(int idConsulta) {
        if (consultorios.containsKey(idConsulta)) {
            consultorios.remove(idConsulta);
        } else {
            throw new NullPointerException("Consulta nao existe");
        }
    }

    @Override
    public String listarClientes(int idConsultorio) {
        if (!consultorios.containsKey(idConsultorio)) {
            throw new NullPointerException("O consultório não existe");
        }

        Consultorio c = consultorios.get(idConsultorio);

        StringBuilder temp = new StringBuilder();

        for (Paciente p : c.getPacientes().values()) {
            temp.append(p).append("\n");
        }
        return temp.toString();
    }

    @Override
    public String listarConsultas() {
        StringBuilder sb = new StringBuilder();
        for (Consultorio c : consultorios.values()) {
            sb.append(c).append("\n");
        }
        return sb.toString();
    }

    @Override
    public void alterarNomeCliente() {
        // no-op placeholder (interface method preserved)
    }

    @Override
    public void alterarConsulta() {
        // no-op placeholder (interface method preserved)
    }

    @Override
    public Paciente listarPaciente(Paciente p) {
        if (p == null) return null;
        for (Consultorio c : consultorios.values()) {
            if (c.getPacientes().containsKey(p.getId())) {
                return c.getPacientes().get(p.getId());
            }
        }
        return null;
    }

    public Consultorio cadastrar(Consultorio consultorio)
            throws ValidacaoException, ConflitoException {

        if (consultorio == null) {
            throw new ValidacaoException(
                "O consultório não pode ser nulo."
            );
        }

        if (consultorios.containsKey(consultorio.getId())) {
            throw new ConflitoException(
                "Já existe um consultório com esse ID."
            );
        }

        if (cnpjJaCadastrado(consultorio.getCnpj(), consultorio.getId())) {
            throw new ConflitoException(
                "Já existe um consultório com esse CNPJ."
            );
        }

        consultorios.put(consultorio.getId(), consultorio);

        return consultorio;
    }

    public Consultorio buscarPorId(int id)
            throws EntidadeNaoEncontradaException {

        Consultorio consultorio = consultorios.get(id);

        if (consultorio == null) {
            throw new EntidadeNaoEncontradaException(
                "Consultório não encontrado."
            );
        }

        return consultorio;
    }

    public List<Consultorio> listarTodos() {
        return new ArrayList<>(consultorios.values());
    }

    public Consultorio atualizar(Consultorio consultorio)
            throws ValidacaoException,
                   EntidadeNaoEncontradaException,
                   ConflitoException {

        if (consultorio == null) {
            throw new ValidacaoException(
                "O consultório não pode ser nulo."
            );
        }

        if (!consultorios.containsKey(consultorio.getId())) {
            throw new EntidadeNaoEncontradaException(
                "Consultório não encontrado."
            );
        }

        if (cnpjJaCadastrado(consultorio.getCnpj(), consultorio.getId())) {
            throw new ConflitoException(
                "Já existe outro consultório com esse CNPJ."
            );
        }

        consultorios.put(consultorio.getId(), consultorio);

        return consultorio;
    }

    public boolean excluir(int id)
            throws EntidadeNaoEncontradaException {

        if (!consultorios.containsKey(id)) {
            throw new EntidadeNaoEncontradaException(
                "Consultório não encontrado."
            );
        }

        consultorios.remove(id);

        return true;
    }

    private boolean cnpjJaCadastrado(String cnpj, int idIgnorado) {
        for (Consultorio consultorio : consultorios.values()) {
            if (consultorio.getCnpj().equals(cnpj)
                    && consultorio.getId() != idIgnorado) {

                return true;
            }
        }

        return false;
    }
}

    }
}