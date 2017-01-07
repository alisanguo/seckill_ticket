1.项目功能：（网址:www.skyrabbit.cn）
  实现高并发电影票抢购，页面每三秒刷新库存，抢购成功后发送邮件(微博qq第三方登录因为网站备案尚未完成无法申请接入等待后续开发)
2.部署环境：
  centos7下：
  nginx+tomcat7+jdk8+mariadb10.1.20
3.整体框架：
  (1)后台：
    mariadb+jpa+springmvc
    
  (2)前台
    bootstrap

4.使用技术
  (1)前端使用nginx实现代理和动静态资源分离
  (2)抢票详情页使用redis缓存
  (3)库存使用redis缓存
  (4)使用mariadb存储过程减少网络延迟，使用事务保证库存和订单一致性
  (5)使用activemq+spring jms异步发送抢购成功邮件
