import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { Building } from '../types';

@Injectable({
  providedIn: 'root'
})
export class BuildingsService {
  constructor(private apiService: ApiService) {}

  getBuildings(url: string, pagination: { page: number; perPage: number; }): Observable<Building[]> {
    return this.apiService.get<Building[]>(url, {
      responseType: 'json',
    });
  }

  getLatestBuildingId(): Observable<number> {
    // Call the appropriate API endpoint to get the latest building ID
    return this.apiService.get<number>('http://localhost:8080/api/buildings/latestId', {
      responseType: 'json', // Ensure the response type is set correctly
    });
  }

  addBuilding(url: string, body: Building): Observable<Building> {
    return this.apiService.post<Building>(url, body, {});
  }

  editBuilding(url: string, body: Building): Observable<Building> {
    return this.apiService.put<Building>(url, body, {});
  }

  deleteBuilding(url: string): Observable<any> {
    return this.apiService.delete(url, {});
  }
}
