# agi-insurance-service

S - Single Responsibility Principle (SRP)
Cada classe tem uma única responsabilidade: InsuranceService - Gerencia regras de negócio do seguro.
InsuranceController - Expõe os endpoints da API.
InsuranceRepositoryPort - Define a interface para persistência de seguros.
CustomerValidationPort - Define a interface para validar a existência de clientes.

O - Open/Closed Principle (OCP)
O cálculo do preço do seguro está isolado no método calculatePrice(type), facilitando extensões futuras sem modificar a lógica existente.
Se quisermos adicionar novos tipos de seguro, basta modificar esse método sem alterar o restante do código.

L - Liskov Substitution Principle (LSP)
Os ports (CustomerValidationPort e InsuranceRepositoryPort) são interfaces, garantindo que qualquer implementação delas possa ser trocada sem quebrar o código.
Podemos substituir o banco de dados MongoDB por outro (ex: PostgreSQL) sem afetar a regra de negócio.

I - Interface Segregation Principle (ISP)
A arquitetura hexagonal naturalmente separa interfaces específicas para cada funcionalidade:
InsuranceRepositoryPort → Para operações no banco.
CustomerValidationPort → Para verificar se o cliente existe.

D - Dependency Inversion Principle (DIP)
Em vez de depender de implementações concretas, o InsuranceService depende apenas de interfaces (InsuranceRepositoryPort, CustomerValidationPort).
As implementações concretas (ex: MongoInsuranceRepository) são injetadas via Spring, garantindo baixo acoplamento.

# Curls

curl -X POST \
http://localhost:9082/insurances/contract \
-H 'Accept: */*' \
-H 'Accept-Encoding: gzip, deflate' \
-H 'Cache-Control: no-cache' \
-H 'Connection: keep-alive' \
-H 'Content-Length: 48' \
-H 'Content-Type: application/json' \
-H 'Host: localhost:9082' \
-H 'Postman-Token: 2de8d730-2679-4c9f-8a57-60c0db8c7d36,567d32ea-5543-4bf7-b417-4d343f17ee4f' \
-H 'User-Agent: PostmanRuntime/7.16.3' \
-H 'cache-control: no-cache' \
-d '{
"cpf": "12345678900",
"type": "gold"
}'


curl -X GET \
http://localhost:9082/insurances/simulate/12345678900 \
-H 'Accept: */*' \
-H 'Accept-Encoding: gzip, deflate' \
-H 'Cache-Control: no-cache' \
-H 'Connection: keep-alive' \
-H 'Content-Type: application/json' \
-H 'Host: localhost:9082' \
-H 'Postman-Token: 0fc022bc-f5f9-4c0b-a6e3-a42323861d18,e822e329-ed0d-4284-9d98-315559c5f1fb' \
-H 'User-Agent: PostmanRuntime/7.16.3' \
-H 'cache-control: no-cache'