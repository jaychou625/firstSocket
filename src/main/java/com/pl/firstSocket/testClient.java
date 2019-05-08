package com.pl.firstSocket;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.pl.firstSocket.bean.MobileEye0x739Protocal;
import com.pl.firstSocket.bean.UltrasonicRadarProtocal;
import com.pl.firstSocket.bean.User;
import com.pl.firstSocket.utils.MapSort;
import com.pl.firstSocket.utils.TransitionUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import javax.xml.crypto.Data;
import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class testClient {
    @Test
    public void client(){
        //客户端
        //1、创建客户端Socket，指定服务器地址和端口
        System.out.println("socket");
        Socket socket = null;
            try {
                socket = new Socket("localhost",6845);
                //2、获取输出流，向服务器端发送信息
                OutputStream os = socket.getOutputStream();//字节输出流
                PrintWriter pw =new PrintWriter(os);//将输出流包装成打印流
                //雷达协议
//                pw.write("55AA004131423243334434999A1F876A01000001108C96FCFCFCFCFCFC64FCFCFC0B0C0D0EBDCC");
                //MobileEye0x739协议
//                pw.write("55AA004131423243334434999A1F876A010000000A07390B198378CD378765BDCC");
                //MobileEye0x73A协议
                pw.write("55AA004131423243334434999A1F876A010000000A073A0B196378CD378795BDCC");
                pw.flush();
                socket.shutdownOutput();
                //3、获取输入流，并读取服务器端的响应信息
                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String info = null;
                while((info=br.readLine())!= null){
                    System.out.println("我是客户端，服务器说："+info);
                }

                //4、关闭资源
                br.close();
                is.close();
                pw.close();
                os.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Test
    public void test(){
        //获取最近一次数据库对应表内容
        Jedis jedis = new Jedis("127.0.0.1",6379);
        Map<String,String> map = jedis.hgetAll("Radar-A1B2C3D4");
        map = MapSort.sortMapByKey(map);
        Iterator<Map.Entry<String,String>> entries = map.entrySet().iterator();
        Map.Entry<String,String> entry = entries.next();
//        System.out.println("key="+entry.getKey()+" value = "+entry.getValue());
        UltrasonicRadarProtocal ultrasonicRadarProtocal = JSON.parseObject(entry.getValue(),new TypeReference<UltrasonicRadarProtocal>() {});
        System.out.println(ultrasonicRadarProtocal.getId());
        Map<String,String> mobileEye = jedis.hgetAll("MobileEye-0739-A1B2C3D4");
        mobileEye = MapSort.sortMapByKey(mobileEye);
        entries = mobileEye.entrySet().iterator();
        entry = entries.next();
        MobileEye0x739Protocal mobileEye0x739Protocal = JSON.parseObject(entry.getValue(),new TypeReference<MobileEye0x739Protocal>() {});
        System.out.println(mobileEye0x739Protocal.toString());
//        while(entries.hasNext()){
//            Map.Entry<String,String> entry = entries.next();
//            System.out.println("key="+entry.getKey()+" value = "+entry.getValue());
//        }
//        System.out.println(JSON.toJSONString(map));

//        System.out.println(Integer.parseInt("0178",16));
//        System.out.println("有符号： "+ TransitionUtil.hex2Dec("0178"));
    }

    @Test
    public void translate(){
//        String str = TransitionUtil.hex2Str("4131423243334434");
//        System.out.print(str);

        String str = "0000016a871f9a99";
        String date = TransitionUtil.hexString2TimeStamp(str);
        System.out.println(date);

//        String s = Integer.toString(time,16);
//        System.out.println(s.toUpperCase());

//        String s = TransitionUtil.hexString2binaryString("8C");
//        System.out.println(s);
//        String hex = TransitionUtil.binaryString2hexString(s);
//        System.out.println(hex);

//        for(int i=0;i<10;i++){
////            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//            String uuid = UUID.randomUUID().toString();
//            System.out.println(uuid);
//        }

//        String s = "999a1f876a010000";
//        TransitionUtil.toLittle(s);

    }

    @Test
    public void testJedis(){
        //连接redis
        Jedis jedis = new Jedis("127.0.0.1",6379);

        Map<String, String> map = new HashMap<String,String>();
        final String USER_TABLE = "USER_TABLE";
        //查询年龄为15,性别为n

        final String USER_TABLE_AGE_15 = "USER_TABLE_AGE_15";
        final String USER_TABLE_SEX_m = "USER_TABLE_SEX_m";
        final String USER_TABLE_SEX_n = "USER_TABLE_SEX_n";

        //向缓存中存入5条数据组成的map
        String uuid1 = UUID.randomUUID().toString();
        User user1 = new User(uuid1, "y1", "m", 15);
        //将对象转为json
        map.put(uuid1, JSON.toJSONString(user1));

        String uuid2 = UUID.randomUUID().toString();
        User user2 = new User(uuid2, "y2", "m", 18);
        map.put(uuid2, JSON.toJSONString(user2));

        String uuid3 = UUID.randomUUID().toString();
        User user3 = new User(uuid3, "y3", "n", 25);
        map.put(uuid3, JSON.toJSONString(user3));

        String uuid4 = UUID.randomUUID().toString();
        User user4 = new User(uuid4, "y4", "n", 15);
        map.put(uuid4, JSON.toJSONString(user4));

        String uuid5 = UUID.randomUUID().toString();
        User user5 = new User(uuid5, "y5", "m", 25);
        map.put(uuid5, JSON.toJSONString(user5));

        //把map存到缓存中
        jedis.hmset("USER_TABLE", map);



    }

}
