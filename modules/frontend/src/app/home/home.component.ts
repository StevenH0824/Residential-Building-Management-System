import { Component } from '@angular/core';
import { BuildingsService } from '../services/buildings.service';
import { Building, Floor } from '../types';
import { CommonModule } from '@angular/common';
import { BuildingComponent } from '../components/building/building.component';
import { EditPopupComponent } from '../components/edit-popup/edit-popup.component';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [BuildingComponent, CommonModule, EditPopupComponent, ButtonModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  buildings: Building[] = [];
  floors: Floor[] = [];
  isLoading: boolean = true;
  selectedBuilding: Building = { buildingId: 0, name: '', address: '', floors: [] }; // Initialize with a default object
  displayEditPopup: boolean = false;
  displayAddPopup: boolean = false;

  constructor(private buildingsService: BuildingsService) { }

  toggleEditPopup(building: Building) {
    this.selectedBuilding = { ...building }; // Clone the building for editing
    this.displayEditPopup = true;
  }

  toggleAddPopup() {
    // Fetch the latest building ID
    this.buildingsService.getLatestBuildingId().subscribe({
      next: (latestId: number) => {
        // Set up a new building object with the next ID
        this.selectedBuilding = {
          buildingId: latestId + 1, // Increment the latest ID
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

  onConfirmEdit(updatedBuilding: Partial<Building>) {
    if (!this.selectedBuilding || this.selectedBuilding.buildingId === undefined) {
      console.error('Building ID is required to edit the building.');
      return;
    }

    this.displayEditPopup = false;
    const buildingToUpdate: Building = {
      ...this.selectedBuilding,
      name: updatedBuilding.name ?? this.selectedBuilding.name,
      address: updatedBuilding.address ?? this.selectedBuilding.address,
    };

    this.editBuilding(buildingToUpdate, this.selectedBuilding.buildingId);
  }

  onConfirmAdd(building: Building) {
    this.addBuilding(building);
    this.displayAddPopup = false;
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

  addBuilding(building: Building) {
    this.buildingsService
      .addBuilding(`http://localhost:8080/api/buildings`, building)
      .subscribe({
        next: (data) => {
          this.buildings.push(data);
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

// import { Component } from '@angular/core';
// import { BuildingsService } from '../services/buildings.service';
// import { Building, Floor } from '../types';
// import { CommonModule } from '@angular/common';
// import { BuildingComponent } from '../components/building/building.component';
// import { EditPopupComponent } from '../components/edit-popup/edit-popup.component';
// import { ButtonModule } from 'primeng/button';

// @Component({
//   selector: 'app-home',
//   standalone: true,
//   imports: [BuildingComponent, CommonModule, EditPopupComponent, ButtonModule],
//   templateUrl: './home.component.html',
//   styleUrls: ['./home.component.scss'],
// })
// export class HomeComponent {
//   buildings: Building[] = [];
//   floors: Floor[] = [];
//   isLoading: boolean = true;
//   selectedBuilding: Building | null = null;

//   constructor(private buildingsService: BuildingsService) { }

//   displayEditPopup: boolean = false;
//   displayAddPopup: boolean = false;

//   toggleEditPopup(building: Building) {
//     this.selectedBuilding = { ...building }; // Clone the building for editing
//     this.displayEditPopup = true;
//   }

//   toggleAddPopup() {
//     this.displayAddPopup = true;
//   }

//   onConfirmEdit(updatedBuilding: Partial<Building>) {
//     if (!this.selectedBuilding || this.selectedBuilding.buildingId === undefined) {
//       console.error('Building ID is required to edit the building.');
//       return;
//     }

//     this.displayEditPopup = false;
//     const buildingToUpdate: Building = {
//       ...this.selectedBuilding, // Retain the buildingId and other properties
//       name: updatedBuilding.name ?? this.selectedBuilding.name,
//       address: updatedBuilding.address ?? this.selectedBuilding.address
//     };

//     // Now `this.selectedBuilding.buildingId` is guaranteed to be a number
//     this.editBuilding(buildingToUpdate, this.selectedBuilding.buildingId);
//   }




//   onConfirmAdd(building: Building) {
//     this.addBuilding(building);
//     this.displayAddPopup = false;
//   }

//   editBuilding(building: Building, id: number) {
//     this.buildingsService
//       .editBuilding(`http://localhost:8080/api/buildings/${id}`, building) // Updated URL
//       .subscribe({
//         next: (data) => {
//           // Update the buildings list with the modified building
//           const index = this.buildings.findIndex(b => b.buildingId === id);
//           if (index > -1) {
//             this.buildings[index] = data;
//           }
//         },
//         error: (error) => {
//           console.error('Error editing building:', error);
//         },
//       });
//   }

//   addBuilding(building: Building) {
//     this.buildingsService
//       .addBuilding(`http://localhost:8080/api/buildings`, building) // Updated URL
//       .subscribe({
//         next: (data) => {
//           this.buildings.push(data);
//         },
//         error: (error) => {
//           console.error('Error adding building:', error);
//         },
//       });
//   }

//   deleteBuilding(building: Building) {
//     this.buildingsService.deleteBuilding(`http://localhost:8080/api/buildings/${building.buildingId}`)
//       .subscribe({
//         next: () => {
//           this.buildings = this.buildings.filter(b => b.buildingId !== building.buildingId);
//         },
//         error: (error) => {
//           console.error('Error deleting building:', error);
//         },
//       });
//   }


//   ngOnInit() {
//     this.buildingsService
//       .getBuildings('http://localhost:8080/api/buildings', { page: 0, perPage: 5 })
//       .subscribe(
//         (response: Building[]) => {
//           this.buildings = response;
//           this.isLoading = false;
//         },
//         (error) => {
//           console.error('Error fetching buildings:', error);
//           this.isLoading = false;
//         }
//       );
//   }
// }



// import { Component } from '@angular/core';
// import { BuildingsService } from '../services/buildings.service';
// import { Building, Floor } from '../types';
// import { CommonModule } from '@angular/common';
// import { BuildingComponent } from '../components/building/building.component';
// import { EditPopupComponent } from '../components/edit-popup/edit-popup.component';
// import { ButtonModule } from 'primeng/button';


// @Component({
//   selector: 'app-home',
//   standalone: true,
//   imports: [BuildingComponent, CommonModule, EditPopupComponent, ButtonModule],
//   templateUrl: './home.component.html',
//   styleUrl: './home.component.scss',
// })

// export class HomeComponent {
//   buildings: Building[] = [];
//   floors: Floor[] = [];
//   isLoading: boolean = true;

//   constructor(private buildingsService: BuildingsService) {}

//   displayEditPopup: boolean = false;
//   displayAddPopup: boolean = false;

//   toggleEditPopup(building: Building) {
//     this.selectedBuilding = building;
//     this.displayEditPopup = true;
//   }

//   toggleDeletePopup(building: Building) {
//     if (!building.buildingId) {
//       return;
//     }
//   }

//   toggleAddPopup() {
//     this.displayAddPopup = true;
//   }

//   selectedBuilding: Building = {
//     buildingId: 0,
//     name: '',
//     address: '',
//     floors: [],
//   };

//   onConfirmEdit(building: Building) {
//     this.displayEditPopup = false;
//     if (!this.selectedBuilding.buildingId) {
//       return;
//     }

//     this.editBuilding(building, this.selectedBuilding.buildingId);
//     this.displayEditPopup = false;
//   }

//   onConfirmAdd(building: Building) {
//     this.addBuilding(building);
//     this.displayAddPopup = false;
//   }

//   onBuildingOutput(building: Building) {
//     console.log(building, 'Output');
//   }

//   editBuilding(building: Building, id: number) {
//     this.buildingsService
//       .editBuilding(`http://localhost:8080/buildings/${id}`, building)
//       .subscribe({
//         next: (data) => {
//           console.log(data);
//         },
//         error: (error) => {
//           console.log(error);
//         },
//       });
//   }

//   deleteBuilding(building: Building) {
//       this.buildingsService.deleteBuilding(`http://localhost:8080/buildings/${building.buildingId}`)
//         .subscribe({
//           next: () => {
//             this.buildings = this.buildings.filter(b => b.buildingId !== building.buildingId);
//           },
//           error: (error) => {
//             console.error('Error deleting building:', error);
//           },
//         });
//   }

//   addBuilding(building: Building) {
//     this.buildingsService
//       .addBuilding(`http://localhost:8080/buildings`, building)
//       .subscribe({
//         next: (data) => {
//           console.log(data);
//         },
//         error: (error) => {
//           console.log(error);
//         },
//       });
//   }


//   ngOnInit() {
//     this.buildingsService
//       .getBuildings('http://localhost:8080/api/buildings', { page: 0, perPage: 5 })
//       .subscribe(
//         (response: Building[]) => {
//           console.log('Fetched buildings:', response);
//           this.buildings = response;
//           // Set loading to false after data is fetched
//           this.isLoading = false;
//         },
//         (error) => {
//           console.error('Error fetching buildings:', error);
//           //set loading to false on error
//           this.isLoading = false;
//         }
//       );
//   }
// }

