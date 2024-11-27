package br.com.hexburger.cliente.application.usecase;

import br.com.hexburger.cliente.application.interfacegateway.ClienteGateway;
import br.com.hexburger.cliente.application.util.exception.ResourceNotFoundException;
import br.com.hexburger.cliente.dominio.entidade.Cliente;
import br.com.hexburger.cliente.framework.repository.ClienteRepositorioImpl;
import br.com.hexburger.cliente.framework.repository.ClienteRepository;
import br.com.hexburger.cliente.interfaceadapters.gateway.ClienteGatewayJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class BuscarClienteUseCaseIT {

    private BuscarClienteUseCase useCase;

    @Autowired
    private ClienteRepository repository;

    @BeforeEach
    void setup() {

        ClienteRepositorioImpl clienteRepositorio = new ClienteRepositorioImpl(repository);
        ClienteGateway clienteGateway = new ClienteGatewayJPA(clienteRepositorio);
        useCase = new BuscarClienteUseCase(clienteGateway);

    }

    @Test
    void deveBuscarCliente() {

        Cliente resultado = useCase.buscarCliente("45678912305");

        assertThat(resultado.getCpf(), equalTo("45678912305"));
        assertThat(resultado.getNome(), equalTo("Gabriel"));
        assertThat(resultado.getEmail(), equalTo("gabriel@email.com"));

    }

    @Test
    void deveLancarExcecaoAoBuscarClienteComCpfInexistente() {

        assertThrows(ResourceNotFoundException.class, () -> useCase.buscarCliente("98765432106"));

    }

    @Test
    void deveLancarExcecaoAoBuscarClienteComCpfNulo() { //TODO: Isso deveria ser assim?

        assertThrows(InvalidDataAccessApiUsageException.class, () -> useCase.buscarCliente(null));

    }

}
