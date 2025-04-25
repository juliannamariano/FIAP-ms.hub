# 💻 Microsserviços - Pagamento & Pedido

Este repositório reúne dois microsserviços desenvolvidos como parte da disciplina **Microsserviços e Engenharia Web** do curso de Sistemas de Informação da FIAP. Os projetos seguem o padrão REST, utilizando Spring Boot, e foram construídos com foco em boas práticas, testes unitários e integração contínua.

---

## 📁 Projetos

### 1. MS-Pagamento
Microsserviço responsável pela gestão de pagamentos.

**Funcionalidades principais:**
- CRUD completo de pagamentos;
- Utilização de DTOs e validações;
- Enum de status de pagamento;
- Testes unitários com JUnit e Mockito:
  - Camadas: Repository, Service e Controller;
- Testes de integração com MockMvc.

📂 Pasta: `/ms-pagamento`

---

### 2. MS-Pedido
Microsserviço responsável pela gestão de pedidos e itens.

**Funcionalidades principais:**
- CRUD de pedidos com múltiplos itens;
- Relacionamento `@OneToMany` entre Pedido e ItemDoPedido;
- Validações com Bean Validation;
- Enum para status dos pedidos;
- DTOs com validação e conversão;
- Camadas bem definidas: Controller, Service, Repository.

📂 Pasta: `/ms-pedido`

---

## 🛠️ Tecnologias Utilizadas

- Java 17  
- Spring Boot  
- Spring Data JPA  
- H2 Database  
- JUnit 5 & Mockito  
- Lombok  
- Maven  
- Swagger (em algumas versões)

---

## 📚 Professora Responsável
**Prof.ª Aparecida F. Castello Rosa**  
