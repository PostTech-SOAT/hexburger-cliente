package br.com.hexburger.cliente.interfaceadapters.presenter;

import br.com.hexburger.cliente.dominio.entidade.Cliente;
import br.com.hexburger.cliente.interfaceadapters.dto.ClienteDTO;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ClientePresenterTest {

    @Test
    void deveConverterDeEntidadeParaDTO() {

        Cliente cliente = new Cliente("12345678900", "Tommy", "tommy@email.com");

        ClienteDTO dto = ClientePresenter.toDTO(cliente);

        assertThat(dto.getCpf(), is(equalTo(cliente.getCpf())));
        assertThat(dto.getNome(), is(equalTo(cliente.getNome())));
        assertThat(dto.getEmail(), is(equalTo(cliente.getEmail())));

    }

}
