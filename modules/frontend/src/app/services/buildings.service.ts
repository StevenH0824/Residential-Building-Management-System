import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { Building, Buildings } from '../types';

@Injectable({
  providedIn: 'root'
})
export class BuildingsService {
  constructor(private apiService: ApiService) { }

  getBuildings(url: string, p0: { page: number; perPage: number; }): Observable<Building[]> {
      return this.apiService.get(url, {
          responseType: 'json',
      });
  }

  addBuilding = (url: string, body: any): Observable<any> => {
    return this.apiService.post(url, body, {});
  }

  editBuilding = (url: string, body: any): Observable<any> => {
    return this.apiService.put(url, body, {});
  }

  deleteBuilding = (url: string): Observable<any> => {
    return this.apiService.delete(url, {});
  }
}
