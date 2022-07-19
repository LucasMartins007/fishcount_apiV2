# Api de serviços Fishcount

## Sobre

Inicialmente projeto feito para ser um projeto de TCC do curso superior de engenharia 
de Software, seguindo as diretrizes de uma Starup e os procedimentos de desenvolvimento 
da metodologia SCRUM.

## Aplicação

#### Ambiente de desenvolvimento (profile development)

  - Deploy automático em um servidor da heroku a partir de qualquer novo commit na branch de ``develop``

  - Host:
    - https://fish-count-api-dev.herokuapp.com/

  - Documentação swagger
    - https://fish-count-api-dev.herokuapp.com/fishcount-api/api/v1/swagger-ui/index.html


#### Ambiente de produção (profile production)

  - Deploy automático em um servidor da heroku a partir da aceitação de uma merge request para a branch ``master``

  - Host:
    - https://fish-count-api-prod.herokuapp.com/

  - Swagger
    - https://fish-count-api-prod.herokuapp.com/fishcount-api/api/v1/swagger-ui/index.html

### Padrões do projeto

   - Java 17.
   - Spring boot.
   - Padrões de código:
<br><br>

     - Services:
       - Devem possuir uma interface para exposição de seus métodos públicos.
       - A interface deve estender a interface ``IAbstractService``
       - Devem extender a classe abstrata ``AbstractServiceImpl`` 
<br><br>

     - Controllers
         - Devem posssuir uma interface para separar a documentação e mapeamento de endpoints do código dos métodos.
         - Devem extender a classe abstrata ``AbstractController``.
         - A documentação swagger deverá referenciar o documento de configuração ``application-swagger.yaml``.
<br><br>
     - Repositories:
       - Devem possuir uma interface customizada com o prefixo ``custom``.
       - Essa interface servirá para realizar a ligação dos métodos do ``Repository`` e do ``RepositoryImpl``, com ambas as classes implementando a customização.
       - Caso necessário, no package de specs, deverão ser criadas as especificações de consulta de cada entidade.
<br><br>
         - Specifications
           - O nome dos campos das entidade deverão estar declarados em constantes estáticas.
           - Deverão possuir somente métodos de consulta estáticos. <br><br>

         - Implementações
           - Deverão estender a classe abstrata ``GenericImpl`` e implementar a interface customizada referente a entidade de consulta.
           - Quando possível, deverão realizar consultas com ``CriteriaQuery`` utilizando o método ``getSpecRepository()``, e referenciando uma Specification da entidade consultada.
           - Caso não seja possível utilizar ``CriteriaQuery``, as consultas devem ser realizadas com ``JPQL``, utilizando os métodos disponíveis na classe ``GenericImpl``. 
<br><br>
     
