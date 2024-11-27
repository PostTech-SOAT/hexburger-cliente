package br.com.hexburger.cliente.application.usecase;

import br.com.hexburger.cliente.application.interfacegateway.ClienteGateway;
import br.com.hexburger.cliente.application.util.exception.ResourceNotFoundException;
import br.com.hexburger.cliente.dominio.entidade.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BuscarClienteUseCaseTest {

    private BuscarClienteUseCase useCase;

    @Mock
    private ClienteGateway clienteGateway;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {

        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new BuscarClienteUseCase(clienteGateway);

    }

    @Test
    void deveBuscarCliente() {

        String cpf = "12345678988";

        Cliente cliente = new Cliente(cpf, "Tommy", "tommy@email.com");

        when(clienteGateway.buscarCliente(any(String.class))).thenReturn(Optional.of(cliente));

        Cliente clienteBuscado = useCase.buscarCliente(cpf);

        assertThat(clienteBuscado.getCpf(), equalTo(cpf));
        assertThat(clienteBuscado.getNome(), equalTo("Tommy"));
        assertThat(clienteBuscado.getEmail(), equalTo("tommy@email.com"));

    }

    @Test
    void deveLancarExcecaoAoBuscarClienteComCpfInexistente() {

        when(clienteGateway.buscarCliente(any(String.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> useCase.buscarCliente("98765432106"));

    }

    @Test
    void deveLancarExcecaoAoBuscarClienteComCpfNulo() {

        when(clienteGateway.buscarCliente(any(String.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> useCase.buscarCliente(null));

    }

}
