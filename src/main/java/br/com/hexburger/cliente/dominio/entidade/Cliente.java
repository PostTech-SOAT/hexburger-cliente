package br.com.hexburger.cliente.dominio.entidade;

public class Cliente {

    private final String cpf;

    private final String nome;

    private final String email;

    public Cliente(String cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        validaCliente();
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    private void validaCliente() {
        if (!validaCpf() || !validaNome() || !validaEmail()) {
            throw new IllegalArgumentException("Cliente inválido");
        }
    }

    private boolean validaCpf() {
        return cpf != null && cpf.length() == 11;
    }

    private boolean validaNome() {
        return nome != null && !nome.isEmpty();
    }

    private boolean validaEmail() {
        return email != null && email.contains("@") && email.contains(".");
    }

}
