package br.com.hexburger.cliente.application.usecase;

import br.com.hexburger.cliente.application.interfacegateway.ClienteGateway;
import br.com.hexburger.cliente.application.util.exception.ConflictException;
import br.com.hexburger.cliente.dominio.entidade.Cliente;

public class CriarClienteUseCase {

    private final ClienteGateway clienteGateway;

    public CriarClienteUseCase(ClienteGateway criarClienteGateway) {
        this.clienteGateway = criarClienteGateway;
    }

    public Cliente criarCliente(Cliente cliente) {
        if (clienteGateway.buscarCliente(cliente.getCpf()).isPresent()) {
            throw new ConflictException("Cliente já cadastrado");
        }
        return clienteGateway.criarCliente(cliente);
    }
}
