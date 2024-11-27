package br.com.hexburger.cliente.interfaceadapters.controller;

import br.com.hexburger.cliente.application.usecase.BuscarClienteUseCase;
import br.com.hexburger.cliente.application.usecase.CriarClienteUseCase;
import br.com.hexburger.cliente.dominio.entidade.Cliente;
import br.com.hexburger.cliente.interfaceadapters.dto.ClienteDTO;
import br.com.hexburger.cliente.interfaceadapters.gateway.ClienteGatewayJPA;
import br.com.hexburger.cliente.interfaceadapters.presenter.ClientePresenter;
import br.com.hexburger.cliente.interfaceadapters.repositorioadaptador.ClienteRepositorioAdaptador;

public class ClienteController {

    public ClienteDTO criarCliente(ClienteDTO clienteDTO, ClienteRepositorioAdaptador repositorio) {
        CriarClienteUseCase useCase = new CriarClienteUseCase(new ClienteGatewayJPA(repositorio));
        return ClientePresenter.toDTO(useCase.criarCliente(new Cliente(clienteDTO.getCpf(),
                clienteDTO.getNome(), clienteDTO.getEmail())));
    }

    public ClienteDTO buscarCliente(String cpf, ClienteRepositorioAdaptador repositorio) {
        BuscarClienteUseCase useCase = new BuscarClienteUseCase(new ClienteGatewayJPA(repositorio));
        return ClientePresenter.toDTO(useCase.buscarCliente(cpf));
    }
}
