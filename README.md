# Sistema de Vendas - Projeto Java

## Integrantes
- Anderson Guilherme
- Alysson Araujo
- Rodolfo Rodrigo Padilha

## Descrição
Sistema de vendas simples que gerencia produtos, clientes, pedidos e relatórios. Implementa serialização para persistência entre execuções.

## Como executar
1. Compilar com `javac` (ou importar no Eclipse/IntelliJ)
2. Executar `java app.Main`
3. Arquivos de dados iniciais: `dados/produtos.csv`, `dados/clientes.csv`
4. Ao encerrar o sistema, os objetos são salvos em `dados/dados.ser` (ou `dados.txt`)

## Conceitos implementados
- Estruturas lógicas: `RelatorioGenerator`, `Main` (buscas, filtros, ordenações)
- Construtores: todas as entidades têm construtor vazio e com parâmetros (ver `model/`)
- Encapsulamento: atributos privados e validação nos setters (ver `model/`)
- Herança/Abstract: `Pessoa` abstrata; `Cliente`, `Funcionario`, `Fornecedor`
- Polimorfismo: `ArrayList<Pessoa>` (ver `service/PessoaService`)
- Relacionamentos: `Loja` 1:N `Produto`; `Pedido` N:N `Produto` via `ItemPedido`
- Serialização: `util/ArquivoUtil` (salvar/carregar objetos)
- Exceptions customizadas: `exceptions.CPFInvalidoException`, `exceptions.SaldoInsuficienteException`
- I/O: leitura CSV e gravação de relatórios TXT

## Histórico de commits

- Crianco classe abstratas e filha definidas - G7Sntx
- Implementando classes pessoas e Produto - G7Sntx
- Implementando classes util - G7Sntx
- Principal mudança no main e acrescentando alguns validadores - G7Sntx
- Acrescentado pequenas mudanças na classe SaldoInsuficienteException - Alysson-AS
- Correções - Alysson-AS
- Adicionando "implements Serializable" - Alysson-AS
- Acrescentando classe Categoria - G7Sntx
- Corrigindo erros das classes Funcionario e Fornecedor - G7Sntx
- implementar classe de Endereco - Alysson-AS
- corrigindo pequenos bugs - Alysson-AS



## Dados iniciais
- `dados/produtos.csv` — contém 40 produtos
- `dados/clientes.csv` — contém 50 clientes
