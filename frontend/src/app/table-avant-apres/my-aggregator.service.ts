import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Adjust your interface to match the exact JSON structure
export interface AggregatedAllClassesDTO {
  classI: ClassIDTO;
  classII: ClassIIDTO;
  classIII: ClassIIIDTO;
  classIV: ClassIVDTO;
  grandTotalVC: string;
  grandTotalVM: string;
}

export interface ClassIDTO {
  bdtVC: string; bdtVM: string;
  vjgVC: string; vjgVM: string;
  opciEtatVC: string; opciEtatVM: string;
  omltPursVC: string; omltPursVM: string;
  operationEncoursVC: string; operationEncoursVM: string;
  omltPbVC: string; omltPbVM: string;
  totalClassIVC: string; totalClassIVM: string;
  ratioI: string;
}

export interface ClassIIDTO {
  cdVC: string; cdVM: string;
  ocVC: string; ocVM: string;
  oncVC: string; oncVM: string;
  monetaireVC: string; monetaireVM: string;
  omltVC: string; omltVM: string;
  omltPrVC: string; omltPrVM: string;
  omltDedVC: string; omltDedVM: string;
  totalClassIIVC: string; totalClassIIVM: string;
  ratioII: string;
}

export interface ClassIIIDTO {
  actVC: string; actVM: string;
  opcvmActDivVC: string; opcvmActDivVM: string;
  fpctVC: string; fpctVM: string;
  actionsDedVC: string; actionsDedVM: string;
  omltActVC: string; omltActVM: string;
  fondsCapRisqueVC: string; fondsCapRisqueVM: string;
  totalClassIIIVC: string; totalClassIIIVM: string;
  ratioIII: string;
}

export interface ClassIVDTO {
  opciPriveVC: string; opciPriveVM: string;
  opciPbTrVC: string; opciPbTrVM: string;
  fondsInvVC: string; fondsInvVM: string;
  totalClassIVC: string; totalClassIVM: string;
  ratioIV: string;
}

@Injectable({ providedIn: 'root' })
export class MyAggregatorService {
  private apiUrl = 'http://localhost:8080/api/situation-avant-traitement/aggregated-all-classes';

  constructor(private http: HttpClient) {}

  getAggregatedAllClasses(): Observable<AggregatedAllClassesDTO> {
    return this.http.get<AggregatedAllClassesDTO>(this.apiUrl);
  }
}
