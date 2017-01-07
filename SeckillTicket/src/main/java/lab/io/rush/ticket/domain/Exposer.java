package lab.io.rush.ticket.domain;

import java.util.Date;

/**
 * 暴露秒杀接口实体
 */
public class Exposer {

    private boolean exposed;

    private int ticketId;

    private long now;

    private long startTime;

    private long endTime;

    private String md5;

    public Exposer(boolean exposed, int ticketId) {
        this.exposed = exposed;
        this.ticketId = ticketId;
    }

    public Exposer(boolean exposed, int ticketId, String md5) {
        this.exposed = exposed;
        this.ticketId = ticketId;
        this.md5 = md5;
    }

    public Exposer(boolean exposed, int ticketId, long now, long startTime, long endTime) {
        this.exposed = exposed;
        this.ticketId = ticketId;
        this.now = now;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public boolean isExposed() {
       return exposed;
   }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
