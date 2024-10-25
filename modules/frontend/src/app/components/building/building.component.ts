import { Building } from '../../types';
import { Component, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { ToastModule } from 'primeng/toast';
import { ConfirmationService } from 'primeng/api';
import { TruncateNamePipe } from '../../pipes/truncate-name.pipe';


@Component({
  selector: 'app-building',
  standalone: true,
  imports: [FormsModule, ButtonModule, ConfirmPopupModule, ToastModule, TruncateNamePipe],
  providers: [ConfirmationService],
  templateUrl: './building.component.html',
  styleUrl: './building.component.css'
})

export class BuildingComponent {
  constructor(private confirmationService: ConfirmationService) {}
  
  @Input() building!: Building;
  @ViewChild('deleteButton') deleteButton: any;
  @Output() edit: EventEmitter<Building> = new EventEmitter<Building>();
  @Output() delete: EventEmitter<Building> = new EventEmitter<Building>();

editBuilding(){
    this.edit.emit(this.building);
  }

confirmDelete(){
  this.confirmationService.confirm({
    target: this.deleteButton.nativeElement,
    message: 'Are you sure that you want to delete this Building?',
    accept:() => {
      this.deleteBuilding();
      },
    })
  };

  deleteBuilding(){
    this.delete.emit(this.building);
  }

  ngOnInit(){
  }
}
























  // constructor(private buildingsService: BuildingsService) {}

  // @ViewChild('paginator') paginator: Paginator | undefined;

  // buildings: Building[] = [];

  // totalRecords: number = 0;
  // rows: number = 12;

  // displayEditPopup: boolean = false;
  // displayAddPopup: boolean = false;

  // toggleEditPopup(building: Building) {
  //   this.selectedBuilding = building;
  //   this.displayEditPopup = true;
  // }

  // toggleDeletePopup(building: Building) {
  //   if (!building.buildingId) {
  //     return;
  //   }
  //   this.deleteBuilding(building.buildingId);
  // }

  // toggleAddPopup() {
  //   this.displayAddPopup = true;
  // }

  // selectedBuilding: Building = {
  //   buildingId: 0,
  //   name: '',
  //   address: '',
  //   floors: FloorComponent[],
  //   total: 0,
  //   items: undefined
  // };

  // onConfirmEdit(building: Building) {
  //   if (!this.selectedBuilding.buildingId) {
  //     return;
  //   }

  //   this.editBuilding(building, this.selectedBuilding.buildingId);
  //   this.displayEditPopup = false;
  // }

  // onConfirmAdd(building: Building) {
  //   this.addBuilding(building);
  //   this.displayAddPopup = false;
  // }

  // onBuildingOutput(building: Building) {
  //   console.log(building, 'Output');
  // }

  // onPageChange(event: any) {
  //   this.fetchBuildings(event.page, event.rows);
  // }

  // resetPaginator() {
  //   this.paginator?.changePage(0);
  // }

  // fetchBuildings(page: number, perPage: number) {
  //   this.buildingsService
  //     .getBuildings('http://localhost:8080/buildings', { page, perPage })
  //     .subscribe({
  //       next: (data: Building) => {
  //         this.buildings = data.items;
  //         this.totalRecords = data.total;
  //       },
  //       error: (error) => {
  //         console.log(error);
  //       },
  //     });
  // }

  // editBuilding(building: Building, id: number) {
  //   this.buildingsService
  //     .editBuilding(`http://localhost:8080/buildings/${id}`, building)
  //     .subscribe({
  //       next: (data: any) => {
  //         console.log(data);
  //         this.fetchBuildings(0, this.rows);
  //         this.resetPaginator();
  //       },
  //       error: (error: any) => {
  //         console.log(error);
  //       },
  //     });
  // }


  // deleteBuilding(id: number) {
  //   this.buildingsService
  //     .deleteBuilding(`http://localhost:3000/clothes/${id}`)
  //     .subscribe({
  //       next: (data) => {
  //         console.log(data);
  //         this.fetchBuildings(0, this.rows);
  //         this.resetPaginator();
  //       },
  //       error: (error) => {
  //         console.log(error);
  //       },
  //     });
  // }

  // addBuilding(building: Building) {
  //   this.buildingsService
  //     .addBuilding(`http://localhost:3000/clothes`, building)
  //     .subscribe({
  //       next: (data) => {
  //         console.log(data);
  //         this.fetchBuildings(0, this.rows);
  //         this.resetPaginator();
  //       },
  //       error: (error) => {
  //         console.log(error);
  //       },
  //     });
  // }

  // ngOnInit() {
  //   this.fetchBuildings(0, this.rows);
  // }

