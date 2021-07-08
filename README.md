
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
![image](https://user-images.githubusercontent.com/40271395/124937836-26b40d80-dfde-11eb-8c8c-236fc6e70bb1.png)

# BUGS/Erros encontrados
Foram encontradas diferenças entre o que consta na documentação/orientação para execução do desafio e o que de fato é retornado no Swagger (backend da aplicação):

### Simulações
- Nas orientações consta que o valor deveria ser >= 1000 e <= 40000, porém no Swagger a restrição era somente ser <= 40000.
- Nas orientações consta que o número de parcelas deveria ser >= 2 e <= 48, porém no Swagger a restrição era somente ser >= 2.
- Nas orientações conta que a mensagem de erro ao inserir uma Simulação com CPF já existente deveria ser "CPF já existente" com HTTP Status 409, porém o que retorna no Swagger é "CPF duplicado" e HTTP Status 400.
- Nas orientações consta que ao remover uma simulação com sucesso deveria retornar o HTTP Status 204, porém o que retorna de fato é HTTP Status 200.
- Nas orientações conta que ao tentar remover uma simulação não encontrada deveria retornar HTTP Status 404 e a mensagem "Simulação não encontrada", porém o que retorna de fato é o HTTP Status 200 (incorretamente) e nenhuma mensagem.

### Restrições
- Nas orientações consta que a mensagem de retorno de uma pessoa com restrição seria "O CPF * possui restrição", porém o que retorna no Swagger é "O CPF * tem problema".
