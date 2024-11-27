package br.com.hexburger.cliente.framework.repository;

import br.com.hexburger.cliente.framework.entidade.ECliente;
import br.com.hexburger.cliente.interfaceadapters.entidadeadaptor.EClienteInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static br.com.hexburger.cliente.util.ClienteTestUtils.criarECliente;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ClienteRepositorioImplIT {

    @Autowired
    private ClienteRepository repository;

    private ClienteRepositorioImpl clienteRepositorio;

    @BeforeEach
    void setup() {

        clienteRepositorio = new ClienteRepositorioImpl(repository);

    }

    @Test
    void deveCriarCliente() {

        ECliente cliente = criarECliente();

        EClienteInterface clienteCriado = clienteRepositorio.criarCliente(cliente.getCpf(), cliente.getNome(), cliente.getEmail());

        assertThat(clienteCriado, is(notNullValue()));
        assertThat(clienteCriado.getCpf(), is(equalTo(cliente.getCpf())));
        assertThat(clienteCriado.getNome(), is(equalTo(cliente.getNome())));
        assertThat(clienteCriado.getEmail(), is(equalTo(cliente.getEmail())));

    }

    @Test
    void deveBuscarCliente() {

        ECliente cliente = criarECliente();

        EClienteInterface clienteObtido = clienteRepositorio.buscarCliente(cliente.getCpf()).orElse(null);

        assertThat(clienteObtido, is(notNullValue()));
        assertThat(clienteObtido.getCpf(), is(equalTo(cliente.getCpf())));
        assertThat(clienteObtido.getNome(), is(equalTo(cliente.getNome())));
        assertThat(clienteObtido.getEmail(), is(equalTo(cliente.getEmail())));

    }

}
