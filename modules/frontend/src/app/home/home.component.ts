import { Component, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BuildingsService } from '../services/buildings.service';
import { Building, PaginatedBuildingResponse } from '../types';
// import { Paginator, PaginatorModule } from 'primeng/paginator';
// import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    // BuildingComponent,
    // CommonModule,
    // PaginatorModule,
    // EditPopupComponent,
    // ButtonModule,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  constructor(
    private buildingsService: BuildingsService
) {}


// ngOnInit() {
//   this.buildingsService.getBuildings('http://localhost:8080/api/buildings', {page: 0, perPage: 5})
//   .subscribe((buildings: Building) => {
//     console.log(buildings.items);
//   });
// }


ngOnInit() {
  this.buildingsService.getBuildings('http://localhost:8080/api/buildings')
      .subscribe(
          (buildings: Building[]) => {
              console.log(buildings); // Log the list of buildings
          },
          (error) => {
              console.error('Error fetching buildings:', error);
          }
      );
}

}

//   @ViewChild('paginator') paginator: Paginator | undefined;,

//   buildings: Building[] = [];

//   totalRecords: number = 0;
//   rows: number = 12;

//   displayEditPopup: boolean = false;
//   displayAddPopup: boolean = false;

//   toggleEditPopup(product: Product) {
//     this.selectedProduct = product;
//     this.displayEditPopup = true;
//   }

//   toggleDeletePopup(building: Building) {
//     if (!building.id) {
//       return;
//     }
//     this.deleteProduct(building.id);
//   }

//   toggleAddPopup() {
//     this.displayAddPopup = true;
//   }

//   selectedBuilding: Building = {
//     id: 0,
//     name: '',
//     address: ''
//   };

//   onConfirmEdit(building: Building) {
//     if (!this.selectedBuilding.id) {
//       return;
//     }

//     this.editBuilding(building, this.selectedBuilding.id);
//     this.displayEditPopup = false;
//   }

//   onConfirmAdd(building: Building) {
//     this.addBuilding(building);
//     this.displayAddPopup = false;
//   }

//   onProductOutput(building: Building) {
//     console.log(building, 'Output');
//   }

//   onPageChange(event: any) {
//     this.fetchBuildings(event.page, event.rows);
//   }

//   resetPaginator() {
//     this.paginator?.changePage(0);
//   }

//   fetchProducts(page: number, perPage: number) {
//     this.buildingService
//       .getBuilding('http://localhost:8080/building', { page, perPage })
//       .subscribe({
//         next: (data: Building) => {
//           this.building = data.items;
//           this.totalRecords = data.total;
//         },
//         error: (error) => {
//           console.log(error);
//         },
//       });
//   }

//   editBuilding(building: Building, id: number) {
//     this.buildingService
//       .editBuilding(`http://localhost:8080/building/${id}`, building)
//       .subscribe({
//         next: (data) => {
//           console.log(data);
//           this.fetchBuildings(0, this.rows);
//           this.resetPaginator();
//         },
//         error: (error) => {
//           console.log(error);
//         },
//       });
//   }

//   deleteProduct(id: number) {
//     this.productsService
//       .deleteBuilding(`http://localhost:8080/building/${id}`)
//       .subscribe({
//         next: (data) => {
//           console.log(data);
//           this.fetchBuildings(0, this.rows);
//           this.resetPaginator();
//         },
//         error: (error) => {
//           console.log(error);
//         },
//       });
//   }

//   addBuilding(building: Building) {
//     this.buildingService
//       .addBuilding(`http://localhost:8080/management`, building)
//       .subscribe({
//         next: (data) => {
//           console.log(data);
//           this.fetchBuildings(0, this.rows);
//           this.resetPaginator();
//         },
//         error: (error) => {
//           console.log(error);
//         },
//       });
//   }

