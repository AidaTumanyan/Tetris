package tetris;

import java.util.Arrays;

// this class represents each spot in Board
public class Spot {


    private boolean filled;
    private boolean committed;


    // Spot should be always initialized to filled=false, committed=false
    // which is the default state

    public Spot() {
        filled = false;
        committed = false;
    }

    public boolean isFilled() {
        return filled;
    }

    public boolean isCommitted() {
        return committed;
    }


    public void fill() {
        if (committed) {
            throw new SpotCommittedException("Spot is already committed");
        }
        filled = true;

    }

    public void commit() {
        if (!committed) {
            committed = true;
        }

    }

    public void undo() {
        if (committed) {
            throw new SpotCommittedException("Spot is already committed");
        }
        filled = false;

    }

    @Override
    public String toString() {
        String message;

        message = "Spot{filled: " + filled + ",committed: " + committed + "}";

        return message;
    }

    @Override
    public int hashCode() {
        int hash = Boolean.hashCode(filled);
        hash = hash * 31 + Boolean.hashCode(committed);


        return hash;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this)
            return true;
        if (!(o instanceof Spot))
            return false;

        Spot pn = (Spot) o;

        return (filled == pn.filled) && (committed == pn.committed);
    }


}