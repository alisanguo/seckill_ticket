package lab.io.rush.ticket.domain;

import java.io.Serializable;

/**
 * Created by ali on 16-12-31.
 */
public class Email implements Serializable{

    private int ticketId;

    private String from="15527206908@163.com";

    private String to;

    private String content;

    private String subject="抢票成功通知";

    public Email(String from, String to, String content, String subject) {
        this.from = from;
        this.to = to;
        this.content = content;
        this.subject = subject;
    }

    public Email(int ticketId,String to, String content) {
        this.ticketId=ticketId;
        this.to = to;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
}
