package tetris;

public class Board {

    private final Spot[][] grid;
    // "grid" – a 2-d array of Spot objects that stores which spots are filled.
    private final int width;
    private final int height;

    private int[] widths;
    private int[] heights;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        grid = new Spot[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = new Spot();
            }
        }
        //Board object is initialized with a grid of Spot objects, where each spot represents an individual position on the board.
        widths = new int[height];
        heights = new int[width];

        calculateHeights();
        calculateWidths();

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMaxHeight() {
        int maxHeight = 0;

        for (int i = 0; i < heights.length; i++) {
            if (getColumnHeight(i) > maxHeight)
                maxHeight = heights[i];
        }

        return maxHeight;
    }

    public int getColumnHeight(int x) {
        return heights[x];
    }

    public int getRowWidth(int y) {
        return widths[y];
    }

    public boolean isFilled(int x, int y) {

        if (grid[x][y].isFilled())
            return true;

        return false;
    }

    public void undo() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grid[i][j].isFilled() && (!grid[i][j].isCommitted())) {
                    grid[i][j].undo();
                }
            }

        }

        calculateHeights();
        calculateWidths();
    }

    public void commit() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grid[i][j].isFilled()) {
                    grid[i][j].commit();
                }
            }
        }
    }
//method calculates the height at which a given Piece object can be dropped onto the game board at the specified X-coordinate x
    public int dropHeight(Piece piece, int x) {
        int[] skirt = piece.getSkirt();
        int i = 0;
        int yToDrop = 0;
        for ( i = 0; i < skirt.length; i++) {

        if(yToDrop < getColumnHeight(x+i)){
            yToDrop = getColumnHeight(x+i);
        }
        }
        return yToDrop;
    }
//method places a Piece object on the game board at the specified coordinates (x, y)
    // If the placement is valid, it fills the corresponding spots in the grid array and recalculates the widths and heights arrays.
    public void place(Piece piece, int x, int y) {

        if (x < 0 || y < 0) {
            throw new OutOfBoundPlaceException("Piece is out of board");
        }
        if ((x + piece.getWidth() > width) || (y + piece.getHeight() > height)) {
            throw new OutOfBoundPlaceException("Piece is out of board");
        }

        TPoint[] body = piece.getBody();
        for (int i = 0; i < body.length; i++) {
            int X = x + body[i].getX();
            int Y = y + body[i].getY();
            if (grid[X][Y].isFilled()) {
                throw new BadPlaceException("It's already filled");
            }
        }

        for (int i = 0; i < body.length; i++) {
            int X = x + body[i].getX();
            int Y = y + body[i].getY();
            grid[X][Y].fill();
        }

        calculateWidths();
        calculateHeights();

    }

    public int clearRows() {
        int To = 0;//represents the destination row where chlcvac strokanery petka gnan
        int From;

        for (From = 0; From <= getMaxHeight(); From++) {

            if (!(getRowWidth(From) == width)) { //ete havasar chi lcvac chi minchev verj

                for (int x = 0; x < width; x++) {
                    grid[x][To] = grid[x][From];//Копируем содержимое строки From в строку To
                }
                To++;
            }

        }
        for (int y = To; y <= getMaxHeight(); y++) {
            for (int x = 0; x < width; x++) {
                grid[x][y] = new Spot(); // сбрасываем содержимое ячеек напустое состояние
            }
        }

        calculateWidths();
        calculateHeights();
        return From - To;//которая представляет количество удаленных заполненных строк.
    }

    public boolean hasFilledRows() {

        for (int y = 0; y < height; y++) {

            if (getRowWidth(y) == width) {
                return true;
            }

        }

        return false;
    }

    public void calculateWidths() {

        for (int y = 0; y < height; y++) {
            widths[y] = 0;
            for (int x = 0; x < width; x++) {
                if (grid[x][y].isFilled()) {
                    widths[y]++;
                }
            }
        }

    }
    public void calculateHeights() {

        for (int x = 0; x < width; x++) {
            heights[x] = 0;
            for (int y = height - 1; y >= 0; y--) {
                if (grid[x][y].isFilled()) {
                    heights[x] = y + 1;
                    break;
                }
            }
        }

    }




    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = height - 1; y >= 0; y--) {
            sb.append('|');
            for (int x = 0; x < width; x++) {
                if (grid[x][y].isFilled()) {
                    sb.append('+');
                } else {
                    sb.append(' ');
                }
            }
            sb.append("|\n");
        }
        for (int x = 0; x < width + 2; x++) {
            sb.append('-');
        }
        sb.append("widths: ");
        for (int num : widths) {
            sb.append(num);
            sb.append(",");
        }
        sb.append("\n");
        sb.append("heights: ");
        for (int num : heights) {
            sb.append(num);
            sb.append(",");
        }
        sb.append("\n");
        return sb.toString();
    }
}



