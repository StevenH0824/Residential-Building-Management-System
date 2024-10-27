import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Building } from '../../types';
import { CommonModule } from '@angular/common';
import { DialogModule } from 'primeng/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';

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
  styleUrl: './edit-popup.component.css'
})
export class EditPopupComponent {
  @Input() display: boolean = false;
  @Input() header!: string;
  @Output() displayChange = new EventEmitter<boolean>();

  // Store the original building and a temporary copy for editing
  private _building!: Building; // Store the input building

  @Input()
  set building(value: Building) {
    this._building = value;
    this.editingBuilding = { ...value }; // Clone to prevent direct modification
  }

  @Output() confirm = new EventEmitter<Building>();

  editingBuilding: Building = { buildingId: 0, name: '', address: '', floors: [] };

  onConfirm() {
    this.confirm.emit(this.editingBuilding); // Emit the modified object
    this.display = false;
    this.displayChange.emit(this.display);
  }

  onCancel() {
    this.display = false;
    this.displayChange.emit(this.display);
  }
}
