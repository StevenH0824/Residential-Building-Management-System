import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Building } from '../../types';
import { CommonModule } from '@angular/common';
import { DialogModule } from 'primeng/dialog';
import { FormsModule,  ReactiveFormsModule} from '@angular/forms';
import { RatingModule } from 'primeng/rating';
import { ButtonModule } from 'primeng/button';


@Component({
  selector: 'app-edit-popup',
  standalone: true,
  imports: [DialogModule,
    CommonModule,
    FormsModule,
    RatingModule,
    ButtonModule,
    ReactiveFormsModule,],
  templateUrl: './edit-popup.component.html',
  styleUrl: './edit-popup.component.css'
})

export class EditPopupComponent {
  @Input() display: boolean = false;
  @Input() header!: string;
  @Output() displayChange = new EventEmitter<boolean>();

  @Input() building: Building = {
    buildingId: 0,
    name: '',
    address: '',
    floors: []
  };

  @Output() confirm = new EventEmitter<Building>();
  
  onConfirm() {
    this.confirm.emit(this.building);
    this.display = false;
    this.displayChange.emit(this.display);
  }

  onCancel() {
    this.display = false;
    this.displayChange.emit(this.display);
  }
}
