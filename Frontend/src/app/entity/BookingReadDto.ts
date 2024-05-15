import { SeatBookingReadDto } from "./SeatBookingReadDto";

export interface BookingReadDto{
    seanceId: number,
    movieName: String,
    ticketCost: number,
    startTime: String,
    endTime: String,
    roomName: String,
    seatsInRoom: SeatBookingReadDto[]
    seatsReservedInRoom: SeatBookingReadDto[]
}

