import { Component } from '@angular/core';
import { Room } from '../../types';
import { ActivatedRoute } from '@angular/router';
import { AccessService } from '../../services/access.service';

@Component({
  selector: 'app-accessible-rooms',
  standalone: true,
  imports: [],
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

  constructor(private route: ActivatedRoute, private accessService: AccessService) {}

  ngOnInit() {
    const personId = this.route.snapshot.params['id'];
    this.accessService.getAccessibleRooms(personId).subscribe((rooms: Room[]) => {
      this.rooms = rooms;
    });
  }
}
