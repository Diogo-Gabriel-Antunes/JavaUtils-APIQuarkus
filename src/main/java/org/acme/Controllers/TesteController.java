package org.acme.Controllers;


import io.quarkus.vertx.web.Body;
import org.acme.models.ClassDTO;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("teste")
@RequestScoped
public class TesteController {


    @POST
    public Response create(@Body ClassDTO dto){
        return Response.accepted().build();
    }
}
