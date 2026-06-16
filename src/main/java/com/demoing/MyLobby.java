package com.demoing;

import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class MyLobby extends JFrame implements ActionListener{
    JButton whitePlayer;
    JButton blackPlayer;

    MyLobby(){
        whitePlayer = new JButton();
        whitePlayer.setBounds(250,100,100,50);
        whitePlayer.addActionListener(this);
        whitePlayer.setText("White");
        whitePlayer.setFocusable(false);

        blackPlayer = new JButton();
        blackPlayer.setBounds(150,100,100,50);
        blackPlayer.addActionListener(this);
        blackPlayer.setText("Black");
        blackPlayer.setFocusable(false);


        this.setSize(560,560);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.add(whitePlayer);
        this.add(blackPlayer);


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

        //PLAY AS ONLY WHITE (WITH OTHER CLIENT PLAYING AS BLACK)


        //PLAY AS ONLY BLACK (WITH OTHER CLIENT PLAYING AS WHITE)
    }
}
