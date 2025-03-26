import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface FichePortefeuilleSummary {
  valeurMarche: number;
  valeurComptable: number;
  act: string;
}

@Injectable({
  providedIn: 'root',
})
export class FichePortefeuilleService {
  private apiUrl = 'http://localhost:8080/api/fiche-portefeuille';

  constructor(private http: HttpClient) {}

  getFichePortefeuilleSummary(): Observable<FichePortefeuilleSummary[]> {
    return this.http.get<FichePortefeuilleSummary[]>(`${this.apiUrl}/summary`);
  }
}
