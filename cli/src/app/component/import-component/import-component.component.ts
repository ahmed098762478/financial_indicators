import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpEvent, HttpEventType } from '@angular/common/http';

@Component({
  standalone: true,
  selector: 'app-import-component',
  templateUrl: './import-component.component.html',
  styleUrls: ['./import-component.component.css'],
  imports: [
    CommonModule
  ]
})
export class ImportComponentComponent {
  selectedFile: File | null = null;
  uploadProgress: number = 0;
  uploadMessage: string = '';

  constructor(private http: HttpClient) {}

  /**
   * Triggered when a file is selected from the file input
   */
  onFileSelected(event: Event): void {
    const element = event.target as HTMLInputElement;
    if (element.files && element.files.length > 0) {
      this.selectedFile = element.files[0];
    }
  }

  /**
   * Sends the selected file to the backend via a POST request
   */
  onUpload(): void {
    if (!this.selectedFile) {
      this.uploadMessage = 'Please select a file first.';
      return;
    }

    const formData = new FormData();
    formData.append('file', this.selectedFile);

    this.http.post('http://localhost:8080/api/fiche-portefeuille/upload', formData, {
      reportProgress: true,
      observe: 'events'
    }).subscribe({
      next: (event: HttpEvent<any>) => {
        if (event.type === HttpEventType.UploadProgress && event.total) {
          this.uploadProgress = Math.round((100 * event.loaded) / event.total);
        } else if (event.type === HttpEventType.Response) {
          this.uploadMessage = 'File uploaded and data imported successfully!';
          this.uploadProgress = 0;
        }
      },
      error: (err) => {
        console.error(err);
        this.uploadMessage = 'Error uploading file.';
        this.uploadProgress = 0;
      }
    });
  }
}
