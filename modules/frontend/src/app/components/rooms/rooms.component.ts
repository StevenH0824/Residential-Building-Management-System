import { Component } from '@angular/core';
import { Room } from '../../types';
import { RoomsService } from '../../services/rooms.service';

@Component({
  selector: 'app-rooms',
  standalone: true,
  imports: [],
  templateUrl: './rooms.component.html',
  styleUrl: './rooms.component.css'
})
export class RoomsComponent {
  rooms: Room[] = [];

  constructor(private roomsService: RoomsService) {}

  ngOnInit(): void {
    this.fetchRooms();
  }

  fetchRooms(): void {
    const url = 'http://localhost:8080/api/rooms/with-building-info'; 
    this.roomsService.getRoomsWithBuilding(url).subscribe(
        data => {
            this.rooms = data; 
            console.log(this.rooms);
        },
        error => {
            console.error('Error fetching rooms:', error);
        }
    );
  }
}
