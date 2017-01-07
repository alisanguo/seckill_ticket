package lab.io.rush.ticket.dao;

import lab.io.rush.ticket.domain.Order;
import lab.io.rush.ticket.domain.Ticket;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by ali on 17-1-7.
 */
@Repository
public class OrderDaoImpl implements OrderDao{

    @PersistenceContext
    private EntityManager em;

    public Order findByTicketIdAndEmail(int ticketId, String email) {
        String sql="select o FROM Order o where o.orderPK.ticketId=:ticketId and o.orderPK.email=:email";
        Query query=em.createQuery(sql);
        query.setParameter("ticketId",ticketId);
        query.setParameter("email",email);
        Order order=(Order)query.getSingleResult();
        return order;
    }
}
