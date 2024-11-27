package br.com.hexburger.cliente.interfaceadapters.repositorioadaptador;

import br.com.hexburger.cliente.interfaceadapters.entidadeadaptor.EClienteInterface;

import java.util.Optional;

public interface ClienteRepositorioAdaptador {

    EClienteInterface criarCliente(String cpf, String nome, String email);
    Optional<? extends EClienteInterface> buscarCliente(String cpf);

}
