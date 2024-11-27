package br.com.hexburger.cliente.interfaceadapters.presenter;

import br.com.hexburger.cliente.dominio.entidade.Cliente;
import br.com.hexburger.cliente.interfaceadapters.dto.ClienteDTO;

public class ClientePresenter {

    public static ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getCpf(), cliente.getNome(), cliente.getEmail());
    }
}
