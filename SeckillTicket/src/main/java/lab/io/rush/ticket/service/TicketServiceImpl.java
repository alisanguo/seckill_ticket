package lab.io.rush.ticket.service;

import java.util.Date;
import java.util.List;

import lab.io.rush.ticket.dao.TicketDao;
import lab.io.rush.ticket.dao.cache.RedisDao;
import lab.io.rush.ticket.domain.*;
import lab.io.rush.ticket.exception.RepeatSeckillException;
import lab.io.rush.ticket.exception.SeckillClosedException;
import lab.io.rush.ticket.exception.SeckillException;
import lab.io.rush.ticket.myenum.SeckillStateEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;


@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private RedisDao redisDao;

	@Autowired
	private JmsOperations jmsOperations;

	private final Logger log= LoggerFactory.getLogger(this.getClass());

	private String salt = "sfvvdcc*)@$#RTGVCSADFD#@$%^%&*(&*(";//md5盐值

	@Transactional(readOnly = true)
	public List<Ticket> findAll() {
		return ticketDao.findAll();
	}

	@Transactional(readOnly = true)
	public Ticket findById(int id) {
		return ticketDao.findById(id);
	}

	@Transactional
	public SeckillExecution seckill(int ticketId, String ticketName,String email, String md5) throws SeckillException,
			RepeatSeckillException, SeckillClosedException {
		SeckillExecution seckillExecution;
		int result=-4;
		if (md5 == null || !md5.equals(this.getMD5(ticketId))) {
			seckillExecution= new SeckillExecution(SeckillStateEnum.DATAREWRITE);
		}
		try {
			result = ticketDao.seckillByProcedure(ticketId,ticketName,email);
			seckillExecution= new SeckillExecution(SeckillStateEnum.indexOf(result));
		} catch (Exception e) {
			log.error("抢票失败",e);
			seckillExecution= new SeckillExecution(SeckillStateEnum.INNERERROR);
		}
		if(result==SeckillStateEnum.SUCCESS.getState()){
			try {
				Email e=new Email(ticketId,email,"恭喜您抢到了一张电影票,详情:");
				this.sendMessage(e);
			}catch (Exception e) {
				log.error("发送邮件失败",e);
				seckillExecution= new SeckillExecution(SeckillStateEnum.EMAILSENDERROR);
			}
			//更新redis库存
			redisDao.updateTicketNum(ticketId);

		}
		return seckillExecution;
	}

	public Exposer exposeUrl(int ticketId) {
		//使用redis缓存
		Ticket ticket=redisDao.getTicket(ticketId);
		if (ticket==null){
			ticket = ticketDao.findById(ticketId);
			if (ticket==null){
				return new Exposer(false,ticketId);
			}else{
				redisDao.putTicket(ticket);
			}
		}
		long now = new Date().getTime();
		long start = ticket.getStartTime().getTime();
		long end = ticket.getEndTime().getTime();
		if (start > now||end<now) {
			return new Exposer(false,ticketId, now, start,end);
		} else {
			String md5 = this.getMD5(ticketId);
			return new Exposer(true, ticketId,md5);
		}
	}


	public SeckillResult<Integer> getTicketNum(int ticketId){
		String numStr=redisDao.getTicketNum(ticketId);
		if(numStr==null){
			Ticket ticket=ticketDao.findById(ticketId);
			if (ticket==null){
				return new SeckillResult<Integer>(false,-1);
			}else {
				int num=ticket.getNum();
				redisDao.putTicketNum(ticketId,num);
				return new SeckillResult<Integer>(true,num);
			}
		}
		return new SeckillResult<Integer>(true,Integer.valueOf(numStr));
	}


	private void sendMessage(Email email) {
		jmsOperations.convertAndSend(email);
	}

	private String getMD5(int ticketId) {
		String str = salt + "/" + ticketId;
		return DigestUtils.md5DigestAsHex(str.getBytes());
	}

}


