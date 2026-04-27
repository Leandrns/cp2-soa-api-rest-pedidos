package br.com.fiap3esa.checkpoint2.model;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.PrePersist;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O nome do cliente é obrigatório.")
    private String clienteNome;

    private LocalDate dataPedido;

    @DecimalMin(value = "0.0", message = "O valor total não pode ser negativo.")
    @Positive
    private BigDecimal valorTotal;

    @PrePersist
    public void prePersist(){
        if (this.dataPedido == null){
            this.dataPedido = LocalDate.now();
        }
    }
}
