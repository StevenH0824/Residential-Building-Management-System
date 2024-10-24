import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Options } from '../types';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  put(url: string, body: any, arg2: {}): Observable<any> {
    throw new Error('Method not implemented.');
  }
  delete(url: string, arg1: {}): Observable<any> {
    throw new Error('Method not implemented.');
  }
  post(url: string, body: any, arg2: {}): Observable<any> {
    throw new Error('Method not implemented.');
  }
  constructor(
    private httpClient: HttpClient) { }

   get<T>(url: string, options: Options): Observable<T>{
    return this.httpClient.get<T>(url, options) as Observable<T>;
   }
}
