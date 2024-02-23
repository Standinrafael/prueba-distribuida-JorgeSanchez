package com.distribuida.orden.compra.db;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name="orden_copra")
@Data
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   @Column(name="producto_id")
    private String productoId;

    private BigDecimal precio;

    @Column(name="cliente_id")
    private Integer clienteId;
}
