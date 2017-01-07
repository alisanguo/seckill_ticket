1.项目功能：
  实现高并发电影票抢购，页面每三秒刷新库存，抢购成功后发送邮件
2.部署环境：
  centos7下：
  nginx+tomcat7+jdk8+mariadb10.1.20
3.整体框架：
  (1)后台：
    mariadb+jpa+springmvc
    
  (2)前台
    bootstrap

4.使用技术
  (1)前端使用nginx实现代理和静态资源管理
  (2)抢票详情页使用redis缓存
  (3)库存使用redis缓存
  (4)使用mariadb存储过程减少网络延迟，使用事务保证库存和订单一致性
  (5)使用activemq异步发送抢购成功邮件
