package br.com.hexburger.cliente.application.usecase;

import br.com.hexburger.cliente.application.interfacegateway.ClienteGateway;
import br.com.hexburger.cliente.application.util.exception.ResourceNotFoundException;
import br.com.hexburger.cliente.dominio.entidade.Cliente;

public class BuscarClienteUseCase {

    private final ClienteGateway clienteGateway;

    public BuscarClienteUseCase(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public Cliente buscarCliente(String cpf) {
        return clienteGateway.buscarCliente(cpf).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }
}
