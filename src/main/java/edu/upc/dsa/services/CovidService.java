package edu.upc.dsa.services;

import edu.upc.dsa.Covid19ManagerImpl;
import edu.upc.dsa.models.Level;
import edu.upc.dsa.models.Paciente;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/covid", description = "ENDPOINT COVID MANAGER SERVICE")
@Path("/covid")
public class CovidService {

    private static Covid19ManagerImpl manager;
    public CovidService(){
       manager = Covid19ManagerImpl.getInstance();
       if(manager.getPersonas().size() == 0){
           manager.setUpResources();
       }
    }

    @POST
    @Path("/persona")
    @ApiOperation(value = "create a new Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Paciente.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Consumes(MediaType.APPLICATION_JSON)

    public Response newTrack( Paciente p) {
        if(p.getAge() == 0 || p.getLevel() != Level.A || p.getName().isEmpty() ) return Response.status(500).entity("Inoformación erronea").build();
        manager.crearPersona(p.getLevel(),p.getName(),p.getAge());
        return Response.status(201).entity("persona creada").build();
    }
}
