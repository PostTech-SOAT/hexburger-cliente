package br.com.hexburger.cliente.application.usecase;

import br.com.hexburger.cliente.application.interfacegateway.ClienteGateway;
import br.com.hexburger.cliente.application.util.exception.ConflictException;
import br.com.hexburger.cliente.dominio.entidade.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CriarClienteUseCaseTest {


    private CriarClienteUseCase useCase;

    @Mock
    private ClienteGateway clienteGateway;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {

        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new CriarClienteUseCase(clienteGateway);

    }

    @Test
    void deveCriarCliente() {

        Cliente cliente = new Cliente("12345678988", "Tommy", "tommy@email.com");

        when(clienteGateway.buscarCliente(any(String.class))).thenReturn(Optional.empty());
        when(clienteGateway.criarCliente(any(Cliente.class))).thenReturn(cliente);

        Cliente clienteCriado = useCase.criarCliente(cliente);

        assertThat(clienteCriado, is(notNullValue()));
        assertThat(cliente.getCpf(), is(equalTo(clienteCriado.getCpf())));
        assertThat(cliente.getNome(), is(equalTo(clienteCriado.getNome())));
        assertThat(cliente.getEmail(), is(equalTo(clienteCriado.getEmail())));

    }

    @Test
    void deveLancarExcecaoAoCriarClienteComCpfExistente() {

        Cliente cliente = new Cliente("45678912305", "Gabriel", "gabriel@email.com");

        when(clienteGateway.buscarCliente(any(String.class))).thenReturn(Optional.of(cliente));
        when(clienteGateway.criarCliente(any(Cliente.class))).thenReturn(cliente);

        assertThrows(ConflictException.class, () -> useCase.criarCliente(cliente));

    }

}
