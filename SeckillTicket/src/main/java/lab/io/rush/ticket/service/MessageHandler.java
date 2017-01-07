package lab.io.rush.ticket.service;

import lab.io.rush.ticket.dao.OrderDao;
import lab.io.rush.ticket.domain.Email;
import lab.io.rush.ticket.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Created by ali on 16-12-27.
 */
public class MessageHandler{

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OrderDao orderDao;


    private final Logger log= LoggerFactory.getLogger(this.getClass());

   public void handleMessage(Email email){
        try {
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setFrom(email.getFrom());
            simpleMailMessage.setTo(email.getTo());
            simpleMailMessage.setSubject(email.getSubject());
            Order order=orderDao.findByTicketIdAndEmail(email.getTicketId(),email.getTo());
            StringBuilder builder=new StringBuilder(email.getContent());
            builder.append("电影票名：").append(order.getTicketName())
                    .append("；").append("下单时间").append(order.getCreateTime());
            simpleMailMessage.setText(builder.toString());
            mailSender.send(simpleMailMessage);
            System.out.print("邮件发送成功");
        }
        catch (Exception e) {
            log.error("发送邮件失败",e);
        }
    }
}
