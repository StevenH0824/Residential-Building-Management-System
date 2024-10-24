import { Component, Input } from '@angular/core';
import { Building } from '../../types';


@Component({
  selector: 'app-building',
  standalone: true,
  imports: [],
  templateUrl: './building.component.html',
  styleUrl: './building.component.css'
})
export class BuildingComponent {
  @Input() building!: Building;
  
























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
}
