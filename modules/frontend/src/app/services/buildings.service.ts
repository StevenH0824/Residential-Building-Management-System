import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { Building } from '../types';

@Injectable({
  providedIn: 'root'
})
export class BuildingsService {
  constructor(private apiService: ApiService) { }

  getBuildings(url: string): Observable<Building[]> {
      return this.apiService.get(url, {
          responseType: 'json',
      });
  }
}
