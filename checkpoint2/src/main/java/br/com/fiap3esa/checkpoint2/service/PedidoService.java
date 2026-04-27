package br.com.fiap3esa.checkpoint2.service;

import br.com.fiap3esa.checkpoint2.model.Pedido;
import br.com.fiap3esa.checkpoint2.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido criarPedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodosPedidos(){
        return pedidoRepository.findAll();
    }

    public Pedido buscarPedidoPorId(Long id){
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isEmpty()) {
            throw new EntityNotFoundException("Pedido não encontrado.");
        }
        return pedidoOptional.get();
    }

    public Pedido atualizarPedido(Long id, Pedido pedido){
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isEmpty()) {
            throw new EntityNotFoundException("Pedido não encontrado.");
        }

        pedidoOptional.map(p -> {
            p.setClienteNome(pedido.getClienteNome());
            p.setValorTotal(pedido.getValorTotal());
            return p;
        });
        return pedidoRepository.save(pedidoOptional.get());
    }

    public void deletarPorId(Long id) {
        try {
            pedidoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException("Pedido não encontrado!");
        }
    }
}
