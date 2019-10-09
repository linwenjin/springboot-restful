前端演示地址：http://vue.linwenjin.top
<br />前端代码：https://github.com/linwenjin/vueadmin

# IDEA+springboot+maven+restful+JWT
###### 后续慢慢完善，最近需要考试，所以没有那么多精力。
###### 和刚入门java的朋友一起学习，不断完善。
###### 由于php是本人的第一语言，代码里能看到php的影子，目录结构主要分成几个部分，如下：

<br />-main
<br />&emsp;├ java
<br />&emsp;&emsp;├ com.example.restfulapi
<br />&emsp;&emsp;&emsp;├ bean           ***&emsp;-- 各类数据对象***
<br />&emsp;&emsp;&emsp;├ controller     ***&emsp;-- 控制器***
<br />&emsp;&emsp;&emsp;├ dao            ***&emsp;-- 数据连接对象目录***
<br />&emsp;&emsp;&emsp;├ middleware     ***&emsp;-- 各种中间件***
<br />&emsp;&emsp;&emsp;&emsp;├ AuthenticationInterceptor      ***&emsp;&emsp;---- 内重写preHandle，请求发起前首先会经过这里***
<br />&emsp;&emsp;&emsp;&emsp;├ BaseException                  ***&emsp;&emsp;---- 抛出类，用来抛出各种自定义错误***
<br />&emsp;&emsp;&emsp;&emsp;├ CustomMultiThreadingConfig                  ***&emsp;&emsp;---- 自定义多线程***
<br />&emsp;&emsp;&emsp;&emsp;├ GlobalExceptionHandler         ***&emsp;&emsp;---- 拦截context-path下所有错误，并制定格式返回***
<br />&emsp;&emsp;&emsp;&emsp;├ RestResultWrapper              ***&emsp;&emsp;---- 定义接口返回的代码格式***
<br />&emsp;&emsp;&emsp;&emsp;└ WebConfigurer                  ***&emsp;&emsp;---- 静态资源映射，注册拦截器AuthenticationInterceptor***
<br />&emsp;&emsp;&emsp;└ service                        ***&emsp;-- 服务层目录***
<br />&emsp;&emsp;&emsp;&emsp;└ impl           ***&emsp;-- 服务实现目录***
<br />&emsp;└ resources
<br />&emsp;&emsp;└ application.yml                  ***&emsp;-- 配置文件***


## 项目打开方式：
  1. 打开mysql服务，配置好mysql。
  2. 要安装好lombok插件，不然编译时会报错。
  3. 点击Reimport All Maven Projects，安装好各种java类
  4. 直接编译，springboot已经内置tomcat环境
  5. 调起一个接口试试：http://localhost:8081/api/system/rule
  6. 注：图片地址我是映射的，映射的地址有另外指向一个域名
