package com.demoing;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
public class Panel extends JPanel implements MouseListener{
    private final int originalColor;	//WHITE 0xFFCF9F  BLACK  0xD28C45
    private final int highlightColor;	//WHITE 0xF6EA70  BLACK  0xDDC34C
    private boolean highlighted=false;	//highlighted on or off
    private final int y;			//y coordinate
    private final int x;			//x coordinate

    MyFrame board;
    Panel(MyFrame board, int originalColor, int highlightColor, int y, int x){
        this.board= board;

        this.y=y;
        this.x=x;
        this.highlightColor = highlightColor;
        this.setPreferredSize(new Dimension(70,70));
        this.originalColor=originalColor;
        this.setBackground(new Color(originalColor));

        this.setLayout(new BorderLayout());
        this.addMouseListener(this);
    }

    public void revert(){
        if(highlighted){
            return;
        }
        this.setBackground(new Color(originalColor));
    }
    public void highlight(){
        this.setBackground(new Color(highlightColor));
    }
    public void highlightON(){
        this.highlighted=true;
    }
    public void highlightOFF(){
        this.highlighted=false;
    }
    //  ----------------------- OVERRIDES --------------------------------------
    @Override
    public void mouseClicked(MouseEvent e){
        board.panelSelected(y,x);
        //System.out.println("selected ["+y+","+x+"]");
    }
    @Override
    public void mousePressed(MouseEvent e){

    }
    @Override
    public void mouseReleased(MouseEvent e){

    }
    @Override
    public void mouseEntered(MouseEvent e){

    }
    @Override
    public void mouseExited(MouseEvent e){
    }
    // ------------------------------------OVERRIDES--------------------------------

}