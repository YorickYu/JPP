# Elasticsearch基本概念

#### Index

Index是文档的容器，是一类文档的结合.类似于数据库的Table

- - Index体现了逻辑空间的概念：每个索引都有自己的Mapping定义，用于定义包含文档的字段和字段类型
  - 索引的Mapping与settings
    - mapping 定义文档字段类型
    - setting 定义不同的数据分布
      - 可在setting中设置数据的shard信息
        - Shard是es存储的最小单元
        - Shard体现了空间的概念：索引中的数据分散在Shard上



#### 节点

每一个节点都是elasticsearch的一个实例

启动时使用 【-E node.name=名字】 指定节点名字

es中的节点分为3中Master eligible,Data Node,Coordinating Node

##### Master eligible

每个节点启动后默认就是一个Master eligible节点

- 可以设置 node.master:false 禁止

- Master eligible节点可以参加选主流程，成为Master节点

- - 当第一个节点启动时，会将自己选成Master节点

  - 每个节点上都保存了集群的状态，只有Master节点才能修改集群状态信息

  - - 集群状态，维护了一个集群中的必要信息

    - - 所有的节点信息
      - 所有的索引和其他相关的Mapping与setting信息
      - 分片的路由信息

    - 任意节点都能修改信息会导致数据的不一致性

##### Data节点

- 可以保存数据的节点，负责保存分片数据。

##### Coordinating节点

- 负责接收Client请求，将请求分发到适合的节点，最终把结果汇集到一起

- 每个节点默认都起到了 Coordinating Node的职责

##### 配置节点类型

| 节点类型          | 配置参数    | 默认值                                                    |
| ----------------- | ----------- | --------------------------------------------------------- |
| master eligible   | node.master | true                                                      |
| data              | node.data   | true                                                      |
| ingest            | node.ingest | true                                                      |
| coordinating only | 无          | 每个节点默认都是coordinating节点。设置其他类型全部为false |
| machine learning  | node.ml     | true( 需要enable x-pack)                                  |
|                   |             |                                                           |



##### 分片Shard

- 主分片

- - 用以解决数据水平扩展的问题。通过主分片，可以将数据分布到集群内的所有节点上

  - - 一个分片是运行Lucene的实例
    - 主分片数在索引创建的时候指定，后续不允许修改，除非Reindex

- 副本分片

- - 用以解决数据高可用的问题，是主分片的拷贝

  - - 副本分片数可以动态调整
    - 增加副本数，一定程度上提高服务的可用性

- - - 