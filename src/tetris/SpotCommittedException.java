package tetris;

public class SpotCommittedException extends RuntimeException {
    public SpotCommittedException(String errorMessage) {
        super(errorMessage);
    }
}