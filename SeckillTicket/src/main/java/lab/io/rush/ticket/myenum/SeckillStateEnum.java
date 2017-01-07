package lab.io.rush.ticket.myenum;

/**
 * 秒杀状态枚举
 */
public enum  SeckillStateEnum {
    SUCCESS(1,"抢票成功,将发送邮件到绑定邮箱,请注意查收"),
    REPEATSECKILL(0,"重复抢票"),
    SECKILLCLOSED(-1,"抢票已结束"),
    DATAREWRITE(-2,"数据被篡改"),
    INNERERROR(-3,"系统内部错误"),
    EMAILSENDERROR(2,"抢票成功,邮件发送失败");
    ;
    private int state;

    private String stateInfo;

    SeckillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStateEnum indexOf(int index){
        for(SeckillStateEnum t:values()){
            if(t.getState()==index){
                return t;
            }
        }
        return null;
    }
}
