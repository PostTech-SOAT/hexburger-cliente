package br.com.hexburger.cliente.framework.api;


import br.com.hexburger.cliente.application.util.exception.ConflictException;
import br.com.hexburger.cliente.application.util.exception.ResourceNotFoundException;
import br.com.hexburger.cliente.framework.repository.ClienteRepositorioImpl;
import br.com.hexburger.cliente.interfaceadapters.controller.ClienteController;
import br.com.hexburger.cliente.interfaceadapters.dto.ClienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

@RestController
@Tag(name = "Cliente")
@RequiredArgsConstructor
@RequestMapping(value = "/v1/cliente")
public class ClienteAPI {

    private final ClienteRepositorioImpl repositorio;

    @PostMapping
    @Operation(summary = "Criar um cliente")
    public ResponseEntity<Object> criarCliente(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto de exemplo para criar Cliente", required = true, content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"cpf\": \"12345678901\", \"nome\": \"João da Silva\", \"email\": \"joao@email.com\"}")))
                                               ClienteDTO clienteDTO) {
        try {
            ClienteController controller = new ClienteController();
            return ResponseEntity.ok(controller.criarCliente(clienteDTO, repositorio));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
        } catch (ConflictException e) {
            return new ResponseEntity<>(e.getMessage(), CONFLICT);
        }
    }

    @GetMapping("/{cpf}")//
    @Operation(summary = "Buscar um cliente por CPF")
    public ResponseEntity<ClienteDTO> buscarCliente(@PathVariable @Parameter(description = "CPF do cliente", required = true, schema = @Schema(type = "string", example = "12345678901")) String cpf) {
        try {
            ClienteController controller = new ClienteController();
            return ResponseEntity.ok(controller.buscarCliente(cpf, repositorio));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
