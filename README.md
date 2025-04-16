# Avaliação CRUD API

API REST desenvolvida em Java com Spring Boot

---

## Endpoints

---

##  Pessoa

| Método | Rota               | Descrição                                  |
|:--------|:-------------------|:--------------------------------------------|
| `GET`    | `/pessoa`            | Retorna a lista de todas as pessoas cadastradas. |
| `GET`    | `/pessoa/{id}`       | Retorna uma pessoa pelo `id`.                      |
| `POST`   | `/pessoa`            | Cadastra uma nova pessoa.                         |
| `PUT`    | `/pessoa/{id}`       | Atualiza os dados de uma pessoa existente.        |
| `DELETE` | `/pessoa/{id}`       | Remove uma pessoa pelo `id`.                      |

###  Exemplo de JSON para criar/atualizar Pessoa:

```json
{
  "cpf": "12345678900",
  "idade": 30
}
```
<br>

## Trabalho
| Método | Rota               | Descrição                                  |
|:--------|:-------------------|:--------------------------------------------|
| `GET`    | `/trabalho`            | Retorna a lista de todos os trabalhos cadastradas. |
| `GET`    | `/trabalho/{id}`       | Retorna uma trabalho pelo `id`.                      |
| `POST`   | `/trabalho`            | Cadastra um novo trabalho.                         |
| `PUT`    | `/trabalho/{id}`       | Atualiza os dados de um trabalho existente.        |
| `DELETE` | `/trabalho/{id}`       | Remove um trabalho pelo `id`.                      |
| `PUT`    | `/trabalho/adicionarPessoa/{trabalhoId}/{pessoaId}`       | Adiciona uma pessoa existente a um trabalho.        |
| `GET`    | `/trabalho/{id}/pessoas`       | Retorna a lista de pessoas associadas a um determinado trabalho.        |

###  Exemplo de JSON para criar/atualizar Trabalho:

```json
{
  "nome": "Obra Residencial",
  "endereco": "Rua das Acácias, 123"
}
```
