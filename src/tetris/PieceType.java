package tetris;


public enum PieceType {
    PYRAMID, L, L2, S, S2, STICK, SQUARE;


    public Piece getPiece() {
        switch (this) {
            case PYRAMID:
                return Piece.PYRAMID;
            case L:
                return Piece.L;
            case L2:
                return Piece.L2;
            case S:
                return Piece.S;
            case S2:
                return Piece.S2;
            case STICK:
                return Piece.STICK;
            case SQUARE:
                return Piece.SQUARE;

        }
        return null;
    }
}
