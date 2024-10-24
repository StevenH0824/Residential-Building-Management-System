import { Component } from '@angular/core';
import { BuildingsService } from '../services/buildings.service';
import { Building, Buildings } from '../types';
import { BuildingComponent } from '../components/building/building.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [BuildingComponent, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})

export class HomeComponent {
  buildings: Building[] = []; // Initialize as an empty array
  isLoading: boolean = true; // Track loading state

  constructor(private buildingsService: BuildingsService) {}

  ngOnInit() {
    this.loadBuildings();
  }

  loadBuildings() {
    this.buildingsService
      .getBuildings('http://localhost:8080/api/buildings', { page: 0, perPage: 5 })
      .subscribe(
        (response: Building[]) => {
          console.log('Fetched buildings:', response); // Log the full response
          this.buildings = response ;
          this.isLoading = false;
        },
        (error) => {
          console.error('Error fetching buildings:', error);
          this.isLoading = false;
        }
      );
  }
}
