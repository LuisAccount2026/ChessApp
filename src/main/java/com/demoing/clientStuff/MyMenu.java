package com.demoing.clientStuff;

import com.demoing.MyFrame;
import com.demoing.MyLobby;
import com.demoing.clientStuff.Client;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class MyMenu extends JFrame implements ActionListener{
    JButton local;
    JButton hostButton;
    public boolean waiting=true;
    JButton joinButton;

    private boolean host=false;
    private boolean join=false;

    private Client client;
    //TEXTFIELD TRY
    JTextField textField;
    //TRY
    public MyMenu(){
        //JTEXTFIELD = a gui textbox component that can be used to add aet or get text

        textField = new JTextField();
        textField.setBounds(200,100,100,50);
        textField.setText("XXXX");
        this.add(textField);


        //END OF JTEXTFIELD TRY
        local = new JButton();
        local.setBounds(0,0,100,50);
        local.addActionListener(this);
        local.setText("Local");
        local.setFocusable(false);

        hostButton = new JButton();
        hostButton.setBounds(0,100,100,50);
        hostButton.addActionListener(this);
        hostButton.setText("Host");
        hostButton.setFocusable(false);

        joinButton = new JButton();
        joinButton.setBounds(100,100,100,50);
        joinButton.addActionListener(this);
        joinButton.setText("Join with:");
        joinButton.setFocusable(false);

        this.setSize(500,500);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.add(hostButton);
        this.add(joinButton);
        this.add(local);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==local){
            new MyLobby();
            this.setVisible(false);
        }
        if(e.getSource()==hostButton){
            System.out.println("HOSTING");
            try {
                textField.setEnabled(false);
                hostButton.setVisible(false);
                joinButton.setVisible(false);
                local.setVisible(false);
                this.host=true;
                client = new Client("Host",this);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()==joinButton){
            System.out.println("JOINING with: "+textField.getText());
            try {
                client = new Client("JOIN:"+textField.getText(),this);
                this.join=true;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        //PLAY AS ONLY WHITE (WITH OTHER CLIENT PLAYING AS BLACK)


        //PLAY AS ONLY BLACK (WITH OTHER CLIENT PLAYING AS WHITE)
    }
    public void hostConnect(String code){
        textField.setText(code);
    }
    public void startGame(){
        if(host){
            System.out.println("STARTED GAME AS HOST");
            this.setVisible(false);
            //initialise myframe to start the game but how
            new MyFrame(1);
        }else if(join){
            System.out.println("STARTED GAME AS JOINED");
            this.setVisible(false);
            new MyFrame(0);
        }
    }
}