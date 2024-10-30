import { Component } from '@angular/core';
import { Building, Floor, Person, Room } from '../../types';
import { ActivatedRoute } from '@angular/router';
import { AccessService } from '../../services/access.service';
import { CommonModule } from '@angular/common';
import { PersonService,  } from '../../services/person.service';
import { BuildingsService } from '../../services/buildings.service';
import { FloorsService } from '../../services/floors.service';

@Component({
  selector: 'app-accessible-rooms',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './accessible-rooms.component.html',
  styleUrl: './accessible-rooms.component.css',
  template: `
     <h3 *ngIf="person">Accessible Rooms for Person ID: {{ person.personId }}</h3>
    <ul>
      <li *ngFor="let room of rooms" class="room-item">
        Floor: {{ room.floor?.number }} - {{ room.floor?.description }} <br />
        Room: {{ room.number }} - {{ room.description }}
      </li>
    </ul>
    <div *ngIf="rooms.length === 0">No accessible rooms available.</div>
`
})
export class AccessibleRoomsComponent {
  rooms: Room[] = [];
  personId!: number;
  person: Person | null = null;

  constructor(
    private route: ActivatedRoute, 
    private accessService: AccessService,
    private personService: PersonService,
    private buildingsService: BuildingsService,
    private floorsService: FloorsService
  ) {}

  ngOnInit() {
    this.personId = +this.route.snapshot.params['id']; // Retrieve personId from route
    console.log('Retrieved personId:', this.personId);
    this.fetchPersonDetails(); // Fetch person details first
  }

  fetchPersonDetails() {
    this.personService.getPersonById(this.personId).subscribe(
      (person: Person) => {
          console.log('Fetched person:', person); // Log the person object
          this.person = person; 
          this.fetchAccessibleRooms();
      },
      (error) => {
          console.error('Error fetching person details:', error);
          this.person = null;
      }
  );
  }

  fetchAccessibleRooms() {
    this.accessService.getAccessibleRooms(this.personId).subscribe(
      (rooms: Room[]) => {
          this.rooms = rooms;

          // Fetch floor and building details for each room
          this.rooms.forEach(room => {
              this.fetchFloorDetails(room);
          });
      },
      (error) => {
          console.error('Error fetching rooms:', error);
      }
  );
}

fetchFloorDetails(room: Room) {
  if (room.floorId) {
    this.floorsService.getFloorsByBuildingId(room.floorId).subscribe(
        (floors: Floor[]) => {
            const floor = floors.find(f => f.floorId === room.floorId);
            if (floor) {
                room.floorDescription = floor.description; // Update room with floor description
                if (floor.building && floor.building.buildingId) {
                    this.buildingsService.getBuildingById(floor.building.buildingId).subscribe(
                        (building: Building) => {
                          room.buildingId = building.buildingId; 
                          room.buildingName = building.name; 
                            room.buildingAddress = building.address; 
                        },
                        (error) => {
                            console.error('Error fetching building:', error);
                        }
                    );
                }
            }
        },
        (error) => {
            console.error('Error fetching floor details:', error);
        }
    );
} else {
    console.warn('Room floorId is undefined:', room);
}
}
}
