package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/big")
public class BigResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Big get() {
        return new Big();
    }
}
