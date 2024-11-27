package br.com.hexburger.cliente.interfaceadapters.gateway;

import br.com.hexburger.cliente.application.interfacegateway.ClienteGateway;
import br.com.hexburger.cliente.dominio.entidade.Cliente;
import br.com.hexburger.cliente.interfaceadapters.entidadeadaptor.EClienteInterface;
import br.com.hexburger.cliente.interfaceadapters.repositorioadaptador.ClienteRepositorioAdaptador;

import java.util.Optional;

public class ClienteGatewayJPA implements ClienteGateway {

    private final ClienteRepositorioAdaptador repository;

    public ClienteGatewayJPA(ClienteRepositorioAdaptador repository) {
        this.repository = repository;
    }

    @Override
    public Cliente criarCliente(Cliente cliente) {
        EClienteInterface eClienteInterface = repository.criarCliente(cliente.getCpf(), cliente.getNome(), cliente.getEmail());
        return new Cliente(eClienteInterface.getCpf(), eClienteInterface.getNome(), eClienteInterface.getEmail());
    }

    @Override
    public Optional<Cliente> buscarCliente(String cpf) {
        return repository.buscarCliente(cpf).map(eClienteInterface -> new Cliente(eClienteInterface.getCpf(), eClienteInterface.getNome(), eClienteInterface.getEmail()));
    }

}
