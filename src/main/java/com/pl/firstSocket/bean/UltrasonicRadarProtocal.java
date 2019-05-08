package com.pl.firstSocket.bean;

/**
 * 雷达协议信息实体类
 */
public class UltrasonicRadarProtocal {
    //唯一标示，unix时间戳
    private String id;
    //超声波雷达后4探1号探头距离，S12，tail4
    private String s12Tail4;
    //超声波雷达后4探2号探头距离,S11，tail3
    private  String s11Tail3;
    //超声波雷达后4探4号探头距离,S10,tail2
    private String s10Tail2;
    //超声波雷达后4探3号探头距离,S9,tail1
    private String s9Tail1;
    //超声波雷达前8探1号探头距离,S1,head1
    private String s1Head1;
    //超声波雷达前8探2号探头距离,S2,head2
    private String s2Head2;
    //超声波雷达前8探4号探头距离,S3,head3
    private String s3Head3;
    //超声波雷达前8探5号探头距离(4探时忽略此数据 ),S4,head4
    private String s4Head4;
    //超声波雷达前8探6号探头距离(4探时忽略此数据 ),S5,right1
    private String s5Right1;
    //超声波雷达前8探7号探头距离(4探时忽略此数据 ),S6,right2
    private String s6Right2;
    //超声波雷达前8探8号探头距离(4探时忽略此数据 ),S7,left1
    private String s7Left1;
    //超声波雷达前8探3号探头距离,S8,left2
    private String s8Left2;
    /**
     * 状态信号(左转，右转，倒车，车速) (不需要时忽略此数据 )
     *其中：  bit0: 左转(为1时表示左转开，为0时表示左转关) 忽略
     *bit1: 右转(为1时表示右转开，为0时表示右转关) 忽略
     *bit2: 倒车(为1时表示倒车状态，为0时表示非倒车状态) 忽略
     *bit3: 车速(为1时表示车速低于10KM/H，为0时表示车速高于10KM/H) 忽略
     */
    private  String lightState;
    //车速（单位KM/H）忽略
    private String speed;
    /**
     * 微波雷达状态数据
     * Bit0: 左侧微波雷达报警状态(为1时报警状态，为0时不报警状态),此状态指有无后方来车，并非指蜂鸣器报警
     * Bit1: 左后微波雷达是否存在（为1时存在，为0时不存在）
     * Bit2: 左前微波雷达是否存在（为1时存在，为0时不存在）
     * Bit3: 保留
     * Bit4: 右侧微波雷达报警状态(为1时报警状态，为0时不报警状态)
     * Bit5: 右后微波雷达是否存在（为1时存在，为0时不存在）
     * Bit6: 右前微波雷达是否存在（为1时存在，为0时不存在）
     * Bit7: 保留
     */
    private String radarState;
    /**
     * 报警声音。
     * Bit4~bit0:超声波雷达声音报警（其中0：不报警； 1:1HZ报警; 2:2HZ报警;3:4HZ报警; 4:长鸣; 其它值：无效 ）
     * Bit5: 左侧微波雷达声音报警（0：不报警； 1:左侧微波雷达报警;）
     * Bit6: 右侧微波雷达声音报警（0：不报警； 1:右侧微波雷达报警;）
     */
    private String alertState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getS12Tail4() {
        return s12Tail4;
    }

    public void setS12Tail4(String s12Tail4) {
        this.s12Tail4 = s12Tail4;
    }

    public String getS11Tail3() {
        return s11Tail3;
    }

    public void setS11Tail3(String s11Tail3) {
        this.s11Tail3 = s11Tail3;
    }

    public String getS10Tail2() {
        return s10Tail2;
    }

    public void setS10Tail2(String s10Tail2) {
        this.s10Tail2 = s10Tail2;
    }

    public String getS9Tail1() {
        return s9Tail1;
    }

    public void setS9Tail1(String s9Tail1) {
        this.s9Tail1 = s9Tail1;
    }

    public String getS1Head1() {
        return s1Head1;
    }

    public void setS1Head1(String s1Head1) {
        this.s1Head1 = s1Head1;
    }

    public String getS2Head2() {
        return s2Head2;
    }

    public void setS2Head2(String s2Head2) {
        this.s2Head2 = s2Head2;
    }

    public String getS3Head3() {
        return s3Head3;
    }

    public void setS3Head3(String s3Head3) {
        this.s3Head3 = s3Head3;
    }

    public String getS4Head4() {
        return s4Head4;
    }

    public void setS4Head4(String s4Head4) {
        this.s4Head4 = s4Head4;
    }

    public String getS5Right1() {
        return s5Right1;
    }

    public void setS5Right1(String s5Right1) {
        this.s5Right1 = s5Right1;
    }

    public String getS6Right2() {
        return s6Right2;
    }

    public void setS6Right2(String s6Right2) {
        this.s6Right2 = s6Right2;
    }

    public String getS7Left1() {
        return s7Left1;
    }

    public void setS7Left1(String s7Left1) {
        this.s7Left1 = s7Left1;
    }

    public String getS8Left2() {
        return s8Left2;
    }

    public void setS8Left2(String s8Left2) {
        this.s8Left2 = s8Left2;
    }

    public String getLightState() {
        return lightState;
    }

    public void setLightState(String lightState) {
        this.lightState = lightState;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getRadarState() {
        return radarState;
    }

    public void setRadarState(String radarState) {
        this.radarState = radarState;
    }

    public String getAlertState() {
        return alertState;
    }

    public void setAlertState(String alertState) {
        this.alertState = alertState;
    }

    public UltrasonicRadarProtocal() {

    }

    public UltrasonicRadarProtocal(String id, String s12Tail4, String s11Tail3, String s10Tail2, String s9Tail1, String s1Head1, String s2Head2, String s3Head3, String s4Head4, String s5Right1, String s6Right2, String s7Left1, String s8Left2, String lightState, String speed, String radarState, String alertState) {
        this.id = id;
        this.s12Tail4 = s12Tail4;
        this.s11Tail3 = s11Tail3;
        this.s10Tail2 = s10Tail2;
        this.s9Tail1 = s9Tail1;
        this.s1Head1 = s1Head1;
        this.s2Head2 = s2Head2;
        this.s3Head3 = s3Head3;
        this.s4Head4 = s4Head4;
        this.s5Right1 = s5Right1;
        this.s6Right2 = s6Right2;
        this.s7Left1 = s7Left1;
        this.s8Left2 = s8Left2;
        this.lightState = lightState;
        this.speed = speed;
        this.radarState = radarState;
        this.alertState = alertState;
    }

    @Override
    public String toString() {
        return "UltrasonicRadarProtocal{" +
                "id='" + id + '\'' +
                ", s12Tail4='" + s12Tail4 + '\'' +
                ", s12Tail3='" + s11Tail3 + '\'' +
                ", s10Tail2='" + s10Tail2 + '\'' +
                ", s9Tail1='" + s9Tail1 + '\'' +
                ", s1Head1='" + s1Head1 + '\'' +
                ", s2Head2='" + s2Head2 + '\'' +
                ", s3Head3='" + s3Head3 + '\'' +
                ", s4Head4='" + s4Head4 + '\'' +
                ", s5Right1='" + s5Right1 + '\'' +
                ", s6Right2='" + s6Right2 + '\'' +
                ", s7Left1='" + s7Left1 + '\'' +
                ", s8Left2='" + s8Left2 + '\'' +
                ", lightState='" + lightState + '\'' +
                ", speed='" + speed + '\'' +
                ", radarState='" + radarState + '\'' +
                ", alertState='" + alertState + '\'' +
                '}';
    }
}
