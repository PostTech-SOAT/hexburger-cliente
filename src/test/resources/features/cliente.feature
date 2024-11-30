# language: pt

Funcionalidade: API de Cliente Para gerenciar clientes, a API deve permitir criação, busca e validação de erros

  Cenário: Criar um cliente com sucesso
    Dado o payload de cliente válido
    Quando o cliente for criado
    Então a resposta deve ter o status 200 OK

  Cenário: Criar um cliente com CPF inválido
    Dado o payload de cliente com CPF inválido
    Quando o cliente for criado
    Então a resposta deve ter o status 400 Bad Request

  Cenário: Criar um cliente já existente
    Dado um cliente já existente no sistema
    E o payload do cliente existente
    Quando o cliente for criado
    Então a resposta deve ter o status 409 Conflict

  Cenário: Buscar um cliente existente
    Dado um cliente já existente no sistema
    Quando o cliente for buscado pelo CPF
    Então a resposta deve ter o status 200 OK

  Cenário: Buscar um cliente inexistente
    Quando um cliente inexistente for buscado pelo CPF
    Então a resposta deve ter o status 404 Not Found