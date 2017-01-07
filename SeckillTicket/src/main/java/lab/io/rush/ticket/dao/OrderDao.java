package lab.io.rush.ticket.dao;

import lab.io.rush.ticket.domain.Order;

/**
 * Created by ali on 17-1-7.
 */
public interface OrderDao {

    Order findByTicketIdAndEmail(int ticketId, String email);
}
