package br.com.hexburger.cliente.framework.repository;

import br.com.hexburger.cliente.framework.entidade.ECliente;
import br.com.hexburger.cliente.interfaceadapters.entidadeadaptor.EClienteInterface;
import br.com.hexburger.cliente.interfaceadapters.repositorioadaptador.ClienteRepositorioAdaptador;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ClienteRepositorioImpl implements ClienteRepositorioAdaptador {

    private final ClienteRepository repository;

    @Override
    public EClienteInterface criarCliente(String cpf, String nome, String email) {
        return repository.save(new ECliente(cpf, nome, email));
    }

    @Override
    public Optional<? extends EClienteInterface> buscarCliente(String cpf) {
        return repository.findById(cpf);
    }
}
