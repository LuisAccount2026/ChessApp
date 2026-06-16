package com.demoing;

public class move{
    Pieces pieces;
    private String name ="";//NAME OF PIECE
    private String y ="";//COORD OF WHERE PIECE MOVED Y(STRING)
    private int x =0;//COORD OF WHERE PIECE MOVED X(INT)
    private boolean pieceTaken = false;//TRUE IF PIECE TAKEN
    private String unique = "";//letter if not unique and "" if unique


    private boolean checkmate = false;//TRUE IF CHECKMATED
    private boolean check = false;//TRUE IF CHECK
    private boolean promoted = false;//TRUE IF PAWN PROMOTES
    //0-0 Castle king-side

    //0-0-0 Castle Queen-side

    public move(Pieces pieces, String name,boolean pieceTaken, String unique, coords from, coords to){
        this.pieces = pieces;
        this.y = convertToString(to.getY());//Coords of where to move y(string)
        this.x = to.getX()+1;//Coords of where to move x(int)
        this.name = convertToName(name);//NAME INTO ONE LETTER
        this.pieceTaken = pieceTaken;
        this.unique = unique;
    }

    public void print(){
        System.out.print(name+""+unique);
        if(pieceTaken)
            System.out.print("x");
        System.out.println(y+""+x);
    }




    private String convertToName(String name){
        switch(name){
            case "WhitePawn":
                return "";
            case "BlackPawn":
                return "";
            case "WhiteKnight":
                return "N";
            case "BlackKnight":
                return "N";
            case "WhiteBishop":
                return "B";
            case "BlackBishop":
                return "B";
            case "WhiteRook":
                return "R";
            case "BlackRook":
                return "R";
            case "WhiteKing":
                return "K";
            case "BlackKing":
                return "K";
            case "WhiteQueen":
                return "Q";
            case "BlackQueen":
                return "Q";
            default:
        }
        return "";
    }

    //----------------------------------HANDLES THE Y VALUE------------------------------------
    private String convertToString(int i){
        switch(i){
            case 0:
                return"a";
            case 1:
                return"b";
            case 2:
                return"c";
            case 3:
                return"d";
            case 4:
                return"e";
            case 5:
                return"f";
            case 6:
                return"g";
            case 7:
                return"h";
            default:
        }
        return "";
    }
    //----------------------------------HANDLES THE Y VALUE------------------------------------
}