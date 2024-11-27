package br.com.hexburger.cliente.application.usecase;

import br.com.hexburger.cliente.application.interfacegateway.ClienteGateway;
import br.com.hexburger.cliente.application.util.exception.ConflictException;
import br.com.hexburger.cliente.dominio.entidade.Cliente;
import br.com.hexburger.cliente.framework.repository.ClienteRepositorioImpl;
import br.com.hexburger.cliente.framework.repository.ClienteRepository;
import br.com.hexburger.cliente.interfaceadapters.gateway.ClienteGatewayJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class CriarClienteUseCaseIT {

    private CriarClienteUseCase useCase;

    @Autowired
    private ClienteRepository repository;

    @BeforeEach
    void setup() {

        ClienteGateway clienteGateway = new ClienteGatewayJPA(new ClienteRepositorioImpl(repository));
        useCase = new CriarClienteUseCase(clienteGateway);

    }

    @Test
    void deveCriarCliente() {

        Cliente cliente = new Cliente("12345678988", "Tommy", "tommy@email.com");

        Cliente clienteCriado = useCase.criarCliente(cliente);

        assertThat(clienteCriado, is(notNullValue()));
        assertThat(cliente.getCpf(), is(equalTo(clienteCriado.getCpf())));
        assertThat(cliente.getNome(), is(equalTo(clienteCriado.getNome())));
        assertThat(cliente.getEmail(), is(equalTo(clienteCriado.getEmail())));

    }

    @Test
    void deveLancarExcecaoAoCriarClienteComCpfExistente() {

        Cliente cliente = new Cliente("45678912305", "Gabriel", "gabriel@email.com");

        assertThrows(ConflictException.class, () -> useCase.criarCliente(cliente));

    }

}
