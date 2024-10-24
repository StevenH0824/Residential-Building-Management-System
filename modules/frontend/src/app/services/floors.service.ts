<<<<<<< HEAD
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { Floor, PaginationParams } from '../types';
import { Injectable } from '@angular/core';
=======
import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Floor } from '../types';
import { Observable } from 'rxjs';
>>>>>>> danaBranch

@Injectable({
  providedIn: 'root'
})
export class FloorsService {
  constructor(private apiService: ApiService) { }

<<<<<<< HEAD
  getBuildings = (url: string, params: PaginationParams): Observable<Floor> => {
    return this.apiService.get(url, {
      params,
      responseType:'json',
    });
  }
  
=======
  getBuildings(url: string): Observable<Floor[]> {
      return this.apiService.get(url, {
          responseType: 'json',
      });
  }
>>>>>>> danaBranch
}
