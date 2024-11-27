package br.com.hexburger.cliente.util;

import br.com.hexburger.cliente.dominio.entidade.Cliente;
import br.com.hexburger.cliente.framework.entidade.ECliente;

public class ClienteTestUtils {

    public static Cliente criarCliente() {

        return new Cliente("12345678900", "Tommy", "tommy@email.com");

    }

    public static ECliente criarECliente() {

        return new ECliente("12345678900", "Tommy", "tommy@email.com");

    }

}
