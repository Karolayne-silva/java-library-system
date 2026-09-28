# 📚 Java Library System

Sistema de gerenciamento de uma biblioteca desenvolvido em **Java**, com foco na aplicação de conceitos fundamentais de Programação Orientada a Objetos, organização em camadas, tratamento de exceções e manipulação de coleções com Streams.

## 🎯 Objetivo

O projeto foi desenvolvido como prática de Java, simulando as principais operações de uma biblioteca:

* Cadastro e gerenciamento de livros
* Cadastro e gerenciamento de usuários
* Realização de empréstimos
* Controle de disponibilidade dos livros
* Controle de empréstimos ativos
* Aplicação de limite de empréstimos por usuário
* Tratamento de situações de erro através de exceções personalizadas

## 🛠️ Tecnologias

* **Java**
* **Git**
* **GitHub**

## 📁 Estrutura do projeto

```text
src/
└── main/
    └── java/
        └── ...
            ├── model/
            │   ├── Livro.java
            │   ├── Usuario.java
            │   └── Emprestimo.java
            │
            ├── service/
            │   ├── LivroService.java
            │   ├── UsuarioService.java
            │   └── EmprestimoService.java
            │
            ├── enums/
            │   ├── StatusLivro.java
            │   └── StatusEmprestimo.java
            │
            └── exception/
                ├── LivroNaoEncontradoException.java
                ├── UsuarioNaoEncontradoException.java
                ├── LivroIndisponivelException.java
                ├── EmprestimoNaoEncontradoException.java
                ├── EmprestimoJaDesenvolvidoException.java
                └── LimiteEmprestimoException.java
```

> A estrutura acima representa a organização utilizada no projeto. Os nomes podem variar de acordo com a estrutura final do repositório.

## 📖 Principais funcionalidades

### 👤 Usuários

O sistema permite:

* Cadastrar usuários
* Buscar usuário por ID
* Validar a existência do usuário antes de realizar operações

---

### 📚 Livros

O sistema permite:

* Cadastrar livros
* Buscar livros por ID
* Listar livros disponíveis
* Alterar o status de disponibilidade

Os livros possuem diferentes estados através do `enum` `StatusLivro`.

```java
public enum StatusLivro {
    DISPONIVEL,
    EMPRESTADO
}
```
---

### 📕 Empréstimos

Para realizar um empréstimo, o sistema verifica:

1. Se o usuário existe
2. Se o livro existe
3. Se o livro está disponível
4. Se o usuário atingiu o limite de empréstimos ativos

Após a realização do empréstimo:

* O empréstimo é criado
* O livro passa para o status `EMPRESTADO`
* O empréstimo é adicionado à lista de empréstimos

## 🧠 Conceitos praticados

Durante o desenvolvimento foram aplicados conceitos importantes de Java:

### Programação Orientada a Objetos

* Classes e objetos
* Encapsulamento
* Construtores
* Atributos e métodos
* Relacionamento entre objetos

### Java Collections

* `List`
* `ArrayList`
* Iteração de coleções

### Java Stream API

* `stream()`
* `filter()`
* `forEach()`
* `findFirst()`
* `count()`
* `toList()`

### Optional

Uso de `Optional` para tratar operações que podem não encontrar um objeto:

```java
Optional<Livro> livro = livroService.buscarPorId(idLivro);
```

### Enums

Utilização de enums para representar estados do sistema:

```java
StatusLivro.DISPONIVEL
StatusLivro.EMPRESTADO
```

### Exceções personalizadas

O projeto possui exceções específicas para diferentes regras de negócio, como:

```java
UsuarioNaoEncontradoException
LivroNaoEncontradoException
LivroIndisponivelException
LimiteEmprestimoException
```

### `final`

Utilização de `final` em dependências e constantes que não devem ser reatribuídas:

## 🔄 Exemplo do fluxo de empréstimo

```text
Usuário solicita empréstimo
          │
          ▼
Usuário existe?
     │          │
    não        sim
     │          │
     ▼          ▼
  Exceção    Livro existe?
                │
           ┌────┴────┐
          não       sim
           │          │
           ▼          ▼
       Exceção    Livro disponível?
                       │
                  ┌────┴────┐
                 não       sim
                  │          │
                  ▼          ▼
              Exceção    Limite atingido?
                              │
                         ┌────┴────┐
                        sim       não
                         │          │
                         ▼          ▼
                     Exceção   Criar empréstimo
                                   │
                                   ▼
                          Livro → EMPRESTADO
                                   │
                                   ▼
                         Salvar empréstimo
```

## 🚀 Como executar

### 1. Clone o repositório

```bash
git clone <URL_DO_REPOSITORIO>
```

### 2. Acesse o projeto

```bash
cd java-library-system
```

### 3. Compile e execute

Execute o projeto utilizando sua IDE Java ou através das ferramentas de build configuradas no projeto.

## 👩‍💻 Sobre o projeto

Este projeto faz parte dos meus estudos de **Java e Programação Orientada a Objetos**, sendo desenvolvido com foco em consolidar fundamentos da linguagem através da implementação de regras de negócio em um sistema prático.

---

⭐ Projeto desenvolvido para fins de estudo e evolução em Java.
