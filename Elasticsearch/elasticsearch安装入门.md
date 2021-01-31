# elasticsearch安装入门

参照：[JPP](https://github.com/tei0513/JPP)/[Elasticsearch](https://github.com/tei0513/JPP/tree/main/Elasticsearch)/[es72](https://github.com/tei0513/JPP/tree/main/Elasticsearch/es72)/**docker_compose.yml**

- 安装docker https://www.docker.com/products/docker-desktop
- 安装 docker-compose https://docs.docker.com/compose/install/



执行命令：docker-compose up 启动es单机版伪集群

启动完成后会启动

- Kibana

  - URL:http://localhost:5601
  - elasticsearch可视化交互界面

- cerebro

  - URL:http://localhost:9000

  - elasticsearch集群监管工具

  - ![image-20210131181834308](/Users/asahi8911/Library/Application Support/typora-user-images/image-20210131181834308.png)

    

- elasticsearch

  - 2个实例，URL地址由docker自动分配，端口号为9200











