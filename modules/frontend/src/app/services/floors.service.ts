import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Floor, FloorRequestDTO, Room } from '../types';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FloorsService {
  private apiUrl = 'http://localhost:8080/api'; // Base URL for your API

  constructor(private apiService: ApiService) { }

  // Fetch all floors with pagination
  getFloors(url: string, pagination: { page: number; perPage: number; }): Observable<Floor[]> {
    return this.apiService.get<Floor[]>(url, {
      responseType: 'json',
    });
  }

  // Fetch rooms by floor ID
  getRoomsByFloorId(floorId: number): Observable<Room[]> {
    return this.apiService.get<Room[]>(`${this.apiUrl}/rooms/flo/${floorId}`, {
      responseType: 'json'
    });
  }

  // Fetch floors by building ID
  getFloorsByBuildingId(buildingId: number): Observable<Floor[]> {
    return this.apiService.get<Floor[]>(`${this.apiUrl}/floors/by-building?buildingId=${buildingId}`, {
      responseType: 'json'
    });
  }

  // Add a new floor
  addFloor(floor: FloorRequestDTO): Observable<FloorRequestDTO> {
    return this.apiService.post<FloorRequestDTO>(`${this.apiUrl}/floors`, floor, {
      responseType: 'json'
    });
  }

  // Delete a floor by ID
  deleteFloor(floorId: number): Observable<void> {
    return this.apiService.delete<void>(`${this.apiUrl}/floors/${floorId}`, {
      responseType: 'json'
    });
  }

  // Update an existing floor
  updateFloor(floorId: number, floor: FloorRequestDTO): Observable<FloorRequestDTO> {
    return this.apiService.put<FloorRequestDTO>(`${this.apiUrl}/floors/${floorId}`, floor, {
      responseType: 'json'
    });
  }
}