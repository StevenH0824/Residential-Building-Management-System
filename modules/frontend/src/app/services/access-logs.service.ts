import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { AccessLog, AccessLogResponseDTO } from '../types';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccessLogsService {
  private apiUrl = 'http://localhost:8080/api/access-logs';

  constructor(private apiService: ApiService) {}

  createAccessLog(newLog: AccessLog): Observable<AccessLogResponseDTO> {
    return this.apiService.post<AccessLogResponseDTO>(`${this.apiUrl}/log`, newLog);
  }

  getAllAccessLogs(): Observable<AccessLogResponseDTO[]> {
    return this.apiService.get<AccessLogResponseDTO[]>(this.apiUrl);
  }

  deleteAccessLog(id: number): Observable<void> {
    return this.apiService.delete<void>(`${this.apiUrl}/${id}`);
  }
}
