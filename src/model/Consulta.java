package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Consulta {
    private int id;
    private int pacienteId;
    private String dataHora;
    private String especialidade;
    private StatusConsulta status;
    private int consultorioId;

    public Consulta(){

    }

    private static final DateTimeFormatter FORMATO_DATA =
	        DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
	                         .withResolverStyle(ResolverStyle.STRICT);

    public Consulta(
            int id,
            int pacienteId,
            int consultorioId,
            String dataHora,
            String especialidade,
            StatusConsulta status) {

        setId(id);
        setPacienteId(pacienteId);
        setDataHora(dataHora);
        setEspecialidade(especialidade);
        setStatus(status);
        setConsultorioId(consultorioId);
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

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        if (pacienteId <= 0) {
            throw new IllegalArgumentException("O ID do paciente deve ser maior que zero.");
        }
        this.pacienteId = pacienteId;
    }

    public int getConsultorioId(){
        return consultorioId;
    }

    public void setConsultorioId(int consultorioId){
        if (consultorioId <= 0){
            throw new IllegalArgumentException("O ID do consultório deve ser maior que zero");
        }

        this.consultorioId = consultorioId;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        if (dataHora == null || dataHora.isBlank()) {
            throw new IllegalArgumentException("Hora da consulta é obrigatória");
        }

        LocalDateTime dataConvertida;

        try{
            dataConvertida = LocalDateTime.parse(dataHora, FORMATO_DATA);
        } catch (DateTimeParseException e){
            throw new IllegalArgumentException("A data da consulta deve estar no formato dd/MM/aaaa HH:mm e ser válida.");
        }

		
		this.dataHora = dataConvertida.format(FORMATO_DATA);

    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        if (especialidade == null || especialidade.isBlank()) {
            throw new IllegalArgumentException("A especialidade não pode ser nula ou vazia.");
        }
        
        this.especialidade = especialidade;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public void setStatus(StatusConsulta status) {
    if (status == null) {
        throw new IllegalArgumentException(
            "O status da consulta é obrigatório."
        );
    }

    this.status = status;
}

    @Override
public String toString() {
    return "Consulta [id=" + id + ", pacienteId=" + pacienteId +
            ", consultorioId=" + consultorioId +
            ", dataHora=" + dataHora +
            ", especialidade=" + especialidade +
            ", status=" + status +
            "]";
}
}
