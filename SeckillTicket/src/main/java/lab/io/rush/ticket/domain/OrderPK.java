package lab.io.rush.ticket.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ali on 17-1-7.
 */
@Embeddable
public class OrderPK implements Serializable{

    @Column(name="ticket_id")
    private int ticketId;

    @Column(name="email")
    private String email;


    public OrderPK() {
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
