package lab.io.rush.ticket.service;

import java.util.List;

import lab.io.rush.ticket.domain.Exposer;
import lab.io.rush.ticket.domain.SeckillExecution;
import lab.io.rush.ticket.domain.SeckillResult;
import lab.io.rush.ticket.domain.Ticket;
import lab.io.rush.ticket.exception.RepeatSeckillException;
import lab.io.rush.ticket.exception.SeckillClosedException;
import lab.io.rush.ticket.exception.SeckillException;

public interface TicketService {
	
	List<Ticket> findAll() ;
	
	Ticket findById(int id);

	public SeckillExecution seckill(int ticketId,String ticketName,String email, String md5) throws
			RepeatSeckillException, SeckillClosedException, SeckillException;

	Exposer exposeUrl(int ticketId);

    SeckillResult<Integer> getTicketNum(int ticketId);
}
