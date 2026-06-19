package com.demoing.clientStuff;

import com.demoing.MyLobby;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Objects;

public class Client {
    private final static String url = "wss://deployment-demo-i37o.onrender.com/ws";
    private MyLobby lobby;
    public Client(String input, MyLobby lobby) throws Exception{
        this.lobby=lobby;
        WebSocketClient client = new WebSocketClient(new URI(url)) {

            @Override
            public void onOpen(ServerHandshake handshakedata) {
                System.out.println("Connected");
                send(input);
                System.out.println("SENT:"+input);
            }

            @Override
            public void onMessage(String message) {
                System.out.println("OnMessage:"+message);
                if(Objects.equals(message, "Paired!")){
                    System.out.println("GAME START");
                    lobby.startGame();
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
}
