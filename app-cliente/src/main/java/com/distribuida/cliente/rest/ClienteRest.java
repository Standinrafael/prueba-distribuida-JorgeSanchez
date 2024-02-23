package com.distribuida.cliente.rest;

import com.distribuida.cliente.db.Cliente;
import com.distribuida.cliente.repo.ClienteRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
@Path("/cliente")
public class ClienteRest {

    @Inject
    ClienteRepository rep;

    @GET
    public List<Cliente> findAll() {
        return rep.findAll().list();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        var cliente = rep.findByIdOptional(id);
        if (cliente.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(cliente.get()).build();
    }

    @POST
    public Response create(Cliente cliente) {
        rep.persist(cliente);

        return Response.status(Response.Status.CREATED.getStatusCode(), "cliente creado").build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Cliente clienteObj) {
        Cliente cliente1 = rep.findById(id);
        cliente1.setNombre(clienteObj.getNombre());
        cliente1.setApellido(clienteObj.getApellido());
        cliente1.setDireccion(clienteObj.getDireccion());

        return Response.ok().build();
    }


    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        rep.deleteById(id);

        return Response.ok( )
                .build();
    }


}
