package br.com.fiap3esa.checkpoint2.controller;

import br.com.fiap3esa.checkpoint2.model.Pedido;
import br.com.fiap3esa.checkpoint2.service.PedidoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Object> criarPedido(@Valid @RequestBody Pedido pedido) {
        try {
            Pedido pedidoSalvo = pedidoService.criarPedido(pedido);
            return new ResponseEntity<>(pedidoSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarTodosPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPedido(@PathVariable Long id){
        try {
            Pedido pedidoBuscado = pedidoService.buscarPedidoPorId(id);
            return new ResponseEntity<>(pedidoBuscado, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarPedido(@PathVariable Long id, @Valid @RequestBody Pedido pedido) {
        try {
            Pedido pedidoAtualizado = pedidoService.atualizarPedido(id, pedido);
            return new ResponseEntity<>(pedidoAtualizado, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarPedido(@PathVariable Long id) {
        try {
            pedidoService.deletarPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
