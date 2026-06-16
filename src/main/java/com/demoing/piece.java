package com.demoing;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.Objects;

public class piece {
    Pieces pieces;		//LINKS TO Pieces class so it can get info
    private final String name;	//ITS NAME example: WhiteKing
    private final int number;	//NUMBER DEMONSTRATING COLOR (might change later)
    private ImageIcon icon;	//ITS CORRESPONDING IMAGE DEPENDING ON PIECE(if blank,its empty)
    private final JLabel label;	//NEEDED TO ADD TO GRID AS LABEL

    private coords location;//ITS LOCATION ON pieces[y][x] on Pieces class

    //List of all moves (optimistic and wont have out of bounds moves)
    ArrayList<coords> moves = new ArrayList<>();

    //List of only vision moves(checks from optimistic moves)
    ArrayList<coords> visionMoves = new ArrayList<>();

    //List of only valid moves(checks from vision moves)
    ArrayList<coords> validMoves = new ArrayList<>();

    //List of coords used when pinning another piece(includes itsOwn location)
    ArrayList<coords> pinnedCoords = new ArrayList<>();


    private boolean isCheck = false;	//IF TRUE IT MEANS THEIR KING IS UNDER ATTACK
    private boolean isPinned = false;	//IF TRUE IT IS PINNED THEN
    //Coords of piece that is being pinned by this piece
    private piece pinnedPiece=null;
    //Coords of piece that is pinning this piece
    private piece pinnedBy=null;

    //HANDLER FOR AN EMPTY PIECE aka no piece in this location
    public piece(coords location,String name){
        this.location = location;	//location on pieces[y][x]
        this.name=name;		//name is none for simplicity
        this.number=-1;			//number -1 because no one can move this piece
        this.label = new JLabel();	//NEEDED TO ADD TO GRID AS LABEL
        //CREATES THE LABEL WITH empty.png which is literally empty
        this.label.setIcon(new ImageIcon("src/main/resources/pieces/empty.png"));
    }
    //HANDLER FOR ALL PIECES aka WhiteKnight or BlackPawn
    public piece(Pieces pieces,coords location,String name,int number,ImageIcon icon){
        this.pieces= pieces;		//Passes Pieces so it links to Pieces class
        this.location = location;	//location on pieces[y][x]
        this.label = new JLabel();	//NEEDED TO ADD TO GRID AS LABEL
        this.name=name;			//name for piece example WhiteKnight
        this.number=number;		//number for piece(white=1,black=0)
        this.icon=icon;			//ImageIcon is the image of said piece
        this.label.setIcon(this.icon);	//Puts the icon into the label for grid
        //SETS SO IT SHOWS IN CENTER OF GRIDs CELL
        this.label.setVerticalAlignment(JLabel.CENTER);
        this.label.setHorizontalAlignment(JLabel.CENTER);
    }
    public String getName(){
        return name;
    }
    public int getNumber(){
        return number;
    }
    public int getSizeValid(){
        return validMoves.size();
    }
    public int getSizeMoves(){
        return moves.size();
    }
    public ArrayList<coords> getMoves(){
        return moves;
    }
    public ArrayList<coords> getVisionMoves(){
        return visionMoves;
    }
    public ArrayList<coords> getValid(){
        return validMoves;
    }
    public JLabel getLabel(){
        return label;
    }
    public boolean isPinning(){
        return pinnedPiece != null;
    }
    public coords getPinnedPieceLoc(){
        if(pinnedPiece!=null){
            return pinnedPiece.getLocation();
        }return null;
    }
    public boolean exists(){
        return number != -1;
    }
    public void updateMoves(){
        //System.out.print("updatingMoves for:"+this.name);
        //System.out.println(" at:["+this.location.getY()+","+this.location.getX()+"]");
        if(Objects.equals(this.name, "WhiteKing") || Objects.equals(this.name, "BlackKing")){
            moves.clear();
            visionMoves.clear();
            validMoves.clear();
            getAllMoves();
            visionMoves();
            validMoves();
        }
        if(isPinned||isCheck){
            //System.out.println("["+this.location.getY()+","+this.location.getX()+"] is pinned");
            return;
        }
        moves.clear();
        visionMoves.clear();
        validMoves.clear();
        getAllMoves();
        visionMoves();
        validMoves();
    }

    public boolean getCheckStatus(){
        return this.isCheck;
    }
    public void updateCoords(coords coord){
        this.location = coord;
    }
    public coords getLocation(){
        return this.location;
    }
    public int getY(){
        return this.location.getY();
    }
    public int getX(){
        return this.location.getX();
    }
    public void resolveCheck(){
        this.isCheck=false;
        updateMoves();
    }
    public void setCheck(ArrayList<coords> list){
        if(Objects.equals(this.name, "WhiteKing") || Objects.equals(this.name, "BlackKing")){
            //System.out.println("______________FOR KING");
            updateMoves();
            validMoves.clear();
            for(coords move : list){
                if(move==list.get(list.size()-1))
                    continue;
                //System.out.println("-----");
                if(visionMoves.contains(move)){
                    //System.out.println("-----");
                    visionMoves.remove(move);
                }
            }
            validMoves();
            this.isCheck=true;
            return;
        }
        if(Objects.equals(this.name, "BlackPawn") || Objects.equals(this.name, "WhitePawn")){
            moves.clear();
            validMoves.clear();
            this.moves.addAll(list);
            validMoves();
        }else{
            visionMoves.clear();
            validMoves.clear();
            this.visionMoves.addAll(list);
            validMoves();
        }
        if(isPinned){
            visionMoves.clear();
            validMoves.clear();
            //System.out.println("is PINNED:"+this.location.getY()+","+this.location.getX());
            return;
        }
        //System.out.println(this.location.getY()+","+this.location.getX());
        this.isCheck=true;
    }
    //pinnedBy value
    public void setPinned(piece pinnedPiece){
        this.pinnedPiece=pinnedPiece;
    }
    public void unPinPiece(){
        //System.out.print(this.name+" UNPINS ");
        if(this.pinnedPiece==null){
            //System.out.println("nothing");
            return;
        }
        //System.out.println(this.pinnedPiece.getName());
        this.pinnedPiece.setUnPinned();
        this.pinnedPiece=null;
    }
    public void pinnedON(){
        this.isPinned=true;
        //System.out.println("["+this.location.getY()+","+this.location.getX()+"] SET TO PINNED");
    }
    public void pinnedOFF(){
        this.isPinned=false;
        //System.out.println("["+this.location.getY()+","+this.location.getX()+"] SET TO NOT PINNED");
    }
    public void setUnPinned(){
        pinnedOFF();
        updateMoves();
    }
    public void setPinnedMoves(ArrayList<coords> list){
        if(Objects.equals(this.name, "none")){
            System.out.println("returned because its none");
            return;
        }
        coords coordsOfPinnedBy =list.get(list.size()-1);
        //System.out.println("coordsOfPinnedBy "+coordsOfPinnedBy.getY()+","+coordsOfPinnedBy.getX());
        this.pinnedBy = pieces.getPiece(coordsOfPinnedBy.getY(),coordsOfPinnedBy.getX());
        //System.out.println(this.pinnedBy.getName());
        pinnedOFF();
        if(Objects.equals(this.name, "WhitePawn") || Objects.equals(this.name, "BlackPawn")){
            this.moves.clear();
            this.validMoves.clear();
            this.moves.addAll(list);
            validMoves();
        }else{
            this.visionMoves.clear();
            this.validMoves.clear();
            this.visionMoves.addAll(list);
            validMoves();
        }
        pinnedON();
    }



    //-------------------------------------GETS ALL MOVES---------------------------------
    private void getAllMoves(){
        if(Objects.equals(this.name, "WhiteKing") || Objects.equals(this.name, "BlackKing")){}else{
            if(isPinned)
                System.out.println("["+this.location.getY()+","+this.location.getX()+"] is pinned");
            if(isCheck)
                System.out.println("["+this.location.getY()+","+this.location.getX()+"] is checked");
            if(isPinned||isCheck){
                return;
            }
        }
        //System.out.print("["+this.location.getY()+","+this.location.getX()+"]");
        //FOR PAWNS-------------------------------------------------------------
        if(Objects.equals(this.name, "WhitePawn"))
            forWhitePawn();
        if(Objects.equals(this.name, "BlackPawn"))
            forBlackPawn();
        //FOR KNIGHTS-------------------------------------------------------------
        if(Objects.equals(this.name, "WhiteKnight") || Objects.equals(this.name, "BlackKnight"))
            forKnights();
        //FOR BISHOPS-------------------------------------------------------------
        if(Objects.equals(this.name, "WhiteBishop") || Objects.equals(this.name, "BlackBishop"))
            forBishops();
        //FOR ROOKS-------------------------------------------------------------
        if(Objects.equals(this.name, "WhiteRook") || Objects.equals(this.name, "BlackRook"))
            forRooks();
        //FOR KINGS-------------------------------------------------------------
        if(Objects.equals(this.name, "WhiteKing") || Objects.equals(this.name, "BlackKing"))
            forKings();
        //FOR QUEENS-------------------------------------------------------------
        if(Objects.equals(this.name, "WhiteQueen") || Objects.equals(this.name, "BlackQueen"))
            forQueens();
    }
    //-------------------------------------GETS ALL MOVES---------------------------------




    //-------------------------------------GETS ALL VALID MOVES---------------------------------
    public void validMoves(){
        if(Objects.equals(this.name, "WhiteKing") || Objects.equals(this.name, "BlackKing")){}else{
            if(isPinned)
                System.out.println("["+this.location.getY()+","+this.location.getX()+"] is pinned");
            if(isCheck)
                System.out.println("["+this.location.getY()+","+this.location.getX()+"] is checked");
            if(isPinned||isCheck){
                System.out.println("["+this.location.getY()+","+this.location.getX()+"] is pinned");
                return;
            }
        }
        if(Objects.equals(this.name, "none"))return;
        //FOR PAWNS-------------------------------------------------------------
        if(Objects.equals(this.name, "WhitePawn")){
            for(coords move : moves){
                if(pieces.validMove(this,this.location,move)){
                    validMoves.add(move);
                    //System.out.println("["+move.getY()+","+move.getX()+"]");
                }
            }
            //System.out.println("["+this.location.getY()+","+this.location.getX()+"]"+
            //"for:"+this.name+" validMoves:"+validMoves.size());
        }
        if(Objects.equals(this.name, "BlackPawn")){
            for(coords move : moves){
                if(pieces.validMove(this,this.location,move)){
                    validMoves.add(move);
                    //System.out.println("["+move.getY()+","+move.getX()+"]");
                }
            }
            //System.out.println("["+this.location.getY()+","+this.location.getX()+"]"+
            //"for:"+this.name+" validMoves:"+validMoves.size());
        }

        //FOR KNIGHTS------------------------------------------------------------
        if(Objects.equals(this.name, "WhiteKnight") || Objects.equals(this.name, "BlackKnight")){
            for(coords move : visionMoves){
                if(pieces.validMove(this,this.location,move)){
                    validMoves.add(move);
                    //System.out.println("["+move.getY()+","+move.getX()+"]");
                }
            }
            //System.out.println("\n["+this.location.getY()+","+this.location.getX()+"]"+
            //"for:"+this.name+" validMoves:"+validMoves.size());
        }

        //FOR BISHOPS-------------------------------------------------------------
        if(Objects.equals(this.name, "WhiteBishop") || Objects.equals(this.name, "BlackBishop")){
            for(coords move : visionMoves){
                if(pieces.validMove(this,this.location,move)){
                    validMoves.add(move);
                    //System.out.println("["+move.getY()+","+move.getX()+"]");
                }
            }
            //System.out.println("\n["+this.location.getY()+","+this.location.getX()+"]"+
            //"for:"+this.name+" validMoves:"+validMoves.size());
        }



        //FOR ROOKS-------------------------------------------------------------
        if(Objects.equals(this.name, "WhiteRook") || Objects.equals(this.name, "BlackRook")){
            for(coords move : visionMoves){
                if(pieces.validMove(this,this.location,move)){
                    validMoves.add(move);
                    //System.out.println("["+move.getY()+","+move.getX()+"]");
                }
            }
            //System.out.println("\n["+this.location.getY()+","+this.location.getX()+"]"+
            //"for:"+this.name+" validMoves:"+validMoves.size());
        }

        //FOR KINGS-------------------------------------------------------------
        if(Objects.equals(this.name, "WhiteKing") || Objects.equals(this.name, "BlackKing")){
            for(coords move : visionMoves){
                if(pieces.validMove(this,this.location,move)){
                    validMoves.add(move);
                    //System.out.println("["+move.getY()+","+move.getX()+"]");
                }
            }
            //System.out.println("\n["+this.location.getY()+","+this.location.getX()+"]"+
            //"for:"+this.name+" validMoves:"+validMoves.size());
        }

        //FOR QUEENS-------------------------------------------------------------
        if(Objects.equals(this.name, "WhiteQueen") || Objects.equals(this.name, "BlackQueen")){
            for(coords move : visionMoves){
                if(pieces.validMove(this,this.location,move)){
                    validMoves.add(move);
                    //System.out.println("["+move.getY()+","+move.getX()+"]");
                }
            }
            //System.out.println("\n["+this.location.getY()+","+this.location.getX()+"]"+
            //"for:"+this.name+" validMoves:"+validMoves.size());
        }
    }
    //-------------------------------------GETS ALL VALID MOVES---------------------------------



    //-------------------------------------GETS ALL VISION MOVES---------------------------------
    public void visionMoves(){
        if(Objects.equals(this.name, "WhiteKing") || Objects.equals(this.name, "BlackKing")){}else{
            if(isPinned)
                System.out.println("["+this.location.getY()+","+this.location.getX()+"] is pinned");
            if(isCheck)
                System.out.println("["+this.location.getY()+","+this.location.getX()+"] is checked");
            if(isPinned||isCheck){
                //System.out.println("["+this.location.getY()+","+this.location.getX()+"] is pinned");
                return;
            }
        }
        if(Objects.equals(this.name, "none"))return;
        //FOR PAWNS-------------------------------------------------------------
        if(Objects.equals(this.name, "WhitePawn")){
            for(coords move : moves){
                if(pieces.visionMove(this,this.location,move)){
                    visionMoves.add(move);
                    //System.out.println("["+move.getY()+","+move.getX()+"]");
                }
            }
            //System.out.println("["+this.location.getY()+","+this.location.getX()+"]"+
            //"for:"+this.name+" visionMoves:"+visionMoves.size());
        }
        if(Objects.equals(this.name, "BlackPawn")){
            for(coords move : moves){
                if(pieces.visionMove(this,this.location,move)){
                    visionMoves.add(move);
                    //System.out.println("["+move.getY()+","+move.getX()+"]");
                }
            }
            //System.out.println("["+this.location.getY()+","+this.location.getX()+"]"+
            //"for:"+this.name+" visionMoves:"+visionMoves.size());
        }

        //FOR KNIGHTS------------------------------------------------------------
        if(Objects.equals(this.name, "WhiteKnight") || Objects.equals(this.name, "BlackKnight")){
            for(coords move : moves){
                if(pieces.visionMove(this,this.location,move)){
                    visionMoves.add(move);
                    //System.out.println("["+move.getY()+","+move.getX()+"]");
                }
            }
            //System.out.println("\n["+this.location.getY()+","+this.location.getX()+"]"+
            //"for:"+this.name+" visionMoves:"+visionMoves.size());
        }

        //FOR BISHOPS-------------------------------------------------------------
        if(Objects.equals(this.name, "WhiteBishop") || Objects.equals(this.name, "BlackBishop")){
            for(coords move : moves){
                if(pieces.visionMove(this,this.location,move)){
                    visionMoves.add(move);
                    //System.out.println("["+move.getY()+","+move.getX()+"]");
                }
            }
            //System.out.println("\n["+this.location.getY()+","+this.location.getX()+"]"+
            //"for:"+this.name+" visionMoves:"+visionMoves.size());
        }



        //FOR ROOKS-------------------------------------------------------------
        if(Objects.equals(this.name, "WhiteRook") || Objects.equals(this.name, "BlackRook")){
            for(coords move : moves){
                if(pieces.visionMove(this,this.location,move)){
                    visionMoves.add(move);
                    //System.out.println("["+move.getY()+","+move.getX()+"]");
                }
            }
            //System.out.println("\n["+this.location.getY()+","+this.location.getX()+"]"+
            //"for:"+this.name+" visionMoves:"+visionMoves.size());
        }

        //FOR KINGS-------------------------------------------------------------
        if(Objects.equals(this.name, "WhiteKing") || Objects.equals(this.name, "BlackKing")){
            for(coords move : moves){
                if(pieces.visionMove(this,this.location,move)){
                    visionMoves.add(move);
                    //System.out.println("["+move.getY()+","+move.getX()+"]");
                }
            }
            //System.out.println("\n["+this.location.getY()+","+this.location.getX()+"]"+
            //"for:"+this.name+" visionMoves:"+visionMoves.size());
        }

        //FOR QUEENS-------------------------------------------------------------
        if(Objects.equals(this.name, "WhiteQueen") || Objects.equals(this.name, "BlackQueen")){
            for(coords move : moves){
                if(pieces.visionMove(this,this.location,move)){
                    visionMoves.add(move);
                    //System.out.println("["+move.getY()+","+move.getX()+"]");
                }
            }
            //System.out.println("\n["+this.location.getY()+","+this.location.getX()+"]"+
            //"for:"+this.name+" visionMoves:"+visionMoves.size());
        }
    }
    //-------------------------------------GETS ALL VISION MOVES---------------------------------



    //-----------------------------------Move for White Pawns----------------------------------
    public void forWhitePawn(){
        if(this.location.getY()+1<8){
            moves.add(pieces.getLocation(this.location.getY()+1,this.location.getX()));
            if(this.location.getX()+1<8)
                moves.add(pieces.getLocation(this.location.getY()+1,this.location.getX()+1));
            if(this.location.getX()-1>-1)
                moves.add(pieces.getLocation(this.location.getY()+1,this.location.getX()-1));
        }
        if(this.location.getY()==1)
            moves.add(pieces.getLocation(this.location.getY()+2,this.location.getX()));
        //System.out.println("for:"+this.name+" moves:"+moves.size());
    }
    //-----------------------------------Move for White Pawns----------------------------------

    //-----------------------------------Move for Black Pawns----------------------------------
    public void forBlackPawn(){
        if(this.location.getY()-1<8){
            moves.add(pieces.getLocation(this.location.getY()-1,this.location.getX()));
            if(this.location.getX()+1<8)
                moves.add(pieces.getLocation(this.location.getY()-1,this.location.getX()+1));
            if(this.location.getX()-1>-1)
                moves.add(pieces.getLocation(this.location.getY()-1,this.location.getX()-1));
        }
        if(this.location.getY()==6)
            moves.add(pieces.getLocation(this.location.getY()-2,this.location.getX()));
        //System.out.println("for:"+this.name+" moves:"+moves.size());
    }
    //-----------------------------------Move for Black Pawns----------------------------------


    //-----------------------------------Move for Knights----------------------------------
    public void forKnights(){
        if(this.location.getY()+2<8){
            if(this.location.getX()-1>-1)
                moves.add(pieces.getLocation(this.location.getY()+2,this.location.getX()-1));
            if(this.location.getX()+1<8)
                moves.add(pieces.getLocation(this.location.getY()+2,this.location.getX()+1));
        }
        if(this.location.getY()-2>-1){
            if(this.location.getX()-1>-1)
                moves.add(pieces.getLocation(this.location.getY()-2,this.location.getX()-1));
            if(this.location.getX()+1<8)
                moves.add(pieces.getLocation(this.location.getY()-2,this.location.getX()+1));
        }
        if(this.location.getX()+2<8){
            if(this.location.getY()-1>-1)
                moves.add(pieces.getLocation(this.location.getY()-1,this.location.getX()+2));
            if(this.location.getY()+1<8)
                moves.add(pieces.getLocation(this.location.getY()+1,this.location.getX()+2));
        }
        if(this.location.getX()-2>-1){
            if(this.location.getY()-1>-1)
                moves.add(pieces.getLocation(this.location.getY()-1,this.location.getX()-2));
            if(this.location.getY()+1<8)
                moves.add(pieces.getLocation(this.location.getY()+1,this.location.getX()-2));
        }
        //System.out.println("for:"+this.name+" moves:"+moves.size());
        //for(coords move : moves){
        //	System.out.print("["+move.getY()+","+move.getX()+"]");
        //}
        //System.out.println();
    }
    //-----------------------------------Move for Knights----------------------------------


    //-----------------------------------Move for Bishops----------------------------------
    public void forBishops(){
        pinnedCoords.clear();
        for(int i=1;i<=7;i++){
            if(this.location.getY()+i<8 && this.location.getX()+i<8){
                moves.add(pieces.getLocation(this.location.getY()+i,this.location.getX()+i));
                pinnedCoords.add(pieces.getLocation(this.location.getY()+i,this.location.getX()+i));
            }
        }
        pinnedCoords.add(this.location);
        pieces.pinnedCheck(pinnedCoords);

        pinnedCoords.clear();
        for(int i=1;i<=7;i++){
            if(this.location.getY()+i<8 && this.location.getX()-i>-1){
                moves.add(pieces.getLocation(this.location.getY()+i,this.location.getX()-i));
                pinnedCoords.add(pieces.getLocation(this.location.getY()+i,this.location.getX()-i));
            }
        }
        pinnedCoords.add(this.location);
        pieces.pinnedCheck(pinnedCoords);

        pinnedCoords.clear();
        for(int i=1;i<=7;i++){
            if(this.location.getY()-i>-1 && this.location.getX()+i<8){
                moves.add(pieces.getLocation(this.location.getY()-i,this.location.getX()+i));
                pinnedCoords.add(pieces.getLocation(this.location.getY()-i,this.location.getX()+i));
            }
        }
        pinnedCoords.add(this.location);
        pieces.pinnedCheck(pinnedCoords);

        pinnedCoords.clear();
        for(int i=1;i<=7;i++){
            if(this.location.getY()-i>-1 && this.location.getX()-i>-1){
                moves.add(pieces.getLocation(this.location.getY()-i,this.location.getX()-i));
                pinnedCoords.add(pieces.getLocation(this.location.getY()-i,this.location.getX()-i));
            }
        }
        pinnedCoords.add(this.location);
        pieces.pinnedCheck(pinnedCoords);


    }
    //-----------------------------------Move for Bishops----------------------------------


    //-----------------------------------Move for Rooks----------------------------------
    public void forRooks(){
        pinnedCoords.clear();
        for(int i=1;i<=7;i++){
            if(this.location.getX()+i<8){
                moves.add(pieces.getLocation(this.location.getY(),this.location.getX()+i));
                pinnedCoords.add(pieces.getLocation(this.location.getY(),this.location.getX()+i));
            }
        }
        pinnedCoords.add(this.location);
        pieces.pinnedCheck(pinnedCoords);

        pinnedCoords.clear();
        for(int i=1;i<=7;i++){
            if(this.location.getX()-i>-1){
                moves.add(pieces.getLocation(this.location.getY(),this.location.getX()-i));
                pinnedCoords.add(pieces.getLocation(this.location.getY(),this.location.getX()-i));
            }
        }
        pinnedCoords.add(this.location);
        pieces.pinnedCheck(pinnedCoords);

        pinnedCoords.clear();
        for(int i=1;i<=7;i++){
            if(this.location.getY()-i>-1){
                moves.add(pieces.getLocation(this.location.getY()-i,this.location.getX()));
                pinnedCoords.add(pieces.getLocation(this.location.getY()-i,this.location.getX()));
            }
        }
        pinnedCoords.add(this.location);
        pieces.pinnedCheck(pinnedCoords);

        pinnedCoords.clear();
        for(int i=1;i<=7;i++){
            if(this.location.getY()+i<8){
                moves.add(pieces.getLocation(this.location.getY()+i,this.location.getX()));
                pinnedCoords.add(pieces.getLocation(this.location.getY()+i,this.location.getX()));
            }
        }
        pinnedCoords.add(this.location);
        pieces.pinnedCheck(pinnedCoords);

        //System.out.println("for:"+this.name+" moves:"+moves.size());
    }
    //-----------------------------------Move for Rooks----------------------------------


    //-----------------------------------Move for Queens----------------------------------
    public void forQueens(){
        pinnedCoords.clear();
        for(int i=1;i<=7;i++){
            if(this.location.getY()+i<8 && this.location.getX()+i<8){
                moves.add(pieces.getLocation(this.location.getY()+i,this.location.getX()+i));
                pinnedCoords.add(pieces.getLocation(this.location.getY()+i,this.location.getX()+i));
            }
        }
        pinnedCoords.add(this.location);
        pieces.pinnedCheck(pinnedCoords);

        pinnedCoords.clear();
        for(int i=1;i<=7;i++){
            if(this.location.getY()+i<8 && this.location.getX()-i>-1){
                moves.add(pieces.getLocation(this.location.getY()+i,this.location.getX()-i));
                pinnedCoords.add(pieces.getLocation(this.location.getY()+i,this.location.getX()-i));
            }
        }
        pinnedCoords.add(this.location);
        pieces.pinnedCheck(pinnedCoords);

        pinnedCoords.clear();
        for(int i=1;i<=7;i++){
            if(this.location.getY()-i>-1 && this.location.getX()+i<8){
                moves.add(pieces.getLocation(this.location.getY()-i,this.location.getX()+i));
                pinnedCoords.add(pieces.getLocation(this.location.getY()-i,this.location.getX()+i));
            }
        }
        pinnedCoords.add(this.location);
        pieces.pinnedCheck(pinnedCoords);

        pinnedCoords.clear();
        for(int i=1;i<=7;i++){
            if(this.location.getY()-i>-1 && this.location.getX()-i>-1){
                moves.add(pieces.getLocation(this.location.getY()-i,this.location.getX()-i));
                pinnedCoords.add(pieces.getLocation(this.location.getY()-i,this.location.getX()-i));
            }
        }
        pinnedCoords.add(this.location);
        pieces.pinnedCheck(pinnedCoords);

        pinnedCoords.clear();
        for(int i=1;i<=7;i++){
            if(this.location.getX()+i<8){
                moves.add(pieces.getLocation(this.location.getY(),this.location.getX()+i));
                pinnedCoords.add(pieces.getLocation(this.location.getY(),this.location.getX()+i));
            }
        }
        pinnedCoords.add(this.location);
        pieces.pinnedCheck(pinnedCoords);

        pinnedCoords.clear();
        for(int i=1;i<=7;i++){
            if(this.location.getX()-i>-1){
                moves.add(pieces.getLocation(this.location.getY(),this.location.getX()-i));
                pinnedCoords.add(pieces.getLocation(this.location.getY(),this.location.getX()-i));
            }
        }
        pinnedCoords.add(this.location);
        pieces.pinnedCheck(pinnedCoords);

        pinnedCoords.clear();
        for(int i=1;i<=7;i++){
            if(this.location.getY()-i>-1){
                moves.add(pieces.getLocation(this.location.getY()-i,this.location.getX()));
                pinnedCoords.add(pieces.getLocation(this.location.getY()-i,this.location.getX()));
            }
        }
        pinnedCoords.add(this.location);
        pieces.pinnedCheck(pinnedCoords);

        pinnedCoords.clear();
        for(int i=1;i<=7;i++){
            if(this.location.getY()+i<8){
                moves.add(pieces.getLocation(this.location.getY()+i,this.location.getX()));
                pinnedCoords.add(pieces.getLocation(this.location.getY()+i,this.location.getX()));
            }
        }
        pinnedCoords.add(this.location);
        pieces.pinnedCheck(pinnedCoords);

        //System.out.println("for:"+this.name+" moves:"+moves.size());

    }
    //-----------------------------------Move for Queens----------------------------------


    //-----------------------------------Move for Kings----------------------------------
    public void forKings(){
        if(this.location.getY()+1<8 && this.location.getX()+1<8)
            moves.add(pieces.getLocation(this.location.getY()+1,this.location.getX()+1));
        if(this.location.getY()+1<8 && this.location.getX()-1>-1)
            moves.add(pieces.getLocation(this.location.getY()+1,this.location.getX()-1));
        if(this.location.getY()-1>-1 && this.location.getX()+1<8)
            moves.add(pieces.getLocation(this.location.getY()-1,this.location.getX()+1));
        if(this.location.getY()-1>-1 && this.location.getX()-1>-1)
            moves.add(pieces.getLocation(this.location.getY()-1,this.location.getX()-1));
        if(this.location.getX()+1<8)
            moves.add(pieces.getLocation(this.location.getY(),this.location.getX()+1));
        if(this.location.getX()-1>-1)
            moves.add(pieces.getLocation(this.location.getY(),this.location.getX()-1));
        if(this.location.getY()-1>-1)
            moves.add(pieces.getLocation(this.location.getY()-1,this.location.getX()));
        if(this.location.getY()+1<8)
            moves.add(pieces.getLocation(this.location.getY()+1,this.location.getX()));
        //System.out.println("for:"+this.name+" moves:"+moves.size());
    }
    //-----------------------------------Move for Kings----------------------------------
}