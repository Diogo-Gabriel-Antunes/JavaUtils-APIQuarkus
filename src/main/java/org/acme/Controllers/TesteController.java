package org.acme.Controllers;


import io.quarkus.vertx.web.Body;
import org.acme.ModelsByTest.ClassDTO;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

public class TesteController {


    public Response create(@Body ClassDTO dto){
        return Response.accepted().build();
    }
}
