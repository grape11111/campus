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

[ajax处理后台返回的list](https://blog.csdn.net/jeremyjone/article/details/80359333)

[css样式](<link rel="stylesheet" type="text/css" href="http://cdn.amazeui.org/amazeui/2.7.2/css/amazeui.min.css">)

[jQuery中国省市区地址三级联动插件Distpicker](https://www.jianshu.com/p/c0c177f2b603)


[map按value排序](https://blog.csdn.net/qq_21539671/article/details/98091016?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task)
[文本余弦相似度计算](https://blog.csdn.net/qq_14955245/article/details/80609554)


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
登录验证
<script>
    function Juge(myform) {
        var username=myform.username.value;
        var password=myform.password.value;
        var type=myform.type.value;
        check(username,password,type)
    }

    function check(username,password,type){
        $.ajax({
            type: 'post',
            url: 'check',
            cache: false, // 禁用缓存
            data: {
                "username": username,
                "password": password,
                "type": type
            },

            success: function (data) {
                if(num<6){
                    $("#tip2").html("<font color=\"red\" size=\"2\">  密码至少要6位！</font>");
                }
            }
        })
    }
</script>