import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Person } from '../types';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  constructor(private apiService: ApiService) { }

  getPersons(url: string, pagination: { page: number; perPage: number; }): Observable<Person[]> {
    return this.apiService.get<Person[]>(url, {
      responseType: 'json',
    });
  }


  getPersonsByPersonId(personId: number): Observable<Person[]> {
    return this.apiService.get<Person[]>(`http://localhost:8080/api/persons/by-person?personId=${personId}`, {
        responseType: 'json'
    });
  }

  addPerson(url: string, body: Person): Observable<Person> {
    return this.apiService.post<Person>(url, body, {});
  }

  editPerson(url: string, body: Person): Observable<Person> {
    return this.apiService.put<Person>(url, body, {});
  }

  deletePerson(url: string): Observable<any> {
    return this.apiService.delete(url, {});
  }
}
