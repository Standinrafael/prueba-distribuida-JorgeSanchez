package com.distribuida.orden.compra.cliente;

import com.distribuida.orden.compra.dtos.ClienteDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;


@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

@RegisterRestClient(configKey = "ClienteRestClient")

public interface ClienteRestClient {

    @GET
    public List<ClienteDto> findAll();

    @GET
    @Path("/{id}")
    public ClienteDto findById(@PathParam("id")Integer id);
}
