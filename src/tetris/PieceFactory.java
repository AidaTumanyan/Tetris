package tetris;

import java.util.Random;

public class PieceFactory {

    public static Piece createRandomPiece(){
        int pick = new Random().nextInt(PieceType.values().length);
        return PieceType.values()[pick].getPiece();

    }
    // ADD YOUR CODE HERE
}
