# Sistema de Vendas - Projeto Java

## Integrantes
- Anderson Guilherme
- Alysson Araujo

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


## Dados iniciais
- `dados/produtos.csv` — contém 40 produtos
- `dados/clientes.csv` — contém 50 clientes
