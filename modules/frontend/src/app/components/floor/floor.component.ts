import { Component, Input } from '@angular/core';
import { Floor } from '../../types';

@Component({
  selector: 'app-floor',
  standalone: true,
  imports: [],
  templateUrl: './floor.component.html',
  styleUrl: './floor.component.css'
})
export class FloorComponent {
  @Input() floor!: Floor;

}
