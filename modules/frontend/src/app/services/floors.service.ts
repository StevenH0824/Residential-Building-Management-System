import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { Floor, PaginationParams } from '../types';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FloorsService {
  constructor(private apiService: ApiService) { }

  getBuildings = (url: string, params: PaginationParams): Observable<Floor> => {
    return this.apiService.get(url, {
      params,
      responseType:'json',
    });
  }
  
}
