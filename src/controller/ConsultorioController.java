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

import javax.jws.WebService;


@WebService
public class ConsultorioController {

    private Consultorio c;
    private  Map<Integer, Consultorio> consultorios = new HashMap<>();

    public void adicionarPaciente(int id , Paciente p) {
        c = consultorios.get(id);
        c.addPaciente(p);
    }

    public void  cadastrar( int id,String nome, String cnpj, String telefone, String endereco){
        Consultorio consultorio= new Consultorio(  id, nome,  cnpj,  telefone,  endereco);
        consultorios.put(id,consultorio);
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