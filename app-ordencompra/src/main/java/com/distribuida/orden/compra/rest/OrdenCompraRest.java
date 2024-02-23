package com.distribuida.orden.compra.rest;

import com.distribuida.orden.compra.cliente.ClienteRestClient;
import com.distribuida.orden.compra.cliente.ProductoRestClient;
import com.distribuida.orden.compra.db.OrdenCompra;
import com.distribuida.orden.compra.dtos.OrdenCompraDto;
import com.distribuida.orden.compra.repo.OrdenCompraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Path("/orden")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class OrdenCompraRest {
    @Inject
    OrdenCompraRepository repo;

    @Inject
    @RestClient
    ClienteRestClient clienteClient;


    @Inject
    @RestClient
    ProductoRestClient productoClient;

    @GET
    public List<OrdenCompraDto> findAll() {
        return repo.streamAll()
                .map(orden->{
                    var a = clienteClient.findById(orden.getClienteId());
                    var b= productoClient.findById(Integer.valueOf(orden.getProductoId()));

                    var dto = OrdenCompraDto.from(orden);

                    Integer id=a.getId();

                    String id2=b.getId().toString();

                   dto.setClienteId(id);
                   dto.setProductoId(id2);

                    return dto;
                })
                .collect(Collectors.toList());
    }


    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id")Integer id) {
        var orden = repo.findByIdOptional(id);

        if(orden.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(orden.get()).build();
    }

    @POST
    public Response create(OrdenCompra obj) {
        obj.setId(null);

        repo.persist(obj);

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")

    public Response update(@PathParam("id")Integer id, OrdenCompra obj) {

        OrdenCompra o = repo.findById(id);

        o.setClienteId(obj.getClienteId());
        o.setPrecio((obj.getPrecio()));
        o.setProductoId(obj.getProductoId());



        return Response.ok()
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Integer id) {
        repo.deleteById(id);

        return Response.ok()
                .build();
    }

}
