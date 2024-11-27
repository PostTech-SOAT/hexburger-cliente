package br.com.hexburger.cliente.framework.api;

import br.com.hexburger.cliente.framework.repository.ClienteRepositorioImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
class ClienteAPIIT {

    private MockMvc mockMvc;

    @Autowired
    private ClienteRepositorioImpl repositorio;

    @BeforeEach
    void setUp() {

        ClienteAPI clienteAPI = new ClienteAPI(repositorio);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteAPI)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();

    }

    @Test
    void deveCriarCliente() throws Exception {

        mockMvc.perform(post("/v1/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cpf\": \"12348888901\", \"nome\": \"João da Silva\", \"email\": \"joao@email.com\"}"))
                .andExpect(status().isOk());

    }

    @Test
    void deveLancarExcecaoAoCriarClienteInvalido() throws Exception {

        mockMvc.perform(post("/v1/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cpf\": \"0\", \"nome\": \"João da Silva\", \"email\": \"joao@email.com\"}"))
                .andExpect(status().isBadRequest());

    }

    @Test
    void deveLancarExcecaoAoCriarClienteJaExistente() throws Exception {

        mockMvc.perform(post("/v1/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cpf\": \"12345678900\", \"nome\": \"João da Silva\", \"email\": \"joao@email.com\"}"))
                .andExpect(status().isConflict());

    }

    @Test
    void deveBuscarCliente() throws Exception {

        mockMvc.perform(get("/v1/cliente/{cpf}", "12345678900"))
                .andExpect(status().isOk());

    }

    @Test
    void deveLancarExceocaoAoBuscarClienteInexistente() throws Exception {

        mockMvc.perform(get("/v1/cliente/{cpf}", "12377778900"))
                .andExpect(status().isNotFound());

    }

}
