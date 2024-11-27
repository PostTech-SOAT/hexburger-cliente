package br.com.hexburger.cliente.framework.entidade;

import br.com.hexburger.cliente.interfaceadapters.entidadeadaptor.EClienteInterface;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cliente")
public class ECliente implements Serializable, EClienteInterface {

    @Id
    private String cpf;

    private String nome;

    private String email;

}