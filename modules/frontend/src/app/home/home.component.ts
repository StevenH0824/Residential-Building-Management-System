
import { Component } from '@angular/core';
import { BuildingsService } from '../services/buildings.service';
import { Building } from '../types';
import { CommonModule } from '@angular/common';
import { BuildingComponent } from '../components/building/building.component';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [BuildingComponent, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})

export class HomeComponent {
  buildings: Building[] = [];
  isLoading: boolean = true; // Initialize as true

  constructor(private buildingsService: BuildingsService) {}

  ngOnInit() {
    this.buildingsService
      .getBuildings('http://localhost:8080/api/buildings', { page: 0, perPage: 5 })
      .subscribe(
        (response: Building[]) => {
          console.log('Fetched buildings:', response);
          this.buildings = response;
          this.isLoading = false; // Set loading to false after data is fetched
        },
        (error) => {
          console.error('Error fetching buildings:', error);
          this.isLoading = false; // Also set loading to false on error
        }
      );
  }
}

