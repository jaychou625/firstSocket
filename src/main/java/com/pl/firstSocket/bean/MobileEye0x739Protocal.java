package com.pl.firstSocket.bean;

/**
 * MobileEye0x739协议实体类
 * 0x739 + i*3 Details – 障碍物数据A
 */
public class MobileEye0x739Protocal {
    //唯一标识，时间戳
    private String id;
    //障碍物编号 Obstacle_ID
    private String obstacle_ID;
    //障碍物位置x  Obstacle_Pos_X
    private String obstacle_Pos_X;
    //障碍物位置 Y  Obstacle_Pos_Y
    private String ostacle_Pos_Y;
    //障碍物释放速度x Obstacle_Rel_Vel_X
    private String obstacle_Rel_Vel_X;
    //障碍物类型  Obstacle_Type
    private String Obstacle_Type;
    //障碍物状态  Obstacle_Status
    private String status;
    //障碍物制动灯  Obstacle_Brake_Lights
    private String obstacle_Brake_Lights;
    //切入和切出  Cut in and out
    private String cutInAndOut;
    //闪光灯信息  Blinker Info
    private String blinker_Info;
    //障碍物时效（新/旧)Obstacle_Valid
    private String obstacle_Valid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObstacle_ID() {
        return obstacle_ID;
    }

    public void setObstacle_ID(String obstacle_ID) {
        this.obstacle_ID = obstacle_ID;
    }

    public String getObstacle_Pos_X() {
        return obstacle_Pos_X;
    }

    public void setObstacle_Pos_X(String obstacle_Pos_X) {
        this.obstacle_Pos_X = obstacle_Pos_X;
    }

    public String getOstacle_Pos_Y() {
        return ostacle_Pos_Y;
    }

    public void setOstacle_Pos_Y(String ostacle_Pos_Y) {
        this.ostacle_Pos_Y = ostacle_Pos_Y;
    }

    public String getObstacle_Rel_Vel_X() {
        return obstacle_Rel_Vel_X;
    }

    public void setObstacle_Rel_Vel_X(String obstacle_Rel_Vel_X) {
        this.obstacle_Rel_Vel_X = obstacle_Rel_Vel_X;
    }

    public String getObstacle_Type() {
        return Obstacle_Type;
    }

    public void setObstacle_Type(String obstacle_Type) {
        Obstacle_Type = obstacle_Type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObstacle_Brake_Lights() {
        return obstacle_Brake_Lights;
    }

    public void setObstacle_Brake_Lights(String obstacle_Brake_Lights) {
        this.obstacle_Brake_Lights = obstacle_Brake_Lights;
    }

    public String getCutInAndOut() {
        return cutInAndOut;
    }

    public void setCutInAndOut(String cutInAndOut) {
        this.cutInAndOut = cutInAndOut;
    }

    public String getBlinker_Info() {
        return blinker_Info;
    }

    public void setBlinker_Info(String blinker_Info) {
        this.blinker_Info = blinker_Info;
    }

    public String getObstacle_Valid() {
        return obstacle_Valid;
    }

    public void setObstacle_Valid(String obstacle_Valid) {
        this.obstacle_Valid = obstacle_Valid;
    }

    public MobileEye0x739Protocal() {
    }

    public MobileEye0x739Protocal(String id, String obstacle_ID, String obstacle_Pos_X, String ostacle_Pos_Y, String obstacle_Rel_Vel_X, String obstacle_Type, String status, String obstacle_Brake_Lights, String cutInAndOut, String blinker_Info, String obstacle_Valid) {
        this.id = id;
        this.obstacle_ID = obstacle_ID;
        this.obstacle_Pos_X = obstacle_Pos_X;
        this.ostacle_Pos_Y = ostacle_Pos_Y;
        this.obstacle_Rel_Vel_X = obstacle_Rel_Vel_X;
        Obstacle_Type = obstacle_Type;
        this.status = status;
        this.obstacle_Brake_Lights = obstacle_Brake_Lights;
        this.cutInAndOut = cutInAndOut;
        this.blinker_Info = blinker_Info;
        this.obstacle_Valid = obstacle_Valid;
    }

    @Override
    public String toString() {
        return "MobileEye0x739Protocal{" +
                "id='" + id + '\'' +
                ", obstacle_ID='" + obstacle_ID + '\'' +
                ", obstacle_Pos_X='" + obstacle_Pos_X + '\'' +
                ", ostacle_Pos_Y='" + ostacle_Pos_Y + '\'' +
                ", obstacle_Rel_Vel_X='" + obstacle_Rel_Vel_X + '\'' +
                ", Obstacle_Type='" + Obstacle_Type + '\'' +
                ", status='" + status + '\'' +
                ", obstacle_Brake_Lights='" + obstacle_Brake_Lights + '\'' +
                ", cutInAndOut='" + cutInAndOut + '\'' +
                ", blinker_Info='" + blinker_Info + '\'' +
                ", obstacle_Valid='" + obstacle_Valid + '\'' +
                '}';
    }
}
