package lab.io.rush.ticket.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="db_ticket")

@NamedStoredProcedureQuery(name = "execute_seckill", procedureName = "execute_seckill", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "v_ticket_id", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "v_ticket_name", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "v_email", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "v_kill_time", type = Date.class),
		@StoredProcedureParameter(mode = ParameterMode.OUT, name = "r_result", type = Integer.class) })
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="name")
	private String name;

	@Column(name="num")
	private int num;

	@Column(name = "price")
	private int price;

	@Column(name="start_time")
	private Date startTime;

	@Column(name="end_time")
	private Date endTime;


	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getNum() {
		return num;
	}

	public int getPrice() {
		return price;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Ticket{" +
				"id=" + id +
				", name='" + name + '\'' +
				", num=" + num +
				", price=" + price +
				", startTime=" + startTime +
				", endTime=" + endTime +
				'}';
	}
}
