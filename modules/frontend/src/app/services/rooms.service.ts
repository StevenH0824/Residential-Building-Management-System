import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { PaginationParams, Room } from '../types';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoomsService {

  constructor(private apiService: ApiService) { }

  getBuildings = (url: string, params: PaginationParams): Observable<Room> => {
    return this.apiService.get(url, {
      params,
      responseType:'json',
    });
  }
}
