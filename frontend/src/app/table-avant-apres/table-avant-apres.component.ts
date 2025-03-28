import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AggregatedAllClassesDTO, MyAggregatorService} from "./my-aggregator.service";

@Component({
  selector: 'app-table-avant-apres',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './table-avant-apres.component.html',
  styleUrls: ['./table-avant-apres.component.css']
})
export class TableAvantApresComponent {

  aggregator: AggregatedAllClassesDTO | null = null;

  constructor(private aggregatorService: MyAggregatorService) {}

  ngOnInit(): void {
    // Call your service to load the aggregator data
    this.aggregatorService.getAggregatedAllClasses()
      .subscribe(data => {
        this.aggregator = data;
      });
  }
}
