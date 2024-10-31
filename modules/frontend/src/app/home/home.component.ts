import { Component, Input } from '@angular/core';
import { BuildingsService } from '../services/buildings.service';
import { Building, Floor, EditEntity, MaintenanceRequest, Person } from '../types';
import { CommonModule } from '@angular/common';
import { BuildingComponent } from '../components/building/building.component';
import { EditPopupComponent } from '../components/edit-popup/edit-popup.component';
import { ButtonModule } from 'primeng/button';
import { FloorsService } from '../services/floors.service';
import { FloorComponent } from '../components/floor/floor.component';
import { RouterModule } from '@angular/router';
import { MaintenanceRequestsService } from '../services/maintenance-requests.service';
import { PersonService } from '../services/person.service';

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
  persons: Person[] =[];
  maintenanceRequests: MaintenanceRequest[] = []; 
  isLoading: boolean = true;
  selectedBuilding: Building | null = null; 
  selectedFloor: Floor | null = null; 
  selectedPerson: Person | null = null; 
  selectedMaintenanceRequest: MaintenanceRequest | null = null;
  displayEditPopup: boolean = false;
  displayAddPopup: boolean = false;
  displayEditMaintenancePopup: boolean = false; 
  displayEditPersonPopup: boolean = false; 


  constructor(
    private buildingsService: BuildingsService,
    private personsService: PersonService,
    private maintenanceRequestsService: MaintenanceRequestsService
  ) {}

  @Input() menuValue: boolean = false;

  onCancelEdit() {
    this.displayEditPopup = false;
  }

  toggleEditPopupBuilding(building: Building) {
    this.selectedBuilding = { ...building };
    this.displayEditPopup = true;
  }

  toggleEditPopupFloor(floor: Floor) {
    this.selectedFloor = { ...floor };
    this.displayEditPopup = true;
  }

  toggleEditPopupMaintenance(maintenanceRequest: MaintenanceRequest | null) {
    if (maintenanceRequest) {
      this.selectedMaintenanceRequest = { ...maintenanceRequest };
      this.displayEditMaintenancePopup = true;
    }
  }

  toggleEditPopupPerson(person: Person | null) {
    if (person) {
      this.selectedPerson = { ...person };
      this.displayEditPersonPopup = true; 
    }
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

  ngOnInit() {
    this.fetchBuildings();
    this.fetchMaintenanceRequests();
  }

  fetchBuildings() {
    this.buildingsService.getBuildings('http://localhost:8080/api/buildings', { page: 0, perPage: 5 })
      .subscribe(data => {
        this.buildings = data;
      }, error => console.error('Error fetching buildings:', error));
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

  fetchMaintenanceRequests() {
    this.maintenanceRequestsService.getMaintenanceRequests().subscribe(
      data => {
        this.maintenanceRequests = data;
      },
      error => console.error('Error fetching maintenance requests:', error)
    );
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
  onConfirmEdit(updatedEntity: EditEntity) {
    if (this.isMaintenanceRequest(updatedEntity)) {
      const id = updatedEntity.maintenanceRequestId;
      if (id !== undefined) {
        this.editMaintenanceRequest(updatedEntity, id);
      } else {
        console.error('Maintenance request ID is undefined.');
      }
    } else if (this.isPerson(updatedEntity)) {
      this.editPerson(updatedEntity);
    } else if (this.isBuilding(updatedEntity)) {
      const buildingId = updatedEntity.buildingId; 
      if (buildingId !== undefined) {
        this.editBuilding(updatedEntity as Building, buildingId); 
      } else {
        console.error('Building ID is undefined.');
      }
    }
  }

  private isMaintenanceRequest(entity: EditEntity): entity is MaintenanceRequest {
    return (entity as MaintenanceRequest).maintenanceRequestId !== undefined; 
  }

  editMaintenanceRequest(request: MaintenanceRequest, id: number) {
    this.maintenanceRequestsService.updateMaintenanceRequest(request).subscribe({
      next: (data) => {
        const index = this.maintenanceRequests.findIndex(req => req.maintenanceRequestId === id);
        if (index > -1) {
          this.maintenanceRequests[index] = data;
        }
      },
      error: (error) => {
        console.error('Error editing maintenance request:', error);
      },
    });
  }

  private isPerson(entity: EditEntity): entity is Person {
    return (entity as Person).personId !== undefined;
  }
  
  editPerson(updatedPerson: Person) {
    this.personsService.editPerson(updatedPerson).subscribe({
      next: (response) => {
        const index = this.persons.findIndex(p => p.personId === updatedPerson.personId);
        if (index !== -1) {
          this.persons[index] = response; // Update the person in the list
        }
      },
      error: (error) => {
        console.error('Error editing person:', error);
      },
    });
  }
  
  private editBuilding(building: Building, id: number) {
    this.buildingsService.editBuilding(`http://localhost:8080/api/buildings/${id}`, building)
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
}