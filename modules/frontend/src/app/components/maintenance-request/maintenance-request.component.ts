import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule, NgFor } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { ToastModule } from 'primeng/toast';
import { ConfirmationService } from 'primeng/api';
import { RouterModule } from '@angular/router';
import { MaintenanceRequest, MaintenanceResponse, StatusType } from '../../types'; // Import your types
import { MaintenanceRequestsService } from '../../services/maintenance-requests.service';
import { EditPopupComponent } from '../edit-popup/edit-popup.component';

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
    CommonModule,
    EditPopupComponent 
  ],
  providers: [ConfirmationService],
  templateUrl: './maintenance-request.component.html',
  styleUrls: ['./maintenance-request.component.css'],
})
export class MaintenancerequestComponent implements OnInit {
  newMaintenanceRequest: MaintenanceRequest = {
    issue: '',
    status: 'PENDING' as StatusType,
    createdDate: new Date(),
    personId: 0,
    roomId: 0,
  };
  requests: MaintenanceResponse[] = [];
  selectedRequest?: MaintenanceRequest;
  editPopup = { display: false };
  searchTerm: string = '';

  constructor(
    private confirmationService: ConfirmationService,
    private maintenanceRequestsService: MaintenanceRequestsService
  ) {}

  ngOnInit(): void {
    this.fetchMaintenanceRequests();
  }

  fetchMaintenanceRequests(): void {
    this.maintenanceRequestsService.getMaintenanceRequests().subscribe(
      data => {
        this.requests = data;
      },
      error => {
        console.error('Error fetching maintenance requests', error);
      }
    );
  }

  createMaintenanceRequest(): void {
    this.newMaintenanceRequest.createdDate = new Date(); 
    this.newMaintenanceRequest.endDate = undefined; 
  
    this.maintenanceRequestsService.createMaintenanceRequest(this.newMaintenanceRequest).subscribe(
      response => {
        if (response) {
          this.requests.push(response);
          this.resetNewMaintenanceRequest();
        }
      },
      error => {
        console.error('Error creating maintenance request:', error);
      }
    );
  }

  startEdit(request: MaintenanceRequest): void {
    this.selectedRequest = request;
    this.editPopup.display = true;
  }

  saveMaintenanceRequest(maintenanceRequest: MaintenanceRequest): void {
    if (maintenanceRequest) { 
      this.maintenanceRequestsService.updateMaintenanceRequest(maintenanceRequest).subscribe({
        next: (updatedRequest) => {
          const index = this.requests.findIndex(req => req.maintenanceRequestId === updatedRequest.maintenanceRequestId);
          if (index !== -1) {
            this.requests[index] = updatedRequest;
          }
          this.resetSelectedMaintenanceRequest();
        },
        error: (error) => console.error('Error saving maintenance request:', error),
      });
    } else {
      console.error('No maintenance request provided');
    }
  }

  deleteMaintenanceRequest(id: number): void {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete this maintenance request?',
      accept: () => {
        this.maintenanceRequestsService.deleteMaintenanceRequest(id).subscribe({
          next: () => {
            this.requests = this.requests.filter(request => request.maintenanceRequestId !== id);
          },
          error: error => {
            console.error('Error deleting maintenance request:', error);
          }
        });
      },
    });
  }

  resetNewMaintenanceRequest(): void {
    this.newMaintenanceRequest = {
    issue: '',
    status: 'PENDING' as StatusType,
    createdDate: new Date(),
    personId: 0,
    roomId: 0,
    endDate: null
    };
  }

  resetSelectedMaintenanceRequest(): void {
    this.selectedRequest = undefined;
    this.editPopup.display = false;
  }

  searchMaintenanceRequests(): void {
  }
}