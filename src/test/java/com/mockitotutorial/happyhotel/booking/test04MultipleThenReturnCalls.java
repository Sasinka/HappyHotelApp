package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class test04MultipleThenReturnCalls {
    private  BookingService bookingService;
    private  PaymentService paymentServiceMock;
    private  RoomService   roomServiceMock;
    private  BookingDAO bookingDAOMock;
    private  MailSender mailSenderMock;

    @BeforeEach
    void setup() {
        this.paymentServiceMock = mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOMock = mock(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);

        this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

    }

   @Test
    void should_countAvailablePlaces_when_calledMultipleTimes(){
        //given
        when(this.roomServiceMock.getAvailableRooms())
                .thenReturn(Collections.singletonList(new Room("Room 1", 5)))
                .thenReturn(Collections.emptyList());
        int expectedFirstCaLL = 5;
        int expectedsECONDCaLL = 0;
       //when
       int actualFirst = bookingService.getAvailablePlaceCount();
       int actualSecond = bookingService.getAvailablePlaceCount();

       //then
       assertAll(
               ()->assertEquals(expectedFirstCaLL, actualFirst),
               ()->assertEquals(expectedsECONDCaLL, actualSecond)
       );

   }
}
