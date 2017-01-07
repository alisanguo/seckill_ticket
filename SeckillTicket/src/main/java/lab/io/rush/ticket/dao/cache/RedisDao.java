package lab.io.rush.ticket.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import lab.io.rush.ticket.domain.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by ali on 16-12-31.
 */
public class RedisDao {

    private final JedisPool jedisPool;

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    private RuntimeSchema<Ticket> schema=RuntimeSchema.createFrom(Ticket.class);

    public RedisDao(String host,int port){
        jedisPool=new JedisPool(host,port);
    }

    public Ticket getTicket(int ticketId){
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            String key="ticket:"+ticketId;
            byte[] bytes=jedis.get(key.getBytes());
            if (bytes!=null){
                Ticket ticket=schema.newMessage();
                ProtostuffIOUtil.mergeFrom(bytes,ticket,schema);
                return ticket;
            }
        }catch (Exception e){
            logger.error("redis获取数据错误",e);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return null;
    }

    public String putTicket(Ticket ticket){
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            byte[] bytes=ProtostuffIOUtil.toByteArray(ticket,schema,
                    LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            String key="ticket:"+ticket.getId();
            //超时缓存
            int time_out=60*60;//一小时
            String result=jedis.setex(key.getBytes(),time_out,bytes);
            return result;
        }catch (Exception e){
            logger.error("redis存储数据错误",e);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return null;
    }


    public String getTicketNum(int ticketId){
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();

            String key="num_ticket:"+ticketId;
            String value=jedis.get(key);
            return value;
        }catch (Exception e){
            logger.error("redis获取数据错误",e);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return null;
    }

    public String putTicketNum(int ticketId, int num) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            String key="num_ticket:"+ticketId;
            //超时缓存
            int time_out=60*60;//一小时
            String result=jedis.setex(key,time_out,Integer.toString(num));
            return result;
        }catch (Exception e){
            logger.error("redis存储数据错误",e);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return null;
    }

    public void updateTicketNum(int ticketId) {
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            String key="num_ticket:"+ticketId;
            //超时缓存
            int time_out=60*60;//一小时
            jedis.decrBy(key,1);
        }catch (Exception e){
            logger.error("redis存储数据错误",e);
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }
}
