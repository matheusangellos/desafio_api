
## Descrição
Repositório referente a desafio de API utilizando Java, REST Assured e Maven, utilizando aplicação back-end disponibilizada previamente no mesmo repositório.

## Requisitos

- [JDK versão 11](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
- [Intellij](https://www.jetbrains.com/idea/download/#section=windows)
- [Git Bash](https://git-scm.com/downloads)
- [Maven](https://maven.apache.org/download.cgi) deve ser instalado e configurado no path da aplicação

## Como Baixar
- Criar uma pasta local no computador.

Exemplo:
````
Diretório C: para baixar os arquivos do projeto
````
- Acessar o git Bash, ir até a pasta criada.
- Realizar o clone do projeto https://github.com/matheusangellos/desafio_api.git usando o comando Git Clone e a URL
- Garantir que as dependências tenham sido baixadas.


## Como Rodar

### Localmente
- Na raiz do projeto, através de seu Prompt de Commando/Terminal/Console execute o 
comando ````mvn clean spring-boot:run```` para subir o back-end da aplicação, necessário para que os testes rodem. Caso retorne erro, também é possível inicianilizar a aplicação por aqui:
![image](https://user-images.githubusercontent.com/40271395/124412715-80d67980-dd25-11eb-811d-70d1bd1dbfb2.png)
- No Intellij, ir em Maven | Lifecycle | test:
![image](https://user-images.githubusercontent.com/40271395/124412655-60a6ba80-dd25-11eb-9a68-85730bbccdb6.png)
- Será gerado um relatório HTML em target | surefire-reports | index.html:
![image](https://user-images.githubusercontent.com/40271395/124412598-466cdc80-dd25-11eb-9829-661284af3038.png)

# Considerações
- Houveram diferenças entre a documentação do desafio e os status code e mensagens de retorno, onde os testes refletiam o que era retornado no swagger, a fim de que os testes passassem corretamente.
