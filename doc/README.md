# 1.Commons(通用包):

## 1.1MessageConst(消息常量类)

### 该类用于定义消息常量,以便之后的类去使用,而不需要去而外定义



# 2.config(配置类):



## 2.1springMvcConfiguration

### 该类用于配置springmvc相关的配置

方法:

- HttpMessageConverters:          HTTP JSON消息转换器,用于将执行方法时提交的参数转化成Java对象.方便控制器的参数接收,这里主要用到阿里巴巴的fastjson

  

## WebConfiguration(web配置类):

### 该类用于配置web相关的配置

方法:

- addCorsMappings:          用于解决请求跨域问题:





# 3.controller(控制层):



## 3.1 CheckItemController

### 该类用于定义检查项的方法,使用了restful风格

方法:

- add:添加操作

- findpage:分页查询,使用了PageHelper分页插件

- delete:删除操作

- edit:编辑操作

- findbyid:根据id查询

  

## 3.2 MyExceptionHandler

### 全局异常处理器,用于处理所有异常情况,其中分为三种异常

- 服务端异常--提示操作失败
- 业务异常-----表示正常的业务逻辑,不需要打印error级别的日志
- 希望交给spring管理的异常---系统级别的异常



## 3.3 Usercontroller

### 该类用于用户的方法,使用了restful风格

方法:

- login:登录操作 ,为防止密码出现明文,采用MD5加密,登录时需要进行MD5匹配



# 4.dao(数据访问层)

### 该报用于封装我们对数据库的访问,通过该层查询数据,返回给service层,用到了mybatis的技术



# 5.entity(实体类)



## 5.1 PageResult(分页实体类)

- Total:总条数

- rows:行

  

## 5.2 QueryPageBean(查询条件封装类)

- currentPage:当前页
- pageSize:每页条数
- queryString:请求参数



## 5.3 Result(统一返回前端的格式)

- flag:执行结果
- message:返回结果信息
- data:返回数据



# 6.exception(异常处理)



## 6.1 BusinessRuntimeException(业务运行时异常)

### 定义业务运行时异常





# 7.mapper(映射层)

### 用于编写xml文件,访问数据库,获取数据,需要在其中编写sql语句,用到了mybatis的技术



# 8.pojo(数据库实体)

### 定义实体类与数据库中的字段一一对应





# 9.service(服务层)

### 获取数据访问层中的数据,并且将数据封装成对象,以供控制层去使用,这里主要用到Mvc三层架构的思想



## 9.1.CheckItemService(检查项服务层)

方法:

- pageQuery:分页查询


- edit:编辑


- findById:根据id查询



# 9.2 UserService(用户服务层)

方法:

- login:登录



# 10.vo(业务实体类)

### 用于定义业务所需要的实体类



























