package lab.io.rush.ticket.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ali on 17-1-7.
 */
@Entity
@Table(name = "db_order")
public class Order {

    @EmbeddedId
    private OrderPK orderPK;

    @Column(name="ticket_name")
    private String ticketName;

    @Column(name="create_time")
    private Date createTime;


    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public OrderPK getOrderPK() {
        return orderPK;
    }

    public void setOrderPK(OrderPK orderPK) {
        this.orderPK = orderPK;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
