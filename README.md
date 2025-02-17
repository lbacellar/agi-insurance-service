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