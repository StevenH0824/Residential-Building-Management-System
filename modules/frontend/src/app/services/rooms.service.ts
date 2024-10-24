import { Injectable } from '@angular/core';
<<<<<<< HEAD
import { ApiService } from './api.service';
import { PaginationParams, Room } from '../types';
import { Observable } from 'rxjs';
=======
import { Room } from '../types';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
>>>>>>> danaBranch

@Injectable({
  providedIn: 'root'
})
export class RoomsService {
<<<<<<< HEAD

  constructor(private apiService: ApiService) { }

  getBuildings = (url: string, params: PaginationParams): Observable<Room> => {
    return this.apiService.get(url, {
      params,
      responseType:'json',
    });
=======
  constructor(private apiService: ApiService) { }

  getBuildings(url: string): Observable<Room[]> {
      return this.apiService.get(url, {
          responseType: 'json',
      });
>>>>>>> danaBranch
  }
}
