import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Building, EditEntity, Person, StatusType } from '../../types';
import { CommonModule } from '@angular/common';
import { DialogModule } from 'primeng/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { PersonService } from '../../services/person.service'; // Import your service
import { BuildingsService } from '../../services/buildings.service'; // Import your service
import { MaintenanceRequest } from '../../types';

@Component({
  selector: 'app-edit-popup',
  standalone: true,
  imports: [
    DialogModule,
    CommonModule,
    FormsModule,
    ButtonModule,
    ReactiveFormsModule,
  ],
  templateUrl: './edit-popup.component.html',
  styleUrls: ['./edit-popup.component.css'],
})
export class EditPopupComponent {
  @Input() display: boolean = false;
  @Input() header!: string;
  @Output() displayChange = new EventEmitter<boolean>();
  @Output() cancel = new EventEmitter<void>();
  @Output() confirm = new EventEmitter<Person | Building | MaintenanceRequest>();

  public _building: Building | null = null;
  public _person: Person | null = null;
  public _maintenanceRequest: MaintenanceRequest | null = null;
  private _entity: Person | MaintenanceRequest | Building | null = null;

  @Input()
  set person(value: Person | null) {
    this._entity = value;
    if (value) {
      this._person = value;
      this.editingPerson = { ...value };
    } else {
      this.resetPerson();
    }
  }

  @Input()
  set building(value: Building | null) {
    if (value) {
      this._building = value;
      this.editingBuilding = { ...value };
    } else {
      this.resetBuilding();
    }
  }

  @Input()
  set maintenanceRequest(value: MaintenanceRequest | null) {
    if (value) {
      this._maintenanceRequest = value;
      this.editingMaintenanceRequest = { ...value };
    } else {
      this.resetMaintenanceRequest();
    }
  }

  editingPerson: Person = {
    personId: 0,
    firstName: '',
    lastName: '',
    email: '',
    phoneNumber: '',
  };
  editingBuilding: Building = {
    buildingId: 0,
    name: '',
    address: '',
    floors: [],
  };
  editingMaintenanceRequest: MaintenanceRequest = {
    maintenanceRequestId: 0,
    createdDate: new Date(),
    issue: '',
    status: 'PENDING' as StatusType,
    personId: 0,
    roomId: 0,
  };

  constructor(
    private personService: PersonService,
    private buildingService: BuildingsService
  ) {}

  // private resetEditingEntity() {
  //   if (this._entity) {
  //     if ('personId' in this._entity) {
  //     } else if ('maintenanceRequestId' in this._entity) {
  //     } else if ('buildingId' in this._entity) {
  //     }
  //   }
  // }

  onConfirm() {
    if (this._person) {
      this.confirm.emit(this.editingPerson);
    } else if (this._maintenanceRequest) {
      this.confirm.emit(this.editingMaintenanceRequest);
    } else if (this._building) {
      this.confirm.emit(this.editingBuilding);
    } else {
      console.error('No valid entity to emit');
    }
    this.closeDialog();
  }

  onCancel() {
    this.cancel.emit();
    this.closeDialog();
  }

  private closeDialog() {
    this.display = false;
    console.log('Emitting displayChange:', this.display); 
    this.displayChange.emit(this.display);
  }

  private resetPerson() {
    this.editingPerson = {
      personId: 0,
      firstName: '',
      lastName: '',
      email: '',
      phoneNumber: '',
    };
  }

  private resetBuilding() {
    this.editingBuilding = { buildingId: 0, name: '', address: '', floors: [] };
  }

  private resetMaintenanceRequest() {
    this.editingMaintenanceRequest = {
      maintenanceRequestId: 0,
      createdDate: new Date(),
      issue: '',
      status: 'PENDING' as StatusType,
      personId: 0,
      roomId: 0,
    };
  }
}
// import { Component, EventEmitter, Input, Output } from '@angular/core';
// import { Building, EditEntity, Person, StatusType } from '../../types';
// import { CommonModule } from '@angular/common';
// import { DialogModule } from 'primeng/dialog';
// import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// import { ButtonModule } from 'primeng/button';
// import { PersonService } from '../../services/person.service'; // Import your service
// import { BuildingsService } from '../../services/buildings.service'; // Import your service
// import { MaintenanceRequest } from '../../types';

// @Component({
//   selector: 'app-edit-popup',
//   standalone: true,
//   imports: [
//     DialogModule,
//     CommonModule,
//     FormsModule,
//     ButtonModule,
//     ReactiveFormsModule,
//   ],
//   templateUrl: './edit-popup.component.html',
//   styleUrls: ['./edit-popup.component.css'],
// })
// export class EditPopupComponent {
//   @Input() display: boolean = false;
//   @Input() header!: string;
//   @Output() displayChange = new EventEmitter<boolean>();
//   @Output() cancel = new EventEmitter<void>();
//   @Output() confirm = new EventEmitter<EditEntity | MaintenanceRequest>(); // Update to include MaintenanceRequest

//   public _building: Building | null = null;
//   public _person: Person | null = null;
//   public _maintenanceRequest: MaintenanceRequest | null = null; // New property for MaintenanceRequest

//   @Input()
//   set person(value: Person | null) {
//     if (value) {
//       this._person = value;
//       this.editingPerson = { ...value };
//     } else {
//       this.resetPerson();
//     }
//   }

//   @Input()
//   set building(value: Building | null) {
//     if (value) {
//       this._building = value;
//       this.editingBuilding = { ...value };
//     } else {
//       this.resetBuilding();
//     }
//   }

//   @Input()
//   set maintenanceRequest(value: MaintenanceRequest | null) {
//     if (value) {
//       this._maintenanceRequest = value;
//       this.editingMaintenanceRequest = { ...value };
//     } else {
//       this.resetMaintenanceRequest();
//     }
//   }

//   editingPerson: Person = { personId: 0, firstName: '', lastName: '', email: '', phoneNumber: '' };
//   editingBuilding: Building = { buildingId: 0, name: '', address: '', floors: [] };
//   editingMaintenanceRequest: MaintenanceRequest = { maintenanceRequestId: 0, createdDate: new Date(), issue: '', status: 'PENDING' as StatusType, personId: 0, roomId: 0 };

//   constructor(private personService: PersonService, private buildingService: BuildingsService) {}

//   onConfirm() {
//     if (this._person) {
//       this.confirm.emit(this.editingPerson);
//     } else if (this._building) {
//       this.confirm.emit(this.editingBuilding);
//     } else if (this._maintenanceRequest) {
//       this.confirm.emit(this.editingMaintenanceRequest);
//     }
//     this.closeDialog();
//   }

//   onCancel() {
//     this.cancel.emit();
//     this.closeDialog();
//   }

//   private closeDialog() {
//     this.display = false;
//     this.displayChange.emit(this.display);
//   }

//   private resetPerson() {
//     this.editingPerson = { personId: 0, firstName: '', lastName: '', email: '', phoneNumber: '' };
//   }

//   private resetBuilding() {
//     this.editingBuilding = { buildingId: 0, name: '', address: '', floors: [] };
//   }

//   private resetMaintenanceRequest() {
//     this.editingMaintenanceRequest = { maintenanceRequestId: 0, createdDate: new Date(), issue: '', status: 'PENDING' as StatusType, personId: 0, roomId: 0 };
//   }
// }
