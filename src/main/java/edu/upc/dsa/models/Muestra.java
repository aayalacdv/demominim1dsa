package edu.upc.dsa.models;

import edu.upc.dsa.utils.RandomUtils;

public class Muestra {
    private String id;
    private String pacienteId;
    private String medicoId;
    private String fecha;
    private String idLab;


    private Informe informe;
    private String Comentario;


    public Muestra(){
        this.id = RandomUtils.getId();
    }

    public Muestra(String pacienteId, String medicoId, String fecha, String idLab){
        this();
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.idLab = idLab;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(String pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(String medicoId) {
        this.medicoId = medicoId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdLab() {
        return idLab;
    }

    public void setIdLab(String idLab) {
        this.idLab = idLab;
    }

    public Informe getInforme() {
        return informe;
    }

    public void setInforme(Informe informe) {
        this.informe = informe;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    @Override
    public String toString(){
        return "Id_muestra" + this.getId() + " resultado: " + this.informe + " comentario: " + this.getComentario();
    }
}
