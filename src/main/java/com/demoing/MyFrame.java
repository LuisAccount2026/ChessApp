package com.demoing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.GridLayout;

public class MyFrame extends JFrame{
    private final Panel[][] board= new Panel[8][8];	//[8][8] for all the Panel objects[y][x]

    Pieces pieces;			    //LINK TO pieces class
    coords selectedCord;		//SELECTED CORD
    Panel selected;		//SELECTED Panel
    JPanel grid = new JPanel(new GridLayout(8,8));	//[8][8] grid for all the Panel objects

    //----------------------------------MyFrame for WHITE/BLACK POV---------------------------
    MyFrame(int white){
        //FOR IT TO EXIT PROGRAM ON CLOSE
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //SET SIZE AND IT Is NOT GOING TO CHANGE
        this.setResizable(false);
        //WHITE HOLDER
        if(white==1){
            for(int i=0;i<8;i++){//WHITE POV
                for(int j=0;j<8;j++){
                    if((i+j)%2==0){
                        board[7-i][j] = new Panel(this,0xFFCF9F,0xF6EA70,7-i,j);
                        grid.add(board[7-i][j]);
                        //System.out.print("["+(7-i)+","+j+"]");
                    }else{
                        board[7-i][j] = new Panel(this,0xD28C45,0xDDC34C,7-i,j);
                        grid.add(board[7-i][j]);
                        //System.out.print("["+(7-i)+","+j+"]");
                    }
                }
                //System.out.println();
            }
            pieces=new Pieces(this,1,0);
        }
        //BLACK HOLDER
        if(white==0){
            for(int i=0;i<8;i++){//BLACK POV
                for(int j=0;j<8;j++){
                    if((i+j)%2==0){
                        board[i][j] = new Panel(this,0xFFCF9F,0xF6EA70,i,j);
                        grid.add(board[i][j]);
                        //System.out.print("["+(i)+","+j+"]");
                    }else{
                        board[i][j] = new Panel(this,0xD28C45,0xDDC34C,i,j);
                        grid.add(board[i][j]);
                        //System.out.print("["+(i)+","+j+"]");
                    }
                }
                //System.out.println();
            }
            pieces=new Pieces(this,1,0);
        }
        //AFTER CREATING AND ADDING PANELS TO GRID ADD TO FRAME(this)
        this.add(grid);
        //stuff so it shows
        this.pack();
        this.setVisible(true);
        this.revalidate();
    }
    //----------------------------------MyFrame for WHITE/BLACK POV--------------------------


    //---------------------------------PANEL SELECTED HANDLER--------------------------------
    public void panelSelected(int y, int x){
        if(selected==board[y][x]){
            pieces.clearValidCircles(selectedCord.getY(), selectedCord.getX());
            selected.revert();
            clearSelected();
            return;
        }

        if(selected!=null){
            pieces.clearValidCircles(selectedCord.getY(), selectedCord.getX());
            if(pieces.pieceTurn(y,x)){
                pieces.addValidCircles(y,x);
            }
            //SELECTED PANEL REVERT TO NORMAL COLOR
            selected.revert();
            pieces.moving(selectedCord,pieces.getLocation(y,x));
            //SELECTED UPDATE TO NEW SELECTED
            selected=board[y][x];
            //PRESELECTED UPDATE WITH NEW CORD
            selectedCord = pieces.getLocation(y,x);
            //SELECTED CHANGE TO HIGHLIGHTED
            selected.highlight();
        }else{
            if(pieces.pieceTurn(y,x)){
                pieces.addValidCircles(y,x);
            }
            //SELECTED UPDATE TO NEW SELECTED
            selected=board[y][x];
            //PRESELECTED UPDATE WITH NEW CORD
            selectedCord = pieces.getLocation(y,x);
            //SELECTED CHANGE TO HIGHLIGHTED
            selected.highlight();
        }
    }
    //---------------------------------PANEL SELECTED HANDLER---------------------------------

    //---------------------------------HIGHLIGHT PANEL----------------------------------------
    public void highlightPanel(int y,int x){
        board[y][x].highlight();
        board[y][x].highlightON();
    }
    //---------------------------------HIGHLIGHT PANEL----------------------------------------


    //---------------------------------REVERT PANEL----------------------------------------
    public void revertPanel(int y,int x){
        board[y][x].highlightOFF();
        board[y][x].revert();
    }
    //---------------------------------REVERT PANEL----------------------------------------


    //---------------------------------ADDING TO BOARD (LABEL, Y, X)------------------------
    public void addPieceToBoard(JLabel label,int y,int x){
        board[y][x].add(label);
        this.repaint();
        this.revalidate();
    }
    //---------------------------------ADDING TO BOARD (LABEL, Y, X)------------------------

    //-----------------------------REMOVING FROM BOARD (LABEL, Y, X)-----------------------
    public void removePieceFromBoard(JLabel label,int y,int x){
        board[y][x].remove(label);
        this.repaint();
        this.revalidate();
    }
    //-----------------------------REMOVING FROM BOARD (LABEL, Y, X)------------------------

    //-----------------------------CLEAR SELECTED STUFF"-----------------------------------
    public void clearSelected(){
        //System.out.println("cleared selected");
        selected=null;
        selectedCord =null;
    }
    //-----------------------------CLEAR SELECTED STUFF"----------------------------------
}