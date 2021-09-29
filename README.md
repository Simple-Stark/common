# <center> Simple-Common

<div style="text-align: center;">

[![](https://img.shields.io/badge/blog-%40SimpleStark-blue.svg)](https://simplestark.top)
[![](https://img.shields.io/badge/SpringBoot-2.3.12.RELEASE-blue.svg)]({https://docs.spring.io/spring-boot/docs/2.3.12.RELEASE/reference/html/})
[![](https://img.shields.io/badge/license-GPL2.0-orange.svg)](https://github.com/Simple-Stark/common/blob/master/LICENSE)

</div>

## 作者
### Simple Stark
- Github: [@Simple-Stark](https://github.com/Simple-Stark)
- Blog:[烟霞志](https://simplestark.top)
- Email:wrh_1125@163.com

## 简介

一个简单的项目公共jar包，一直有着做开源项目的想法，这个项目应该可以算做是地基，在以后的开源项目中我想应该是用得到。

## 使用

示例项目：[![](https://img.shields.io/badge/SimpleStark-SpringBootInit-blue.svg)](https://github.com/Simple-Stark/Spring-Boot-Init)


计划之后推送到中央仓库，在此之前只能下载源码
```https://github.com/Simple-Stark/common.git```
运行maven命令
```
maven clean
maven install    
```
将这个jar包安装到你的本地maven仓库

在你需要引用的项目中添加以下依赖，刷新maven即可使用
```
<!-- 自定义Common包 -->
<dependency>
    <groupId>com.simple</groupId>
    <artifactId>common</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 待办事项
- [x] 全局统一消息返回
  - [x] CodeMsg构造方法使用 protected修饰，支持项目自定义消息返回
- [x] 全局异常拦截
  - [ ] 自定义异常类
- [x] mybatisPlus 代码自动生成器
    - [ ] 调整模板自动生成增删改查接口
    - [ ] 生成对应Vo
- [ ] MybatisPlus 分页插件
- [ ] 字典工具类

## 更新日志
### v 1.0.0
    - 全局统一消息返回
    - 全局异常拦截及自定义异常
    - MybatisPlus 最新代码生成器集成

## License

[![](https://img.shields.io/badge/license-GPL2.0-orange.svg)](https://github.com/Simple-Stark/common/blob/master/LICENSE)

## 


