package br.com.fiap3esa.checkpoint2.repository;

import br.com.fiap3esa.checkpoint2.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
