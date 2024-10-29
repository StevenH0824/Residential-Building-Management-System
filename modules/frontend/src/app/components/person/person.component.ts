import { ActivatedRoute, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgFor } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { ToastModule } from 'primeng/toast';
import { TruncateNamePipe } from '../../pipes/truncate-name.pipe';
import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { Building, EditEntity, Person } from '../../types';
import { PersonService } from '../../services/person.service';
import { HttpClient } from '@angular/common/http';
import { ConfirmationService } from 'primeng/api';
import { CommonModule } from '@angular/common';
import { EditPopupComponent } from '../edit-popup/edit-popup.component';
import { DialogModule } from 'primeng/dialog';

@Component({
  selector: 'app-person',
  standalone: true,
  imports: [RouterModule, FormsModule, ButtonModule, ConfirmPopupModule, ToastModule, TruncateNamePipe, NgFor, CommonModule, EditPopupComponent,DialogModule],
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css'],
  providers: [ConfirmationService]
})
export class PersonComponent {
  newPerson: Partial<Person> = {}; // Initialize as an empty object
  validationErrors: string[] = []; // Array to hold validation messages
  persons: Person[] = [];
  filteredPersons: Person[] = [];
  selectedPerson: Person | null = null; // Holds the currently selected person for editing
  searchTerm: string = '';
  total: number = 0;
  page: number = 1;
  perPage: number = 10;

  @Input() person!: Person;
  @ViewChild('deleteButton') deleteButton: any;

  @Output() edit: EventEmitter<Person> = new EventEmitter<Person>();
  @Output() delete: EventEmitter<Person> = new EventEmitter<Person>();
  editPopup: { display: boolean } = { display: false }; // Initialize editPopup

  constructor(private route: ActivatedRoute, private personService: PersonService, private http: HttpClient, private confirmationService: ConfirmationService) { }

  startEdit(person: Person) {
    this.selectedPerson = person; // Set the selected person for editing
    this.editPopup.display = true; // Open the edit popup
  }

  // Updated savePerson to handle both Person and Building
  savePerson(editedEntity: EditEntity) {
    if ((editedEntity as Person).personId !== undefined) {
      // Handle as Person
      this.personService.editPerson(editedEntity as Person).subscribe({
        next: (updatedPerson) => {
          const index = this.persons.findIndex(p => p.personId === updatedPerson.personId);
          if (index !== -1) {
            this.persons[index] = updatedPerson;
            this.filteredPersons[index] = updatedPerson;
          }
          this.resetSelectedPerson();
        },
        error: (error) => console.error('Error updating Person:', error),
      });
    } else {
      console.error('Unexpected entity type:', editedEntity);
    }
  }

  resetSelectedPerson() {
    this.selectedPerson = null; // Reset to null when not editing
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
    this.personService.deletePerson(person).subscribe({
      next: () => {
        this.persons = this.persons.filter(p => p.personId !== person.personId);
        this.filteredPersons = this.filteredPersons.filter(p => p.personId !== person.personId);
        this.total--;
      },
      error: (error) => console.error('Error deleting Person:', error),
    });
  }

  addPerson() {
    this.validationErrors = []; // Reset errors before validation

    // Validate inputs
    if (!this.newPerson.firstName) {
      this.validationErrors.push('First Name is required.');
    }
    if (!this.newPerson.lastName) {
      this.validationErrors.push('Last Name is required.');
    }
    if (!this.newPerson.phoneNumber) {
      this.validationErrors.push('Phone Number is required.');
    }
    if (!this.newPerson.email || !this.validateEmail(this.newPerson.email)) {
      this.validationErrors.push('A valid Email is required.');
    }

    // If there are validation errors, don't proceed
    if (this.validationErrors.length > 0) {
      return;
    }

    this.http.post<Person>('http://localhost:8080/api/persons', this.newPerson).subscribe({
      next: (person) => {
        this.persons.push(person);
        this.total++;
        this.resetNewPerson();
      },
      error: (error) => console.error('Error adding Person:', error),
    });
  }

  validateEmail(email: string): boolean {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // Basic email regex
    return re.test(email);
  }

  resetNewPerson() {
    this.newPerson = { personId: 0, firstName: '', lastName: '', email: '', phoneNumber: '' };
  }

  fetchPersons(page: number, perPage: number) {
    this.http.get<Person[]>(`http://localhost:8080/api/persons?page=${page}&perPage=${perPage}`).subscribe({
      next: (response) => {
        if (Array.isArray(response)) {
          this.persons = response; // Directly assign the array to persons
          this.filteredPersons = response;
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

  searchPersons() {
    if (!this.searchTerm) {
      this.filteredPersons = this.persons; // If searchTerm is empty, show all persons
      return;
    }
    const lowerCaseTerm = this.searchTerm.toLowerCase();
    this.filteredPersons = this.persons.filter(person => {
      return (
        person.personId?.toString().includes(lowerCaseTerm) || // Search by ID
        `${person.firstName} ${person.lastName}`.toLowerCase().includes(lowerCaseTerm) || // Search by full name
        person.phoneNumber.includes(lowerCaseTerm) || // Search by phone number
        person.lastName.toLowerCase().includes(lowerCaseTerm) || // Search by last name
        person.email.toLowerCase().includes(lowerCaseTerm) // Search by email
      );
    });
  }

  ngOnInit() {
    this.fetchPersons(this.page, this.perPage);
  }
}