
import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Floor, Room } from '../types';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FloorsService {
  constructor(private apiService: ApiService) { }

  getFloors(url: string, pagination: { page: number; perPage: number; }): Observable<Floor[]> {
    return this.apiService.get<Floor[]>(url, {
      responseType: 'json',
    });
  }

  getRoomsByFloorId(floorId: number): Observable<Room[]> {
    return this.apiService.get<Room[]>(`http://localhost:8080/api/rooms/by-floor?floorId=${floorId}`, {
      responseType: 'json'
    });
  }
  getFloorsByBuildingId(buildingId: number): Observable<Floor[]> {
    return this.apiService.get<Floor[]>(`http://localhost:8080/api/floors/by-building?buildingId=${buildingId}`, {
      responseType: 'json'
    });
  }

  addFloor(url: string, body: Floor): Observable<Floor> {
    return this.apiService.post<Floor>(url, body, {});
  }

  editFloor(url: string, body: Floor): Observable<Floor> {
    return this.apiService.put<Floor>(url, body, {});
  }

  deleteFloor(url: string): Observable<any> {
    return this.apiService.delete(url, {});
  }
}

