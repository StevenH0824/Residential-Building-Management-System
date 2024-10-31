import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { MaintenanceRequest, MaintenanceResponse } from '../types';

@Injectable({
  providedIn: 'root'
})
export class MaintenanceRequestsService {
  private apiUrl = 'http://localhost:8080/api/maintenance-requests'; 

  constructor(private apiService: ApiService) { }

  createMaintenanceRequest(request: MaintenanceRequest): Observable<MaintenanceResponse> {
    return this.apiService.post<MaintenanceResponse>(this.apiUrl, request); 
}

  getMaintenanceRequests(): Observable<MaintenanceResponse[]> {
    return this.apiService.get<MaintenanceResponse[]>(this.apiUrl);
  }

  updateMaintenanceRequest(request: MaintenanceRequest): Observable<MaintenanceResponse> {
    return this.apiService.put<MaintenanceResponse>(`${this.apiUrl}/${request.maintenanceRequestId}`, request);
  }

  deleteMaintenanceRequest(id: number): Observable<void> {
    return this.apiService.delete<void>(`${this.apiUrl}/${id}`);
  }
}