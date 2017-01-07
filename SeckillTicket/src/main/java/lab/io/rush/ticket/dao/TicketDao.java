package lab.io.rush.ticket.dao;

import java.util.List;

import lab.io.rush.ticket.domain.Ticket;

public interface TicketDao {
	
	public List<Ticket> findAll();
	
	public Ticket findById(int id);

	public int seckillByProcedure(int ticketId,String ticketName,String email);

}
