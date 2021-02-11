# Elasticsearch入门CRUD

#### 创建一个文档，文档Index为users，ID为1

`PUT users/_create/1`

`{"firstName":"Jack"}`

##### 如果需要es自动生成ID，则需要使用POST

`POST users/_doc`

`{"firstName":"Jack"}`



#### GET一个文档

##### 语法 GET 索引名/_doc/文档ID

`GET users/_doc/1`



#### Update一个文档

##### 语法 POST 索引名/_update/文档ID

##### {

##### 	"doc":{"属性名":"属性值"}

##### }

###### `POST users/_update/1`

`{"doc":{"secondName":"yy"}}`



#### Delete by Query

##### 删除语句中query条件可以自己定义

`POST users/_delete_by_query`
`{"query":{"match":{ "firstName":"Jack"}}}`



#### 批量读取

#### 可同时读取多个Index，在docs中设置多个Index

`GET _mget`

`{ "docs":[{"_index":"users","_id":1}]}`







