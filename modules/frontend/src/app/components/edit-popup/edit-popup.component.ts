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
  
  // Input properties for person and building
  @Input()
  set person(value: Person | null) {
    if (value) {
      this._person = value;
      this.editingPerson = { ...value }; // Create a copy for editing
    } else {
      this.resetPerson(); // Reset if no person provided
    }
  }

  @Input()
  set building(value: Building | null) {
    if (value) {
      this._building = value;
      this.editingBuilding = { ...value }; // Create a copy for editing
    } else {
      this.resetBuilding(); // Reset if no building provided
    }
  }

  editingPerson: Person = { personId: 0, firstName: '', lastName: '', email: '', phoneNumber: '' };
  editingBuilding: Building = { buildingId: 0, name: '', address: '', floors: [] };

  constructor(private personService: PersonService, private buildingService: BuildingsService) {}

  onConfirm() {
    if (this._person) {
      this.confirm.emit(this.editingPerson); // Emit the edited person
    } else if (this._building) {
      this.confirm.emit(this.editingBuilding); // Emit the edited building
    }
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

  private resetPerson() {
    this.editingPerson = { personId: 0, firstName: '', lastName: '', email: '', phoneNumber: '' };
  }

  private resetBuilding() {
    this.editingBuilding = { buildingId: 0, name: '', address: '', floors: [] };
  }
}