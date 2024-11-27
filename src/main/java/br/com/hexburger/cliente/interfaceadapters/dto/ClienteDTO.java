package br.com.hexburger.cliente.interfaceadapters.dto;

public class ClienteDTO {

    private String cpf;

    private String nome;

    private String email;

    public ClienteDTO(String cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
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

}
