# **Desafio de Busca de CEP**

Este projeto implementa uma aplicação de busca de CEP utilizando Java 21, Spring Boot, OpenFeign, e Mockoon para simular uma API externa de consulta de CEP. O projeto segue os princípios de design de software **SOLID**, para garantir escalabilidade, flexibilidade e facilidade de manutenção.

## **📜 Descrição do Desafio**

A aplicação:

* Realiza a busca de um CEP em uma API externa mockada.
* Registra logs das consultas em um banco de dados, com os detalhes da consulta e a resposta da API.
* Segue os princípios de **SOLID**.
* É desenvolvida usando **Java 21** e **Spring Boot**.
* Usa **Mockoon** para mockar a API externa.
* Contém testes unitários usando **JUnit** e **Mockito**.
---

## **🔄 Fluxo da Aplicação**

O fluxo básico da aplicação é representado pelo diagrama abaixo:

    +--------------+      +----------------+      +-----------------+      +----------------+
    | Cliente REST | ---> | CepController   | ---> | CepService       | ---> | API Externa     |
    +--------------+      +----------------+      +-----------------+      +----------------+
                                                 |                       |
                                                 |                       v
                                                 |                +-----------------+
                                                 |                | LogService       |
                                                 |                +-----------------+
                                                 |
                                                 v
                                          +-----------------+
                                          | Banco de Dados  |
                                          +-----------------+

  
  **Descrição do fluxo:**
1. O cliente REST faz uma requisição ao `CepController` para buscar um CEP.
2. O `CepController` chama o `CepService`, que é responsável pela lógica de consulta do CEP.
3. O `CepService` faz a requisição à API externa (mockada pelo **Mockoon**) usando **OpenFeign**.
4. Após receber a resposta da API externa, o `CepService` chama o `LogService` para registrar os detalhes da consulta no banco de dados.
5. O `CepService` retorna a resposta da API para o cliente REST.
---

## **🛠️ Princípios SOLID Aplicados**
 

### **1\. S \- Single Responsibility Principle (Responsabilidade Única)**
Cada classe tem uma única responsabilidade:
*  **`CepService`**: Responsável por chamar a API externa e coordenar a lógica de negócio.
*  **`LogService`**: Responsável apenas por registrar os logs das consultas no banco de dados.
*  **`CepController`**: Responsável por receber requisições HTTP e enviar respostas.

Isso facilita a manutenção e garante que cada classe tenha uma função específica e focada.

### **2\. O \- Open/Closed Principle (Aberto para Extensão, Fechado para Modificação)**

A aplicação está aberta para extensões. Por exemplo, se quisermos adicionar outra API de CEP no futuro, podemos criar outra implementação de `CepServiceInterface` sem modificar o código existente.

### **3\. L \- Liskov Substitution Principle (Substituição de Liskov)**
O sistema permite substituir uma implementação concreta (por exemplo, `CepClient`) por outra que siga a mesma interface, garantindo flexibilidade sem impactar o código que depende dessas classes.

### **4\. I \- Interface Segregation Principle (Segregação de Interface)**
A interface `CepClient` é focada em uma única responsabilidade: buscar dados de CEP. Isso garante que as classes implementem apenas métodos que realmente precisam, sem serem sobrecarregadas com funcionalidades extras.

### **5\. D \- Dependency Inversion Principle (Inversão de Dependência)**

Tanto `CepService` quanto `LogService` dependem de abstrações (interfaces) em vez de implementações concretas. Isso facilita a substituição e teste das dependências, melhorando a modularidade do sistema.

---
## **⚙️ Principais Classes**

### **1\.  `CepController`**

  

Responsável por receber as requisições HTTP e delegar a chamada ao `CepService`.

  

java


    @RestController
    @RequestMapping("api/v1/cep")
    public class CepController {
    	
    	private final CepService service;
    	
    	public CepController(CepService service) {
    		this.service = service;
    	}
    	
    	@GetMapping(value = "/{cep}", consumes = "application/json; charset=UTF-8")
    	public ResponseEntity<MessageResponse> buscarCep(@PathVariable String cep){
    		try {
    			service.buscarCep(cep);
    			return new ResponseEntity<MessageResponse>(new MessageResponse("Cep localizado: " + cep), HttpStatus.OK);
    		} catch (Exception e) {
    			return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    		}
    	}
    }

    
### **2\.  `CepService`**
Lida com a lógica de busca de CEP e coordena a interação com o `LogService` e o cliente Feign para acessar a API mockada.


    @Service
    public  class CepService implements CepServiceInterface{
    
	    private  final CepClient cepClient;
	    private  final LogService logService;
	    
	    public CepService(CepClient cepClient, LogService logService) {
		    this.cepClient = cepClient;
		    this.logService = logService;
	    }
	    
	    public String buscarCep(String cep) {
		    String respostaApi = cepClient.buscarCep(cep);
		    logService.registrarLog(cep, respostaApi);
		    return  respostaApi;
	    }
    
    }

### **3\.  `LogService`**

Responsável pelo registro de logs no banco de dados.

    @Service
    public class LogService implements LogServiceInterface{
    
    	private final LogRepository logRepository;
    	
    	public LogService(LogRepository logRepository) {
    		this.logRepository = logRepository;
    	}
    
    	public void registrarLog(String cep, String respostaApi) {
    		ConsultaLog log = new ConsultaLog(cep, respostaApi, LocalDateTime.now());
    		logRepository.save(log);
    	}
    }


### **4\.  `CepClient` (Feign Client)**

Interface para comunicação com a API externa mockada.

    @FeignClient(name = "CepCLient", url = "http://localhost:3000")
    public interface CepClient {
    
    	@GetMapping("/cep/{cep}")
    	String buscarCep(@PathVariable("cep") String cep);
    }


### **5\.  `ConsultaLog`**

Classe que representa o log de consulta.

    @Entity
    public class ConsultaLog {
    
    	@Id
    	@GeneratedValue(strategy = GenerationType.IDENTITY)
    	private Long id;
    
    	private String cep;
    	private String respostaApi;
    	private LocalDateTime dataConsulta;
    
    	//construtores, getters e setters
    
    }

---
## **📝 Configurações do Projeto**

*  **Java 21**
*  **Spring Boot 3.x**
*  **OpenFeign** para comunicação com API externa.
*  **H2** como banco de dados em memória para armazenar logs.
*  **JUnit 5** e **Mockito** para testes unitários.
*  **Mockoon** para simular a API externa.

---
## **📦 Execução da API**
1.  **Rodando a API Mockada (Mockoon)**:
* Abra o Mockoon e configure um mock da API de CEP.
* Configure o endpoint `/cep/:cep` para retornar o seguinte JSON para o CEP `01001000`:

    {
    	"cep": "01001-000",
    	"logradouro": "Praça da Sé"
    }

2.  **Requisição de Exemplo (Usando cURL ou Postman)**:
* Faça uma requisição GET para `http://localhost:8080/api/cep/01001000`.

**Exemplo de resposta**:

    {
	    "cep": "01001-000",
	    "logradouro": "Praça da Sé"
    }

## **🧪 Testes Unitários**

Os testes unitários foram implementados para:
* Verificar se o `CepService` chama corretamente a API e registra os logs.
* Validar que o `LogService` salva os logs no banco de dados.
* Garantir que o `CepController` responde corretamente às requisições HTTP.

Os testes podem ser rodados usando o comando:
bash

    `./mvnw test`


