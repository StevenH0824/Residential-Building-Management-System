import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Options } from '../types';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(private httpClient: HttpClient) {}

  // Updated delete method to accept a generic type
  delete<T>(url: string, options: {} = {}): Observable<T> {
    return this.httpClient.delete<T>(url, options);
  }

  // Updated put method to accept a generic type
  put<T>(url: string, body: any, options: {} = {}): Observable<T> {
    return this.httpClient.put<T>(url, body, { ...options, headers: this.getHeaders() });
  }

  // Updated post method to accept a generic type
  post<T>(url: string, body: any, options: {} = {}): Observable<T> {
    return this.httpClient.post<T>(url, body, { ...options, headers: this.getHeaders() });
  }

  // Generic get method
  get<T>(url: string, options: Options): Observable<T> {
    return this.httpClient.get<T>(url, options);
  }

  // Helper to get headers (if needed)
  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      // Add more headers here if needed
    });
  }
}
