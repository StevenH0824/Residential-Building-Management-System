import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Room } from '../types';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class AccessService {
  constructor(private apiService: ApiService) {}

  getAccessibleRooms(personId: number): Observable<Room[]> {
    return this.apiService.get<Room[]>(`http://localhost:8080/api/persons/${personId}/spaces`);
  }

}
