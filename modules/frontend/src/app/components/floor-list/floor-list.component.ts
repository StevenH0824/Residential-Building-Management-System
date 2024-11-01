import { Component } from '@angular/core';
import { FloorRequestDTO, Room, Building, Floor } from '../../types'; // Ensure this path is correct
import { FloorsService } from '../../services/floors.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup } from '@angular/forms';

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
    buildingId!: number; // Ensure this is always a number
    buildingName!: string;
    showRooms: { [floorId: number]: boolean } = {};
    floorForm: FormGroup;

    constructor(private route: ActivatedRoute, private floorsService: FloorsService, private fb: FormBuilder) {
        this.floorForm = this.fb.group({
            number: [''],
            description: [''],
        });
    }

    ngOnInit(): void {
        this.route.paramMap.subscribe(params => {
            this.buildingId = +params.get('buildingId')!; // Ensure buildingId is a number
            this.buildingName = params.get('name')!;
            this.fetchFloors();
        });
    }

    fetchFloors(): void {
        this.floorsService.getFloorsByBuildingId(this.buildingId).subscribe(
            (floors: Floor[]) => {
                this.floors = floors.map(floor => this.convertToDTO(floor));
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

    addFloor(): void {
        const newFloor: FloorRequestDTO = {
            number: this.floorForm.value.number,
            description: this.floorForm.value.description,
            buildingId: this.buildingId,
            roomIds: [], // Initialize if you have room IDs
        };
        this.floorsService.addFloor(newFloor).subscribe(
            (floor) => {
                // Convert Floor to FloorRequestDTO before pushing to the array
                const newFloorDTO: FloorRequestDTO = {
                    floorId: floor.floorId,
                    number: floor.number,
                    description: floor.description,
                    buildingId: floor.buildingId, // Ensure buildingId is accessed correctly
                    roomIds: floor.roomIds || [], // Ensure roomIds are included if available
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
            },
            (error) => {
                console.error('Error deleting floor:', error);
            }
        );
    }

    updateFloor(floorId: number): void {
        const updatedFloor: FloorRequestDTO = {
            floorId: floorId, // Include the floorId for the update
            number: this.floorForm.value.number,
            description: this.floorForm.value.description,
            buildingId: this.buildingId, // Include buildingId for consistency
            roomIds: [], // If you want to manage selected room IDs
        };
        this.floorsService.updateFloor(floorId, updatedFloor).subscribe(
            (floor) => {
                // Convert Floor to FloorRequestDTO before updating the array
                const updatedFloorDTO: FloorRequestDTO = {
                    floorId: floor.floorId,
                    number: floor.number,
                    description: floor.description,
                    buildingId: floor.buildingId,
                    roomIds: floor.roomIds || [],
                };
                const index = this.floors.findIndex(f => f.floorId === floorId);
                this.floors[index] = updatedFloorDTO; // Update with the DTO
                this.floorForm.reset();
            },
            (error) => {
                console.error('Error updating floor:', error);
            }
        );
    }

    private convertToDTO(floor: Floor): FloorRequestDTO {
        if (!floor.building || !floor.building.buildingId) {
            throw new Error('Building ID is required'); // or handle it as you see fit
        }

        return {
            floorId: floor.floorId, // Optional ID
            number: floor.number,
            description: floor.description,
            buildingId: floor.building.buildingId, // Get building ID from the building object
            roomIds: floor.roomIds?.map(room => room.roomId) // Map to extract room IDs
        };
    }
}