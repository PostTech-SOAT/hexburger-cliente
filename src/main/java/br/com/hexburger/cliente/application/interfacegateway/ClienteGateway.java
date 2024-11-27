package br.com.hexburger.cliente.application.interfacegateway;

import br.com.hexburger.cliente.dominio.entidade.Cliente;

import java.util.Optional;

public interface ClienteGateway {

    Cliente criarCliente(Cliente cliente);
    Optional<Cliente> buscarCliente(String cpf);

}
