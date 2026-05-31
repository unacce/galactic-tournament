import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Species } from '../../models/species.model';
import { Ranking } from '../../models/ranking.model';

@Injectable({
  providedIn: 'root',
})
export class SpeciesService {
  private readonly apiUrl = 'http://localhost:8080/api/species';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Species[]> {
    return this.http.get<Species[]>(this.apiUrl);
  }

  create(species: any): Observable<Species> {
    return this.http.post<Species>(this.apiUrl, species);
  }

  getRanking(): Observable<Ranking[]> {
    return this.http.get<Ranking[]>(`${this.apiUrl}/ranking`);
  }
}
