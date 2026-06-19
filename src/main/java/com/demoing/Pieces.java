package com.demoing;

import java.util.ArrayList;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pieces{
    MyFrame board;
    public int turn=0;
    public coords[] lastMove = new coords[2]; //LAST MOVE COORDS [0]is from, [1]is to
    //pieces [8][8]
    private final piece[][] pieces= new piece[8][8];
    private final JLabel[][] circles= new JLabel[8][8];
    private final coords[][] locations= new coords[8][8];
    //ARRAYLISTS
    private final ArrayList<coords> whiteVision= new ArrayList<>();
    private final ArrayList<coords> blackVision= new ArrayList<>();
    private final ArrayList<move> whiteMoves= new ArrayList<>();
    private final ArrayList<move> blackMoves= new ArrayList<>();
    private final ArrayList<piece> whitePieces= new ArrayList<>();
    private final ArrayList<piece> blackPieces= new ArrayList<>();
    private final ArrayList<coords> whiteValidMoves= new ArrayList<>();
    private final ArrayList<coords> blackValidMoves= new ArrayList<>();
    //KINGS LOCATIONS
    private boolean whiteCheck=false;
    private boolean blackCheck=false;
    private piece whiteKING;
    private piece blackKING;
    private boolean enPassant=false;
    private piece enPassantPawn=null;
    private coords enPassantCords=null;

    private static final String WhiteRook = "WhiteRook";
    private static final String WhiteKnight = "WhiteKnight";
    private static final String WhiteBishop = "WhiteBishop";
    private static final String WhiteQueen = "WhiteQueen";
    private static final String WhiteKing = "WhiteKing";
    private static final String WhitePawn = "WhitePawn";
    private static final String BlackRook = "BlackRook";
    private static final String BlackKnight = "BlackKnight";
    private static final String BlackBishop = "BlackBishop";
    private static final String BlackQueen = "BlackQueen";
    private static final String BlackKing = "BlackKing";
    private static final String BlackPawn = "BlackPawn";
    private static final String none = "none";

    private int white=1;
    private int black=0;
    //--------------------------------------set pieces ----------------------------------------
    Pieces(){
    }
    Pieces(MyFrame board){
        this.board=board;
        setBoard();
        updateAll();
        pieces[7][3].updateMoves();
        pieces[0][3].updateMoves();
    }
    private void setBoard(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                //FOR WHITE ROOKS----------------------------------------WHITE PIECES
                if(i==0&&(j==0||j==7)){
                    locations[i][j]= new coords(i,j);
                    pieces[i][j]= new piece(this,locations[i][j],WhiteRook,white,new ImageIcon("src/main/resources/pieces/white-rook.png"));
                    board.addPieceToBoard(pieces[i][j].getLabel(),i,j);
                    whitePieces.add(pieces[i][j]);
                }
                //FOR WHITE KNIGHTS
                if(i==0&&(j==1||j==6)){
                    locations[i][j]= new coords(i,j);
                    pieces[i][j]= new piece(this,locations[i][j],WhiteKnight,white,new ImageIcon("src/main/resources/pieces/white-knight.png"));
                    board.addPieceToBoard(pieces[i][j].getLabel(),i,j);
                    whitePieces.add(pieces[i][j]);
                }
                //FOR WHITE BISHOPS
                if(i==0&&(j==2||j==5)){
                    locations[i][j]= new coords(i,j);
                    pieces[i][j]= new piece(this,locations[i][j],WhiteBishop,white,new ImageIcon("src/main/resources/pieces/white-bishop.png"));
                    board.addPieceToBoard(pieces[i][j].getLabel(),i,j);
                    whitePieces.add(pieces[i][j]);
                }
                //FOR WHITE Queen
                if(i==0&&j==3){
                    locations[i][j]= new coords(i,j);
                    pieces[i][j]= new piece(this,locations[i][j],WhiteQueen,white,new ImageIcon("src/main/resources/pieces/white-queen.png"));
                    board.addPieceToBoard(pieces[i][j].getLabel(),i,j);
                    whitePieces.add(pieces[i][j]);
                }
                //FOR WHITE King
                if(i==0&&j==4){
                    locations[i][j]= new coords(i,j);
                    pieces[i][j]= new piece(this,locations[i][j],WhiteKing,white,new ImageIcon("src/main/resources/pieces/white-king.png"));
                    board.addPieceToBoard(pieces[i][j].getLabel(),i,j);
                    whiteKING=pieces[i][j];
                    whitePieces.add(pieces[i][j]);
                }
                //FOR WHITE Pawns
                if(i==1){
                    locations[i][j]= new coords(i,j);
                    pieces[i][j]= new piece(this,locations[i][j],WhitePawn,white,new ImageIcon("src/main/resources/pieces/white-pawn.png"));
                    board.addPieceToBoard(pieces[i][j].getLabel(),i,j);
                    whitePieces.add(pieces[i][j]);
                }
                //FOR BLACK ROOKS--------------------------------------------BLACK PIECES
                if(i==7&&(j==0||j==7)){
                    locations[i][j]= new coords(i,j);
                    pieces[i][j]= new piece(this,locations[i][j],BlackRook,black,new ImageIcon("src/main/resources/pieces/black-rook.png"));
                    board.addPieceToBoard(pieces[i][j].getLabel(),i,j);
                    blackPieces.add(pieces[i][j]);
                }
                //FOR BLACK KNIGHTS
                if(i==7&&(j==1||j==6)){
                    locations[i][j]= new coords(i,j);
                    pieces[i][j]= new piece(this,locations[i][j],BlackKnight,black,new ImageIcon("src/main/resources/pieces/black-knight.png"));
                    board.addPieceToBoard(pieces[i][j].getLabel(),i,j);
                    blackPieces.add(pieces[i][j]);
                }
                //FOR BLACK BISHOPS
                if(i==7&&(j==2||j==5)){
                    locations[i][j]= new coords(i,j);
                    pieces[i][j]= new piece(this,locations[i][j],BlackBishop,black,new ImageIcon("src/main/resources/pieces/black-bishop.png"));
                    board.addPieceToBoard(pieces[i][j].getLabel(),i,j);
                    blackPieces.add(pieces[i][j]);
                }
                //FOR BLACK Queen
                if(i==7&&j==3){
                    locations[i][j]= new coords(i,j);
                    pieces[i][j]= new piece(this,locations[i][j],BlackQueen,black,new ImageIcon("src/main/resources/pieces/black-queen.png"));
                    board.addPieceToBoard(pieces[i][j].getLabel(),i,j);
                    blackPieces.add(pieces[i][j]);
                }
                //FOR BLACK King
                if(i==7&&j==4){
                    locations[i][j]= new coords(i,j);
                    pieces[i][j]= new piece(this,locations[i][j],BlackKing,black,new ImageIcon("src/main/resources/pieces/black-king.png"));
                    board.addPieceToBoard(pieces[i][j].getLabel(),i,j);
                    blackKING=pieces[i][j];
                    blackPieces.add(pieces[i][j]);
                }
                //FOR BLACK Pawns
                if(i==6){
                    locations[i][j]= new coords(i,j);
                    pieces[i][j]= new piece(this,locations[i][j],BlackPawn,black,new ImageIcon("src/main/resources/pieces/black-pawn.png"));
                    board.addPieceToBoard(pieces[i][j].getLabel(),i,j);
                    blackPieces.add(pieces[i][j]);
                }
                //FOR EMPTY TILES------------------------------------------------------
                if(i>1&&i<6){
                    locations[i][j]= new coords(i,j);
                    pieces[i][j]= new piece(locations[i][j],none);
                    board.addPieceToBoard(pieces[i][j].getLabel(),i,j);
                }
                //FOR CIRCLES
                circles[i][j]=new JLabel();
                circles[i][j].setIcon(new ImageIcon("src/main/resources/pieces/circle.png"));
                circles[i][j].setVerticalAlignment(JLabel.CENTER);
                circles[i][j].setHorizontalAlignment(JLabel.CENTER);
            }
        }
    }
    //--------------------------------------set pieces ----------------------------------------
    private String convertToString(int i){
        return switch (i) {
            case 0 -> "a";
            case 1 -> "b";
            case 2 -> "c";
            case 3 -> "d";
            case 4 -> "e";
            case 5 -> "f";
            case 6 -> "g";
            case 7 -> "h";
            default -> "";
        };
    }
    public void updateAll(){
        for(piece whitePiece : whitePieces)
            whitePiece.updateMoves();
        for(piece blackPiece : blackPieces)
            blackPiece.updateMoves();
        wholeVision();
    }
    public void printMadeMoves(){
        System.out.println("WHITE MOVES-----");
        for(move move :  whiteMoves){
            move.print();
        }
        System.out.println("BLACK MOVES-----");
        for(move move :  blackMoves){
            move.print();
        }
    }
    public boolean pieceTurn(int y,int x){
        //WHITE AND even turns
        if(pieces[y][x].getNumber()==1 && turn%2==0){
            return true;
        }else return pieces[y][x].getNumber() == 0 && turn % 2 != 0;
    }

    public piece getPiece(int y, int x){
        return pieces[y][x];
    }
    public coords getLocation(int y, int x){
        return locations[y][x];
    }

    public void addValidCircles(int y,int x){
        //System.out.println("ADDING CIRCLES! from ["+y+","+x+"]");
        for(coords circle : pieces[y][x].getValid()){
            //System.out.println("circle at:["+circle.getY()+","+circle.getX()+"]");
            board.addPieceToBoard(circles[circle.getY()][circle.getX()],circle.getY(),circle.getX());
        }
    }
    public void clearValidCircles(int y,int x){
        //System.out.println("REMOVING CIRCLES! from ["+y+","+x+"]");
        for(coords circle : pieces[y][x].getValid()){
            //System.out.println("get rid of circle at:["+circle.getY()+","+circle.getX()+"]");
            board.removePieceFromBoard(circles[circle.getY()][circle.getX()],circle.getY(),circle.getX());
        }
    }

    //--------------------------------------GETS UNIQUENESS----------------------------------------
    private String getUnique(coords from, coords to){
        if(Objects.equals(pieces[from.getY()][from.getX()].getName(), "WhitePawn") || Objects.equals(pieces[from.getY()][from.getX()].getName(), "BlackPawn")){
            //FOR PAWNS ONLY
            if(from.getX()==to.getX())//pawn move
                return "";
            for(int j=0;j<8;j++){
                if(j==from.getX())
                    continue;
                if(Objects.equals(pieces[from.getY()][j].getName(), pieces[from.getY()][from.getX()].getName())){
                    //SAME PIECE NAME
                    if(pieces[from.getY()][j].getValid().contains(to)){
                        //BOTH HAVE TO AS TARGET
                        return (j+1)+"";//X COORD AS STRING
                    }
                }
            }
            //NO OTHER PAWN HAS SAME TARGET SO JUST Y COORD
            return convertToString(from.getY());//Y COORD AS STRING
        }
        if(pieces[from.getY()][from.getX()].getNumber()==1){
            for(piece whitePiece : whitePieces){
                if(whitePiece==pieces[from.getY()][from.getX()])
                    continue;//SKIP ITSELF
                if(Objects.equals(whitePiece.getName(), pieces[from.getY()][from.getX()].getName())){
                    //TRUE IF both have same unique name
                    //System.out.println(whitePiece.getName()+"["+whitePiece.getY()+","+whitePiece.getX()+"]");
                    if(whitePiece.getValid().contains(to)){
                        //TRUE IF whitePiece has to coord in validmoves
                        if(whitePiece.getY()==from.getY())//both of them share Y coord
                            return (whitePiece.getX()+1)+"";//X COORD AS STRING
                        if(whitePiece.getX()==from.getX())
                            return convertToString(whitePiece.getY());//Y COORD AS STRING
                    }
                }
            }
        }
        else if(pieces[from.getY()][from.getX()].getNumber()==0){
            for(piece blackPiece : blackPieces){
                if(blackPiece==pieces[from.getY()][from.getX()])
                    continue;//SKIP ITSELF
                if(Objects.equals(blackPiece.getName(), pieces[from.getY()][from.getX()].getName())){
                    //TRUE IF both have same unique name
                    //System.out.println(blackPiece.getName()+"["+blackPiece.getY()+","+blackPiece.getX()+"]");
                    if(blackPiece.getValid().contains(to)){
                        //TRUE IF whitePiece has to coord in validmoves
                        if(blackPiece.getY()==from.getY())//both of them share Y coord
                            return (blackPiece.getX()+1)+"";//X COORD AS STRING
                        if(blackPiece.getX()==from.getX())
                            return convertToString(blackPiece.getY());//Y COORD AS STRING
                    }
                }
            }
        }
        return "";
    }

    //---------------------------------------Move piece -------------------------------------------
    public void moving(coords from,coords to){
        //BLANK PIECE JUST RETURNS
        if(pieces[from.getY()][from.getX()].getNumber()==-1)return;
        //SELECTED BLACK and then BLACK AND SELECTED WHITE and then WHITE
        if(pieces[from.getY()][from.getX()].getNumber()==pieces[to.getY()][to.getX()].getNumber())return;

        //turn 0 and black pieces = return
        if(turn%2==0&&pieces[from.getY()][from.getX()].getNumber()==0)return;
        //turn 1 and white pieces = return
        if(turn%2!=0&&pieces[from.getY()][from.getX()].getNumber()==1)return;


        //CHECK IF TO COORDS ARE IN validMoves ArrayList<coords>
        if(pieces[from.getY()][from.getX()].getValid().contains(to)){


            whiteCheck=false;
            blackCheck=false;
            //WHITE CASTLE HANDLER
            if(Objects.equals(pieces[from.getY()][from.getX()].getName(),WhiteKing)||
                    Objects.equals(pieces[from.getY()][from.getX()].getName(),BlackKing) ){
                if(to.getY()==from.getY() && to.getX()==from.getX()+2){//right side castling
                    //MOVE ROOK TO THE LEFT OF THE KING [from.getY()][from.getX()+1]
                    takePiece(locations[from.getY()][from.getX()+1]);
                    board.addPieceToBoard(pieces[from.getY()][from.getX()+3].getLabel(),from.getY(),from.getX()+1);
                    removePieceVision(pieces[from.getY()][from.getX()+3]);
                    pieces[from.getY()][from.getX()+3].updateCoords(locations[from.getY()][from.getX()+1]);
                    pieces[from.getY()][from.getX()+1]=pieces[from.getY()][from.getX()+3];
                    pieces[from.getY()][from.getX()+3]= new piece(locations[from.getY()][from.getX()+1],none);
                }else if(to.getY()==from.getY() && to.getX()==from.getX()-2) {//left side castling
                    //MOVE ROOK TO THE RIGHT OF THE KING [from.getY()][from.getX()-1]
                    takePiece(locations[from.getY()][from.getX()-1]);
                    board.addPieceToBoard(pieces[from.getY()][from.getX()-4].getLabel(),from.getY(),from.getX()-1);
                    removePieceVision(pieces[from.getY()][from.getX()-4]);
                    pieces[from.getY()][from.getX()-4].updateCoords(locations[from.getY()][from.getX()-1]);
                    pieces[from.getY()][from.getX()-1]=pieces[from.getY()][from.getX()-4];
                    pieces[from.getY()][from.getX()-4]= new piece(locations[from.getY()][from.getX()-1],none);
                }
            }

            if(enPassant){
                if(Objects.equals(pieces[from.getY()][from.getX()].getName(),WhitePawn)||
                        Objects.equals(pieces[from.getY()][from.getX()].getName(),BlackPawn)){
                    if(to==enPassantCords){
                        takePiece(enPassantPawn.getLocation());
                        pieces[enPassantPawn.getY()][enPassantPawn.getX()]= new piece(enPassantPawn.getLocation(),none);
                    }
                }
            }
            //check for moving enpassant
            if(Objects.equals(pieces[from.getY()][from.getX()].getName(), WhitePawn)){
                if(to.getY()-from.getY()==2){
                    enPassant=true;
                    enPassantPawn=pieces[from.getY()][from.getX()];//piece
                    enPassantCords=locations[to.getY()-1][to.getX()];//coord from one step below
                    if(to.getX()-1>-1) {
                        if (Objects.equals(pieces[to.getY()][to.getX() - 1].getName(), BlackPawn)) {
                            pieces[to.getY()][to.getX() - 1].updateMoves();
                        }
                    }
                    if(to.getX()+1<8) {
                        if (Objects.equals(pieces[to.getY()][to.getX() + 1].getName(), BlackPawn)) {
                            pieces[to.getY()][to.getX() + 1].updateMoves();
                        }
                    }
                }else{
                    enPassant=false;
                    enPassantPawn=null;
                    enPassantCords=null;
                }
            }else if(Objects.equals(pieces[from.getY()][from.getX()].getName(), BlackPawn)){
                if(from.getY()-to.getY()==2){
                    enPassant=true;
                    enPassantPawn=pieces[from.getY()][from.getX()];//piece
                    enPassantCords=locations[to.getY()+1][to.getX()];//coord from one step below
                    if(to.getX()-1>-1) {
                        if (Objects.equals(pieces[to.getY()][to.getX() - 1].getName(), WhitePawn)) {
                            pieces[to.getY()][to.getX() - 1].updateMoves();
                        }
                    }
                    if(to.getX()+1<8) {
                        if (Objects.equals(pieces[to.getY()][to.getX() + 1].getName(), WhitePawn)) {
                            pieces[to.getY()][to.getX() + 1].updateMoves();
                        }
                    }
                }else{
                    enPassant=false;
                    enPassantPawn=null;
                    enPassantCords=null;
                }
            }else {
                enPassant=false;
                enPassantPawn=null;
                enPassantCords=null;
            }
            //EnPassant check ending
            //System.out.println("____________BEGINNING MOVE FUNCTION______________");

            //IF VALID MOVE FOUND = GET OUT OF CHECK ELSE IT WOULDNT BE VALID MOVE
            //System.out.println("CHECKING IF PIECE WAS IN CHECK");
            if(pieces[from.getY()][from.getX()].getCheckStatus())
                resolveChecks(pieces[from.getY()][from.getX()].getNumber());

            //LOCATION OF VALID MOVES HANDLER (AFTER MOVE)
            clearValidCircles(from.getY(),from.getX());

            //System.out.println("WHITE KING LOCATION:["+whiteKING.getY()+","+whiteKING.getX()+"]");
            //System.out.println("BLACK KING LOCATION:["+blackKING.getY()+","+blackKING.getX()+"]");

            //SAVE MOVED INTO WHITE OR BLACK MOVES ARRAY LIST
            String unique = getUnique(from,to);
            if(turn%2==0){//WHITE
                if(!Objects.equals(pieces[to.getY()][to.getX()].getName(), "none"))
                    whiteMoves.add(new move(this,pieces[from.getY()][from.getX()].getName(),true,unique,from,to));
                else
                    whiteMoves.add(new move(this,pieces[from.getY()][from.getX()].getName(),false,unique,from,to));
            }
            if(turn%2!=0){//BLACK
                if(!Objects.equals(pieces[to.getY()][to.getX()].getName(), "none"))
                    blackMoves.add(new move(this,pieces[from.getY()][from.getX()].getName(),true,unique,from,to));
                else
                    blackMoves.add(new move(this,pieces[from.getY()][from.getX()].getName(),false,unique,from,to));
            }
            //SAVE MOVED INTO WHITE OR BLACK MOVES ARRAY LIST

            //HANDLER FOR HIGHLIGHED MOVE (LAST MOVES TOO)
            if(lastMove[0]!=null){
                board.revertPanel(lastMove[0].getY(),lastMove[0].getX());
                board.revertPanel(lastMove[1].getY(),lastMove[1].getX());
                lastMove[0]=from;
                lastMove[1]=to;
                board.highlightPanel(lastMove[0].getY(),lastMove[0].getX());
                board.highlightPanel(lastMove[1].getY(),lastMove[1].getX());
            }else{
                lastMove[0]=from;
                lastMove[1]=to;
                board.highlightPanel(lastMove[0].getY(),lastMove[0].getX());
                board.highlightPanel(lastMove[1].getY(),lastMove[1].getX());
            }
            //HANDLER FOR HIGHLIGHED MOVE (LAST MOVES TOO)

            //PROMOTION HANDLER
            if(Objects.equals(pieces[from.getY()][from.getX()].getName(), WhitePawn) && to.getY()==7){
                int number = pieces[from.getY()][from.getX()].getNumber();
                //reached last square so promote
                whitePieces.remove(pieces[from.getY()][from.getX()]);
                board.removePieceFromBoard(pieces[from.getY()][from.getX()].getLabel(),from.getY(),from.getX());
                pieces[from.getY()][from.getX()]=new piece(this,locations[from.getY()][from.getX()],WhiteQueen,number,new ImageIcon("src/main/resources/pieces/white-queen.png"));
                whitePieces.add(pieces[from.getY()][from.getX()]);
            }else if(Objects.equals(pieces[from.getY()][from.getX()].getName(), BlackPawn) && to.getY()==0){
                int number = pieces[from.getY()][from.getX()].getNumber();
                //reached last square so promote
                blackPieces.remove(pieces[from.getY()][from.getX()]);
                board.removePieceFromBoard(pieces[from.getY()][from.getX()].getLabel(),from.getY(),from.getX());
                pieces[from.getY()][from.getX()]=new piece(this,locations[from.getY()][from.getX()],BlackQueen,number,new ImageIcon("src/main/resources/pieces/black-queen.png"));
                blackPieces.add(pieces[from.getY()][from.getX()]);
            }

            //TAKING PIECE (EVEN IF ITS BLANK YOU ALWAYS TAKE)
            takePiece(to);//ONLY REMOVES THE LABEL SO ANOTHER LABEL CAN BE ADDED LATER
            //DOESNT CHANGE piece[][]


            //PRINTS HOW MANY PIECES REMAINING IN THEIR RESPECTIVE ARRAYLISTS
            if(pieces[to.getY()][to.getX()].getNumber()==1){//WHITE
                whitePieces.remove(pieces[to.getY()][to.getX()]);
                //System.out.println("removed 1 white piece remaining:"+whitePieces.size());
            }else if(pieces[to.getY()][to.getX()].getNumber()==0){//BLACK
                blackPieces.remove(pieces[to.getY()][to.getX()]);
                //System.out.println("removed 1 black piece remaining:"+blackPieces.size());
            }
            //PRINTS HOW MANY PIECES REMAINING IN THEIR RESPECTIVE ARRAYLISTS


            //BEFORE ANYTHING TAKING A PIECE RESOLVE PINS IF ANY
            //System.out.println("BEFORE ANYTHING TAKING A PIECE RESOLVE PINS IF ANY");
            pieces[from.getY()][from.getX()].unPinPiece();//RESOLVES PINS OF TAKEN PIECE

            //MOVE PIECE THERE (LABEL WISE)
            board.addPieceToBoard(pieces[from.getY()][from.getX()].getLabel(),to.getY(),to.getX());
            removePieceVision(pieces[from.getY()][from.getX()]);
            //UPDATE COORDS (give the to coords to from piece)
            pieces[from.getY()][from.getX()].updateCoords(to);//COORD HANDLER


            //MOVES PIECE TO pieces[][]
            pieces[to.getY()][to.getX()]=pieces[from.getY()][from.getX()];

            //MAKES FROM PLACE AS BLANK in pieces[][]
            pieces[from.getY()][from.getX()]= new piece(locations[from.getY()][from.getX()],none);


            //UPDATE NECESSARY PIECES MOVES AND VALID MOVES
            pieces[to.getY()][to.getX()].updateMoves();//MOVED PIECE UPDATES FIRST

            //CHECK FOR MOVES THAT COVER THE MOVED PIECE (FROM AND TO)
            for(piece whitePiece : whitePieces){
                if(whitePiece==whiteKING)
                    continue;//SKIPS WHITE KING
                if(whitePiece==pieces[to.getY()][to.getX()])
                    continue;//SKIPS ITSELF BC ALREADY UPDATED
                if(whitePiece.getMoves().contains(from) || whitePiece.getMoves().contains(to))
                    whitePiece.updateMoves();
            }
            for(piece blackPiece : blackPieces){
                if(blackPiece==blackKING)
                    continue;//SKIPS BLACK KING
                if(blackPiece==pieces[to.getY()][to.getX()])
                    continue;//SKIPS ITSELF BC ALREADY UPDATED
                if(blackPiece.getMoves().contains(from) || blackPiece.getMoves().contains(to))
                    blackPiece.updateMoves();
            }
            //TURN ADD
            turn++;
            wholeVision();
            //System.out.println("UPDATING KINGS");
            blackKING.updateMoves();
            whiteKING.updateMoves();
            //WHOSE TURN IT IS
            if(turn%2==0){
                updateWhiteValidMoves();
                if(whiteValidMoves.isEmpty()){
                    if(whiteCheck){
                        System.out.println("CHECKMATE BLACK WINS");
                        return;
                    }
                    System.out.println("ITS A DRAW");
                }
                System.out.println("WHITE TURN!");
                System.out.println(whiteValidMoves.size());
            }else{
                updateBlackValidMoves();
                if(blackValidMoves.isEmpty()){
                    if(blackCheck){
                        System.out.println("CHECKMATE WHITE WINS");
                        return;
                    }
                    System.out.println("ITS A DRAW");
                }
                System.out.println("BLACK TURN!");
                System.out.println(blackValidMoves.size());
            }
        }


        //System.out.println("____________ENDING OF MOVE FUNCTION______________");
    }
    //---------------------------------------Move piece -------------------------------------------


    //----------------------------------------updateWhiteValidMoves()---------------------------------
    public void updateWhiteValidMoves(){
        whiteValidMoves.clear();
        for(piece whitePiece : whitePieces){
            whiteValidMoves.addAll(whitePiece.getValid());
        }
    }
    //----------------------------------------updateWhiteValidMoves()---------------------------------
    //----------------------------------------updateBlackValidMoves()---------------------------------
    public void updateBlackValidMoves(){
        blackValidMoves.clear();
        for(piece blackPiece : blackPieces){
            blackValidMoves.addAll(blackPiece.getValid());
        }
    }
    //----------------------------------------updateBlackValidMoves()---------------------------------

    //----------------------------------------Take Piece-------------------------------------------
    public void takePiece(coords to){
        //System.out.println("REMOVED PIECE: "+pieces[to.getY()][to.getX()].getName());
        board.removePieceFromBoard(pieces[to.getY()][to.getX()].getLabel(),to.getY(),to.getX());
    }
    //----------------------------------------Take Piece-------------------------------------------


    //-----------------------------------------Valid Move--------------------------------------------
    public boolean validMove(piece piece,coords from,coords to){
        //FOR PAWNS-------------------------------------------------------------
        if(Objects.equals(piece.getName(), WhitePawn))
            return forWhitePawn(from,to);
        if(Objects.equals(piece.getName(), BlackPawn))
            return forBlackPawn(from,to);
        //FOR KNIGHTS-------------------------------------------------------------
        if(Objects.equals(piece.getName(), WhiteKnight) || Objects.equals(piece.getName(), BlackKnight))
            return forKnights(from,to);
        //FOR BISHOPS-------------------------------------------------------------
        if(Objects.equals(piece.getName(), WhiteBishop) || Objects.equals(piece.getName(), BlackBishop))
            return forBishops(from,to);
        //FOR ROOKS-------------------------------------------------------------
        if(Objects.equals(piece.getName(), WhiteRook) || Objects.equals(piece.getName(), BlackRook))
            return forRooks(from,to);
        //FOR KINGS-------------------------------------------------------------
        if(Objects.equals(piece.getName(), WhiteKing) || Objects.equals(piece.getName(), BlackKing))
            return forKings(from,to);
        //FOR QUEENS-------------------------------------------------------------
        if(Objects.equals(piece.getName(), WhiteQueen) || Objects.equals(piece.getName(), BlackQueen))
            return forQueens(from,to);
        //IF FALSE THEN IT AINT VALID MOVE
        return false;
    }
    //-----------------------------------------Valid Move--------------------------------------------
    //-----------------------------------------Vision Move--------------------------------------------
    public boolean visionMove(piece piece,coords from,coords to){
        //FOR PAWNS-------------------------------------------------------------
        if(Objects.equals(piece.getName(), WhitePawn))
            return forWhitePawnVision(from,to);
        if(Objects.equals(piece.getName(), BlackPawn))
            return forBlackPawnVision(from,to);
        //FOR KNIGHTS-------------------------------------------------------------
        if(Objects.equals(piece.getName(), WhiteKnight) || Objects.equals(piece.getName(), BlackKnight))
            return forKnightsVision(from,to);
        //FOR BISHOPS-------------------------------------------------------------
        if(Objects.equals(piece.getName(), WhiteBishop) || Objects.equals(piece.getName(), BlackBishop))
            return forBishopsVision(from,to);
        //FOR ROOKS-------------------------------------------------------------
        if(Objects.equals(piece.getName(), WhiteRook) || Objects.equals(piece.getName(), BlackRook))
            return forRooksVision(from,to);
        //FOR KINGS-------------------------------------------------------------
        if(Objects.equals(piece.getName(), WhiteKing) || Objects.equals(piece.getName(), BlackKing))
            return forKingsVision(from,to);
        //FOR QUEENS-------------------------------------------------------------
        if(Objects.equals(piece.getName(), WhiteQueen) || Objects.equals(piece.getName(), BlackQueen))
            return forQueensVision(from,to);
        //IF FALSE THEN IT AINT VALID MOVE
        return false;
    }
    //-----------------------------------------Vision Move--------------------------------------------

    //---------------------------------------REMOVING PIECE VISION------------------------------------
    public void removePieceVision(piece pieceVision){
        if(pieceVision.getNumber()==1){//WHITE
            for(coords vision : pieceVision.getVisionMoves()){
                whiteVision.remove(vision);
            }
        }else
        if(pieceVision.getNumber()==0){//BLACK
            for(coords vision : pieceVision.getVisionMoves()){
                blackVision.remove(vision);
            }
        }
    }
    //---------------------------------------REMOVING PIECE VISION------------------------------------


    //-----------------------------------------UPDATING VISION----------------------------------------
    public void updateVision(piece pieceVision, coords visionSquare){
        if(pieceVision.getNumber()==1){//WHITE
            if(whiteVision.contains(visionSquare))
                return;
            whiteVision.add(visionSquare);
        }else
        if(pieceVision.getNumber()==0){//BLACK
            if(blackVision.contains(visionSquare))
                return;
            blackVision.add(visionSquare);
        }
    }
    //-----------------------------------------UPDATING VISION----------------------------------------

    //-----------------------------------------WHOLE VISION--------------------------------------------
    public void wholeVision(){
        whiteVision.clear();
        for(piece whitePiece : whitePieces){
            whiteVision.addAll(whitePiece.getVisionMoves());
        }
        //System.out.println("whiteVISION OF:"+whiteVision.size());
        blackVision.clear();
        for(piece blackPiece : blackPieces){
            blackVision.addAll(blackPiece.getVisionMoves());
        }
        //System.out.println("blackVISION OF:"+blackVision.size());
        //System.out.println("blackVISION OF:"+blackVision.size());
    }
    //-----------------------------------------WHOLE VISION--------------------------------------------


    //-------------------------------------CHECK HANDLER------------------------------
    public void inCheck(ArrayList<coords> list, int check){//check=1 means white in check /0 black
        //inCheck(pinnedList,0); //WHITE CHECKS BLACK
        if(check==0){
            blackCheck=true;
            for(piece blackPiece : blackPieces)
                blackPiece.setCheck(list);
        }
        if(check==1){
            whiteCheck=true;
            for(piece whitePiece : whitePieces)
                whitePiece.setCheck(list);
        }
    }
    //-------------------------------------CHECK HANDLER------------------------------

    //-------------------------------------CHECK UNHANDLER------------------------------
    public void resolveChecks(int color){
        if(color==1){
            //System.out.println("RESOLVING CHECKS FOR WHITE-------------------");
            for(piece whitePiece : whitePieces){
                //System.out.println(whitePiece.getName()+" out of check");
                whitePiece.resolveCheck();
            }
            //System.out.println("______________________________________________");
            return;
        }
        if(color==0){
            //System.out.println("RESOLVING CHECKS FOR BLACK-------------------");
            for(piece blackPiece : blackPieces){
                //System.out.println(blackPiece.getName()+" out of check");
                blackPiece.resolveCheck();
            }
            //System.out.println("______________________________________________");
        }
    }
    //-------------------------------------CHECK UNHANDLER------------------------------

    //-------------------------------------Print Moves of pieces[8][8]------------------------------
    public void printMovesPieces(){
        System.out.println("_____________MOVES_OF_WHITE_PIECES_______________]");
        for(piece whitePiece : whitePieces){
            System.out.print(whitePiece.getName());
            System.out.println(":["+whitePiece.getSizeMoves()+"] getSizeMoves");
        }
        System.out.println("_____________MOVES_OF_BLACK_PIECES_______________]");
        for(piece blackPiece : blackPieces){
            System.out.print(blackPiece.getName());
            System.out.println(":["+blackPiece.getSizeMoves()+"] getSizeMoves");
        }
    }
    //-------------------------------------Print Moves of pieces[8][8]------------------------------

    //-------------------------------------Print ValidMoves of pieces[8][8]------------------------------
    public void printValidMovesPieces(){
        System.out.println("_____________VALID_MOVES_OF_WHITE_PIECES_______________]");
        for(piece whitePiece : whitePieces){
            System.out.print(whitePiece.getName());
            System.out.println("["+whitePiece.getSizeValid()+"] getSizeValid");
        }
        System.out.println("_____________VALID_MOVES_OF_BLACK_PIECES_______________]");
        for(piece blackPiece : blackPieces){
            System.out.print(blackPiece.getName());
            System.out.println("["+blackPiece.getSizeValid()+"] getSizeValid");
        }
    }
    //-------------------------------------Print ValidMoves of pieces[8][8]------------------------------


    //----------------------------TEMPORARY FOR LIST
    public void printPinnedList(ArrayList<coords> list){
        System.out.println("_________________________LIST_________________");
        for(coords l : list)
            System.out.println("["+l.getY()+","+l.getX()+"]");
        System.out.println("_________________________LIST_________________");
    }
//----------------------------TEMPORARY FOR LIST



    //-----------------------------------PINNED HANDLER----------------------------------
    public void pinnedCheck(ArrayList<coords> list){
        //IF PINNED LIST HAS ENEMY KING IN THE LIST THEN IT COULD BE AN ACTUAL PIN
        //IF NOT THEN NO WAY IT CAN BE
        boolean samePin= false;

        //LASTNUMBERLIST IS THE LAST COORDS OF LIST (self piece coords)
        int lastNumberList=list.size();//Size of list if 8 then 7 is last coord in list

        //LASTCOORDS ARE THE COORDS OF SELF PIECE
        coords lastCoords = list.get(lastNumberList-1);
        piece lastPiece = pieces[lastCoords.getY()][lastCoords.getX()];

        boolean found =false;
        if(lastPiece.getNumber()==1){//WHITE
            for(coords loc : list){//SEARCH FOR BLACK KING
                if(Objects.equals(pieces[loc.getY()][loc.getX()].getName(), BlackKing)){
                    found=true;//FOUND IT
                    break;
                }
            }
        }
        if(lastPiece.getNumber()==0){//BLACK
            for(coords loc : list){//SEARCH FOR WHITE KING
                if(Objects.equals(pieces[loc.getY()][loc.getX()].getName(), WhiteKing)){
                    found=true;//FOUND IT
                    break;
                }
            }
        }
        //ARRAY LIST OF PINNEDLIST
        ArrayList<coords> pinnedList = new ArrayList<>();
        //START COUNT OF PIECES BETWEEN
        int numberPiecesBetween=0;
        //PPP ARE possible pinned piece location and are set to lastcoords
        coords ppp =lastCoords;//POSSIBLE PINNED PIECE

        //System.out.print("CHECKING PINNED PIECES FROM "+lastPiece.getName());
        //System.out.println(" WITH ["+lastNumberList+"] coords");

        //FOR EACH LOCATION OF LIST IN THIS CASE ALL THE COORDS OF ATTACKING PIECE
        //WITH ADDITION TO ATTACKING PIECE LOCATION(at the end of arraylist)
        for(coords loc : list){
            //IF LASTCOORDS ARE LOC(LAST coords in list) then SKIP
            if(lastCoords==loc)
                continue;
            if(lastPiece.isPinning()){
                if(loc==lastPiece.getPinnedPieceLoc()){
                    samePin=true;
                }
            }
            //IF NO PIECES IN BETWEEN
            if(numberPiecesBetween==0){
                //IF WHITE PIECE ATTACKING BLACK KING
                if(lastPiece.getNumber()==1
                        && Objects.equals(pieces[loc.getY()][loc.getX()].getName(), BlackKing)){
                    //WHITE CHECKS BLACK
                    System.out.println("WHITE CHECKS BLACK!");
                    pinnedList.add(lastCoords);
                    //WITH pinnedList CALL GLOBAL CHECK FOR BLACK
                    //RESOLVE PINNED IF CHECKING
                    lastPiece.unPinPiece();
                    inCheck(pinnedList,0);
                    //printPinnedList(pinnedList);
                    return;
                }
                //IF BLACK PIECE ATTACKING WHITE KING
                if(lastPiece.getNumber()==0
                        && Objects.equals(pieces[loc.getY()][loc.getX()].getName(), WhiteKing)){
                    //BLACK CHECKS WHITE
                    System.out.println("BLACK CHECKS WHITE!");
                    pinnedList.add(lastCoords);
                    //WITH pinnedList CALL GLOBAL CHECK FOR WHITE
                    //RESOLVE PINNED IF CHECKING
                    lastPiece.unPinPiece();
                    inCheck(pinnedList,1);
                    //printPinnedList(pinnedList);
                    return;
                }
            }
            //END OF CHECK HANDLER------------------------------


            //CHECK IF IS ENEMY KING AFTER numberPiecesBetween is updated
            if(lastPiece.getNumber()==0	//BLACK ATTACKING WHITE KING
                    && Objects.equals(pieces[loc.getY()][loc.getX()].getName(), WhiteKing)){
                //			System.out.println("-----------------BREAK1---------------");
                break;
            }
            if(lastPiece.getNumber()==1	//WHITE ATTACKING BLACK KING
                    && Objects.equals(pieces[loc.getY()][loc.getX()].getName(), BlackKing)){
                //			System.out.println("-----------------BREAK2---------------");
                break;
            }
            //IF THERE IS SOMETHING IN LOC ADD 1 to numberPiecesBetween
            if(pieces[loc.getY()][loc.getX()].exists())
                numberPiecesBetween++;
            //IF THERE IS NOTHING YOU DONT ADD TO numberPiecesBetween

            //FOR PIECES IN BETWEEN ==1
            if(numberPiecesBetween==1){	//UPDATE POSSIBLE PINNED PIECE
                if(!Objects.equals(pieces[loc.getY()][loc.getX()].getName(), none)){
                    ppp=loc;		//ONLY USED IF AT END numberPiecesBetween is 1
                    if(pieces[ppp.getY()][ppp.getX()].getNumber() == lastPiece.getNumber()){
                        //IF SAME COLOR PIECE IS BLOCKING ATTACKING PIECE THEN NO PIN
                        //			System.out.println("-----------------RETURN---------------");
                        return;//RETURNS IF BLOCKED BY SAME COLOR PIECE
                    }
                    //IF NOT SAME COLOR THEN IT ENEMY COLOR
                }
            }
            pinnedList.add(loc);
        }
        pinnedList.add(lastCoords);

        if(numberPiecesBetween==1 && found){
            //IF PINNED PASS OUT INFO TO PINNED PIECE
            //System.out.println("PINNED PIECE["+ppp.getY()+","+ppp.getX()+"]");
            lastPiece.setPinned(pieces[ppp.getY()][ppp.getX()]);
            pieces[ppp.getY()][ppp.getX()].setPinnedMoves(pinnedList);
            pieces[ppp.getY()][ppp.getX()].updateMoves();
        }
        //System.out.println(lastPiece.getName()+" found:"+found+" samePin"+samePin);
        if(!found && samePin){
            lastPiece.unPinPiece();
        }
        //	System.out.println("numberPiecesBetween="+numberPiecesBetween);

    }
    //-----------------------------------PINNED HANDLER----------------------------------



    //-----------------------------------Move for White Pawns----------------------------------
    public boolean forWhitePawn(coords from,coords to){
        int fromY=from.getY();
        int fromX=from.getX();
        int toY=to.getY();
        int toX=to.getX();

        if(toY>7||toY<0)return false;
        if(toX>7||toX<0)return false;

        //MOVE PAWN ONE SPACE FORWARD-------------------------------------------------
        if(toY==(fromY+1)&&toX==fromX){
            return !pieces[toY][toX].exists();
        }
        //MOVE PAWN TWO SPACES FORWARD-------------------------------------------------
        if(toY==(fromY+2)&&fromY==1&&toX==fromX){
            return !pieces[toY][toX].exists() && !pieces[toY - 1][toX].exists();
        }
        //TAKE WITH PAWN TO THE RIGHT-------------------------------------------------
        if((fromX+1)!=8){
            if(enPassant){
                if(enPassantCords.getY()==to.getY() && enPassantCords.getX()==to.getX()){
                    return true;
                }
            }
            if(pieces[fromY + 1][fromX + 1].exists() && toY==fromY+1 && toX==fromX+1
                    && 	pieces[fromY][fromX].getNumber()!=pieces[toY][toX].getNumber()){
                if(Objects.equals(pieces[toY][toX].getName(), BlackKing)){
                    ArrayList<coords> pinnedList = new ArrayList<>();
                    pinnedList.add(from);
                    inCheck(pinnedList,0); //WHITE CHECKS BLACK
                }
                return true;
            }
        }
        //TAKE WITH PAWN TO THE LEFT-------------------------------------------------
        if(fromX-1!=-1){
            if(enPassant){
                if(enPassantCords.getY()==to.getY() && enPassantCords.getX()==to.getX()){
                    return true;
                }
            }
            if(pieces[fromY + 1][fromX - 1].exists() && toY==fromY+1 && toX==fromX-1
                    && 	pieces[fromY][fromX].getNumber()!=pieces[toY][toX].getNumber()){
                if(Objects.equals(pieces[toY][toX].getName(), BlackKing)){
                    ArrayList<coords> pinnedList = new ArrayList<>();
                    pinnedList.add(from);
                    inCheck(pinnedList,0); //WHITE CHECKS BLACK
                }
                return true;
            }
        }
        return false;
    }
    //-----------------------------------Move for White Pawns----------------------------------


    //-----------------------------------Move for Black Pawns----------------------------------
    public boolean forBlackPawn(coords from,coords to){
        int fromY=from.getY();
        int fromX=from.getX();
        int toY=to.getY();
        int toX=to.getX();

        if(toY>7||toY<0)return false;
        if(toX>7||toX<0)return false;
        //MOVE PAWN ONE SPACE FORWARD-------------------------------------------------
        if(toY==(fromY-1)&&toX==fromX){
            return !pieces[toY][toX].exists();
        }
        //MOVE PAWN TWO SPACES FORWARD-------------------------------------------------
        if(toY==(fromY-2)&&fromY==6&&toX==fromX){
            return !pieces[toY][toX].exists() && !pieces[toY + 1][toX].exists();
        }
        //TAKE WITH PAWN TO THE RIGHT-------------------------------------------------
        if((fromX-1)!=-1){
            if(enPassant){
                if(enPassantCords.getY()==to.getY() && enPassantCords.getX()==to.getX()){
                    return true;
                }
            }
            if(pieces[fromY - 1][fromX - 1].exists() && toY==fromY-1 && toX==fromX-1
                    && 	pieces[fromY][fromX].getNumber()!=pieces[toY][toX].getNumber()){
                if(Objects.equals(pieces[toY][toX].getName(), WhiteKing)){
                    ArrayList<coords> pinnedList = new ArrayList<>();
                    pinnedList.add(from);
                    inCheck(pinnedList,1); //BLACK CHECKS WHITE
                }
                return true;
            }
        }
        //TAKE WITH PAWN TO THE LEFT-------------------------------------------------
        if(fromX+1!=8){
            if(enPassant){
                if(enPassantCords.getY()==to.getY() && enPassantCords.getX()==to.getX()){
                    return true;
                }
            }
            if(pieces[fromY - 1][fromX + 1].exists() && toY==fromY-1 && toX==fromX+1
                    && 	pieces[fromY][fromX].getNumber()!=pieces[toY][toX].getNumber()){
                if(Objects.equals(pieces[toY][toX].getName(), WhiteKing)){
                    ArrayList<coords> pinnedList = new ArrayList<>();
                    pinnedList.add(from);
                    inCheck(pinnedList,1); //BLACK CHECKS WHITE
                }
                return true;
            }
        }
        return false;
    }
    //-----------------------------------Move for Black Pawns----------------------------------


    //-----------------------------------Move for Knights----------------------------------
    public boolean forKnights(coords from,coords to){
        int fromY=from.getY();
        int fromX=from.getX();
        int toY=to.getY();
        int toX=to.getX();
        int color = pieces[toY][toX].getNumber();
        if(pieces[fromY][fromX].getNumber()==pieces[toY][toX].getNumber())
            return false;
        //SAME PIECE RETURN FALSE
        if(toY>7)return false;
        if(toX>7)return false;
        //MOVE UP -----------------------------
        if(toY>fromY){
            //MOVE LEFT
            if(toX<fromX){
                //MOVE UP 2 and LEFT 1
                if(toY==fromY+2 && toX==fromX-1){
                    if(Objects.equals(pieces[toY][toX].getName(), WhiteKing) || Objects.equals(pieces[toY][toX].getName(), BlackKing)){
                        ArrayList<coords> pinnedList = new ArrayList<>();
                        pinnedList.add(from);
                        inCheck(pinnedList,color);
                    }
                    return true;
                }
                //MOVE UP 1 and LEFT 2
                if(toY==fromY+1 && toX==fromX-2){
                    if(Objects.equals(pieces[toY][toX].getName(), WhiteKing) || Objects.equals(pieces[toY][toX].getName(), BlackKing)){
                        ArrayList<coords> pinnedList = new ArrayList<>();
                        pinnedList.add(from);
                        inCheck(pinnedList,color);
                    }
                    return true;
                }
            }
            //MOVE RIGHT
            if(toX>fromX){
                //MOVE UP 2 and RIGHT 1
                if(toY==fromY+2 && toX==fromX+1){
                    if(Objects.equals(pieces[toY][toX].getName(), WhiteKing) || Objects.equals(pieces[toY][toX].getName(), BlackKing)){
                        ArrayList<coords> pinnedList = new ArrayList<>();
                        pinnedList.add(from);
                        inCheck(pinnedList,color);
                    }
                    return true;
                }
                //MOVE UP 1 and RIGHT 2
                if(toY==fromY+1 && toX==fromX+2){
                    if(Objects.equals(pieces[toY][toX].getName(), WhiteKing) || Objects.equals(pieces[toY][toX].getName(), BlackKing)){
                        ArrayList<coords> pinnedList = new ArrayList<>();
                        pinnedList.add(from);
                        inCheck(pinnedList,color);
                    }
                    return true;
                }
            }
        }
        //MOVE DOWN -----------------------------
        if(toY<fromY){
            //MOVE LEFT
            if(toX<fromX){
                //MOVE DOWN 2 and LEFT 1
                if(toY==fromY-2 && toX==fromX-1){
                    if(Objects.equals(pieces[toY][toX].getName(), WhiteKing) || Objects.equals(pieces[toY][toX].getName(), BlackKing)){
                        ArrayList<coords> pinnedList = new ArrayList<>();
                        pinnedList.add(from);
                        inCheck(pinnedList,color);
                    }
                    return true;
                }
                //MOVE DOWN 1 and LEFT 2
                if(toY==fromY-1 && toX==fromX-2){
                    if(Objects.equals(pieces[toY][toX].getName(), WhiteKing) || Objects.equals(pieces[toY][toX].getName(), BlackKing)){
                        ArrayList<coords> pinnedList = new ArrayList<>();
                        pinnedList.add(from);
                        inCheck(pinnedList,color);
                    }
                    return true;
                }
            }
            //MOVE RIGHT
            if(toX>fromX){
                //MOVE DOWN 2 and RIGHT 1
                if(toY==fromY-2 && toX==fromX+1){
                    if(Objects.equals(pieces[toY][toX].getName(), WhiteKing) || Objects.equals(pieces[toY][toX].getName(), BlackKing)){
                        ArrayList<coords> pinnedList = new ArrayList<>();
                        pinnedList.add(from);
                        inCheck(pinnedList,color);
                    }
                    return true;
                }
                //MOVE DOWN 1 and RIGHT 2
                if(toY==fromY-1 && toX==fromX+2){
                    if(Objects.equals(pieces[toY][toX].getName(), WhiteKing) || Objects.equals(pieces[toY][toX].getName(), BlackKing)){
                        ArrayList<coords> pinnedList = new ArrayList<>();
                        pinnedList.add(from);
                        inCheck(pinnedList,color);
                    }
                    return true;
                }
            }
        }
        return false;
    }
    //-----------------------------------Move for Knights----------------------------------


    //-----------------------------------Move for Bishops----------------------------------
    public boolean forBishops(coords from,coords to){
        if(to.getY()>7||to.getY()<0)return false;
        if(to.getX()>7||to.getX()<0)return false;
        //FOR TOP LEFT MOVEMENT---------------------
        if(to.getY()>from.getY() && to.getX()<from.getX()){//Y+1 X-1
            int topLeft = Math.min(7 - from.getY(), from.getX());
            for(int i=1;i<topLeft+1;i++){
                if(pieces[from.getY()][from.getX()].getNumber()==pieces[from.getY()+i][from.getX()-i].getNumber())
                    return false;
                if(to.getY()==from.getY()+i && to.getX()==from.getX()-i)
                    return true;
                if(pieces[from.getY() + i][from.getX() - i].exists())
                    break;
            }
        }
        //FOR TOP RIGHT MOVEMENT---------------------
        if(to.getY()>from.getY() && to.getX()>from.getX()){//Y+1 X+1
            int topRight = Math.min(7 - from.getX(), 7 - from.getY());
            for(int i=1;i<topRight+1;i++){
                if(pieces[from.getY()][from.getX()].getNumber()==pieces[from.getY()+i][from.getX()+i].getNumber())
                    return false;
                if(to.getY()==from.getY()+i && to.getX()==from.getX()+i)
                    return true;
                if(pieces[from.getY() + i][from.getX() + i].exists())
                    break;
            }
        }
        //FOR BOTTOM LEFT MOVEMENT---------------------
        if(to.getY()<from.getY() && to.getX()<from.getX()){//Y-1 X-1
            int botLeft = Math.min(from.getY(), from.getX());
            for(int i=1;i<botLeft+1;i++){
                if(pieces[from.getY()][from.getX()].getNumber()==pieces[from.getY()-i][from.getX()-i].getNumber())
                    return false;
                if(to.getY()==from.getY()-i && to.getX()==from.getX()-i)
                    return true;
                if(pieces[from.getY()-i][from.getX()-i].exists())
                    break;
            }
        }
        //FOR BOTTOM RIGHT MOVEMENT---------------------
        if(to.getY()<from.getY() && to.getX()>from.getX()){//Y-1 X+1
            int botRight = Math.min(from.getY(), 7 - from.getX());
            for(int i=1;i<botRight+1;i++){
                if(pieces[from.getY()][from.getX()].getNumber()==pieces[from.getY()-i][from.getX()+i].getNumber())
                    return false;
                if(to.getY()==from.getY()-i && to.getX()==from.getX()+i)
                    return true;
                if(pieces[from.getY()-i][from.getX()+i].exists())
                    break;
            }
        }
        return false;
    }
    //-----------------------------------Move for Bishops----------------------------------


    //-----------------------------------Move for Rooks----------------------------------
    public boolean forRooks(coords from,coords to){
        if(to.getY()>7||to.getY()<0)return false;
        if(to.getX()>7||to.getX()<0)return false;
        //FOR LEFT MOVEMENT---------------------------
        if(to.getY()==from.getY() && to.getX()<from.getX()){//Y= X-
            int left=from.getX();
            for(int i=1;i<left+1;i++){
                if(pieces[from.getY()][from.getX()].getNumber()==pieces[from.getY()][from.getX()-i].getNumber())
                    return false;
                if(to.getY()==from.getY()  && to.getX()==from.getX()-i)
                    return true;
                if(pieces[from.getY()][from.getX()-i].exists())
                    break;
            }
        }
        //FOR RIGHT MOVEMENT---------------------------
        if(to.getY()==from.getY() && to.getX()>from.getX()){//Y= X+
            int right=7-from.getX();
            for(int i=1;i<right+1;i++){
                if(pieces[from.getY()][from.getX()].getNumber()==pieces[from.getY()][from.getX()+i].getNumber())
                    return false;
                if(to.getY()==from.getY()  && to.getX()==from.getX()+i)
                    return true;
                if(pieces[from.getY()][from.getX()+i].exists())
                    break;
            }
        }
        //FOR UP MOVEMENT---------------------------
        if(to.getY()>from.getY() && to.getX()==from.getX()){//Y+ X=
            int up=7-from.getY();
            for(int i=1;i<up+1;i++){
                if(pieces[from.getY()][from.getX()].getNumber()==pieces[from.getY()+i][from.getX()].getNumber())
                    return false;
                if(to.getY()==from.getY()+i  && to.getX()==from.getX())
                    return true;
                if(pieces[from.getY()+i][from.getX()].exists())
                    break;
            }
        }
        //FOR DOWN MOVEMENT---------------------------
        if(to.getY()<from.getY() && to.getX()==from.getX()){//Y- X=
            int down=from.getY();
            for(int i=1;i<down+1;i++){
                if(pieces[from.getY()][from.getX()].getNumber()==pieces[from.getY()-i][from.getX()].getNumber())
                    return false;
                if(to.getY()==from.getY()-i  && to.getX()==from.getX())
                    return true;
                if(pieces[from.getY()-i][from.getX()].exists())
                    break;
            }
        }
        return false;
    }
    //-----------------------------------Move for Rooks----------------------------------


    //-----------------------------------Move for Queens----------------------------------
    public boolean forQueens(coords from,coords to){
        if(to.getY()>7||to.getY()<0)return false;
        if(to.getX()>7||to.getX()<0)return false;
        //FOR TOP LEFT MOVEMENT---------------------
        if(to.getY()>from.getY() && to.getX()<from.getX()){//Y+1 X-1
            int topLeft = Math.min(7 - from.getY(), from.getX());
            for(int i=1;i<topLeft+1;i++){
                if(pieces[from.getY()][from.getX()].getNumber()==pieces[from.getY()+i][from.getX()-i].getNumber())
                    return false;
                if(to.getY()==from.getY()+i && to.getX()==from.getX()-i)
                    return true;
                if(pieces[from.getY()+i][from.getX()-i].exists())
                    break;
            }
        }
        //FOR TOP RIGHT MOVEMENT---------------------
        if(to.getY()>from.getY() && to.getX()>from.getX()){//Y+1 X+1
            int topRight = Math.min(7 - from.getX(), 7 - from.getY());
            for(int i=1;i<topRight+1;i++){
                if(pieces[from.getY()][from.getX()].getNumber()==pieces[from.getY()+i][from.getX()+i].getNumber())
                    return false;
                if(to.getY()==from.getY()+i && to.getX()==from.getX()+i)
                    return true;
                if(pieces[from.getY()+i][from.getX()+i].exists())
                    break;
            }
        }
        //FOR BOTTOM LEFT MOVEMENT---------------------
        if(to.getY()<from.getY() && to.getX()<from.getX()){//Y-1 X-1
            int botLeft = Math.min(from.getY(), from.getX());
            for(int i=1;i<botLeft+1;i++){
                if(pieces[from.getY()][from.getX()].getNumber()==pieces[from.getY()-i][from.getX()-i].getNumber())
                    return false;
                if(to.getY()==from.getY()-i && to.getX()==from.getX()-i)
                    return true;
                if(pieces[from.getY()-i][from.getX()-i].exists())
                    break;
            }
        }
        //FOR BOTTOM RIGHT MOVEMENT---------------------
        if(to.getY()<from.getY() && to.getX()>from.getX()){//Y-1 X+1
            int botRight = Math.min(from.getY(), 7 - from.getX());
            for(int i=1;i<botRight+1;i++){
                if(pieces[from.getY()][from.getX()].getNumber()==pieces[from.getY()-i][from.getX()+i].getNumber())
                    return false;
                if(to.getY()==from.getY()-i && to.getX()==from.getX()+i)
                    return true;
                if(pieces[from.getY()-i][from.getX()+i].exists())
                    break;
            }
        }
        //FOR LEFT MOVEMENT---------------------------
        if(to.getY()==from.getY() && to.getX()<from.getX()){//Y= X-
            int left=from.getX();
            for(int i=1;i<left+1;i++){
                if(pieces[from.getY()][from.getX()].getNumber()==pieces[from.getY()][from.getX()-i].getNumber())
                    return false;
                if(to.getY()==from.getY()  && to.getX()==from.getX()-i)
                    return true;
                if(pieces[from.getY()][from.getX()-i].exists())
                    break;
            }
        }
        //FOR RIGHT MOVEMENT---------------------------
        if(to.getY()==from.getY() && to.getX()>from.getX()){//Y= X+
            int right=7-from.getX();
            for(int i=1;i<right+1;i++){
                if(pieces[from.getY()][from.getX()].getNumber()==pieces[from.getY()][from.getX()+i].getNumber())
                    return false;
                if(to.getY()==from.getY()  && to.getX()==from.getX()+i)
                    return true;
                if(pieces[from.getY()][from.getX()+i].exists())
                    break;
            }
        }
        //FOR UP MOVEMENT---------------------------
        if(to.getY()>from.getY() && to.getX()==from.getX()){//Y+ X=
            int up=7-from.getY();
            for(int i=1;i<up+1;i++){
                if(pieces[from.getY()][from.getX()].getNumber()==pieces[from.getY()+i][from.getX()].getNumber())
                    return false;
                if(to.getY()==from.getY()+i  && to.getX()==from.getX())
                    return true;
                if(pieces[from.getY()+i][from.getX()].exists())
                    break;
            }
        }
        //FOR DOWN MOVEMENT---------------------------
        if(to.getY()<from.getY() && to.getX()==from.getX()){//Y- X=
            int down=from.getY();
            for(int i=1;i<down+1;i++){
                if(pieces[from.getY()][from.getX()].getNumber()==pieces[from.getY()-i][from.getX()].getNumber())
                    return false;
                if(to.getY()==from.getY()-i  && to.getX()==from.getX())
                    return true;
                if(pieces[from.getY()-i][from.getX()].exists())
                    break;
            }
        }
        return false;

    }
    //-----------------------------------Move for Queens----------------------------------


    //-----------------------------------Move for Kings----------------------------------
    public boolean forKings(coords from,coords to){
        if(pieces[from.getY()][from.getX()].getNumber()==1 && blackVision.contains(to)){
            //System.out.println("forKings on vision at "+to.getY()+","+to.getX());
            return false;//CANT MOVE THERE
        }
        if(pieces[from.getY()][from.getX()].getNumber()==0 && whiteVision.contains(to)){
            //System.out.println("forKings on vision at "+to.getY()+","+to.getX());
            return false;//CANT MOVE THERE
        }
        if(!pieces[from.getY()][from.getX()].isMoved()){
            if(from.getY()==to.getY() && to.getX()==from.getX()+2){
                if(pieces[from.getY()][from.getX()+3].isMoved()){
                    //System.out.println(pieces[from.getY()][from.getX()].getName()+"forKings return false");
                    return false;
                }
                if(pieces[from.getY()][from.getX()].getNumber()==1){//WHITE
                    //locations[from.getY()][from.getX()+1] and locations[from.getY()][from.getX()+2]
                    if(blackVision.contains(locations[from.getY()][from.getX()+1])||
                            blackVision.contains(locations[from.getY()][from.getX()+2])){
                        return false;
                    }
                }else if(pieces[from.getY()][from.getX()].getNumber()==0) {//BLACK
                    //locations[from.getY()][from.getX()+1] and locations[from.getY()][from.getX()+2]
                    if(whiteVision.contains(locations[from.getY()][from.getX()+1])||
                            whiteVision.contains(locations[from.getY()][from.getX()+2])){
                        return false;
                    }
                }
                //System.out.println(pieces[from.getY()][from.getX()].getName()+"forKings return true for :"+to.getX());
                return true;
            }else if(from.getY()==to.getY() && to.getX()==from.getX()-2){
                if(pieces[from.getY()][from.getX()-4].isMoved()){
                    //System.out.println(pieces[from.getY()][from.getX()].getName()+"forKings return false");
                    return false;
                }
                if(pieces[from.getY()][from.getX()].getNumber()==1){//WHITE
                    //locations[from.getY()][from.getX()-1] and locations[from.getY()][from.getX()-2]
                    if(blackVision.contains(locations[from.getY()][from.getX()-1])||
                            blackVision.contains(locations[from.getY()][from.getX()-2])){
                        return false;
                    }
                }if(pieces[from.getY()][from.getX()].getNumber()==0) {//BLACK
                    //locations[from.getY()][from.getX()+1] and locations[from.getY()][from.getX()+2]
                    if(whiteVision.contains(locations[from.getY()][from.getX()-1])||
                            whiteVision.contains(locations[from.getY()][from.getX()-2])){
                        return false;
                    }
                }
                //System.out.println(pieces[from.getY()][from.getX()].getName()+"forKings return true"+to.getX());
                return true;
            }
        }
        //from is WHITE KING AND TO COORDS ARE IN ENEMY VISION
        //System.out.println("forKings "+to.getY()+","+to.getX());

        //if(to.getY()>7||to.getY()<0)return false;
        //if(to.getX()>7||to.getX()<0)return false;
        //FOR TOP LEFT MOVEMENT---------------------
        if(to.getY()>from.getY() && to.getX()<from.getX()){//Y+1 X-1
            return pieces[from.getY()][from.getX()].getNumber() != pieces[from.getY() + 1][from.getX() - 1].getNumber();
        }
        //FOR TOP RIGHT MOVEMENT---------------------
        if(to.getY()>from.getY() && to.getX()>from.getX()){//Y+1 X+1
            return pieces[from.getY()][from.getX()].getNumber() != pieces[from.getY() + 1][from.getX() + 1].getNumber();
        }
        //FOR BOTTOM LEFT MOVEMENT---------------------
        if(to.getY()<from.getY() && to.getX()<from.getX()){//Y-1 X-1
            return pieces[from.getY()][from.getX()].getNumber() != pieces[from.getY() - 1][from.getX() - 1].getNumber();
        }
        //FOR BOTTOM RIGHT MOVEMENT---------------------
        if(to.getY()<from.getY() && to.getX()>from.getX()){//Y-1 X+1
            return pieces[from.getY()][from.getX()].getNumber() != pieces[from.getY() - 1][from.getX() + 1].getNumber();
        }
        //FOR LEFT MOVEMENT---------------------------
        if(to.getY()==from.getY() && to.getX()<from.getX()){//Y= X-
            return pieces[from.getY()][from.getX()].getNumber() != pieces[from.getY()][from.getX() - 1].getNumber();
        }
        //FOR RIGHT MOVEMENT---------------------------
        if(to.getY()==from.getY() && to.getX()>from.getX()){//Y= X+
            return pieces[from.getY()][from.getX()].getNumber() != pieces[from.getY()][from.getX() + 1].getNumber();
        }
        //FOR UP MOVEMENT---------------------------
        if(to.getY()>from.getY() && to.getX()==from.getX()){//Y+ X=
            return pieces[from.getY()][from.getX()].getNumber() != pieces[from.getY() + 1][from.getX()].getNumber();
        }
        //FOR DOWN MOVEMENT---------------------------
        if(to.getY()<from.getY() && to.getX()==from.getX()){//Y- X=
            return pieces[from.getY()][from.getX()].getNumber() != pieces[from.getY() - 1][from.getX()].getNumber();
        }
        return false;
    }
    //-----------------------------------Move for Kings----------------------------------

    //-----------------------------------Vision for White Pawns----------------------------------
    public boolean forWhitePawnVision(coords from,coords to){
        if(to.getY()>7||to.getY()<0)return false;
        if(to.getX()>7||to.getX()<0)return false;

        //MOVE PAWN ONE SPACE FORWARD-------------------------------------------------
        if(to.getY()==(from.getY()+1)&&to.getX()==from.getX()){
            return false;
        }
        //MOVE PAWN TWO SPACES FORWARD-------------------------------------------------
        if(to.getY()==(from.getY()+2)&&from.getY()==1&&to.getX()==from.getX()){
            if(pieces[to.getY() - 1][to.getX()].exists()){
                return false;
            }
            return false;
        }
        //TAKE WITH PAWN TO THE RIGHT-------------------------------------------------
        if((from.getX()+1)!=8){
            if(to.getY()==from.getY()+1 && to.getX()==from.getX()+1){
                return true;
            }
        }
        //TAKE WITH PAWN TO THE LEFT-------------------------------------------------
        if(from.getX()-1!=-1){
            return to.getY() == from.getY() + 1 && to.getX() == from.getX() - 1;
        }
        return false;
    }
    //-----------------------------------Vision for White Pawns----------------------------------


    //-----------------------------------Vision for Black Pawns----------------------------------
    public boolean forBlackPawnVision(coords from,coords to){
        if(to.getY()>7||to.getY()<0)return false;
        if(to.getX()>7||to.getX()<0)return false;
        //MOVE PAWN ONE SPACE FORWARD-------------------------------------------------
        if(to.getY()==(from.getY()-1)&&to.getX()==from.getX()){
            return false;
        }
        //MOVE PAWN TWO SPACES FORWARD-------------------------------------------------
        if(to.getY()==(from.getY()-2)&&from.getY()==6&&to.getX()==from.getX()){
            if(pieces[to.getY()+1][to.getX()].exists()){
                return false;
            }
            return false;
        }
        //TAKE WITH PAWN TO THE RIGHT-------------------------------------------------
        if((from.getX()-1)!=-1){
            if(to.getY()==from.getY()-1 && to.getX()==from.getX()-1){
                return true;
            }
        }
        //TAKE WITH PAWN TO THE LEFT-------------------------------------------------
        if(from.getX()+1!=8){
            return to.getY() == from.getY() - 1 && to.getX() == from.getX() + 1;
        }
        return false;
    }
    //-----------------------------------Vision for Black Pawns----------------------------------


    //-----------------------------------Vision for Knights----------------------------------
    public boolean forKnightsVision(coords from,coords to){
        if(to.getY()>7||to.getY()<0)return false;
        if(to.getX()>7||to.getX()<0)return false;
        //MOVE UP -----------------------------
        if(to.getY()>from.getY()){
            //MOVE LEFT
            if(to.getX()<from.getX()){
                //MOVE UP 2 and LEFT 1
                if(to.getY()==from.getY()+2 && to.getX()==from.getX()-1)
                    return true;
                //MOVE UP 1 and LEFT 2
                if(to.getY()==from.getY()+1 && to.getX()==from.getX()-2)
                    return true;

            }
            //MOVE RIGHT
            if(to.getX()>from.getX()){
                //MOVE UP 2 and RIGHT 1
                if(to.getY()==from.getY()+2 && to.getX()==from.getX()+1)
                    return true;
                //MOVE UP 1 and RIGHT 2
                if(to.getY()==from.getY()+1 && to.getX()==from.getX()+2)
                    return true;
            }
        }
        //MOVE DOWN -----------------------------
        if(to.getY()<from.getY()){
            //MOVE LEFT
            if(to.getX()<from.getX()){
                //MOVE DOWN 2 and LEFT 1
                if(to.getY()==from.getY()-2 && to.getX()==from.getX()-1)
                    return true;
                //MOVE DOWN 1 and LEFT 2
                if(to.getY()==from.getY()-1 && to.getX()==from.getX()-2)
                    return true;

            }
            //MOVE RIGHT
            if(to.getX()>from.getX()){
                //MOVE DOWN 2 and RIGHT 1
                if(to.getY()==from.getY()-2 && to.getX()==from.getX()+1)
                    return true;
                //MOVE DOWN 1 and RIGHT 2
                return to.getY() == from.getY() - 1 && to.getX() == from.getX() + 2;
            }
        }
        return false;
    }
    //-----------------------------------Vision for Knights----------------------------------


    //-----------------------------------Vision for Bishops----------------------------------
    public boolean forBishopsVision(coords from,coords to){
        int fromY=from.getY();
        int fromX=from.getX();
        int toY=to.getY();
        int toX=to.getX();

        int color=pieces[fromY][fromX].getNumber();//1=WHITE 0=BLACK
        //FROM IS THE PIECE THAT WANT TO MOVE

        //TO IS WHAT THE PIECE CAN SEE (TRUE it can see it) (FALSE it cant see it)

        if(toY>7||toY<0)return false;
        if(toX>7||toX<0)return false;
        //FOR TOP LEFT MOVEMENT---------------------
        if(toY>fromY && toX<fromX){//Y+1 X-1
            int topLeft = Math.min(7 - fromY, fromX);
            for(int i=1;i<topLeft+1;i++){//Y+i X-i TOPLEFT
                if(toY==fromY+i && toX==fromX-i){
                    //CHECK IF ALLY KING IF SO RETURN FALSE
                    if(color==1){
                        if(Objects.equals(pieces[fromY + i][fromX - i].getName(), WhiteKing)){
                            return false;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY + i][fromX - i].getName(), BlackKing)){
                            return false;
                        }
                    }
                    updateVision(pieces[fromY][fromX],to);
                    return true;
                }
                //CHECKING IF A PIECE IS THERE
                if(pieces[fromY + i][fromX - i].exists()){
                    //THIS MEANS THERE IS A PIECE HERE
                    //CHECK IF ENEMY KING IF SO CONTINUE INSTEAD OF BREAK(SEE TRU KING)
                    if(color==1){
                        if(Objects.equals(pieces[fromY + i][fromX - i].getName(), BlackKing)){
                            continue;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY + i][fromX - i].getName(), WhiteKing)){
                            continue;
                        }
                    }
                    break;
                }
            }
            return false;
        }
        //FOR TOP RIGHT MOVEMENT---------------------
        if(toY>fromY && toX>fromX){//Y+1 X+1
            int topRight = Math.min(7 - fromX, 7 - fromY);
            for(int i=1;i<topRight+1;i++){//Y+i X+i TOPRIGHT
                if(toY==fromY+i && toX==fromX+i){
                    //CHECK IF ALLY KING IF SO RETURN FALSE
                    if(color==1){
                        if(Objects.equals(pieces[fromY + i][fromX + i].getName(), WhiteKing)){
                            return false;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY + i][fromX + i].getName(), BlackKing)){
                            return false;
                        }
                    }
                    updateVision(pieces[fromY][fromX],to);
                    return true;
                }
                //CHECKING IF A PIECE IS THERE
                if(pieces[fromY+i][fromX+i].exists()){
                    //THIS MEANS THERE IS A PIECE HERE
                    //CHECK IF ENEMY KING IF SO CONTINUE INSTEAD OF BREAK(SEE TRU KING)
                    if(color==1){
                        if(Objects.equals(pieces[fromY + i][fromX + i].getName(), BlackKing)){
                            continue;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY + i][fromX + i].getName(), WhiteKing)){
                            continue;
                        }
                    }
                    break;
                }
            }
            return false;
        }
        //FOR BOTTOM LEFT MOVEMENT---------------------
        if(toY<fromY && toX<fromX){//Y-1 X-1
            int botLeft = Math.min(fromY, fromX);
            for(int i=1;i<botLeft+1;i++){//Y-i X-i BOTLEFT
                if(toY==fromY-i && toX==fromX-i){
                    //CHECK IF ALLY KING IF SO RETURN FALSE
                    if(color==1){
                        if(Objects.equals(pieces[fromY - i][fromX - i].getName(), WhiteKing)){
                            return false;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY - i][fromX - i].getName(), BlackKing)){
                            return false;
                        }
                    }
                    updateVision(pieces[fromY][fromX],to);
                    return true;
                }
                //CHECKING IF A PIECE IS THERE
                if(pieces[fromY-i][fromX-i].exists()){
                    //THIS MEANS THERE IS A PIECE HERE
                    //CHECK IF ENEMY KING IF SO CONTINUE INSTEAD OF BREAK(SEE TRU KING)
                    if(color==1){
                        if(Objects.equals(pieces[fromY - i][fromX - i].getName(), BlackKing)){
                            continue;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY - i][fromX - i].getName(), WhiteKing)){
                            continue;
                        }
                    }
                    break;
                }
            }
            return false;
        }
        //FOR BOTTOM RIGHT MOVEMENT---------------------
        if(toY<fromY && toX>fromX){//Y-1 X+1
            int botRight = Math.min(fromY, 7 - fromX);
            for(int i=1;i<botRight+1;i++){//Y-i X+i BOTRIGHT
                if(toY==fromY-i && toX==fromX+i){
                    //CHECK IF ALLY KING IF SO RETURN FALSE
                    if(color==1){
                        if(Objects.equals(pieces[fromY - i][fromX + i].getName(), WhiteKing)){
                            return false;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY - i][fromX + i].getName(), BlackKing)){
                            return false;
                        }
                    }
                    updateVision(pieces[fromY][fromX],to);
                    return true;
                }
                //CHECKING IF A PIECE IS THERE
                if(pieces[fromY-i][fromX+i].exists()){
                    //THIS MEANS THERE IS A PIECE HERE
                    //CHECK IF ENEMY KING IF SO CONTINUE INSTEAD OF BREAK(SEE TRU KING)
                    if(color==1){
                        if(Objects.equals(pieces[fromY - i][fromX + i].getName(), BlackKing)){
                            continue;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY - i][fromX + i].getName(), WhiteKing)){
                            continue;
                        }
                    }
                    break;
                }
            }
            return false;
        }
        return false;
    }
    //-----------------------------------Vision for Bishops----------------------------------


    //-----------------------------------Vision for Rooks----------------------------------
    public boolean forRooksVision(coords from,coords to){
        int fromY=from.getY();
        int fromX=from.getX();
        int toY=to.getY();
        int toX=to.getX();

        int color=pieces[fromY][fromX].getNumber();//1=WHITE 0=BLACK
        if(toY>7||toY<0)return false;
        if(toX>7||toX<0)return false;
        //FOR LEFT MOVEMENT---------------------------
        if(toY==fromY && toX<fromX){//Y= X-
            for(int i=1;i<fromX+1;i++){//Y= X-i LEFT
                if(toX == fromX - i){
                    //CHECK IF ALLY KING IF SO RETURN FALSE
                    if(color==1){
                        if(Objects.equals(pieces[fromY][fromX - i].getName(), WhiteKing)){
                            return false;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY][fromX - i].getName(), BlackKing)){
                            return false;
                        }
                    }
                    //System.out.println("rook"+toY+","+toX);
                    updateVision(pieces[fromY][fromX],to);
                    return true;
                }
                //CHECKING IF A PIECE IS THERE
                if(pieces[fromY][fromX - i].exists()){
                    //THIS MEANS THERE IS A PIECE HERE
                    //CHECK IF ENEMY KING IF SO CONTINUE INSTEAD OF BREAK(SEE TRU KING)
                    if(color==1){
                        if(Objects.equals(pieces[fromY][fromX - i].getName(), BlackKing)){
                            continue;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY][fromX - i].getName(), WhiteKing)){
                            continue;
                        }
                    }
                    //System.out.println(pieces[fromY][fromX-i].getName()+"BREAK");
                    break;
                }
            }
        }
        //FOR RIGHT MOVEMENT---------------------------
        if(toY==fromY && toX>fromX){//Y= X+
            int  right=7-fromX;
            for(int i=1;i<right+1;i++){//Y= X+i RIGHT
                if(toX == fromX + i){
                    //CHECK IF ALLY KING IF SO RETURN FALSE
                    if(color==1){
                        if(Objects.equals(pieces[fromY][fromX + i].getName(), WhiteKing)){
                            return false;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY][fromX + i].getName(), BlackKing)){
                            return false;
                        }
                    }
                    updateVision(pieces[fromY][fromX],to);
                    return true;
                }
                //CHECKING IF A PIECE IS THERE
                if(pieces[fromY][fromX + i].exists()){
                    //THIS MEANS THERE IS A PIECE HERE
                    //CHECK IF ENEMY KING IF SO CONTINUE INSTEAD OF BREAK(SEE TRU KING)
                    if(color==1){
                        if(Objects.equals(pieces[fromY][fromX + i].getName(), BlackKing)){
                            continue;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY][fromX + i].getName(), WhiteKing)){
                            continue;
                        }
                    }
                    break;
                }
            }
        }
        //FOR UP MOVEMENT---------------------------
        if(toY>fromY && toX==fromX){//Y+ X=
            int up=7-fromY;
            for(int i=1;i<up+1;i++){//Y+i X= UP
                if(toY == fromY + i){
                    //CHECK IF ALLY KING IF SO RETURN FALSE
                    if(color==1){
                        if(Objects.equals(pieces[fromY + i][fromX].getName(), WhiteKing)){
                            return false;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY + i][fromX].getName(), BlackKing)){
                            return false;
                        }
                    }
                    updateVision(pieces[fromY][fromX],to);
                    return true;
                }
                //CHECKING IF A PIECE IS THERE
                if(pieces[fromY+i][fromX].exists()){
                    //THIS MEANS THERE IS A PIECE HERE
                    //CHECK IF ENEMY KING IF SO CONTINUE INSTEAD OF BREAK(SEE TRU KING)
                    if(color==1){
                        if(Objects.equals(pieces[fromY + i][fromX].getName(), BlackKing)){
                            continue;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY + i][fromX].getName(), WhiteKing)){
                            continue;
                        }
                    }
                    break;
                }
            }
        }
        //FOR DOWN MOVEMENT---------------------------
        if(toY<fromY && toX==fromX){//Y- X=
            for(int i=1;i<fromY+1;i++){//Y-i X= DOWN
                if(toY == fromY - i){
                    //CHECK IF ALLY KING IF SO RETURN FALSE
                    if(color==1){
                        if(Objects.equals(pieces[fromY - i][fromX].getName(), WhiteKing)){
                            return false;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY - i][fromX].getName(), BlackKing)){
                            return false;
                        }
                    }
                    updateVision(pieces[fromY][fromX],to);
                    return true;
                }
                //CHECKING IF A PIECE IS THERE
                if(pieces[fromY-i][fromX].exists()){
                    //THIS MEANS THERE IS A PIECE HERE
                    //CHECK IF ENEMY KING IF SO CONTINUE INSTEAD OF BREAK(SEE TRU KING)
                    if(color==1){
                        if(Objects.equals(pieces[fromY - i][fromX].getName(), BlackKing)){
                            continue;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY - i][fromX].getName(), WhiteKing)){
                            continue;
                        }
                    }
                    break;
                }
            }
        }
        return false;
    }
    //-----------------------------------Vision for Rooks----------------------------------


    //-----------------------------------Vision for Queens----------------------------------
    public boolean forQueensVision(coords from,coords to){
        int fromY=from.getY();
        int fromX=from.getX();
        int toY=to.getY();
        int toX=to.getX();

        int color=pieces[fromY][fromX].getNumber();//1=WHITE 0=BLACK
        if(to.getY()>7||to.getY()<0)return false;
        if(to.getX()>7||to.getX()<0)return false;
        //FOR TOP LEFT MOVEMENT---------------------
        if(toY>fromY && toX<fromX){//Y+1 X-1
            int topLeft = Math.min(7 - fromY, fromX);
            for(int i=1;i<topLeft+1;i++){//Y+i X-i TOPLEFT
                if(toY==fromY+i && toX==fromX-i){
                    //CHECK IF ALLY KING IF SO RETURN FALSE
                    if(color==1){
                        if(Objects.equals(pieces[fromY + i][fromX - i].getName(), WhiteKing)){
                            return false;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY + i][fromX - i].getName(), BlackKing)){
                            return false;
                        }
                    }
                    updateVision(pieces[fromY][fromX],to);
                    return true;
                }
                //CHECKING IF A PIECE IS THERE
                if(pieces[fromY + i][fromX - i].exists()){
                    //THIS MEANS THERE IS A PIECE HERE
                    //CHECK IF ENEMY KING IF SO CONTINUE INSTEAD OF BREAK(SEE TRU KING)
                    if(color==1){
                        if(Objects.equals(pieces[fromY + i][fromX - i].getName(), BlackKing)){
                            continue;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY + i][fromX - i].getName(), WhiteKing)){
                            continue;
                        }
                    }
                    break;
                }
            }
            return false;
        }
        //FOR TOP RIGHT MOVEMENT---------------------
        if(toY>fromY && toX>fromX){//Y+1 X+1
            int topRight = Math.min(7 - fromX, 7 - fromY);
            for(int i=1;i<topRight+1;i++){//Y+i X+i TOPRIGHT
                if(toY==fromY+i && toX==fromX+i){
                    //CHECK IF ALLY KING IF SO RETURN FALSE
                    if(color==1){
                        if(Objects.equals(pieces[fromY + i][fromX + i].getName(), WhiteKing)){
                            return false;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY + i][fromX + i].getName(), BlackKing)){
                            return false;
                        }
                    }
                    updateVision(pieces[fromY][fromX],to);
                    return true;
                }
                //CHECKING IF A PIECE IS THERE
                if(pieces[fromY+i][fromX+i].exists()){
                    //THIS MEANS THERE IS A PIECE HERE
                    //CHECK IF ENEMY KING IF SO CONTINUE INSTEAD OF BREAK(SEE TRU KING)
                    if(color==1){
                        if(Objects.equals(pieces[fromY + i][fromX + i].getName(), BlackKing)){
                            continue;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY + i][fromX + i].getName(), WhiteKing)){
                            continue;
                        }
                    }
                    break;
                }
            }
            return false;
        }
        //FOR BOTTOM LEFT MOVEMENT---------------------
        if(toY<fromY && toX<fromX){//Y-1 X-1
            int botLeft = Math.min(fromY, fromX);
            for(int i=1;i<botLeft+1;i++){//Y-i X-i BOTLEFT
                if(toY==fromY-i && toX==fromX-i){
                    //CHECK IF ALLY KING IF SO RETURN FALSE
                    if(color==1){
                        if(Objects.equals(pieces[fromY - i][fromX - i].getName(), WhiteKing)){
                            return false;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY - i][fromX - i].getName(), BlackKing)){
                            return false;
                        }
                    }
                    updateVision(pieces[fromY][fromX],to);
                    return true;
                }
                //CHECKING IF A PIECE IS THERE
                if(pieces[fromY-i][fromX-i].exists()){
                    //THIS MEANS THERE IS A PIECE HERE
                    //CHECK IF ENEMY KING IF SO CONTINUE INSTEAD OF BREAK(SEE TRU KING)
                    if(color==1){
                        if(Objects.equals(pieces[fromY - i][fromX - i].getName(), BlackKing)){
                            continue;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY - i][fromX - i].getName(), WhiteKing)){
                            continue;
                        }
                    }
                    break;
                }
            }
            return false;
        }
        //FOR BOTTOM RIGHT MOVEMENT---------------------
        if(toY<fromY && toX>fromX){//Y-1 X+1
            int botRight = Math.min(fromY, 7 - fromX);
            for(int i=1;i<botRight+1;i++){//Y-i X+i BOTRIGHT
                if(toY==fromY-i && toX==fromX+i){
                    //CHECK IF ALLY KING IF SO RETURN FALSE
                    if(color==1){
                        if(Objects.equals(pieces[fromY - i][fromX + i].getName(), WhiteKing)){
                            return false;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY - i][fromX + i].getName(), BlackKing)){
                            return false;
                        }
                    }
                    updateVision(pieces[fromY][fromX],to);
                    return true;
                }
                //CHECKING IF A PIECE IS THERE
                if(pieces[fromY-i][fromX+i].exists()){
                    //THIS MEANS THERE IS A PIECE HERE
                    //CHECK IF ENEMY KING IF SO CONTINUE INSTEAD OF BREAK(SEE TRU KING)
                    if(color==1){
                        if(Objects.equals(pieces[fromY - i][fromX + i].getName(), BlackKing)){
                            continue;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY - i][fromX + i].getName(), WhiteKing)){
                            continue;
                        }
                    }
                    break;
                }
            }
            return false;
        }
        //FOR LEFT MOVEMENT---------------------------
        if(toY==fromY && toX<fromX){//Y= X-
            for(int i=1;i<fromX+1;i++){//Y= X-i LEFT
                if(toX == fromX - i){
                    //CHECK IF ALLY KING IF SO RETURN FALSE
                    if(color==1){
                        if(Objects.equals(pieces[fromY][fromX - i].getName(), WhiteKing)){
                            return false;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY][fromX - i].getName(), BlackKing)){
                            return false;
                        }
                    }
                    //System.out.println("rook"+toY+","+toX);
                    updateVision(pieces[fromY][fromX],to);
                    return true;
                }
                //CHECKING IF A PIECE IS THERE
                if(pieces[fromY][fromX - i].exists()){
                    //THIS MEANS THERE IS A PIECE HERE
                    //CHECK IF ENEMY KING IF SO CONTINUE INSTEAD OF BREAK(SEE TRU KING)
                    if(color==1){
                        if(Objects.equals(pieces[fromY][fromX - i].getName(), BlackKing)){
                            continue;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY][fromX - i].getName(), WhiteKing)){
                            continue;
                        }
                    }
                    //System.out.println(pieces[fromY][fromX-i].getName()+"BREAK");
                    break;
                }
            }
        }
        //FOR RIGHT MOVEMENT---------------------------
        if(toY==fromY && toX>fromX){//Y= X+
            int  right=7-fromX;
            for(int i=1;i<right+1;i++){//Y= X+i RIGHT
                if(toX == fromX + i){
                    //CHECK IF ALLY KING IF SO RETURN FALSE
                    if(color==1){
                        if(Objects.equals(pieces[fromY][fromX + i].getName(), WhiteKing)){
                            return false;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY][fromX + i].getName(), BlackKing)){
                            return false;
                        }
                    }
                    updateVision(pieces[fromY][fromX],to);
                    return true;
                }
                //CHECKING IF A PIECE IS THERE
                if(pieces[fromY][fromX + i].exists()){
                    //THIS MEANS THERE IS A PIECE HERE
                    //CHECK IF ENEMY KING IF SO CONTINUE INSTEAD OF BREAK(SEE TRU KING)
                    if(color==1){
                        if(Objects.equals(pieces[fromY][fromX + i].getName(), BlackKing)){
                            continue;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY][fromX + i].getName(), WhiteKing)){
                            continue;
                        }
                    }
                    break;
                }
            }
        }
        //FOR UP MOVEMENT---------------------------
        if(toY>fromY){//Y+ X=
            int up=7-fromY;
            for(int i=1;i<up+1;i++){//Y+i X= UP
                if(toY == fromY + i){
                    //CHECK IF ALLY KING IF SO RETURN FALSE
                    if(color==1){
                        if(Objects.equals(pieces[fromY + i][fromX].getName(), WhiteKing)){
                            return false;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY + i][fromX].getName(), BlackKing)){
                            return false;
                        }
                    }
                    updateVision(pieces[fromY][fromX],to);
                    return true;
                }
                //CHECKING IF A PIECE IS THERE
                if(pieces[fromY+i][fromX].exists()){
                    //THIS MEANS THERE IS A PIECE HERE
                    //CHECK IF ENEMY KING IF SO CONTINUE INSTEAD OF BREAK(SEE TRU KING)
                    if(color==1){
                        if(Objects.equals(pieces[fromY + i][fromX].getName(), BlackKing)){
                            continue;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY + i][fromX].getName(), WhiteKing)){
                            continue;
                        }
                    }
                    break;
                }
            }
        }
        //FOR DOWN MOVEMENT---------------------------
        if(toY<fromY){//Y- X=
            for(int i=1;i<fromY+1;i++){//Y-i X= DOWN
                if(toY == fromY - i){
                    //CHECK IF ALLY KING IF SO RETURN FALSE
                    if(color==1){
                        if(Objects.equals(pieces[fromY - i][fromX].getName(), WhiteKing)){
                            return false;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY - i][fromX].getName(), BlackKing)){
                            return false;
                        }
                    }
                    updateVision(pieces[fromY][fromX],to);
                    return true;
                }
                //CHECKING IF A PIECE IS THERE
                if(pieces[fromY-i][fromX].exists()){
                    //THIS MEANS THERE IS A PIECE HERE
                    //CHECK IF ENEMY KING IF SO CONTINUE INSTEAD OF BREAK(SEE TRU KING)
                    if(color==1){
                        if(Objects.equals(pieces[fromY - i][fromX].getName(), BlackKing)){
                            continue;
                        }
                    }else if(color==0){
                        if(Objects.equals(pieces[fromY - i][fromX].getName(), WhiteKing)){
                            continue;
                        }
                    }
                    break;
                }
            }
        }
        return false;

    }
    //-----------------------------------Vision for Queens----------------------------------


    //-----------------------------------Vision for Kings----------------------------------
    public boolean forKingsVision(coords from,coords to){

        if(!pieces[from.getY()][from.getX()].isMoved()) {//KING HAS NOT MOVED
            if (to.getY() == from.getY() && to.getX() == 6) {//checking for king side castling
                if (!pieces[to.getY()][7].isMoved()) {//ROOK HAS NOT MOVED(IF MOVED IT WOULD BE DIFFERENT PIECE )
                    //CHECK IN BETWEEN[from.getY()][5] and [from.getY()][6]
                    if (pieces[from.getY()][5].exists() || pieces[from.getY()][6].exists()) {
                        return false;
                    }
                    return true;
                }
            } else if (to.getY() == from.getY() && to.getX() == 2) {//checking for Queen side castling
                if (!pieces[to.getY()][0].isMoved()) {//ROOK HAS NOT MOVED(IF MOVED IT WOULD BE DIFFERENT PIECE )
                    //CHECK IN BETWEEN[from.getY()][1] ,[from.getY()][2] and [from.getY()][3]
                    if (pieces[from.getY()][1].exists() || pieces[from.getY()][2].exists() ||
                            pieces[from.getY()][3].exists()) {
                        return false;
                    }
                    return true;
                }
            }
        }
        //BEFORE ANYTHING CHECK ENEMY KINGS MOVES(CANT MOVE INTO ENEMY KINGS MOVES NO MATTER WHAT)
        if(pieces[from.getY()][from.getX()]==whiteKING){
            if(Math.abs(blackKING.getY()-from.getY())<=2 && Math.abs(blackKING.getX()-from.getX())<=2){
                System.out.println("vision for whiteKING");
                for(coords move : blackKING.getMoves()){
                    if(to==move)
                        return false;
                }
            }
        }else if(pieces[from.getY()][from.getX()]==blackKING){
            if(Math.abs(whiteKING.getY()-from.getY())<=2 && Math.abs(whiteKING.getX()-from.getX())<=2){
                System.out.println("vision for blackKING");
                for(coords move : whiteKING.getMoves()){
                    if(to==move)
                        return false;
                }
            }
        }
        //FOR TOP LEFT MOVEMENT---------------------
        if(to.getY()>from.getY() && to.getX()<from.getX()){//Y+1 X-1
            return true;
        }
        //FOR TOP RIGHT MOVEMENT---------------------
        if(to.getY()>from.getY() && to.getX()>from.getX()){//Y+1 X+1
            return true;
        }
        //FOR BOTTOM LEFT MOVEMENT---------------------
        if(to.getY()<from.getY() && to.getX()<from.getX()){//Y-1 X-1
            return true;
        }
        //FOR BOTTOM RIGHT MOVEMENT---------------------
        if(to.getY()<from.getY() && to.getX()>from.getX()){//Y-1 X+1
            return true;
        }
        //FOR LEFT MOVEMENT---------------------------
        if(to.getY()==from.getY() && to.getX()<from.getX()){//Y= X-
            return true;
        }
        //FOR RIGHT MOVEMENT---------------------------
        if(to.getY()==from.getY() && to.getX()>from.getX()){//Y= X+
            return true;
        }
        //FOR UP MOVEMENT---------------------------
        if(to.getY()>from.getY() && to.getX()==from.getX()){//Y+ X=
            return true;
        }
        //FOR DOWN MOVEMENT---------------------------
        //Y- X=
        return to.getY() < from.getY() && to.getX() == from.getX();
    }
    //-----------------------------------Vision for Kings----------------------------------

}