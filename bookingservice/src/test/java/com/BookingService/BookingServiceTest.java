package com.BookingService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import com.BookingService.Entities.Booking;
import com.BookingService.Enum.BookingStatus;
import com.BookingService.Repositories.BookedSeatRepository;
import com.BookingService.Repositories.BookingRepository;
import com.BookingService.Service.BookingService;

@SpringBootTest
public class BookingServiceTest {

    @Mock private BookingRepository bookingRepository;
    @Mock private BookedSeatRepository seatRepository;

    @InjectMocks private BookingService bookingService;

    private List<String> seatIds;
    private String userId = "user1";
    private String showId = "show1";
    private String screenId = "screen1";
    private double totalAmount = 500;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        seatIds = List.of("A1", "A2");
    }

    @Test
    public void testInitiateBooking_Success() {
        when(seatRepository.existsBySeatIdAndStatusIn(anyString(), anyList())).thenReturn(false);
        when(bookingRepository.save(any(Booking.class))).thenAnswer(i -> i.getArguments()[0]);

        Booking booking = bookingService.initiateBooking(userId, showId, screenId, seatIds, totalAmount);

        assertEquals(BookingStatus.PENDING, booking.getStatus());
        assertEquals(2, booking.getSeats().size());
        verify(bookingRepository, times(1)).save(any());
    }

    @Test
    public void testConfirmBooking_Success() {
        Booking booking = new Booking();
        booking.setBookingId("b1");
        booking.setSeats(new ArrayList<>());
        booking.setStatus(BookingStatus.PENDING);

        when(bookingRepository.findById("b1")).thenReturn(Optional.of(booking));
        when(bookingRepository.save(any())).thenReturn(booking);

        Booking result = bookingService.confirmBooking("b1", "pay123");
        assertEquals(BookingStatus.CONFIRMED, result.getStatus());
        assertEquals("pay123", result.getPaymentId());
    }

    @Test
    public void testCancelBooking_Success() {
        Booking booking = new Booking();
        booking.setBookingId("b2");
        booking.setSeats(new ArrayList<>());

        when(bookingRepository.findById("b2")).thenReturn(Optional.of(booking));
        when(bookingRepository.save(any())).thenReturn(booking);

        Booking result = bookingService.cancelBooking("b2");
        assertEquals(BookingStatus.CANCELLED, result.getStatus());
    }
}
