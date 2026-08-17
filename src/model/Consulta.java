package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Consulta {
    private int id;
    private int pacienteid;
    private String dataHora;
    private String especialidade;
    private String status;

    public Consulta(
            int id,
            int pacienteid,
            String dataHora,
            String especialidade,
            String status) {

        setId(id);
        setPacienteid(pacienteid);
        setDataHora(dataHora);
        setEspecialidade(especialidade);
        setStatus(status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID deve ser maior que zero.");
        }
        this.id = id;
    }

    public int getPacienteid() {
        return pacienteid;
    }

    public void setPacienteid(int pacienteid) {
        if (pacienteid <= 0) {
            throw new IllegalArgumentException("O ID do paciente deve ser maior que zero.");
        }
        this.pacienteid = pacienteid;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        if (dataHora == null || dataHora.trim().isEmpty()) {
            throw new IllegalArgumentException("A data e hora não podem ser nulas ou vazias.");
        }
        this.dataHora = dataHora;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new IllegalArgumentException("A especialidade não pode ser nula ou vazia.");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Consulta [id=" + id + ", pacienteid=" + pacienteid + ", dataHora=" + dataHora + ", especialidade="
                + especialidade + ", status=" + status + "]";
    }
}
