package com.pl.firstSocket.controller;



import com.alibaba.fastjson.JSON;
import com.pl.firstSocket.Thread.ServerThread;
import com.pl.firstSocket.utils.RedisUtil;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;


@RestController
public class UserController {

    //开启服务器端
    @GetMapping("/connect")
    public void connect() {
//        System.out.println("建立连接");
//        /**
//         * 设置服务器相关信息
//         */
//
//        /**
//         * 基于TCP协议的Socket通信，实现用户登录，服务端
//         */
//        //1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
//        try {
//            //1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
//            ServerSocket serverSocket = new ServerSocket(6845);//1024-65535的某个端口
//            Socket socket = null;
//            //记录客户端数量
//            int count = 0;
//            System.out.println("服务器即将启动，等待客户端连接");
//            //循环侦听等待客户端请求
//            while (true) {
//                socket = serverSocket.accept();
//                ServerThread st = new ServerThread(socket);
//                st.start();
//                count++;
//                System.out.println("客户端数量为：" + count);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @GetMapping("/test")
    @ResponseBody
    public void testAdd(@RequestParam String info){
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
        Jedis jedis = pool.getResource();
        try {
            /// 开始使用
            jedis.rpush("info", info);
            System.out.println(jedis.llen("info"));

        } finally {
            /// 使用完后，将连接放回连接池
            if (null != jedis) {
                jedis.close();
            }
        }
        /// 应用退出时，关闭连接池:
        pool.destroy();

    }
}
