package Exception;

public class SeatAlreadyBookedException extends BookingException {
    public SeatAlreadyBookedException(String message) {
        super(message);
    }
}