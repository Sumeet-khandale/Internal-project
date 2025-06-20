package com.BookingService.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.BookingService.Enum.BookingStatus;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookedSeatRequestDTO {

    @NotBlank(message = "Seat ID cannot be blank")
    private String id;

    @NotBlank(message = "Seat ID must not be blank")
    private String seatId;

    @NotBlank(message = "Screen ID must not be blank")
    private String screenId;

    @NotBlank(message = "Column must not be blank")
    private String column;

    @NotBlank(message = "Row must not be blank")
    private String row;

    @NotNull(message = "LockedAt timestamp must not be null")
    private LocalDateTime lockedAt;

    @NotNull(message = "Status must be specified")
    private BookingStatus status;

   
}
