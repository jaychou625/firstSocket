package com.pl.firstSocket.Thread;

import com.alibaba.fastjson.JSON;
import com.pl.firstSocket.bean.MobileEye0x739Protocal;
import com.pl.firstSocket.bean.TcpInfo;
import com.pl.firstSocket.bean.UltrasonicRadarProtocal;
import com.pl.firstSocket.controller.UserController;
import com.pl.firstSocket.service.MobileEyeProtocalService;
import com.pl.firstSocket.service.RadarProtocalService;
import com.pl.firstSocket.service.TcpService;
import com.pl.firstSocket.service.impl.MobileEyeProtocalServiceImpl;
import com.pl.firstSocket.service.impl.RadarProtocalServiceImpl;
import com.pl.firstSocket.service.impl.TcpServiceImpl;
import com.pl.firstSocket.utils.TransitionUtil;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务器端线程处理类
 */
public class ServerThread extends Thread {
    //和本线程相关的socket
    Socket socket = null;
    UserController uc = new UserController();
    TcpService tcpService = new TcpServiceImpl();
    public ServerThread(Socket socket){
        this.socket = socket;
    }
    private static long oldTime = new Date().getTime();
    private  static  boolean flag = false;


    //线程执行的操作，响应客户端请求
    public void run(){
        //3、获取输入流，并读取客户端信息
        Map<String, String> map = new HashMap<String,String>();
        UltrasonicRadarProtocal ultrasonicRadarProtocal = null;
        Object obj = null;
        String messageId = "";
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            //获取客户端输入流文件
            //读取字节流
            is = socket.getInputStream();
//            byte[] buffer = new byte[1024];
//            int len = -1;
//            is.read(buffer);
//            String entireInfo = bytesToHexString(buffer);

            //测试用例，读取模拟客户端发送的报文字符串
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String entireInfo = br.readLine();
//            System.out.println(entireInfo);
            //获取有效报文
//            String infometion = entireInfo.substring(0,entireInfo.indexOf("55AA",4));
            //根据有效信息生成tcp数据对象，后续解析要用
            String infometion = entireInfo;
            TcpInfo tcpInfo = tcpService.getTcpInfo(infometion);
//            System.out.println(tcpInfo.toString());
            //判断消息类型，做不同处理，雷达协议，以及mobileEye协议，通过对象的infoType来判断，00：mobileEye，01：雷达
            if(tcpInfo.getInfoType().equals("00")){
                //处理mobileEye协议
                MobileEyeProtocalService mobileEyeProtocalService = new MobileEyeProtocalServiceImpl();
                obj = mobileEyeProtocalService.getMobileEyeInfo(tcpInfo);
                messageId = tcpInfo.getInfoContent().substring(0,4);
            }else if(tcpInfo.getInfoType().equals("01")){
                //处理雷达协议
                RadarProtocalService radarProtocalService = new RadarProtocalServiceImpl();
                ultrasonicRadarProtocal = radarProtocalService.getRadarInfo(tcpInfo);

            }else{
                //协议有误处理
            }

            //存储数据库操作
            //时间控制，5秒存储一次
            long nowTime = new Date().getTime();
            if(nowTime - oldTime >= 5000 || flag == false){
                oldTime = nowTime;
                flag = true;
                if(ultrasonicRadarProtocal != null){
                    map.put(tcpInfo.getUnixTime(), JSON.toJSONString(ultrasonicRadarProtocal));
                    Jedis jedis = new Jedis("127.0.0.1",6379);
                    jedis.hmset("Radar-" + tcpInfo.getEquipmentNo(),map);
                    jedis.close();
                    ultrasonicRadarProtocal = null;
                    map = null;
                }
                if(obj != null){
                    map.put(tcpInfo.getUnixTime(),JSON.toJSONString(obj));
                    Jedis jedis = new Jedis("127.0.0.1",6379);
                    jedis.hmset("MobileEye-" + messageId + "-" + tcpInfo.getEquipmentNo(),map);
                    jedis.close();
                    obj = null;
                }
            }else{
                System.out.println("时间没到5秒");
            }

//            socket.shutdownInput();//关闭输入流
            //4、获取输出流，响应客户端的请求
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
//            pw.write("欢迎您！");
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(pw != null)
                    pw.close();
                if(os != null)
                    os.close();
                if(br != null)
                    br.close();
                if(isr != null)
                    isr.close();
                if(is != null)
                    is.close();
                if(socket != null)
                    socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    //字节转码函数
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp =Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }
}
