package com.distribuida.orden.compra.dtos;

import com.distribuida.orden.compra.db.OrdenCompra;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class OrdenCompraDto {

    private Integer id;
    private String productoId;
    private BigDecimal Precio;
    private Integer clienteId;

    public static OrdenCompraDto from (OrdenCompra obj){
        OrdenCompraDto ret= new OrdenCompraDto();
        ret.setId((obj.getId()));
        ret.setPrecio(obj.getPrecio());
        ret.setProductoId(obj.getProductoId());
        ret.setClienteId(obj.getClienteId());
        return ret;
    }


}
