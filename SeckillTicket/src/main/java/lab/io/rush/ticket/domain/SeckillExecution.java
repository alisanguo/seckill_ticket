package lab.io.rush.ticket.domain;

import lab.io.rush.ticket.myenum.SeckillStateEnum;

/**
 * 秒杀service执行结果
 */
public class SeckillExecution {

    private int state;//执行结果状态码

    private String stateInfo;//执行信息

    public SeckillExecution(SeckillStateEnum e){
        this.state=e.getState();
        this.stateInfo=e.getStateInfo();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
