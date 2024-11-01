import { Component } from '@angular/core';
import { FloorRequestDTO, Room, Building, Floor } from '../../types'; // Ensure this path is correct
import { FloorsService } from '../../services/floors.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup } from '@angular/forms';
import { BuildingsService } from '../../services/buildings.service';
import { RoomsService } from '../../services/rooms.service';

@Component({
    selector: 'app-floor-list',
    standalone: true,
    imports: [CommonModule, ReactiveFormsModule],
    templateUrl: './floor-list.component.html',
    styleUrls: ['./floor-list.component.css']
})
export class FloorListComponent {
    floors: FloorRequestDTO[] = [];
    rooms: { [floorId: number]: Room[] } = {};
    buildingId!: number; 
    buildingName!: string; 
    showRooms: { [floorId: number]: boolean } = {};
    floorForm: FormGroup;

    constructor(
        private route: ActivatedRoute, 
        private floorsService: FloorsService, 
        private roomService: RoomsService,
        private fb: FormBuilder,         
        private buildingService: BuildingsService
    ) {
        this.floorForm = this.fb.group({
            number: [''],
            description: [''],
        });
    }

    ngOnInit(): void {
        this.route.paramMap.subscribe(params => {
            this.buildingId = +params.get('buildingId')!;
            this.fetchBuildingName();
            this.fetchFloors();
        });
    }

    fetchBuildingName(): void {
        this.buildingService.getBuildingById(this.buildingId).subscribe(
            (building: Building) => {
                this.buildingName = building.name;
            },
            (error) => {
                console.error('Error fetching building name:', error);
            }
        );
    }

    fetchFloors(): void {
        this.floorsService.getFloorsByBuildingId(this.buildingId).subscribe(
            (floors: Floor[]) => {
                this.floors = floors.map(floor => this.convertToDTO(floor));
                this.floors.forEach(floor => {
                    this.showRooms[floor.floorId!] = false; // Initialize room visibility
                });
            },
            (error) => {
                console.error('Error fetching floors:', error);
            }
        );
    }

    toggleRooms(floorId: number): void {
        this.showRooms[floorId] = !this.showRooms[floorId];
    if (this.showRooms[floorId] && !this.rooms[floorId]) {
        this.roomService.getRoomsByFloorId(floorId).subscribe(
            (rooms: Room[]) => {
                this.rooms[floorId] = rooms.map(room => ({
                    roomId: room.roomId,
                    roomNumber: room.roomNumber,
                    roomDescription: room.roomDescription,
                    floorId: room.floorId,
                    floorDescription: room.floorDescription,
                    buildingId: room.buildingId,
                    buildingName: room.buildingName,
                    buildingAddress: room.buildingAddress
                }));
            },
            (error) => {
                console.error('Error fetching rooms:', error);
            }
        );
    }
    }

    addFloor(): void {
        const newFloor: FloorRequestDTO = {
            number: this.floorForm.value.number,
            description: this.floorForm.value.description,
            buildingId: this.buildingId,
            roomIds: [],
        };
        this.floorsService.addFloor(newFloor).subscribe(
            (floor) => {
                const newFloorDTO: FloorRequestDTO = {
                    floorId: floor.floorId,
                    number: floor.number,
                    description: floor.description,
                    buildingId: floor.buildingId,
                    roomIds: floor.roomIds || [],
                };
                this.floors.push(newFloorDTO);
                this.floorForm.reset();
            },
            (error) => {
                console.error('Error adding floor:', error);
            }
        );
    }

    deleteFloor(floorId: number): void {
        this.floorsService.deleteFloor(floorId).subscribe(
            () => {
                this.floors = this.floors.filter(floor => floor.floorId !== floorId);
                delete this.rooms[floorId]; // Clean up rooms for deleted floor
            },
            (error) => {
                console.error('Error deleting floor:', error);
            }
        );
    }

    updateFloor(floorId: number): void {
        const floorToUpdate = this.floors.find(floor => floor.floorId === floorId);
        if (!floorToUpdate) return;

        const updatedFloor: FloorRequestDTO = {
            floorId: floorId,
            number: this.floorForm.value.number,
            description: this.floorForm.value.description,
            buildingId: this.buildingId,
            roomIds: [],
        };
        this.floorsService.updateFloor(floorId, updatedFloor).subscribe(
            (floor) => {
                const updatedFloorDTO: FloorRequestDTO = {
                    floorId: floor.floorId,
                    number: floor.number,
                    description: floor.description,
                    buildingId: floor.buildingId,
                    roomIds: floor.roomIds || [],
                };
                const index = this.floors.findIndex(f => f.floorId === floorId);
                this.floors[index] = updatedFloorDTO;
                this.floorForm.reset();
            },
            (error) => {
                console.error('Error updating floor:', error);
            }
        );
    }

    private convertToDTO(floor: Floor): FloorRequestDTO {
        if (!floor.building || floor.building.buildingId === undefined) {
            throw new Error('Building ID is required');
        }
    
        return {
            floorId: floor.floorId,
            number: floor.number,
            description: floor.description,
            buildingId: floor.building.buildingId,
            roomIds: floor.roomIds?.map(room => room.roomId) || []
        };
    }
}