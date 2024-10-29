import { Component } from '@angular/core';
import { BuildingsService } from '../services/buildings.service';
import { Building, Floor, Person, EditEntity } from '../types';
import { CommonModule } from '@angular/common';
import { BuildingComponent } from '../components/building/building.component';
import { EditPopupComponent } from '../components/edit-popup/edit-popup.component';
import { ButtonModule } from 'primeng/button';
import { FloorsService } from '../services/floors.service';
import { FloorComponent } from '../components/floor/floor.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterModule, FloorComponent, BuildingComponent, CommonModule, EditPopupComponent, ButtonModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  buildings: Building[] = [];
  floors: Floor[] = [];
  isLoading: boolean = true;
  selectedBuilding: Building | null = null; 
  selectedFloor: Floor | null = null; 
  displayEditPopup: boolean = false;
  displayAddPopup: boolean = false;

  constructor(private buildingsService: BuildingsService, private floorsService: FloorsService) { }

  toggleEditPopupBuilding(building: Building) {
    this.selectedBuilding = { ...building };
    this.displayEditPopup = true;
  }

  onCancelEdit() {
    this.displayEditPopup = false;
  }

  toggleEditPopupFloor(floor: Floor) {
    this.selectedFloor = { ...floor };
    this.displayEditPopup = true;
  }

  onConfirmEdit(updatedEntity: EditEntity) {
    if (this.isBuilding(updatedEntity)) {
      if (!this.selectedBuilding || this.selectedBuilding.buildingId === undefined) {
        console.error('Building ID is required to edit the building.');
        return;
      }

      this.displayEditPopup = false;

      const buildingToUpdate: Building = {
        ...this.selectedBuilding,
        name: updatedEntity.name ?? this.selectedBuilding.name,
        address: updatedEntity.address ?? this.selectedBuilding.address,
      };

      this.editBuilding(buildingToUpdate, this.selectedBuilding.buildingId);
    } else {
      console.error('Expected a Building but got:', updatedEntity);
    }
  }

  onConfirmAdd(entity: EditEntity) {
    if (this.isBuilding(entity)) {
      this.addBuilding(entity);
      this.displayAddPopup = false;
    } else {
      console.error('Expected a Building but got:', entity);
    }
  }

  private isBuilding(entity: EditEntity): entity is Building {
    return (entity as Building).buildingId !== undefined;
  }

  editBuilding(building: Building, id: number) {
    this.buildingsService
      .editBuilding(`http://localhost:8080/api/buildings/${id}`, building)
      .subscribe({
        next: (data) => {
          const index = this.buildings.findIndex(b => b.buildingId === id);
          if (index > -1) {
            this.buildings[index] = data; 
          }
        },
        error: (error) => {
          console.error('Error editing building:', error);
        },
      });
  }

  toggleAddPopup() {
    this.buildingsService.getLatestBuildingId().subscribe({
      next: (latestId: number) => {
        this.selectedBuilding = {
          buildingId: latestId + 1, 
          name: '',
          address: '',
          floors: [],
        };
        this.displayAddPopup = true; 
      },
      error: (error) => {
        console.error('Error fetching latest building ID:', error);
      },
    });
  }

  addBuilding(building: Building) {
    this.buildingsService.addBuilding(`http://localhost:8080/api/buildings`, building).subscribe({
      next: (data) => {
        this.buildings.push(data); 
        this.selectedBuilding = null; 
      },
      error: (error) => {
        console.error('Error adding building:', error);
      },
    });
  }

  deleteBuilding(building: Building) {
    this.buildingsService.deleteBuilding(`http://localhost:8080/api/buildings/${building.buildingId}`)
      .subscribe({
        next: () => {
          this.buildings = this.buildings.filter(b => b.buildingId !== building.buildingId);
        },
        error: (error) => {
          console.error('Error deleting building:', error);
        },
      });
  }

  ngOnInit() {
    this.buildingsService
      .getBuildings('http://localhost:8080/api/buildings', { page: 0, perPage: 5 })
      .subscribe(
        (response: Building[]) => {
          this.buildings = response;
          this.isLoading = false;
        },
        (error) => {
          console.error('Error fetching buildings:', error);
          this.isLoading = false;
        }
      );
  }
}