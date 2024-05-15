import { SeatBookingReadDto } from "./SeatBookingReadDto";

export interface ReservationUserDto{

    reservationId: number;
    movieName: String;
    movieUrl: String;
    ticketCost: number;
    startTime: number;
    endTime: number;
    roomName: String
    reservationStatus: String;
    reservedSeats: SeatBookingReadDto[]

}