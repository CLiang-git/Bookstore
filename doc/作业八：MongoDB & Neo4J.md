**将用户对书的评论存入了mongodb数据库**



在application.properties里加入mongodb的配置：

<img src="作业八：MongoDB&Neo4J.assets/image-20211117224549937.png" alt="image-20211117224549937" style="zoom: 67%;" />

指定放入名为comment的数据库



创建实体类Comment用于存入mongoDB数据库：

<img src="作业八：MongoDB&Neo4J.assets/image-20211117224922036.png" alt="image-20211117224922036" style="zoom:50%;" />



创建CommentRepository：

<img src="作业八：MongoDB&Neo4J.assets/image-20211117225633534.png" alt="image-20211117225633534" style="zoom:50%;" />

主要提供两种方式查找，一种是根据书来查评论，另一种是根据用户来查评论



除查找之外，还有添加评论和删除评论：

<img src="作业八：MongoDB&Neo4J.assets/image-20211117225818098.png" alt="image-20211117225818098" style="zoom:50%;" />