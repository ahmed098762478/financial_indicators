import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FichePortefeuilleService, FichePortefeuilleSummary } from './fiche-portefeuille.service';

@Component({
  selector: 'app-situation',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './situation.component.html',
  styleUrls: ['./situation.component.css']
})
export class SituationComponent {
  fichePortefeuilleSummary: FichePortefeuilleSummary[] = [];

  constructor(private fichePortefeuilleService: FichePortefeuilleService) {}

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    this.fichePortefeuilleService.getFichePortefeuilleSummary().subscribe({
      next: (data) => {
        this.fichePortefeuilleSummary = data;
      },
      error: (err) => {
        console.error('Error while loading FichePortefeuilleSummary', err);
      },
    });
  }
}
