import { ActivatedRoute, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgFor } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { ToastModule } from 'primeng/toast';
import { TruncateNamePipe } from '../../pipes/truncate-name.pipe';
import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { Person } from '../../types';
import { PersonService } from '../../services/person.service';
import { HttpClient } from '@angular/common/http';
import { ConfirmationService } from 'primeng/api';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-person',
  standalone: true,
  imports: [RouterModule, FormsModule, ButtonModule, ConfirmPopupModule, ToastModule, TruncateNamePipe, NgFor, CommonModule],
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css'],
  providers: [ConfirmationService]
})
export class PersonComponent {
  newPerson: Person = { personId: 0, firstName: '', lastName: '', email: '', phoneNumber: '' }; // Initialize newPerson
  persons: Person[] = [];
  total: number = 0;
  page: number = 1;
  perPage: number = 10;

  @Input() person!: Person;
  @ViewChild('deleteButton') deleteButton: any;

  @Output() edit: EventEmitter<Person> = new EventEmitter<Person>();
  @Output() delete: EventEmitter<Person> = new EventEmitter<Person>();

  constructor(private route: ActivatedRoute, private personService: PersonService, private http: HttpClient, private confirmationService: ConfirmationService) { }

  editPerson(person: Person) {
    this.edit.emit(person);
  }

  confirmDelete(person: Person) {
    this.person = person; // Store the person to be deleted
    this.confirmationService.confirm({
      target: this.deleteButton.nativeElement,
      message: 'Are you sure that you want to delete this Person?',
      accept: () => {
        this.deletePerson(person);
      },
    });
  }

  deletePerson(person: Person) {
    this.delete.emit(this.person);
  }

  addPerson() {
    this.http
      .post<Person>('http://localhost:8080/api/persons', this.newPerson)
      .subscribe({
        next: (person) => {
          this.persons.push(person);
          this.total++;
          this.resetNewPerson();
        },
        error: (error) => console.error('Error adding Person:', error),
      });
  }

  resetNewPerson() {
    this.newPerson = { personId: 0, firstName: '', lastName: '', email: '', phoneNumber: '' };
  }

  fetchPersons(page: number, perPage: number) {
    this.http.get<Person[]>(`http://localhost:8080/api/persons?page=${page}&perPage=${perPage}`)
      .subscribe({
        next: (response) => {
          // Check if response is an array
          if (Array.isArray(response)) {
            this.persons = response; // Directly assign the array to persons
            this.total = response.length; // Set total count based on the array length
          } else {
            console.error('Unexpected response structure:', response);
          }
        },
        error: (error) => {
          console.error('Error fetching persons:', error); // Log any errors
        }
      });
  }
  

  ngOnInit() {
    this.fetchPersons(this.page, this.perPage);
  }
}