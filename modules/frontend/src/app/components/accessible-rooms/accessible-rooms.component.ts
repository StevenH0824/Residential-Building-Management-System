import { Component } from '@angular/core';
import { Room } from '../../types';
import { ActivatedRoute } from '@angular/router';
import { AccessService } from '../../services/access.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-accessible-rooms',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './accessible-rooms.component.html',
  styleUrl: './accessible-rooms.component.css',
  template: `
    <h3>Accessible Rooms</h3>
    <ul>
      <li *ngFor="let room of rooms">
        {{ room.number }} - {{ room.description }}
      </li>
    </ul>
    `
})
export class AccessibleRoomsComponent {
  rooms: Room[] = [];
  personId!: number;
  constructor(private route: ActivatedRoute, private accessService: AccessService) {}

  ngOnInit() {
    this.personId = +this.route.snapshot.params['id'];
    this.accessService.getAccessibleRooms(this.personId).subscribe(
      (rooms: Room[]) => {
        this.rooms = rooms;
      },
      (error) => {
        console.error('Error fetching rooms:', error);
        // Optionally show an error message to the user
      }
    );
  }
}
