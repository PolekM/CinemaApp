import { movieReadDto } from "./movieReadDto";

export interface MoviePageableDto{
    content: movieReadDto[];
    currentPage: number;
    totalPage: number;
    totalElements: number;
}