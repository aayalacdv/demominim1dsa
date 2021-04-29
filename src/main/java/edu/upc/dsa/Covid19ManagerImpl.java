package edu.upc.dsa;

import edu.upc.dsa.models.*;
import edu.upc.dsa.utils.RandomUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Covid19ManagerImpl implements Covid19Manager{


    private HashMap<String, Paciente> personas;
    private HashMap<String, Muestra> muestras;
    private Lab[] labs;
    private Queue<Muestra> colaMuestras;
    private int index;

    final static Logger logger = Logger.getLogger(Covid19ManagerImpl.class);
    private static Covid19ManagerImpl covidManager;

    public static Covid19ManagerImpl getInstance(){
        if(covidManager == null ){
            covidManager = new Covid19ManagerImpl();
        }
        return covidManager;
    }

    private Covid19ManagerImpl (){
        this.personas = new HashMap<>();
        this.colaMuestras = new LinkedList<>();
        this.labs = new Lab[100];
        this.muestras = new HashMap<>();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public HashMap<String, Paciente> getPersonas() {
        return personas;
    }

    public void setPersonas(HashMap<String, Paciente> personas) {
        this.personas = personas;
    }

    public HashMap<String, Muestra> getMuestras() {
        return muestras;
    }

    public void setMuestras(HashMap<String, Muestra> muestras) {
        this.muestras = muestras;
    }

    public Lab[] getLabs() {
        return labs;
    }

    public void setLabs(Lab[] labs) {
        this.labs = labs;
    }

    public Queue<Muestra> getColaMuestras() {
        return colaMuestras;
    }

    public void setColaMuestras(Queue<Muestra> colaMuestras) {
        this.colaMuestras = colaMuestras;
    }

    @Override
    public void crearPersona(Level level, String name, int age) {
       logger.info("creando persona");
       logger.debug(this.personas);
       Paciente p = new Paciente(name,age,level);
       this.personas.put(p.getId(),p);
       logger.info("persona creada");
       logger.debug(this.personas);
    }

    @Override
    public void extraerMuestra(String idPersona,  String idMedico, String idLab, String fecha) {
        logger.info("Procesando muestra");
        logger.debug(this.muestras);
        logger.debug(this.colaMuestras);
        Muestra muestra = new Muestra(idPersona, idMedico, fecha, idLab);
        this.muestras.put(muestra.getId(), muestra);
        this.colaMuestras.add(muestra);
        logger.info("Muestra procesada");
        logger.debug(this.muestras);
        logger.debug(this.colaMuestras);
    }

    @Override
    public void procesarmuestra( Informe infome, String comentario) {

        logger.info("procesando muestras ");
        logger.debug(this.colaMuestras);
        logger.debug(this.personas);
        logger.debug(this.muestras);
        //encontrar la muestra en el  hasmap
        Muestra m = this.colaMuestras.peek();
        //actualizar informe
        m.setComentario(comentario);
        m.setInforme(infome);
        //añadir a lista de usuario
        Paciente p = getPersonabyId(m.getPacienteId());
        p.getMuestras().add(m);
        //actualizamos la lista de pacientes y de muestras
        this.personas.put(p.getId(),p);
        this.muestras.put(m.getId(),m);
        //sacar de la cola
        this.colaMuestras.poll();
        logger.info("muestra procesada");
        logger.debug(this.colaMuestras);
        logger.debug(this.personas);
        logger.debug(this.muestras);
    }

    @Override
    public void crearLab(String name) {
       logger.info("INSERTANDO ELEMENTO");
       logger.debug(this.labs);
        Lab l = new Lab(name);
        this.labs[index] = l;
        index++;
        logger.info("ELEMENTO INSERTADO");
        logger.debug(this.labs);
    }

    @Override
    public List<Muestra> listaMuestrasUsuario(String idUsuario) {
        Paciente p = this.personas.get(idUsuario);
        return p.getMuestras();
    }

    @Override
    public Paciente getPersonabyId(String id) throws NullPointerException {
        return this.personas.get(id);
    }

    @Override
    public Muestra getMuestrabyId(String id) throws NullPointerException{
        return this.muestras.get(id);
    }

    @Override
    public Lab getLabbyId(String id )  {
        Lab lab = new Lab("Test");
       for(Lab l : this.labs ) {
          if(l.getId().equals(id)) {
              lab.setId(l.getId());
              lab.setName(l.getName());
          }

       }
       return lab;
    }

    public void
    setUpResources(){

        //Creamos usuario
        Paciente p1 = new Paciente("axel", 19,Level.A);
        Paciente p2 = new Paciente("edu", 20, Level.D);

        this.personas.put(p1.getId(),p1);
        this.personas.put(p2.getId(), p2);

        //creamos labs
        Lab l1 = new Lab("M");
        Lab l2 = new Lab("V");


        // Creamos muestras
        Muestra m1 = new Muestra(p1.getId(), RandomUtils.getId(),"2/2/99",l1.getId());
        Muestra m2 = new Muestra(p2.getId(), RandomUtils.getId(), "9/9/55", l2.getId());

        this.muestras.put(m1.getId(),m1);
        this.muestras.put(m2.getId(),m2);

        //llenamos lista de labs
        this.labs[0] = l1;
        this.labs[1] = l2;

        //llenamos cola de mmuestras
        this.colaMuestras.add(m1);
        this.colaMuestras.add(m2);

        setIndex(2);

    }
    public void tearDownResources(){
        setColaMuestras(new LinkedList<Muestra>());
        setLabs(new Lab[100]);
        setMuestras(new HashMap<String,Muestra>());
        setPersonas(new HashMap<String,Paciente>());
    }
}
