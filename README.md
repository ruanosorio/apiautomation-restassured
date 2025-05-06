# Projeto de Automação de Testes de API com RestAssured

##  Descrição

Este projeto tem como objetivo realizar testes automatizados de backend na API pública [ReqRes](https://reqres.in/), utilizando uma stack robusta em Java e ferramentas populares do ecossistema de QA.

## Tecnologias Utilizadas

- **Java:** Linguagem principal do projeto.
- **RestAssured:** Framework para testes de APIs RESTful.
- **TestNG:** Framework de execução e organização dos testes.
- **Lombok:** Reduz o código boilerplate com anotações úteis.
- **Allure Report:** Geração de relatórios elegantes e interativos.

## 📁 Estrutura do Projeto
- src/
- ├── main/
- │   ├── java/
- │   │   ├── clients/         # Requisições à API
- │   │   ├── dataprovider/    # Fornecedores de dados para os testes
- │   │   ├── dto/             # Objetos de transferência de dados (ex: UserDTO)
- │   │   ├── spec/            # Especificações e configurações do RestAssured
- │   │   └── utils/           # Classe base e utilitários
- │   └── resources/
- ├── test/
- │   ├── java/
- │   │   ├── contract/        # Testes de contrato (validação de schema)
- │   │   ├── funcional/       # Testes funcionais da API
- │   │   └── SmokeTest.java   # Testes de fumaça (smoke test)
- │   └── resources/
- │       └── json_schemas/    # Schemas JSON para validação


## Execução de todos os cenários de testes:

```
gradle test --info
```

* Exemplo para execução de uma classe de testes específica:

```
gradle test --tests "SmokeTest"
```

## Para rodar e gerar o relatório:

```
./gradlew clean test allureReport
```

* E para visualizar:

```
./gradlew allureServe
```
