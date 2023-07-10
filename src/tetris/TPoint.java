package tetris;

final class TPoint {
    private int x;
    private int y;


    //constructor
    public TPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //copy constructor
    public TPoint(TPoint obj) {
        this.x = obj.x;
        this.y = obj.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TPoint))
            return false;
        TPoint pn = (TPoint) o;
        return pn.x == x && pn.y == y;
    }


    @Override
    public int hashCode() {
        int hash = Integer.hashCode(x);
        hash = 31 * hash + Integer.hashCode(y);
        return hash;
    }


    @Override
    public String toString() {
        String str = "(" + this.x + "," + this.y + ")";
        return str;
    }

}

