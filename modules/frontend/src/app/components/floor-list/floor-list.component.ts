import { Component } from '@angular/core';
import { Floor } from '../../types';
import { FloorsService } from '../../services/floors.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-floor-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './floor-list.component.html',
  styleUrl: './floor-list.component.css'
})
export class FloorListComponent {
  floors: Floor[] = [];
  buildingId!: number;

  constructor(private route: ActivatedRoute, private floorsService: FloorsService) {}

  ngOnInit(): void {
      this.route.paramMap.subscribe(params => {
          this.buildingId = +params.get('buildingId')!; // Get the building ID from the route
          console.log('Building ID:', this.buildingId); // Log for debugging

          this.fetchFloors(); // Fetch floors for this building
      });
  }

  fetchFloors(): void {
      this.floorsService.getFloorsByBuildingId(this.buildingId).subscribe(
          (floors) => {
              this.floors = floors;
          },
          (error) => {
              console.error('Error fetching floors:', error);
          }
      );
  }
}
