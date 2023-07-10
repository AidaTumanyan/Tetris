package tetris;

class OutOfBoundPlaceException extends RuntimeException {
    public OutOfBoundPlaceException(String errorMessage) {
        super(errorMessage);
    }
}

