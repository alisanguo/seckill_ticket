package lab.io.rush.ticket.test;

import lab.io.rush.ticket.dao.OrderDao;
import lab.io.rush.ticket.dao.TicketDao;
import lab.io.rush.ticket.domain.*;
import lab.io.rush.ticket.service.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by ali on 16-12-24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml",
        "classpath:spring/spring-email.xml"})
public class MainTest {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private DefaultMessageListenerContainer container;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private JmsOperations jmsOperations;

    @Test
    public void testDaoList(){
        try{
            List<Ticket> list=ticketDao.findAll();
            for(Ticket t:list){
                System.out.println(t);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testExposerService(){
        ticketService.exposeUrl(1);
    }

    @Test
    public void testServiceSeckill(){
        //container.start();
        Exposer exposer=ticketService.exposeUrl(1);
        String md5=exposer.getMd5();
        SeckillExecution se=ticketService.seckill(1,"变形金刚电影票","1539724141@qq.com",md5);
        System.out.print(se.getStateInfo());
    }

    @Test
    public void testSendEmail(){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("15527206908@163.com");
        simpleMailMessage.setTo("1165275118@qq.com");
        simpleMailMessage.setText("hahaha");
        mailSender.send(simpleMailMessage);
        System.out.print("邮件发送成功");
    }

    @Test
    public void testSendMessage(){
        container.start();
        try {
            Email email=new Email(1,"1539724141@qq.com","恭喜您抢到了一张电影票!");
            jmsOperations.convertAndSend(email);
            System.out.print("fafff");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSeckillByProcedure(){
        int r=ticketDao.seckillByProcedure(1,"变形金刚电影票","1539724141@qq.com");
        System.out.println("################"+r);
    }


    @Test
    public void testOrderDao(){
        Order order=orderDao.findByTicketIdAndEmail(1,"1539724141@qq.com");
        System.out.print("RRR"+order.getTicketName()+order.getCreateTime());
    }
}
