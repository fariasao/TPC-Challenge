# - NOME DA APLICAÇÃO -

## Integrantes
- Beatriz Lucas - RM99104 (Responsável por atuar em Disruptive Architectures, Compliance & Quality Assurance e DevOps Tools & Cloud Computing);
- Enzo Farias - RM98792 (Responsável por atuar em Disruptive Architectures, Advanced Business Development with .NET, Java Advanced e Mobile App Development);
- Ewerton Gonçalves - RM98571 (Responsável por atuar em Mastering Relational and Non-Relational Database, Mobile App Development e DevOps Tools & Cloud Computing);
- Guilherme Tantulli - RM97890 (Responsável por atuar em Compliance & Quality Assurance, Advanced Business Development with .NET e Mobile App Development);
- Thiago Zupelli - RM99085 (Responsável por atuar em Disruptive Architectures, Advanced Business Development with .NET, Java Advanced e Mastering Relational and Non-Relational Database).

## Instruções para uso da aplicação
Para rodar a aplicação, basta rodar o arquivo TpcApplication.java por meio do Java Run.\
Assim, para acesso ao banco de dados, inicialmente no h2, é necessário utilizar a url "localhost:8080/h2-console" e, em JDBC URL, alterar para "jdbc:h2:mem:tpcjava". Dessa forma, as requisições poderão ser alteradas em tempo real no banco de dados e atualizadas utilizando um novo select.\
Para acessar as urls com métodos específicos de cada classe, seguir para a parte [Endpoints](#listagem-de-endpoints).

## Imagem dos diagramas de classe
UML:\
![UML](./images/TPC-Challenge24v1.png)

Banco de Dados:\
![Banco de Dados](./images/DB_TPC.png)

## Link para vídeo com a proposta tecnológica
[![Vídeo TPC](http://img.youtube.com/vi/D7EWoFBEPvs/0.jpg)](http://www.youtube.com/watch?v=D7EWoFBEPvs)

## Listagem de Endpoints
### Endpoints para UserMaster:
GET /usermasters - Buscar todos os usuários mestres.\
GET /usermasters/{masterId} - Buscar um usuário mestre pelo ID.\
POST /usermasters - Criar um novo usuário mestre.\
PUT /usermasters/{masterId} - Atualizar um usuário mestre existente.\
DELETE /usermasters/{masterId} - Deletar um usuário mestre pelo ID.\

### Endpoints para UserPDV:
GET /userpdv - Buscar todos os usuários PDV.\
GET /userpdv/{atUserId} - Buscar um usuário PDV pelo ID.\
POST /userpdv - Criar um novo usuário PDV.\
PUT /userpdv/{atUserId} - Atualizar um usuário PDV existente.\
DELETE /userpdv/{atUserId} - Deletar um usuário PDV pelo ID.\

### Endpoints para Loja:
GET /lojas - Buscar todas as lojas.\
GET /lojas/{pdvId} - Buscar uma loja pelo ID.\
POST /lojas - Criar uma nova loja.\
PUT /lojas/{pdvId} - Atualizar uma loja existente.\
DELETE /lojas/{pdvId} - Deletar uma loja pelo ID.\

### Endpoints para Produtos:
GET /produtos - Buscar todos os produtos.\
GET /produtos/{pdvId} - Buscar um produto pelo ID.\
POST /produtos - Criar um novo produto.\
PUT /produtos/{pdvId} - Atualizar um produto existente.\
DELETE /produtos/{pdvId} - Deletar um produto pelo ID.\

### Endpoints para Credit:
GET /credits - Buscar todos os créditos.\
GET /credits/{creditId} - Buscar um crédito pelo ID.\
POST /credits - Criar um novo crédito.\
PUT /credits/{creditId} - Atualizar um crédito existente.\
DELETE /credits/{creditId} - Deletar um crédito pelo ID.\

### Endpoints para Pontos:
GET /pontos - Buscar todos os pontos.\
GET /pontos/{pointId} - Buscar um ponto pelo ID.\
POST /pontos - Criar novos pontos.\
PUT /pontos/{pointId} - Atualizar um ponto existente.\
DELETE /pontos/{pointId} - Deletar um ponto pelo ID.\

### Endpoints para UserCluster:
GET /usercluster - Buscar todas as associações UserCluster.\
GET /usercluster/{clusterInfoId} - Buscar uma associação UserCluster pelo ID.\
POST /usercluster - Criar uma nova associação UserCluster.
PUT /usercluster/{clusterInfoId} - Atualizar uma associação UserCluster existente.\
DELETE /usercluster/{clusterInfoId} - Deletar uma associação UserCluster pelo ID.\

### Endpoints para Notificacoes:
GET /notificacoes - Buscar todas as notificações.\
GET /notificacoes/{mensagemId} - Buscar uma notificação pelo ID.\
POST /notificacoes - Criar uma nova notificação.\
PUT /notificacoes/{mensagemId} - Atualizar uma notificação existente.\
DELETE /notificacoes/{mensagemId} - Deletar uma notificação pelo ID.\

### Endpoints para Compras:
GET /compras - Buscar todas as compras.\
GET /compras/{compraId} - Buscar uma compra pelo ID.\
POST /compras - Criar uma nova compra.\
PUT /compras/{compraId} - Atualizar uma compra existente.\
DELETE /compras/{compraId} - Deletar uma compra pelo ID.\

### Endpoints para Campanhas:
GET /campanhas - Buscar todas as campanhas.\
GET /campanhas/{campanhaId} - Buscar uma campanha pelo ID.\
POST /campanhas - Criar uma nova campanha.\
PUT /campanhas/{campanhaId} - Atualizar uma campanha existente.\
DELETE /campanhas/{campanhaId} - Deletar uma campanha pelo ID.\

### Endpoints para Categorias:
GET /categorias - Buscar todas as categorias.\
GET /categorias/{categoriaId} - Buscar uma categoria pelo ID.\
POST /categorias - Criar uma nova categoria.\
PUT /categorias/{categoriaId} - Atualizar uma categoria existente.\
DELETE /categorias/{categoriaId} - Deletar uma categoria pelo ID.\

### Endpoints para Cluster:
GET /clusters - Buscar todos os clusters.\
GET /clusters/{clusterId} - Buscar um cluster pelo ID.\
POST /clusters - Criar um novo cluster.\
PUT /clusters/{clusterId} - Atualizar um cluster existente.\
DELETE /clusters/{clusterId} - Deletar um cluster pelo ID.\

### Endpoints para CompraPontos:
GET /comprapontos - Buscar todas as compras de pontos.\
GET /comprapontos/{pedidoId} - Buscar uma compra de pontos pelo ID.\
POST /comprapontos - Criar uma nova compra de pontos.\
PUT /comprapontos/{pedidoId} - Atualizar uma compra de pontos existente.\
DELETE /comprapontos/{pedidoId} - Deletar uma compra de pontos pelo ID.\

