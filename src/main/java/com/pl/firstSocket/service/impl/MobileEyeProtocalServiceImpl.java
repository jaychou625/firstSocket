package com.pl.firstSocket.service.impl;

import com.pl.firstSocket.bean.MobileEye0x739Protocal;
import com.pl.firstSocket.bean.MobileEye0x73AProtocal;
import com.pl.firstSocket.bean.TcpInfo;
import com.pl.firstSocket.service.MobileEyeProtocalService;
import com.pl.firstSocket.utils.TransitionUtil;

public class MobileEyeProtocalServiceImpl implements MobileEyeProtocalService {
    @Override
    public Object getMobileEyeInfo(TcpInfo tcpInfo) {
        Object obj = null;
        //获取协议消息号
        String protocalNo = tcpInfo.getInfoContent().substring(0,4);
        //根据协议消息号判断执行不同程序
        if(protocalNo.equals("0739")){
            obj = getMobileEye0x739ProtocalInfo(tcpInfo);
        }else if(protocalNo.equals("073A")){
            obj = getMobileEye0x73AProtocalInfo(tcpInfo);
        }
        return obj;
    }

    /**
     * 消息ID:0739执行程序
     * @param tcpInfo
     * @return
     */
    private static MobileEye0x739Protocal getMobileEye0x739ProtocalInfo(TcpInfo tcpInfo){
        MobileEye0x739Protocal mobileEye0x739Protocal = new MobileEye0x739Protocal();
        String content = tcpInfo.getInfoContent().substring(4);
        //设置ID unix时间戳
        mobileEye0x739Protocal.setId(tcpInfo.getUnixTime());
        //障碍物编号 Obstacle_ID,16进制转换10进制即可
        String obstacleId = String.valueOf(Integer.parseInt(content.substring(0,2),16));
        mobileEye0x739Protocal.setObstacle_ID(obstacleId);
        //障碍物位置x  Obstacle_Pos_X,复合字节，调用第二字节和第三字节的低4位，无符号数
        String obstaclePosX = TransitionUtil.hexString2binaryString(content.substring(4,6)) + TransitionUtil.hexString2binaryString(content.substring(2,4));
        obstaclePosX = obstaclePosX.substring(4);
        obstaclePosX = Integer.valueOf(obstaclePosX,2).toString();
        double temp = Double.parseDouble(obstaclePosX) *  0.0625;
        obstaclePosX = String.valueOf(temp) + "米";
        mobileEye0x739Protocal.setObstacle_Pos_X(obstaclePosX);
        //障碍物位置 Y  Obstacle_Pos_Y,有符号数
        String obstaclePosY = TransitionUtil.hexString2binaryString(content.substring(8,10)) + TransitionUtil.hexString2binaryString(content.substring(6,8));
        obstaclePosY = obstaclePosY.substring(6);
        obstaclePosY = TransitionUtil.binaryString2hexString(obstaclePosY);
        obstaclePosY = TransitionUtil.hex2Dec(obstaclePosY);
        temp = Double.parseDouble(obstaclePosY) *  0.0625;
        obstaclePosY = String.valueOf(temp) + "米";
        mobileEye0x739Protocal.setOstacle_Pos_Y(obstaclePosY);
        //障碍物释放速度x Obstacle_Rel_Vel_X 有符号数
        String obstacleRelVelX = TransitionUtil.hexString2binaryString(content.substring(12,14)) + TransitionUtil.hexString2binaryString(content.substring(10,12));
        obstacleRelVelX = obstacleRelVelX.substring(4);
        obstacleRelVelX = TransitionUtil.binaryString2hexString(obstacleRelVelX);
        obstacleRelVelX = TransitionUtil.hex2Dec(obstacleRelVelX);
        temp = Double.parseDouble(obstacleRelVelX) *  0.0625;
        obstacleRelVelX = String.valueOf(temp) + "米/秒";
        mobileEye0x739Protocal.setObstacle_Rel_Vel_X(obstacleRelVelX);
        //障碍物类型  Obstacle_Type 无符号
        String obstacleType = TransitionUtil.hexString2binaryString(content.substring(12,14));
        obstacleType = obstacleType.substring(1,4);
        switch (obstacleType){
            case "000":
                obstacleType = "Vehicle";
                break;
            case "001":
                obstacleType = "Truck";
                break;
            case "010":
                obstacleType = "Bike";
                break;
            case "011":
                obstacleType = "Ped";
                break;
            case "100":
                obstacleType = "Bicycle";
                break;
            case "101":
                obstacleType = "unused";
                break;
            case "110":
                obstacleType = "unused";
                break;
            case "111":
                obstacleType = "unused";
                break;
                default:
                    obstacleType = "unused";
                    break;
        }
        mobileEye0x739Protocal.setObstacle_Type(obstacleType);
        //障碍物状态  Obstacle_Status 无符号
        String obstacleStatus = TransitionUtil.hexString2binaryString(content.substring(14,16));
        obstacleStatus = obstacleStatus.substring(5);
        obstacleStatus = String.valueOf(Integer.parseInt(obstacleStatus,2));
        switch (obstacleStatus){
            case "0":
                obstacleStatus = "Undefined";
                break;
            case "1":
                obstacleStatus = "Standing";
                break;
            case "2":
                obstacleStatus = "Stopped";
                break;
            case "3":
                obstacleStatus = "Moving";
                break;
            case "4":
                obstacleStatus = "Oncoming";
                break;
            case "5":
                obstacleStatus = "Parked";
                break;
            case "6":
                obstacleStatus = "Unused";
                break;
                default:
                    obstacleStatus = "Unused";
                    break;
        }
        mobileEye0x739Protocal.setStatus(obstacleStatus);
        //障碍物制动灯  Obstacle_Brake_Lights 0:关闭，1：开启
        String obstacleBrakeLights = TransitionUtil.hexString2binaryString(content.substring(14,16));
        obstacleBrakeLights = obstacleBrakeLights.substring(4,5);
        if(obstacleBrakeLights.equals("0")){
            obstacleBrakeLights = "障碍制动灯关闭";
        }else if(obstacleBrakeLights.equals("1")){
            obstacleBrakeLights = "障碍制动灯关闭";
        }else{
            obstacleBrakeLights = "未知状况";
        }
        mobileEye0x739Protocal.setObstacle_Brake_Lights(obstacleBrakeLights);
        //切入和切出  Cut in and out 无符号数
        String cutInAndOut = TransitionUtil.hexString2binaryString(content.substring(8,10));
        cutInAndOut = cutInAndOut.substring(0,3);
        cutInAndOut = String.valueOf(Integer.parseInt(cutInAndOut,2));
        switch (cutInAndOut){
            case "0":
                cutInAndOut = "undefined";
                break;
            case "1":
                cutInAndOut = "in_host_lane";
                break;
            case "2":
                cutInAndOut = "out_host_lane";
                break;
            case "3":
                cutInAndOut = "cut_in";
                break;
            case "4":
                cutInAndOut = "cut_out";
                break;
                default:
                    cutInAndOut = "undefined";
                    break;
        }
        mobileEye0x739Protocal.setCutInAndOut(cutInAndOut);
        //闪光灯信息  Blinker Info 无符号数
        String blinkerInfo = TransitionUtil.hexString2binaryString(content.substring(8,10));
        blinkerInfo = blinkerInfo.substring(3,6);
        blinkerInfo = String.valueOf(Integer.parseInt(blinkerInfo,2));
        switch (blinkerInfo){
            case "0":
                blinkerInfo = "unavailable";
                break;
            case "1":
                blinkerInfo = "off";
                break;
            case "2":
                blinkerInfo = "left";
                break;
            case "3":
                blinkerInfo = "right";
                break;
            case "4":
                blinkerInfo = "both";
                break;
                default:
                    blinkerInfo = "unavailable";
                    break;
        }
        mobileEye0x739Protocal.setBlinker_Info(blinkerInfo);
        //障碍物时效（新/旧)Obstacle_Valid
        String obstacleValid = TransitionUtil.hexString2binaryString(content.substring(14,16));
        obstacleValid = obstacleValid.substring(0,2);
        obstacleValid = String.valueOf(Integer.parseInt(obstacleValid,2));
        if(obstacleValid.equals("1")){
            obstacleValid = "New valid";
        }else if(obstacleValid.equals("2")){
            obstacleValid = "Older valid";
        }else{
            obstacleValid = "unavailable";
        }
        mobileEye0x739Protocal.setObstacle_Valid(obstacleValid);
        return mobileEye0x739Protocal;
    }

    /**
     * 消息ID:073A执行程序
     * @param tcpInfo
     * @return
     */
    private static MobileEye0x73AProtocal getMobileEye0x73AProtocalInfo(TcpInfo tcpInfo){
        MobileEye0x73AProtocal mobileEye0x73AProtocal = new MobileEye0x73AProtocal();
        String content = tcpInfo.getInfoContent().substring(4);
        //设置ID unix时间戳
        mobileEye0x73AProtocal.setId(tcpInfo.getUnixTime());
        //障碍物长度 Obstacle_Length 无符号数
        String obstacleLength = content.substring(0,2);
        double temp = Integer.parseInt(obstacleLength,16);
        obstacleLength = String.valueOf(temp * 0.5) + "米";
        mobileEye0x73AProtocal.setObstacle_Length(obstacleLength);
        //障碍物宽度 Obstacle_Width 无符号数
        String obstacleWidth = content.substring(2,4);
        temp = Integer.parseInt(obstacleWidth,16);
        obstacleWidth = String.valueOf(temp * 0.5) + "米";
        mobileEye0x73AProtocal.setObstacle_Width(obstacleWidth);
        //障碍物年龄  Obstacle_Age 无符号数
        String obstacleAge = content.substring(4,6);
        obstacleAge = String.valueOf(Integer.parseInt(obstacleAge,16));
        mobileEye0x73AProtocal.setObstacle_Age(obstacleAge);
        //障碍车道 Obstacle_Lane 无符号数
        String obstacleLane = content.substring(6,8);
        obstacleLane = TransitionUtil.hexString2binaryString(obstacleLane);
        obstacleLane = obstacleLane.substring(6);
        obstacleLane = String.valueOf(Integer.parseInt(obstacleLane,2));
        switch (obstacleLane){
            case "0":{
                obstacleLane = "Not assigned";
                break;
            }
            case "1":{
                obstacleLane = "Ego lane";
                break;
            }
            case "2":{
                obstacleLane = "Next lane";
                break;
            }
            case "3":{
                obstacleLane = "Invalid signa";
                break;
            }
        }
        mobileEye0x73AProtocal.setObstacle_Lane(obstacleLane);
        //CIPV标志  CIPV_Flag 无符号数
        String cipFlag = content.substring(6,8);
        cipFlag = TransitionUtil.hexString2binaryString(cipFlag);
        cipFlag = cipFlag.substring(5,6);
        if(cipFlag.equals("0")){
            cipFlag = "Not CIPV";
        }else if(cipFlag.equals("1")){
            cipFlag = "CIPV";
        }
        mobileEye0x73AProtocal.setcIPV_Flag(cipFlag);
        //雷达位置x  Radar_Pos_X 无符号数
        String radarPosX = TransitionUtil.hexString2binaryString(content.substring(8,10)) + TransitionUtil.hexString2binaryString(content.substring(6,8)).substring(0,4);
        temp = Integer.parseInt(radarPosX,2) * 0.0625;
        radarPosX = String.valueOf(temp) + "米";
        mobileEye0x73AProtocal.setRadar_Pos_X(radarPosX);
        //雷达水平x   Radar_Vel_X 有符号数
        String radarVelX = TransitionUtil.hexString2binaryString(content.substring(12,14)).substring(4) + TransitionUtil.hexString2binaryString(content.substring(10,12));
        radarVelX = TransitionUtil.binaryString2hexString(radarVelX);
        temp = Double.parseDouble(TransitionUtil.hex2Dec(radarVelX)) * 0.0625;
        radarVelX = String.valueOf(temp) + "米/秒";
        mobileEye0x73AProtocal.setRadar_Vel_X(radarVelX);
        //雷达匹配置信度  Radar_Match_Confidence 无符号数
        String radarMatchConfidence = TransitionUtil.hexString2binaryString(content.substring(12,14)).substring(1,4);
        radarMatchConfidence = String.valueOf(Integer.parseInt(radarMatchConfidence,2));
        switch (radarMatchConfidence){
            case "0":
                radarMatchConfidence = "No match";
                break;
            case "1":
                radarMatchConfidence = "Multi match";
                break;
            case "2":
                radarMatchConfidence = "middle confidence match";
                break;
            case "3":
                radarMatchConfidence = "middle confidence match";
                break;
            case "4":
                radarMatchConfidence = "middle confidence match";
                break;
            case "5":
                radarMatchConfidence = "high confidence match";
                break;
                default:
                    radarMatchConfidence = "No match";
                    break;
        }
        mobileEye0x73AProtocal.setRadar_Match_Confidence(radarMatchConfidence);
        //匹配的雷达_ID  Matched_Radar_ID 无符号数
        String matchedRadarId = TransitionUtil.hexString2binaryString(content.substring(14,16)).substring(1);
        matchedRadarId = TransitionUtil.binaryString2hexString(matchedRadarId);
        matchedRadarId = String.valueOf(Integer.parseInt(matchedRadarId,16));
        mobileEye0x73AProtocal.setMatched_Radar_ID(matchedRadarId);

        return  mobileEye0x73AProtocal;
    }
}
