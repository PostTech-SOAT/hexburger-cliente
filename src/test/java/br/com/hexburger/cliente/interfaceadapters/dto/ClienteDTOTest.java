package br.com.hexburger.cliente.interfaceadapters.dto;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ClienteDTOTest {

    @Test
    void deveCriarCliente() {

        ClienteDTO cliente = new ClienteDTO("12345678900", "Tommy", "tommy@email.com");

        assertThat(cliente, is(notNullValue()));
        assertThat(cliente.getCpf(), is(equalTo("12345678900")));
        assertThat(cliente.getNome(), is(equalTo("Tommy")));
        assertThat(cliente.getEmail(), is(equalTo("tommy@email.com")));

    }

}
