package tetris;

import java.util.Arrays;

public final class Piece {


    // ADD 7 standard pieces here: STICK, L, L2, S, S2, SQUARE, PYRAMID
    public static final Piece SQUARE = makeAllRotations(new Piece(new TPoint[]{new TPoint(0, 0), new TPoint(0, 1), new TPoint(1, 0), new TPoint(1, 1)}));
    public static final Piece STICK = makeAllRotations(new Piece(new TPoint[]{new TPoint(0, 0), new TPoint(0, 1), new TPoint(0, 2), new TPoint(0, 3)}));
    public static final Piece L = makeAllRotations(new Piece(new TPoint[]{new TPoint(0, 0), new TPoint(0, 1), new TPoint(1, 0), new TPoint(0, 2)}));
    public static final Piece L2 = makeAllRotations(new Piece(new TPoint[]{new TPoint(0, 0), new TPoint(1, 0), new TPoint(1, 1), new TPoint(1, 2)}));
    public static final Piece S = makeAllRotations(new Piece(new TPoint[]{new TPoint(0, 0), new TPoint(2, 1), new TPoint(1, 0), new TPoint(1, 1)}));
    public static final Piece S2 = makeAllRotations(new Piece(new TPoint[]{new TPoint(1, 1), new TPoint(0, 1), new TPoint(1, 0), new TPoint(2, 0)}));
    public static final Piece PYRAMID = makeAllRotations(new Piece(new TPoint[]{new TPoint(0, 0), new TPoint(1, 1), new TPoint(1, 0), new TPoint(2, 0)}));

    private final TPoint[] body;
    private final int height;
    private final int width;
    private final int[] skirt; //the lowest y values
    private Piece next;


    private Piece(TPoint[] body) {
        this.body = body;


        int max_x = body[0].getX();
        int max_y = body[0].getY();
        for (int i = 0; i < 4; i++) {
            if (max_x < body[i].getX()) {
                max_x = body[i].getX();
            }
            if (max_y < body[i].getY()) {
                max_y = body[i].getY();
            }
        }
        this.height = max_y + 1;
        this.width = max_x + 1;

        //for skirt
        skirt = new int[width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if ((body[0].getY() == j) && (body[0].getX() == i)) {
                    skirt[i] = body[0].getY();
                    break;
                } else if ((body[1].getY() == j) && (body[1].getX() == i)) {
                    skirt[i] = body[1].getY();
                    break;
                } else if ((body[2].getY() == j) && (body[2].getX() == i)) {
                    skirt[i] = body[2].getY();
                    break;
                } else if ((body[3].getY() == j) && (body[3].getX() == i)) {
                    skirt[i] = body[3].getY();
                    break;
                }
            }
        }
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int[] getSkirt() {
        return skirt.clone();
    }

    public TPoint[] getBody() {
        return body.clone();
    }

    public Piece nextRotation() {
        return next;
    }

    // for example :  {(0,0), (0,1), (1,0), (1,1)}
    @Override
    public String toString() {
        // ADD YOUR CODE HERE
        String coordinates = "{" + body[0].toString() + "," + body[1].toString() + "," + body[2].toString()
                + "," + body[3].toString() + "}";
        return coordinates;
    }

    //  note that these are equal
    //
    // {(0,0), (0,1), (1,0), (1,1)}
    // {(1,1), (0,1), (1,0), (0,0)}


    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Piece))
            return false;

        Piece pn = (Piece) obj;

        int flag_body = 0;
        boolean flag_skirt = true;

        for (TPoint o : body) {
            for (TPoint u : pn.body) {
                if (o.equals(u)) {
                    flag_body++;
                    break;
                }
            }
        }
        if (skirt.length == pn.skirt.length) {
            for (int i = 0; i < skirt.length; i++) {
                if (skirt[i] != pn.skirt[i]) {
                    flag_skirt = false;
                }
            }
        }
        return pn.height == height && pn.width == width &&
                (flag_body == 4) && flag_skirt;

    }

    @Override
    public int hashCode() {
        int hash = Integer.hashCode(height);
        hash = 31 * hash + Integer.hashCode(width);
        hash = 31 * hash + Arrays.hashCode(skirt);
        hash = 31 * hash + Arrays.hashCode(body);
        return hash;

    }

    private static Piece makeAllRotations(Piece root) {

        Piece obj = root;


        while (!(root.equals(obj.computeNextRotation()))) {
//checks if the next rotation is equal to the original piece.
            obj.next = obj.computeNextRotation();

            obj = obj.next;

        }
        if (obj.computeNextRotation().equals(root)) {
            obj.next = root;
        }

        return root;
    }

    private Piece computeNextRotation() {
        // newY = oldX
        // newX = -oldY + (heigth - 1) ,  height - 1 is for origin fix
        TPoint[] points = new TPoint[body.length];
        for (int i = 0; i < body.length; i++) {
            points[i] = new TPoint(height - body[i].getY() - 1, body[i].getX());
        }
        return new Piece(points);
    }
}