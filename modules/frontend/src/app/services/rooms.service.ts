import { Injectable } from '@angular/core';
import { Room } from '../types';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class RoomsService {

  constructor(private apiService: ApiService) { }

  getBuildings(url: string): Observable<Room[]> {
      return this.apiService.get(url, {
          responseType: 'json',
      });
  }
}
