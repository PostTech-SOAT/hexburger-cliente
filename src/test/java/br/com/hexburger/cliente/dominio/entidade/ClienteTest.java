package br.com.hexburger.cliente.dominio.entidade;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClienteTest {

    @Test
    void deveCriarCliente() {

        Cliente cliente = new Cliente("12345678900", "Tommy", "tommy@email.com");

        assertThat(cliente, is(notNullValue()));
        assertThat(cliente.getCpf(), is(equalTo("12345678900")));
        assertThat(cliente.getNome(), is(equalTo("Tommy")));
        assertThat(cliente.getEmail(), is(equalTo("tommy@email.com")));

    }

    @Test
    void deveLancarExcecaoAoCriarClienteComCpfInvalido() {

        assertThrows(IllegalArgumentException.class, () -> new Cliente("456789123", "Gabriel", "gabriel@email.com"));
        assertThrows(IllegalArgumentException.class, () -> new Cliente(null, "Gabriel", "gabriel@email.com"));

    }

    @Test
    void deveLancarExcecaoAoCriarClienteComNomeInvalido() {

        assertThrows(IllegalArgumentException.class, () -> new Cliente("45678912300", "", "gabriel@email.com"));
        assertThrows(IllegalArgumentException.class, () -> new Cliente("45678912300", null, "gabriel@email.com"));

    }

    @Test
    void deveLancarExcecaoAoCriarClienteComEmailInvalido() {

        assertThrows(IllegalArgumentException.class, () -> new Cliente("45678912300", "Gabriel", ""));
        assertThrows(IllegalArgumentException.class, () -> new Cliente("45678912300", "Gabriel", "gabrielemail.com"));
        assertThrows(IllegalArgumentException.class, () -> new Cliente("45678912300", "Gabriel", "gabrielemailcom"));
        assertThrows(IllegalArgumentException.class, () -> new Cliente("45678912300", "Gabriel", null));

    }

}
