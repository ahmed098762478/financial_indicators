import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-transparisation',
  templateUrl: './transparisation.component.html',
  styleUrls: ['./transparisation.component.css']
})
export class TransparisationFormComponent implements OnInit {

  transparisationData: any[] = [];  // Array to store fetched data

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.loadTransparisationData();
  }

  loadTransparisationData(): void {
    // Replace with your API URL for fetching data from the server
    this.http.get<any[]>('https://your-api-endpoint/transparisation')
      .subscribe(data => {
        this.transparisationData = data;
      });
  }

}
