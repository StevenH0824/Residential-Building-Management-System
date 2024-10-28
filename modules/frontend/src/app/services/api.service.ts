import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Options } from '../types';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(private httpClient: HttpClient) {}

  // Updated delete method to accept a generic type
  delete<T>(url: string, options: {} = {}): Observable<T> {
    return this.httpClient.delete<T>(url, options).pipe(
      catchError(this.handleError)
    );
  }

  // Updated put method to accept a generic type
  put<T>(url: string, body: any, options: {} = {}): Observable<T> {
    return this.httpClient.put<T>(url, body, { ...options, headers: this.getHeaders() }).pipe(
      catchError(this.handleError)
    );
  }
  // Updated post method to accept a generic type
  post<T>(url: string, body: any, options: {} = {}): Observable<T> {
    return this.httpClient.post<T>(url, body, { ...options, headers: this.getHeaders() }).pipe(
      catchError(this.handleError)
    );
  }
  // Generic get method
  get<T>(url: string, options: Options = {}): Observable<T> {
    return this.httpClient.get<T>(url, { ...options, headers: this.getHeaders() });
  }

  // Helper to get headers (if needed)
  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      // Add more headers here if needed
    });
  }

  // Error handling function
private handleError(error: any) {
  console.error('API Error:', error);
  return throwError(() => new Error('Something went wrong; please try again later.'));
}
}
