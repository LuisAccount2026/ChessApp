package com.demoing.clientStuff;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Objects;

public class Client {
    private final static String url = "wss://deployment-demo-i37o.onrender.com/ws";
    private MyMenu menu;
    private WebSocketClient client;
    public Client(String input, MyMenu menu) throws Exception{
        this.menu=menu;
        client = new WebSocketClient(new URI(url)) {

            @Override
            public void onOpen(ServerHandshake handshakedata) {
                //System.out.println("Connected");
                send(input);
                //System.out.println("SENT:"+input);
            }

            @Override
            public void onMessage(String message) {
                if(Objects.equals(message,"Not your turn")){
                    //System.out.println("NotYOURturn___________");
                    return;
                }else if(Objects.equals(message,"Wait for your turn")){
                    //System.out.println("NotYOURturn___________");
                    return;
                }
                //System.out.println("OnMessage:"+message);
                if(message.startsWith("CODE:")){
                    String code = message.substring(5);
                    menu.hostConnect(code);
                }
                if(Objects.equals(message, "Paired!")){
                    //System.out.println("GAME START");
                    menu.startGame();
                }
                if(message.startsWith("MOVE:")){
                    String move = message.substring(5);
                    //System.out.println(move);
                    menu.receivedMove(move);
                }
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                System.out.println("Closed: "+reason);
            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
            }
        };
        client.connect();
    }

    public void sendMessage(String message){
        client.send(message);
    }
}
