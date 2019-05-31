package com.zs.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author fei
 * @title: WebSocket
 * @projectName sell
 * @description: WebSocket后端服务
 * @date 2019/5/26 14:56
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    /** 创建一个会话*/
    private Session session;

    /***创建一个session容器 */
    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();

    /**
     * 打开连接
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSockets.add(this);
        log.info("【webSocket消息】 有新的连接，总数：{}",webSockets.size());
    }

    /**
     * 断开连接的使用
     */
    @OnClose
    public void onClose(){
        webSockets.remove(this);
        log.info("【webSocket消息】连接断开，总数：{}",webSockets.size());
    }

    /**
     * 通信消息
     * @param message
     */
    @OnMessage
    public void onMessage(String message){
        log.info("【webSocket消息】收到客户端的消息；{}",message);
    }

    /**
     * 广播发送消息
     * @param message
     */
    public void sendMessage(String message){
        for(WebSocket webSocket: webSockets){
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
