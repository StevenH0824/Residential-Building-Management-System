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


  getPersonById(personId: number): Observable<Person> {
    return this.apiService.get<Person>(`http://localhost:8080/api/persons/by-person?personId=${personId}`, {
        responseType: 'json'
    });
  }

  addPerson(url: string, body: Person): Observable<Person> {
    return this.apiService.post<Person>(url, body, {});
  }

  editPerson(person: Person): Observable<Person> {
    const url = `http://localhost:8080/api/persons/${person.personId}`; 
    return this.apiService.put<Person>(url, person, {});
  }

  deletePerson(person: Person): Observable<any> {
    const url = `http://localhost:8080/api/persons/${person.personId}`; 
    return this.apiService.delete(url, {});
  }
}
