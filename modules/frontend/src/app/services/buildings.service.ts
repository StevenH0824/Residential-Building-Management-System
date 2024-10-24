import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
<<<<<<< HEAD
import { Building, PaginatedBuildingResponse, PaginationParams } from '../types';
=======
import { Building } from '../types';
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

  getBuildings(url: string): Observable<Building[]> {
      return this.apiService.get(url, {
          responseType: 'json',
      });
  }
>>>>>>> danaBranch
}
