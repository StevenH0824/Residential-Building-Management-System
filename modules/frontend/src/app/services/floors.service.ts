
import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Floor } from '../types';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FloorsService {
  constructor(private apiService: ApiService) { }

  getBuildings(url: string): Observable<Floor[]> {
      return this.apiService.get(url, {
          responseType: 'json',
      });
  }
}
