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


@Component({
  selector: 'app-person',
  standalone: true,
  imports: [RouterModule, FormsModule, ButtonModule, ConfirmPopupModule, ToastModule, TruncateNamePipe, NgFor],
  templateUrl: './person.component.html',
  styleUrl: './person.component.css'
})
export class PersonComponent {
  newPerson: Person = { firstName: '', lastName: '', email: '', phoneNumber: '', floorId: 0 }; // Initialize newPerson
  persons: Person[] = [];
  PersonId!: number;
  total: number = 0; // Total number of Persons
  page: number = 1; // Current page for pagination
  perPage: number = 10; // Items per page
  confirmationService: any;


  constructor(private route: ActivatedRoute, private PersonsService: PersonService,  private http: HttpClient) {}

  @Input() person!: Person;
  @ViewChild('deleteButton') deleteButton: any;

  @Output() edit: EventEmitter<Person> = new EventEmitter<Person>();
  @Output() delete: EventEmitter<Person> = new EventEmitter<Person>();
  

  editPerson() {
    this.edit.emit(this.person);
  }

  confirmDelete() {
    this.confirmationService.confirm({
      target: this.deleteButton.nativeElement,
      message: 'Are you sure that you want to delete this Person?',
      accept: () => {
        this.deletePerson();
      },
    })
  };

  deletePerson() {
    this.delete.emit(this.person);
  }

  addPerson() {
    this.http
      .post<Person>('http://localhost:8080/api/persons', this.newPerson) // Updated URL
      .subscribe({
        next: (person) => {
          this.persons.push(person); // Add the new Person to the list
          this.total += 1; // Increment total Persons count
          this.resetNewPerson(); // Reset the newPerson after adding
        },
        error: (error) => console.log('Error adding Person:', error),
      });
  }

  resetNewPerson() {
    this.newPerson = { firstName: '', lastName: '', email: '', phoneNumber: '', floorId: 0 };
  }

  fetchPersons(page: number, perPage: number) {
    this.http.get<{ items: Person[], total: number, page: number, perPage: number }>(
      `http://localhost:8080/api/persons?page=${page}&perPage=${perPage}`
    ).subscribe(response => {
      this.persons = response.items;
      this.total = response.total;
      this.page = response.page;
      this.perPage = response.perPage;
    });
  }

  ngOnInit() {
    this.fetchPersons(this.page, this.perPage); 
  }
}
