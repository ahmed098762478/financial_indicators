import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-transparisation',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './transparisation.component.html',
  styleUrls: ['./transparisation.component.css']
})
export class TransparisationFormComponent {
  transparisationData: any[] = [];
  dateImage: string = '';
  dateImageFin: string = '9999-12-31';
  ptf: string = 'CIV';
  private apiUrl = 'http://localhost:8080/api/transparisation/by-date';

  constructor(private http: HttpClient) { }

  chercherInformations() {
    if (!this.dateImage) {
      alert('Veuillez saisir une date image');
      return;
    }

    const params = {
      targetDate: this.dateImage,
      dateFin: this.dateImageFin,
      ptf: this.ptf
    };

    this.http.get<any[]>(this.apiUrl, { params }).subscribe({
      next: (data) => {
        this.transparisationData = data;
        console.log('Table trans_tempo créée et données récupérées:', data);
      },
      error: (err) => {
        console.error('Erreur:', err);
        this.transparisationData = [];
      }
    });
  }
}
