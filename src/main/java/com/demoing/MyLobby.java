package com.demoing;

import com.demoing.clientStuff.Client;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class MyLobby extends JFrame implements ActionListener{
    JButton whitePlayer;
    JButton blackPlayer;
    JButton hostButton;
    JButton joinButton;

    //TEXTFIELD TRY
    JTextField textField;
    //TRY
    MyLobby(){
        //JTEXTFIELD = a gui textbox component that can be used to add aet or get text

        textField = new JTextField();
        textField.setBounds(200,100,100,50);
        textField.setText("XXXX");
        this.add(textField);


        //END OF JTEXTFIELD TRY
        whitePlayer = new JButton();
        whitePlayer.setBounds(0,0,100,50);
        whitePlayer.addActionListener(this);
        whitePlayer.setText("White");
        whitePlayer.setFocusable(false);

        blackPlayer = new JButton();
        blackPlayer.setBounds(100,0,100,50);
        blackPlayer.addActionListener(this);
        blackPlayer.setText("Black");
        blackPlayer.setFocusable(false);

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
        this.add(whitePlayer);
        this.add(blackPlayer);
        this.add(hostButton);
        this.add(joinButton);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==whitePlayer){//POV AS WHITE BUT CAN STILL PLAY BLACK
            this.setVisible(false);
            new MyFrame(1);
        }
        if(e.getSource()==blackPlayer){//POV AS BLACK BUT CAN STILL PLAY WHITE
            this.setVisible(false);
            new MyFrame(0);
        }
        if(e.getSource()==hostButton){
            System.out.println("HOSTING");
            try {
                new Client("Host",this);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()==joinButton){
            System.out.println("JOINING with: "+textField.getText());
            try {
                new Client("JOIN:"+textField.getText(),this);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        //PLAY AS ONLY WHITE (WITH OTHER CLIENT PLAYING AS BLACK)


        //PLAY AS ONLY BLACK (WITH OTHER CLIENT PLAYING AS WHITE)
    }
    public void startGame(){
        System.out.println("STARTED GAME CHOOSE COLOR");
    }
}
