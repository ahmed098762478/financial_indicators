import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common'; // Ajouté
import { FormsModule } from '@angular/forms'; // Ajouté

@Component({
  selector: 'app-transparisation',
  standalone: true, // Ajouté pour les composants autonomes
  imports: [CommonModule, FormsModule], // Ajouté
  templateUrl: './transparisation.component.html',
  styleUrls: ['./transparisation.component.css']
})
export class TransparisationFormComponent {
  transparisationData: any[] = [];
  dateImage: Date | null = null; // Changé en Date
  private apiUrl = 'http://localhost:8080/api/transparisation/by-date';

  constructor(private http: HttpClient) { }

  chercherInformations() {
    if (!this.dateImage) return;

    const params = {
      targetDate: this.dateImage.toISOString().split('T')[0] // Format YYYY-MM-DD
    };

    this.http.get<any[]>(this.apiUrl, { params }).subscribe({
      next: (data) => {
        this.transparisationData = data;
      },
      error: (err) => {
        console.error('Erreur:', err);
        this.transparisationData = [];
      }
    });
  }
}
