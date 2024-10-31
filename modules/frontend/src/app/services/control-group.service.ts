import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Room } from '../types';

@Injectable({
  providedIn: 'root'
})
export class ControlGroupService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private apiService: ApiService) {}

  getControlGroupsForPerson(personId: number): Observable<any[]> {
    return this.apiService.get<any[]>(`${this.baseUrl}/control-group-persons?personId=${personId}`);
  }

  getRoomsForControlGroups(groupIds: number[]): Observable<Room[]> {
    const ids = groupIds.join(','); 
    return this.apiService.get<any[]>(`${this.baseUrl}/rooms?controlGroupIds=${ids}`);
  }
}
