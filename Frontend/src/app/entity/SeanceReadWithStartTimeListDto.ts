import { SeanceStartTimeDto } from "./SeanceStartTimeDto";

export interface SeanceReadWithStartTimeListDto{

    ticketCost: number;
    room: String;
    movie: String;
    movieUlr: String;
    seanceStartTimeList: SeanceStartTimeDto[];
}