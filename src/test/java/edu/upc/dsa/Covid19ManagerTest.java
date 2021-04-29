package edu.upc.dsa;

import edu.upc.dsa.models.Informe;
import edu.upc.dsa.models.Level;
import edu.upc.dsa.models.Muestra;
import edu.upc.dsa.models.Paciente;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class Covid19ManagerTest {

    private static Covid19ManagerImpl manager;


    @BeforeEach
    public void setUp(){
       manager = Covid19ManagerImpl.getInstance();
       manager.setUpResources();
    }

    @Test
    public void crearPacienteTest(){
        manager.crearPersona( Level.A, "Bruno", 12);
        assertEquals(3,manager.getPersonas().size());
    }

    @Test
    public void procesarMuestraTest(){
        Muestra m = manager.getColaMuestras().peek();
        System.out.println(m);
        Paciente p = manager.getPersonabyId(m.getPacienteId());
        manager.procesarmuestra(Informe.POSITIVO,"Beber mucho líquido");
        assertEquals(1,manager.getColaMuestras().size());
        assertEquals(Informe.POSITIVO,manager.getMuestrabyId(m.getId()).getInforme());
        assertEquals(1,manager.getPersonabyId(p.getId()).getMuestras().size());


    }


    @AfterEach
    public void tearDown(){
        manager.tearDownResources();
        System.out.println(manager.getColaMuestras());
    }
}
