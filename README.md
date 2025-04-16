# Calculadora de Juros - API

Este projeto é uma API desenvolvida com **Java 21**, **Spring Boot** e **Gradle**, com o objetivo de calcular **juros simples e compostos** com ou sem aportes mensais. A API também fornece documentação interativa via Swagger.

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Gradle
- Spring Boot 3.4.4
- Spring Web
- Spring Validator
- Swagger (SpringDoc OpenAPI)

## 📘 Endpoints

### `POST /interest`

Calcula o valor final, os juros totais e o valor total investido com base nos dados fornecidos.

### 📥 Exemplo de Requisição

```json
{
  "capital": 1000.0,
  "monthlyValue": 200.0,
  "interestRate": 10.0,
  "time": 2,
  "type": 1
}

```

- `capital`: Valor inicial investido (obrigatório)
- `monthlyValue`: Aporte mensal (opcional)
- `interestRate`: Taxa anual de juros em porcentagem (ex: 10 para 10%)
- `time`: Tempo de investimento em anos
- `type`: Tipo de juros (`1` para simples, `2` para composto)

### 📤 Exemplo de Resposta

```json
{
  "finalAmount": 5720.00,
  "interestAmount": 520.00,
  "valueInvested": 5200.00
}

```

---

## 📚 Documentação Swagger

Acesse a documentação interativa gerada automaticamente em:

```
http://localhost:8080/swagger-ui.html

```

---

## 🛠️ Como rodar o projeto

1. Clone o repositório:
    
    ```bash
    git clone https://github.com/allmuniz/api-interest-calculator.git
    
    ```
    
2. Navegue até o diretório do projeto:
    
    ```bash
    cd api-interest-calculator
    
    ```
    
3. Execute o projeto:
    
    ```bash
    ./gradlew bootRun
    
    ```
    

---

## 🧑‍💻 Autor

Desenvolvido por **Allan Muniz** 🚀

Conecte-se comigo no [LinkedIn](https://www.linkedin.com/in/allan-muniz-reis/)!
