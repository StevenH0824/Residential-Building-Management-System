import { Component } from '@angular/core';
import { Floor, Room } from '../../types';
import { FloorsService } from '../../services/floors.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-floor-list',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './floor-list.component.html',
    styleUrl: './floor-list.component.css'
})
export class FloorListComponent {
    floors: Floor[] = [];
    rooms: { [floorId: number]: Room[] } = {};
    buildingId!: number;
    showRooms: { [floorId: number]: boolean } = {};

    constructor(private route: ActivatedRoute, private floorsService: FloorsService) { }

    ngOnInit(): void {
        this.route.paramMap.subscribe(params => {
            this.buildingId = +params.get('buildingId')!;
            this.fetchFloors();
        });
    }

    fetchFloors(): void {
        this.floorsService.getFloorsByBuildingId(this.buildingId).subscribe(
            (floors) => {
                this.floors = floors;
                this.floors.forEach(floor => this.showRooms[floor.floorId!] = false);
            },
            (error) => {
                console.error('Error fetching floors:', error);
            }
        );
    }

    toggleRooms(floorId: number): void {
        this.showRooms[floorId] = !this.showRooms[floorId];
        if (this.showRooms[floorId] && !this.rooms[floorId]) {
            this.floorsService.getRoomsByFloorId(floorId).subscribe(
                (rooms: Room[]) => {
                    this.rooms[floorId] = rooms;
                },
                (error) => {
                    console.error('Error fetching rooms:', error);
                }
            );
        }
    }
}

