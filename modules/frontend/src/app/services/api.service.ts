import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
<<<<<<< HEAD
import { Observable } from 'rxjs';
import { Options } from '../types';
=======
import { Options } from '../types';
import { Observable } from 'rxjs';
>>>>>>> danaBranch

@Injectable({
  providedIn: 'root'
})
export class ApiService {
<<<<<<< HEAD

  constructor(
    private httpClient: HttpClient

  ) { }

  get<T>(url: string, options: Options): Observable<T> {
    return this.httpClient.get<T>(url, options) as Observable<T>;
  }
=======
  constructor(
    private httpClient: HttpClient) { }

   get<T>(url: string, options: Options): Observable<T>{
    return this.httpClient.get<T>(url, options) as Observable<T>;
   }
>>>>>>> danaBranch
}
