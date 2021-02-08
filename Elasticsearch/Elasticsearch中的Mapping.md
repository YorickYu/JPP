# Elasticsearch的Mapping

Elasticsearch中的Mapping类似于数据库中表的schema，用于定义Index中字段名称，类型等信息。

Elasticsearch中常见的字段类型

- 简单类型

- - Text / Keyword
  - Date
  - Integer / Floating
  - Boolean
  - IPv4 & IPv6

- 复杂类型

- - 对象类型/ 嵌套类型

- 特殊类型

- - geo_point / geo_shape / percolator

    

创建索引时，Mapping默认会先更具插入文档的信息在定义好的Mapping文件中查找相关配置信息，如果字段未被定义，则会由Elasticsearch自动判断字段的类型并自动创建。对已有字段一旦已经有数据写入，就不再支持修改字段的定义（Lucene实现的倒排索引，一旦生成，就不允许修改，如果希望改变字段类型，必须Reindex API,重建索引）。

- 

Elasticsearch的Dynamic设置

- Dynamic设为true时，一旦有新增字段的文档写入，Mapping也同时被更新（默认）

- Dynamic设为false时，Mapping不会被更新，新增字段的数据无法被索引，但信息会出现在_source中

- Dynamic设置成Strict，文档写入失败

- | Dynamic       | "true" | "false" | "strict" |
  | ------------- | ------ | ------- | -------- |
  | 文档可索引    | T      | T       | F        |
  | 字段可索引    | T      | F       | F        |
  | Mapping被更新 | T      | F       | F        |

#### 

#### 使用默认Dynamic Mapping创建Index

##### 定义一个Index，不设置Mapping

`PUT products`

##### 插入一条数据

`POST /products/_doc`

`{"goods_name":"iPhone"}`

##### 检查索引Mapping信息

 `GET products/_mapping`

`{
  "products" : {
    "mappings" : {
      "properties" : {
        "goods_name" : {
          "type" : "text",
          "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            }
          }
        }
      }
    }
  }
}`



#### 设置Dynamic=false，创建数据

##### 删除已创建的索引

`DELETE products`

##### 添加索引，并将dynamic设置为false

`PUT products
{
  "mappings":{
    "dynamic":false,
    "properties":{
        "goods_name":{
          "type":"text"
        }
      }
  }
}`



##### 添加一个文档

`POST /products/_doc
{
  "goods_name":"iPhone",
  "goods_code":"11111"
}`



##### 

##### 获取索引mapping信息

`GET products/_mapping`

此时可以看到mapping中并没有将goods_code写入

`{
  "products" : {
    "mappings" : {
      "dynamic" : "false",
      "properties" : {
        "goods_name" : {
          "type" : "text"
        }
      }
    }
  }
}`



##### 尝试搜索goods_code,此时得到的结果为0,dynamic设置成了false，新增的字段只会在_source中显示，无法被搜索

`GET /products/_search
{
  "query": {
    "match": {
      "goods_code": "11111"
    }
  }
}`



#### 设置Dynamic=stric

##### 删除已创建的索引

`DELETE products`

##### 添加索引，并将dynamic设置为Strict

`PUT products
{
  "mappings":{
    "dynamic":"Strict",
    "properties":{
        "goods_name":{
          "type":"text"
        }
      }
  }
}`

##### 尝试插入一个新文档

`POST /products/_doc
{
  "goods_name":"iPhone",
  "goods_code":"11111"
}`

此时elasticsearch会报异常

######         "reason": "mapping set to strict, dynamic introduction of [goods_code] within [_doc] is not allowed"

