import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { Building, Floor } from '../../types';
import { FormsModule } from '@angular/forms';
import { NgFor } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ButtonModule } from 'primeng/button';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { ToastModule } from 'primeng/toast';
import { ConfirmationService } from 'primeng/api';
import { TruncateNamePipe } from '../../pipes/truncate-name.pipe';
import { FloorsService } from '../../services/floors.service';
import { BuildingsService } from '../../services/buildings.service';

@Component({
  selector: 'app-floor',
  standalone: true,
  imports: [FormsModule, ButtonModule, ConfirmPopupModule, ToastModule, TruncateNamePipe, NgFor],
  providers: [ConfirmationService],
  templateUrl: './floor.component.html',
  styleUrl: './floor.component.scss'
})
export class FloorComponent {
  buildings: Building[] = []; // Store the list of buildings
  newFloor: Floor = { number: '', description: '', building: { buildingId: 0, name: '', address: '' }, roomIds: [] }; // Initialize with a valid building object
  floors: Floor[] = []; // Store the list of buildings
  total: number = 0; // Total number of buildings
  page: number = 1; // Current page for pagination
  perPage: number = 10; // Items per page

  constructor(
    private confirmationService: ConfirmationService,
    private http: HttpClient, // Inject HttpClient for HTTP requests
    private floorsService: FloorsService,
    private buildingsService: BuildingsService
  ) { }

  @Input() floor!: Floor;
  @ViewChild('deleteButton') deleteButton: any;

  @Output() edit: EventEmitter<Floor> = new EventEmitter<Floor>();
  @Output() delete: EventEmitter<Floor> = new EventEmitter<Floor>();

  editFloor() {
    this.edit.emit(this.floor);
  }

  confirmDelete() {
    this.confirmationService.confirm({
      target: this.deleteButton.nativeElement,
      message: 'Are you sure that you want to delete this Floor?',
      accept: () => {
        this.deleteFloor();
      },
    })
  };

  deleteFloor() {
    this.delete.emit(this.floor);
  }

  addFloor() {
    this.http
      .post<Floor>('http://localhost:8080/api/floors', this.newFloor) // Updated URL
      .subscribe({
        next: (floor) => {
          this.floors.push(floor); // Add the new floor to the list
          this.total += 1; // Increment total floor count
          this.resetNewFloor(); // Reset the newFloor after adding
        },
        error: (error) => console.log('Error adding floor:', error),
      });
  }

  resetNewFloor() {
    this.newFloor = { number: '', description: '', building: { buildingId: 0, name: '', address: '' }, roomIds: [] }; // Reset to a valid state
  }

  fetchFloors(page: number, perPage: number) {
  //   this.http.get<Floor[]>('http://localhost:8080/api/floors').subscribe(response => {
  //     this.floors = response;
  //     this.total = response.length; 
  // });
  this.floorsService.getFloors('http://localhost:8080/api/floors', { page: 1, perPage: 10 })
  .subscribe(floors => {
      this.floors = floors;
      this.fetchBuildingForFloors(); // Fetch buildings after getting floors
  });
  }

  fetchBuildingForFloors() {
    this.floors.forEach(floor => {
        if (floor.building.buildingId) {
            this.buildingsService.getBuildingById(floor.building.buildingId).subscribe(building => {
                floor.building = building; // Assign the fetched building to the floor
            });
        }
    });
}

  ngOnInit() {
    this.fetchFloors(this.page, this.perPage); // Fetch buildings on component init
  }
}
