package com.huang.oa.ws;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.huang.oa.util.MessageUtils;
import com.huang.oa.ws.pojo.Message;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat/{satoken}")
@Component
@Slf4j
public class ChatEndpoint {

    private static final Map<String, Session> onlineUsers = new ConcurrentHashMap<String, Session>();

    private SaSession saSession;

    /**
     * 建立链接后被调用
     * @param session
     * @param satoken
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("satoken")String satoken) {
        //1. 将session进行保存
        Object userId = StpUtil.getLoginIdByToken(satoken);
        this.saSession = StpUtil.getSessionByLoginId(userId);
        log.info("用户{}进入聊天室",saSession.getLoginId().toString());
        onlineUsers.put(saSession.getLoginId().toString(), session);
        //2. 广播消息。需要将登陆的所有用户推送给所有用户
        String message = MessageUtils.getMessage(true,null,getFriends());
        broadcastAllUser(message);
    }

    private Set<String> getFriends() {
        return onlineUsers.keySet();
    }

    /**
     * 浏览器发送消息时被调用
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        try {
            Message msgs = JSON.parseObject(message, Message.class);
            // 获取 toName
            String toName = msgs.getToName();
            String msg = msgs.getMessage();
            onlineUsers.get(toName).getBasicRemote().sendText(MessageUtils.getMessage(false,this.saSession.getLoginId().toString(),msg));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 断开连接时被调用
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        //1. 从map集合中剔除session对象
        onlineUsers.remove(this.saSession.getLoginId().toString());
        //2. 通知其他所有的用户，当前用户下线了
        String message = MessageUtils.getMessage(true, null, getFriends());
        broadcastAllUser(message);
    }

    private void broadcastAllUser(String message) {
        Set<Map.Entry<String, Session>> entries = onlineUsers.entrySet();

        entries.forEach(entry -> {
            try {
                entry.getValue().getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}
