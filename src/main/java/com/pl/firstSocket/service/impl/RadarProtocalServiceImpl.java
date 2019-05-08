package com.pl.firstSocket.service.impl;

import com.pl.firstSocket.bean.TcpInfo;
import com.pl.firstSocket.bean.UltrasonicRadarProtocal;
import com.pl.firstSocket.service.RadarProtocalService;
import com.pl.firstSocket.utils.TransitionUtil;

import java.text.DecimalFormat;
import java.util.Date;

public class RadarProtocalServiceImpl implements RadarProtocalService {
    @Override
    public UltrasonicRadarProtocal getRadarInfo(TcpInfo tcpinfo) {
        UltrasonicRadarProtocal ultrasonicRadarProtocal = new UltrasonicRadarProtocal();
        //设置雷达协议id信息
//        System.out.println("雷达协议ID：" + tcpinfo.getEquipmentNo() + "-" + tcpinfo.getUnixTime());
        ultrasonicRadarProtocal.setId(tcpinfo.getUnixTime());
        //超声波雷达后4探1号探头距离，S12，tail4算法设置
        String s12Tail4 = tcpinfo.getInfoContent().substring(0,2);
        s12Tail4 = TransitionUtil.radarTransfor(s12Tail4);
        ultrasonicRadarProtocal.setS12Tail4(s12Tail4);
//        System.out.println(ultrasonicRadarProtocal.getS12Tail4());
        //超声波雷达后4探2号探头距离,S11，tail3
        String s11Tail3 = tcpinfo.getInfoContent().substring(2,4);
        s11Tail3 = TransitionUtil.radarTransfor(s11Tail3);
        ultrasonicRadarProtocal.setS11Tail3(s11Tail3);
//        System.out.println(ultrasonicRadarProtocal.getS11Tail3());
        //超声波雷达后4探4号探头距离,S10,tail2
        String s10Tail2 = tcpinfo.getInfoContent().substring(4,6);
        s10Tail2 = TransitionUtil.radarTransfor(s10Tail2);
        ultrasonicRadarProtocal.setS10Tail2(s10Tail2);
//        System.out.println(ultrasonicRadarProtocal.getS10Tail2());
        //超声波雷达后4探3号探头距离,S9,tail1
        String s9Tai11 = tcpinfo.getInfoContent().substring(6,8);
        s9Tai11 = TransitionUtil.radarTransfor(s9Tai11);
        ultrasonicRadarProtocal.setS9Tail1(s9Tai11);
//        System.out.println(ultrasonicRadarProtocal.getS9Tail1());
        //超声波雷达前8探1号探头距离,S1,head1
        String s1Head1 = tcpinfo.getInfoContent().substring(8,10);
        s1Head1 = TransitionUtil.radarTransfor(s1Head1);
        ultrasonicRadarProtocal.setS1Head1(s1Head1);
//        System.out.println(ultrasonicRadarProtocal.getS1Head1());
        //超声波雷达前8探2号探头距离,S2,head2
        String s2Head2 = tcpinfo.getInfoContent().substring(10,12);
        s2Head2 = TransitionUtil.radarTransfor(s2Head2);
        ultrasonicRadarProtocal.setS2Head2(s2Head2);
//        System.out.println(ultrasonicRadarProtocal.getS2Head2());
        //超声波雷达前8探4号探头距离,S3,head3
        String s3Head3 = tcpinfo.getInfoContent().substring(12,14);
        s3Head3 = TransitionUtil.radarTransfor(s3Head3);
        ultrasonicRadarProtocal.setS3Head3(s3Head3);
//        System.out.println(ultrasonicRadarProtocal.getS3Head3());
        //超声波雷达前8探5号探头距离(4探时忽略此数据 ),S4,head4
        String s4Head4 = tcpinfo.getInfoContent().substring(14,16);
        s4Head4 = TransitionUtil.radarTransfor(s4Head4);
        ultrasonicRadarProtocal.setS4Head4(s4Head4);
//        System.out.println(ultrasonicRadarProtocal.getS4Head4());
        //超声波雷达前8探6号探头距离(4探时忽略此数据 ),S5,right1
        String s5Right1 = tcpinfo.getInfoContent().substring(16,18);
        s5Right1 = TransitionUtil.radarTransfor(s5Right1);
        ultrasonicRadarProtocal.setS5Right1(s5Right1);
//        System.out.println(ultrasonicRadarProtocal.getS5Right1());
        //超声波雷达前8探7号探头距离(4探时忽略此数据 ),S6,right2
        String s6Right2 = tcpinfo.getInfoContent().substring(18,20);
        s6Right2 = TransitionUtil.radarTransfor(s6Right2);
        ultrasonicRadarProtocal.setS6Right2(s6Right2);
//        System.out.println(ultrasonicRadarProtocal.getS6Right2());
        //超声波雷达前8探8号探头距离(4探时忽略此数据 ),S7,left1
        String s7Left1 = tcpinfo.getInfoContent().substring(20,22);
        s7Left1 = TransitionUtil.radarTransfor(s7Left1);
        ultrasonicRadarProtocal.setS7Left1(s7Left1);
//        System.out.println(ultrasonicRadarProtocal.getS7Left1());
        //超声波雷达前8探3号探头距离,S8,left2
        String s8Left2 = tcpinfo.getInfoContent().substring(22,24);
        s8Left2 = TransitionUtil.radarTransfor(s8Left2);
        ultrasonicRadarProtocal.setS8Left2(s8Left2);
//        System.out.println(ultrasonicRadarProtocal.getS8Left2());
        /**
         * 状态信号(左转，右转，倒车，车速) (不需要时忽略此数据 )
         *其中：  bit0: 左转(为1时表示左转开，为0时表示左转关) 忽略
         *bit1: 右转(为1时表示右转开，为0时表示右转关) 忽略
         *bit2: 倒车(为1时表示倒车状态，为0时表示非倒车状态) 忽略
         *bit3: 车速(为1时表示车速低于10KM/H，为0时表示车速高于10KM/H) 忽略
         */
        String lightState = tcpinfo.getInfoContent().substring(24,26);
        lightState = TransitionUtil.hexString2binaryString(lightState);
        String[] sArr = lightState.split("");
        if(sArr[7].equals("0")){
            lightState = "左转关";
        }else{
            lightState = "左转开";
        }
        if(sArr[6].equals("0")){
            lightState += ";右转关";
        }else{
            lightState += ";右转开";
        }
        if(sArr[5].equals("0")){
            lightState += ";非倒车状态";
        }else{
            lightState += ";倒车状态";
        }
        if(sArr[4].equals("0")){
            lightState += ";车速高于10KM/H";
        }else{
            lightState += ";车速低于10KM/H";
        }
        ultrasonicRadarProtocal.setLightState(lightState);
//        System.out.println(ultrasonicRadarProtocal.getLightState());
        //车速（单位KM/H）忽略
        String speed = tcpinfo.getInfoContent().substring(26,28);
        speed = String.valueOf(Integer.parseInt(speed,16));
        ultrasonicRadarProtocal.setSpeed(speed);
//        System.out.println(ultrasonicRadarProtocal.getSpeed());
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
        String radarState = tcpinfo.getInfoContent().substring(28,30);
        radarState = TransitionUtil.hexString2binaryString(radarState);
        String[] sArr2 = radarState.split("");
        if(sArr2[7].equals("0")){
            radarState = "左侧微波雷达未报警";
        }else{
            radarState = "左侧微波雷达报警";
        }
        if(sArr2[6].equals("0")){
            radarState += ";左后微波雷达不存在";
        }else{
            radarState += ";左后微波雷达存在";
        }
        if(sArr2[5].equals("0")){
            radarState += ";左前微波雷达不存在";
        }else{
            radarState += ";左前微波雷达存在";
        }
        if(sArr2[3].equals("0")){
            radarState += ";右侧微波雷达未报警";
        }else{
            radarState += ";右侧微波雷达报警";
        }
        if(sArr2[2].equals("0")){
            radarState += ";右后微波雷达不存在";
        }else{
            radarState += ";右后微波雷达存在";
        }
        if(sArr2[1].equals("0")){
            radarState += ";右前微波雷达不存在";
        }else{
            radarState += ";右前微波雷达存在";
        }
        ultrasonicRadarProtocal.setRadarState(radarState);
//        System.out.println(ultrasonicRadarProtocal.getRadarState());
        /**
         * 报警声音。
         * Bit4~bit0:超声波雷达声音报警（其中0：不报警； 1:1HZ报警; 2:2HZ报警;3:4HZ报警; 4:长鸣; 其它值：无效 ）
         * Bit5: 左侧微波雷达声音报警（0：不报警； 1:左侧微波雷达报警;）
         * Bit6: 右侧微波雷达声音报警（0：不报警； 1:右侧微波雷达报警;）
         */
        String alertState = tcpinfo.getInfoContent().substring(30,32);
        alertState = TransitionUtil.hexString2binaryString(alertState);
        String[] sArr3 = alertState.split("");
        if(sArr3[7].equals("0")){
            alertState = "超声波雷达声音未报警";
        }else{
            alertState = "超声波雷达声音1HZ报警";
        }
        if(sArr3[6].equals("0")){
            alertState = "超声波雷达声音未报警";
        }else{
            alertState = "超声波雷达声音2HZ报警";
        }
        if(sArr3[5].equals("0")){
            alertState = "超声波雷达声音未报警";
        }else{
            alertState = "超声波雷达声音3HZ报警";
        }
        if(sArr3[4].equals("0")){
            alertState = "超声波雷达声音未报警";
        }else{
            alertState = "超声波雷达声音4HZ报警";
        }
        if(sArr3[3].equals("0")){
            alertState += ";左侧微波雷达声音未报警";
        }else{
            alertState += ";左侧微波雷达声音报警";
        }
        if(sArr3[2].equals("0")){
            alertState += ";右侧微波雷达声音未报警";
        }else{
            alertState += ";右侧微波雷达声音报警";
        }
        ultrasonicRadarProtocal.setAlertState(alertState);
//        System.out.println(ultrasonicRadarProtocal.getAlertState());

        return ultrasonicRadarProtocal;
    }
}
