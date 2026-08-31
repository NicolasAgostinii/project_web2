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


@WebService
public class ConsultorioController implements ConsultorioService {

    private Map<Integer, Consultorio> consultorios = new HashMap<>();

    @Override
    public void adicionarPaciente(int idConsultorio, int idPaciente, String nome, String cpf, String telefone, String drtNascimento) {
        Paciente paciente = new Paciente(idPaciente, nome, cpf, telefone, drtNascimento);
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
        Paciente paciente = new Paciente(idPacinte, nome, cpf, telefone, drtNascimento);

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

        if (consultorios.isEmpty()) {
            return "Não existem consultórios cadastrados.";
        }

        StringBuilder temp = new StringBuilder();

        for (Consultorio c : consultorios.values()) {
            temp.append(c).append("\n");
        }

        return temp.toString();
    }

    @Override
    public void alterarNomeCliente(int idConsultorio, int idPaciente, String novoNome) {
        if (!consultorios.containsKey(idConsultorio)) {
            throw new NullPointerException("A parada nao existe");
        }

        Consultorio c = consultorios.get(idConsultorio);

        for (Paciente p : c.getPacientes().values()) {
            if (idPaciente == p.getId()) {
                p.setNome(novoNome);
            }
        }
    }

    @Override
    public void alterarConsulta(
            int id,
            String novoNome,
            String novoCnpj,
            String novoTelefone,
            String novoEndereco) {

        if (!consultorios.containsKey(id)) {
            throw new NullPointerException("O consultório não existe");
        }

        Consultorio c = consultorios.get(id);

        c.setNome(novoNome);
        c.setCnpj(novoCnpj);
        c.setTelefone(novoTelefone);
        c.setEndereco(novoEndereco);
    }
}