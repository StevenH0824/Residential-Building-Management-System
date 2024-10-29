import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Building, EditEntity, Person } from '../../types';
import { CommonModule } from '@angular/common';
import { DialogModule } from 'primeng/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { PersonService } from '../../services/person.service'; // Import your service
import { BuildingsService } from '../../services/buildings.service'; // Import your service



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
  @Output() confirm = new EventEmitter<EditEntity>();

  public _building: Building | null = null;
  public _person: Person | null = null;
  persons: Person[] = []; // Declare persons array
  buildings: Building[] = []; // Declare buildings array
  personService: PersonService; // Declare person service
  buildingService: BuildingsService; // Declare building service

  // Input properties for person and building
  @Input()
  set person(value: Person | null) {
    if (value) {
      this._person = value;
      this.editingPerson = { ...value };
    } else {
      this._person = null;
      this.editingPerson = { personId: 0, firstName: '', lastName: '', email: '', phoneNumber: '' };
    }
  }

  @Input()
  set building(value: Building | null) {
    if (value) {
      this._building = value;
      this.editingBuilding = { ...value };
    } else {
      this._building = null;
      this.editingBuilding = { buildingId: 0, name: '', address: '', floors: [] };
    }
  }

  editingPerson: Person = { personId: 0, firstName: '', lastName: '', email: '', phoneNumber: '' };
  editingBuilding: Building = { buildingId: 0, name: '', address: '', floors: [] };

  constructor(personService: PersonService, buildingService: BuildingsService) {
    this.personService = personService; // Initialize person service
    this.buildingService = buildingService; // Initialize building service
  }

  // get dialogWidth(): string {
  //   return this._person ? '600px' : '400px'; // Set width based on whether a person is being edited
  // }

  onConfirm() {
    const editedEntity: EditEntity = this._person ? this.editingPerson : this.editingBuilding;
    this.confirm.emit(editedEntity);
    this.closeDialog();
  }
  

  onCancel() {
    this.cancel.emit();
    this.closeDialog();
  }

  private closeDialog() {
    this.display = false;
    this.displayChange.emit(this.display);
  }
}
