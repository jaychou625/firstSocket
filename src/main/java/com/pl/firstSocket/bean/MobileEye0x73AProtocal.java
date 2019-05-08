package com.pl.firstSocket.bean;
/**
 * MobileEye0x73A协议实体类
 * 0x73A + i*3 Details – 障碍物数据B
 */
public class MobileEye0x73AProtocal {
    //唯一标识，unix时间戳
    private String id;
    //障碍物长度 Obstacle_Length
    private String obstacle_Length;
    //障碍物宽度 Obstacle_Width
    private String obstacle_Width;
    //障碍物年龄  Obstacle_Age
    private String obstacle_Age;
    //障碍车道 Obstacle_Lane
    private String obstacle_Lane;
    //CIPV标志  CIPV_Flag
    private String cIPV_Flag;
    //雷达位置x  Radar_Pos_X
    private String radar_Pos_X;
    //雷达水平x   Radar_Vel_X
    private String radar_Vel_X;
    //雷达匹配置信度  Radar_Match_Confidence
    private String radar_Match_Confidence;
    //匹配的雷达_ID  Matched_Radar_ID
    private String matched_Radar_ID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObstacle_Length() {
        return obstacle_Length;
    }

    public void setObstacle_Length(String obstacle_Length) {
        this.obstacle_Length = obstacle_Length;
    }

    public String getObstacle_Width() {
        return obstacle_Width;
    }

    public void setObstacle_Width(String obstacle_Width) {
        this.obstacle_Width = obstacle_Width;
    }

    public String getObstacle_Age() {
        return obstacle_Age;
    }

    public void setObstacle_Age(String obstacle_Age) {
        this.obstacle_Age = obstacle_Age;
    }

    public String getObstacle_Lane() {
        return obstacle_Lane;
    }

    public void setObstacle_Lane(String obstacle_Lane) {
        this.obstacle_Lane = obstacle_Lane;
    }

    public String getcIPV_Flag() {
        return cIPV_Flag;
    }

    public void setcIPV_Flag(String cIPV_Flag) {
        this.cIPV_Flag = cIPV_Flag;
    }

    public String getRadar_Pos_X() {
        return radar_Pos_X;
    }

    public void setRadar_Pos_X(String radar_Pos_X) {
        this.radar_Pos_X = radar_Pos_X;
    }

    public String getRadar_Vel_X() {
        return radar_Vel_X;
    }

    public void setRadar_Vel_X(String radar_Vel_X) {
        this.radar_Vel_X = radar_Vel_X;
    }

    public String getRadar_Match_Confidence() {
        return radar_Match_Confidence;
    }

    public void setRadar_Match_Confidence(String radar_Match_Confidence) {
        this.radar_Match_Confidence = radar_Match_Confidence;
    }

    public String getMatched_Radar_ID() {
        return matched_Radar_ID;
    }

    public void setMatched_Radar_ID(String matched_Radar_ID) {
        this.matched_Radar_ID = matched_Radar_ID;
    }

    public MobileEye0x73AProtocal() {
    }

    public MobileEye0x73AProtocal(String id, String obstacle_Length, String obstacle_Width, String obstacle_Age, String obstacle_Lane, String cIPV_Flag, String radar_Pos_X, String radar_Vel_X, String radar_Match_Confidence, String matched_Radar_ID) {
        this.id = id;
        this.obstacle_Length = obstacle_Length;
        this.obstacle_Width = obstacle_Width;
        this.obstacle_Age = obstacle_Age;
        this.obstacle_Lane = obstacle_Lane;
        this.cIPV_Flag = cIPV_Flag;
        this.radar_Pos_X = radar_Pos_X;
        this.radar_Vel_X = radar_Vel_X;
        this.radar_Match_Confidence = radar_Match_Confidence;
        this.matched_Radar_ID = matched_Radar_ID;
    }

    @Override
    public String toString() {
        return "MobileEye0x73AProtocal{" +
                "id='" + id + '\'' +
                ", obstacle_Length='" + obstacle_Length + '\'' +
                ", obstacle_Width='" + obstacle_Width + '\'' +
                ", obstacle_Age='" + obstacle_Age + '\'' +
                ", obstacle_Lane='" + obstacle_Lane + '\'' +
                ", cIPV_Flag='" + cIPV_Flag + '\'' +
                ", radar_Pos_X='" + radar_Pos_X + '\'' +
                ", radar_Vel_X='" + radar_Vel_X + '\'' +
                ", radar_Match_Confidence='" + radar_Match_Confidence + '\'' +
                ", matched_Radar_ID='" + matched_Radar_ID + '\'' +
                '}';
    }
}
