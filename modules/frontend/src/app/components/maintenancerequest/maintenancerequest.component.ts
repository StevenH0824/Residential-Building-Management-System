import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgFor } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ButtonModule } from 'primeng/button';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { ToastModule } from 'primeng/toast';
import { ConfirmationService } from 'primeng/api';
import { RouterModule } from '@angular/router';
import { MaintenanceRequest, StatusType } from '../../types'; // Import your types

@Component({
  selector: 'app-maintenancerequest',
  standalone: true,
  imports: [
    RouterModule,
    FormsModule,
    ButtonModule,
    ConfirmPopupModule,
    ToastModule,
    NgFor,
  ],
  providers: [ConfirmationService],
  templateUrl: './maintenancerequest.component.html',
  styleUrls: ['./maintenancerequest.component.css'],
})
export class MaintenancerequestComponent implements OnInit {
  newMaintenanceRequest: MaintenanceRequest = {
    issue: '',
    status: 'PENDING',
    createdDate: new Date().toISOString(),
    personId: 0, // Set default or obtain from user context
    roomId: 0,   // Set default or obtain from user context
  };
  maintenanceRequests: MaintenanceRequest[] = [];
  total: number = 0;

  constructor(
    private confirmationService: ConfirmationService,
    private http: HttpClient
  ) { }

  ngOnInit(): void {
    this.fetchMaintenanceRequests(); // Fetch maintenance requests when the component initializes
  }

  fetchMaintenanceRequests(): void {
    this.http.get<MaintenanceRequest[]>('/api/maintenanceRequest').subscribe(requests => {
      this.maintenanceRequests = requests;
      this.total = requests.length; // Update total count based on fetched requests
    });
  }

  createMaintenanceRequest(): void {
    this.http.post<MaintenanceRequest>('/api/maintenanceRequest', this.newMaintenanceRequest).subscribe(response => {
      if (response) {
        this.maintenanceRequests.push(response); // Add new maintenance request to the list
        this.resetNewMaintenanceRequest(); // Reset form
      }
    });
  }

  deleteMaintenanceRequest(id: number): void {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete this maintenance request?',
      accept: () => {
        this.http.delete(`/api/maintenanceRequest/${id}`).subscribe(() => {
          this.maintenanceRequests = this.maintenanceRequests.filter(request => request.maintenanceRequestId !== id); // Remove request from array
        });
      },
    });
  }

  resetNewMaintenanceRequest(): void {
    this.newMaintenanceRequest = {
      issue: '',
      status: 'PENDING',
      createdDate: new Date().toISOString(),
      personId: 0, // Reset or set as needed
      roomId: 0,   // Reset or set as needed
    }; // Reset form fields
  }
}