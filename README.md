# Loja AgilStore

## Requisitos para o projeto;

### Descrição
Para executar essa API você precisa ter instalado o Java 17 (ou superior) o Docker para execultar o banco de dados, esse
projeto foi desenvolvido usando o Postgres como banca de dados para retenção de dados então será nessesario instalar a 
imagem do postgres, segue abaixo os links para download:

### Links:

#### Java

```
https://www.oracle.com/java/technologies/downloads/
```

#### Docker
```
https://www.docker.com/get-started/
```

#### Imagem Postgres
`````
https://hub.docker.com/_/postgres
`````

---

## Descrição;

Esse projeto foi pensado para suprir a necessidade da empresa loja AgilStore de ter uma forma adequada de gerir o seu 
estoque e de interagir com os clientes, então essa nessecidade inspirou a criação dessa API, ela foi desenvolvida com 
5 rotas:

1. **Criação** - Para ser adicionada um produto ao banco de dados


2. **Listagem** - Para ver os produtos presentes no estoque
   - essa rota também dá ao usuário a opção altera a ordenação
   da lista, ele pode ordena a lista por:
     - Nome
     - Quantidade
     - Preço
     
    
3. **Atualização** - Para atualizar um produto em estoque o usuário so precisa enviar o id do produto e os campos que 
4. deseja atualizar


4. **Excluir** - Para deletar um produto do Banco de dados o usuário precisa enviar so o id do produto


5. **Pesquisa** - Para o usuário buscar um produto no estoque ele pode fazer essa pesquisa através de:
   - id do produto
   - Nome - Parcial e completo

___

## Rotas;

### - Criação:

#### End Point
``
    POST http://localhost:8080/product/
``

#### Exemplo de Envio
```
    {
	"name": "caderno",
	"category": "escolar",
	"amount": 1,
	"price": 10
    }
```

#### Exemplo de Resposta
```
{
	"idProduct": 9,
	"name": "caderno",
	"category": "escolar",
	"amount": 1,
	"price": 10
}
```
***

### - Listagem

#### End Point

``
GET http://localhost:8080/product/
``

#### End point para Ordenação - Nome
``
 GET http://localhost:8080/product/name
``

#### End point para Ordenação - Quantidade
``
GET http://localhost:8080/product/amount
``

#### End point para Ordenação - Preço
``
GET http://localhost:8080/product/price
``

#### Exemplo de resposta
```
[
	{
		"idProduct": 7,
		"name": "jogo de cha",
		"category": "utensilio",
		"amount": 1,
		"price": 50
	},
	{
		"idProduct": 8,
		"name": "PC Gamer",
		"category": "utensilio",
		"amount": 1,
		"price": 1000
	},
	{
		"idProduct": 9,
		"name": "caderno",
		"category": "escolar",
		"amount": 1,
		"price": 10
	}
]
```

### - Atualização

#### End Point
``
PUT http://localhost:8080/product/{idProduto}
``

#### Exemplo de Envio
```
{
	"name": "batedeira",
	"category": "eletro-domestico",
	"amount": 3,
	"price": 50
}
```
##### Obs: Lembrando que o usuário so precisa enviar os campos que deseja alterar.


#### Exemplo de resposta
```
{
	"idProduct": 5,
	"name": "batedeira",
	"category": "eletro-domestico",
	"amount": 3,
	"price": 50
}
```

### - Excluir

#### End Point
``
DELETE http://localhost:8080/product/{idProduto}
``

##### Obs: Essa rota não tem corpo de resposta, se a operação for bem sucedida voce vera o código de 204.

### - Pesquisa

#### End point par buscar por nome
``
GET http://localhost:8080/product/find/?name={nome}
``

#### End point par buscar por id do produto
``
GET http://localhost:8080/product/find/?name={idProduto}
``

```
[
	{
		"idProduct": 2,
		"name": "sofa",
		"category": "movel",
		"amount": 2,
		"price": 100
	}
]
```