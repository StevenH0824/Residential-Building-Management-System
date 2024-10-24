<<<<<<< HEAD
<<<<<<< HEAD
import { Component, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BuildingsService } from '../services/buildings.service';
import { Building, PaginatedBuildingResponse } from '../types';
// import { Paginator, PaginatorModule } from 'primeng/paginator';
// import { ButtonModule } from 'primeng/button';
=======
import { Component } from "@angular/core";
import { BuildingsService } from "../services/buildings.service";
import { Building } from "../types";
>>>>>>> danaBranch
=======
import { Component } from '@angular/core';
import { BuildingsService } from '../services/buildings.service';
import { Building, Buildings } from '../types';
import { BuildingComponent } from '../components/building/building.component';
import { CommonModule } from '@angular/common';
>>>>>>> danaBranch

@Component({
  selector: 'app-home',
  standalone: true,
<<<<<<< HEAD
  imports: [
<<<<<<< HEAD
    // BuildingComponent,
    // CommonModule,
    // PaginatorModule,
    // EditPopupComponent,
    // ButtonModule,
=======
   
>>>>>>> danaBranch
  ],
=======
  imports: [BuildingComponent, CommonModule],
>>>>>>> danaBranch
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})

export class HomeComponent {
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
=======
  buildings: Building[] = []; // Initialize as an empty array
  isLoading: boolean = true; // Track loading state

>>>>>>> danaBranch
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
<<<<<<< HEAD

  // products: Product[] = [];

  // totalRecords: number = 0;
  // rows: number = 12;

  // displayEditPopup: boolean = false;
  // displayAddPopup: boolean = false;

  // toggleEditPopup(product: Product) {
  //   this.selectedProduct = product;
  //   this.displayEditPopup = true;
  // }

  // toggleDeletePopup(product: Product) {
  //   if (!product.id) {
  //     return;
  //   }
  //   this.deleteProduct(product.id);
  // }

  // toggleAddPopup() {
  //   this.displayAddPopup = true;
  // }

  // selectedProduct: Product = {
  //   id: 0,
  //   name: '',
  //   image: '',
  //   price: '',
  //   rating: 0,
  // };

  // onConfirmEdit(product: Product) {
  //   if (!this.selectedProduct.id) {
  //     return;
  //   }

  //   this.editProduct(product, this.selectedProduct.id);
  //   this.displayEditPopup = false;
  // }

  // onConfirmAdd(product: Product) {
  //   this.addProduct(product);
  //   this.displayAddPopup = false;
  // }

  // onProductOutput(product: Product) {
  //   console.log(product, 'Output');
  // }

  // onPageChange(event: any) {
  //   this.fetchProducts(event.page, event.rows);
  // }

  // resetPaginator() {
  //   this.paginator?.changePage(0);
  // }

  // fetchProducts(page: number, perPage: number) {
  //   this.productsService
  //     .getProducts('http://localhost:3000/clothes', { page, perPage })
  //     .subscribe({
  //       next: (data: Products) => {
  //         this.products = data.items;
  //         this.totalRecords = data.total;
  //       },
  //       error: (error) => {
  //         console.log(error);
  //       },
  //     });
  // }

  // editProduct(product: Product, id: number) {
  //   this.productsService
  //     .editProduct(`http://localhost:3000/clothes/${id}`, product)
  //     .subscribe({
  //       next: (data) => {
  //         console.log(data);
  //         this.fetchProducts(0, this.rows);
  //         this.resetPaginator();
  //       },
  //       error: (error) => {
  //         console.log(error);
  //       },
  //     });
  // }

  // deleteProduct(id: number) {
  //   this.productsService
  //     .deleteProduct(`http://localhost:3000/clothes/${id}`)
  //     .subscribe({
  //       next: (data) => {
  //         console.log(data);
  //         this.fetchProducts(0, this.rows);
  //         this.resetPaginator();
  //       },
  //       error: (error) => {
  //         console.log(error);
  //       },
  //     });
  // }

  // addProduct(product: Product) {
  //   this.productsService
  //     .addProduct(`http://localhost:3000/clothes`, product)
  //     .subscribe({
  //       next: (data) => {
  //         console.log(data);
  //         this.fetchProducts(0, this.rows);
  //         this.resetPaginator();
  //       },
  //       error: (error) => {
  //         console.log(error);
  //       },
  //     });
  // }

  
>>>>>>> danaBranch

=======
>>>>>>> danaBranch
