package lab.io.rush.ticket.dao;

import lab.io.rush.ticket.dao.TicketDao;
import lab.io.rush.ticket.domain.Ticket;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Repository
public class TicketDaoImpl implements TicketDao {

	public int seckillByProcedure(int ticketId) {
		return 1;
	}

	@PersistenceContext
	private EntityManager em;


	public List<Ticket> findAll(){
		String sql="select t FROM Ticket t";
		Query query=em.createQuery(sql);
		List<Ticket> list=query.getResultList();
		return list;
	}

	public Ticket findById(int id) {
		return em.find(Ticket.class,id);
	}


	public int seckillByProcedure(int ticketId,String ticketName,String email){
		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("execute_seckill");
		query.setParameter("v_ticket_id", ticketId);
		query.setParameter("v_ticket_name", ticketName);
		query.setParameter("v_email", email);
		query.setParameter("v_kill_time", new Date());
		query.execute();
		System.out.print(ticketName);
		int result = (Integer) query.getOutputParameterValue("r_result");
		return result;
	}
}
