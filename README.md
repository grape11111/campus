## Grape111笔记

##  资料
[Spring 文档](https://github.com/grape11111/community)

[社区首页](https://spring.io/guides/gs/serving-web-content/#initial)


## 工具
[百度](https://www.baidu.com)

[Spring文档](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/web.html#mvc-config-interceptors)

[Thymeleaf文档](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)

[SpringBoot文档](https://docs.spring.io/spring-boot/docs/2.0.1.RELEASE/reference/htmlsingle/#boot-features-webflux-error-handling-custom-error-pages)

[BootStrap](https://v3.bootcss.com/css/)

[图片上传](https://www.jb51.net/article/146012.htm)


##SQL语句
```sql

create table user
(
  id           int auto_increment
    primary key,
  account_id   varchar(100) charset utf8 null,
  name         varchar(50) charset utf8  null,
  token        char(36)                  null,
  gmt_create   bigint                    null,
  gmt_modified bigint                    null
);


ALTER TABLE user ADD bio varchar(256) NULL;
```


```bash
mvn flyway:migrate;
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```