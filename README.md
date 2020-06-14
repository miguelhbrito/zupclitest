
# Projeto - ZupIT 

Uma startup do setor de saúde, o(a) contratou para construir um app no formato **CLI** ( Command Line Interface ) para que seus
colaboradores possam filtrar dados da COVID-19 por país, utilizando o terminal de seus computadores.

### Input:
* country: string
### Output:
* confirmed: int
* deaths: int
* recovered: int
* mortality_rate: int (​ % ​ )

## Getting Started

Faça o download do projeto e compile o projeto:<br><br>
      ```mvn clean install``` <br><br>
Após compilado, crie um arquivo com o nome *covid19* no diretorio */usr/local/bin/* com o conteúdo:<br><br>
      ```#!/bin/bash
      java -jar {diretorio aonde esta o projeto}/zupTesteJava/target/zupTesteJava-1.jar $@``` <br> <br>
      
Feito isso, dê privilégio de execução para o arquivo:<br><br>
      ``` chmod +x {nome do arquivo} ```<br><br>
      
Para executar o **CLI** abra o terminal e digite:<br><br>
      ``` {nome do arquivo} country {nome do país} ```<br><br>

## Referencias usadas:
https://docs.oracle.com/javase/8/docs/api/java/net/HttpURLConnection.html

https://docs.oracle.com/javase/8/docs/api/java/net/URLEncoder.html

https://stackoverflow.com/questions/2678551/when-to-encode-space-to-plus-or-20

https://www.urlencoder.io/java/

https://www.javatpoint.com/URL-class

https://stackoverflow.com/questions/6115896/understanding-checked-vs-unchecked-exceptions-in-java

https://claritysoftware.co.uk/mocking-javas-url-with-mockito/

https://github.com/javieraviles/covidAPI

https://dzone.com/articles/naming-conventions-from-uncle-bobs-clean-code-phil

