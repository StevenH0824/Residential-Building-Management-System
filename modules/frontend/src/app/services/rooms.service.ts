import { Injectable } from '@angular/core';
import { Room } from '../types';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class RoomsService {
  private apiUrl = 'http://localhost:8080/api/rooms'; 

  

  constructor(private apiService: ApiService) { }
  
getRoomsByFloorId(floorId: number): Observable<Room[]> {
        return this.apiService.get<Room[]>(`${this.apiUrl}/by-floor/${floorId}`);
    }

  getRoomsWithBuilding(url: string): Observable<Room[]> {
    return this.apiService.get(url, { responseType: 'json' });
}
}
