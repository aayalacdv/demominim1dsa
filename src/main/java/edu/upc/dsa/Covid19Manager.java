package edu.upc.dsa;

import edu.upc.dsa.models.*;

import java.util.List;

public interface Covid19Manager {


    //Operaciones especificadas
    //crear una persona en el sistema es meterla en el hasmap
    public void crearPersona(Level level, String name, int age);
    //enviar al laboratorio significa poner en una cola la muestra
    public void extraerMuestra(String idPersona, String idMuestra, String idMedico, String idLab);
    //sacar de la cola una muestra y setear el comentario y el resultado y meterla en la cola del usuario
    public void procesarmuestra(   Informe infome, String comentario);
    //crear un laboratorio quiere decir ponerlo en el array de labs
    public void crearLab(String name);
    //devolver lista de muestras del usuario
    public List<Muestra> listaMuestrasUsuario(String idUsuario);


    //Operaciones complementarias
    public Paciente getPersonabyId(String id ) throws Exception;
    public Muestra getMuestrabyId(String id ) throws Exception;
    public Lab getLabbyId( String id ) ;


}
