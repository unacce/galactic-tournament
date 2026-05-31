import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BattleResponse } from '../../models/battle-response.model';
import { BattleHistory } from '../../models/battle-history.model';

@Injectable({
  providedIn: 'root',
})
export class BattleService {
  private readonly apiUrl = 'http://localhost:8080/api/battles';

  constructor(private http: HttpClient) {}

  createBattle(request: any): Observable<BattleResponse> {
    return this.http.post<BattleResponse>(this.apiUrl, request);
  }

  getHistory(): Observable<BattleHistory[]> {
    return this.http.get<BattleHistory[]>(this.apiUrl);
  }

  createRandomBattle(): Observable<BattleResponse> {
    return this.http.post<BattleResponse>(`${this.apiUrl}/random`, {});
  }
}
