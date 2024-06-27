import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SpeciesReadDto } from '../entity/SpeciesReadDto';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SpeciesService {

    private baseUrl: String = "http://localhost:8080/species"

  constructor(private httpClient: HttpClient) { }


getAllSpecies(): Observable<SpeciesReadDto[]>{
  return this.httpClient.get<SpeciesReadDto[]>(`${this.baseUrl}`)

}

}
