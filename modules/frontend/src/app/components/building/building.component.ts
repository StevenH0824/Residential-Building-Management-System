// import { Component, EventEmitter, Input, Output, ViewChild} from '@angular/core';
// import { Product } from '../../../types';
// import { RatingModule } from 'primeng/rating';
// import { FormsModule } from '@angular/forms';
// import { ButtonModule } from 'primeng/button';
// import { ConfirmPopupModule } from 'primeng/confirmpopup';
// import { ToastModule } from 'primeng/toast';
// import { ConfirmationService } from 'primeng/api';

// @Component({
//   selector: 'app-building',
//   standalone: true,
//   imports: [RatingModule, FormsModule, ButtonModule, ConfirmPopupModule, ToastModule],
//   providers: [ConfirmationService],
//   templateUrl: './building.component.html',
//   styleUrl: './building.component.scss'
// })
// export class BuildingComponent {
//   constructor(private confirmationService: ConfirmationService) {}
//   @ViewChild('deleteButton') deleteButton: any;
//   @Input() building!: Building;
//   @Output() edit: EventEmitter<Building> = new EventEmitter<Building>();
//   @Output() delete: EventEmitter<Building> = new EventEmitter<Building>();

//   toggleDeletePopup() {
//     throw new Error('Method not implemented.');
// }

//   editBuilding(){
//     this.edit.emit(this.building);
//   }

// confirmDelete(){
//   this.confirmationService.confirm({
//     target: this.deleteButton.nativeElement,
//     message: 'Are you sure that you want to delete this product?',
//     accept:() => {
//       this.deleteBuilding();
//       },
//     })
//   };

//   deleteBuilding(){
//     this.delete.emit(this.building);
//   }

//   ngOnInit(){
//   }
// }
