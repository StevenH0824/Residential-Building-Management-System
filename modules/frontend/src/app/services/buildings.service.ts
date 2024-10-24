import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
<<<<<<< HEAD
<<<<<<< HEAD
import { Building, PaginatedBuildingResponse, PaginationParams } from '../types';
=======
import { Building } from '../types';
>>>>>>> danaBranch
=======
import { Building, Buildings } from '../types';
>>>>>>> danaBranch

@Injectable({
  providedIn: 'root'
})
export class BuildingsService {
<<<<<<< HEAD

  constructor(private apiService: ApiService) { }

  getBuildings(url: string): Observable<Building[]> {
    return this.apiService.get(url, {
        responseType: 'json',
    });
}
=======
  constructor(private apiService: ApiService) { }

  getBuildings(url: string, p0: { page: number; perPage: number; }): Observable<Building[]> {
      return this.apiService.get(url, {
          responseType: 'json',
      });
  }
<<<<<<< HEAD
>>>>>>> danaBranch
=======

  addBuilding = (url: string, body: any): Observable<any> => {
    return this.apiService.post(url, body, {});
  }

  editBuilding = (url: string, body: any): Observable<any> => {
    return this.apiService.put(url, body, {});
  }

  deleteBuilding = (url: string): Observable<any> => {
    return this.apiService.delete(url, {});
  }
>>>>>>> danaBranch
}
