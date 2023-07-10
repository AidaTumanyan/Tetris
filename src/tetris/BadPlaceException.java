package tetris;


class BadPlaceException extends RuntimeException {
    public BadPlaceException(String errorMessage) {
        super(errorMessage);
    }
}

