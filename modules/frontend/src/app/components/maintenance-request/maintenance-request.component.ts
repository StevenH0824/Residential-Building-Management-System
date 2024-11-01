import { Component, NO_ERRORS_SCHEMA, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule, NgFor } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { ToastModule } from 'primeng/toast';
import { ConfirmationService } from 'primeng/api';
import { RouterModule } from '@angular/router';
import { DialogModule } from 'primeng/dialog';

import {
  Building,
  EditEntity,
  MaintenanceRequest,
  MaintenanceResponse,
  Person,
  StatusType,
} from '../../types';
import { MaintenanceRequestsService } from '../../services/maintenance-requests.service';
import { EditPopupComponent } from '../edit-popup/edit-popup.component';

@Component({
  selector: 'app-maintenancerequest',
  standalone: true,
  imports: [
    DialogModule,
    RouterModule,
    FormsModule,
    ButtonModule,
    ConfirmPopupModule,
    ToastModule,
    NgFor,
    CommonModule,
    EditPopupComponent,
  ],
  schemas: [NO_ERRORS_SCHEMA], 
  providers: [ConfirmationService],
  templateUrl: './maintenance-request.component.html',
  styleUrls: ['./maintenance-request.component.css'],
})
export class MaintenancerequestComponent implements OnInit {
  requests: MaintenanceResponse[] = [];
  filteredRequests: MaintenanceRequest[] = []; 

  statusTypes = StatusType;
  selectedRequest: MaintenanceRequest | null = null;
  editPopup = { display: false };

  searchTerm: string = '';
  issueInput: string = '';
  statusInput: StatusType = StatusType.PENDING;
  selectedPersonId: number | null = null;
  selectedRoomId: number | null = null;
  // selectedMaintenanceRequest: MaintenanceRequest | null = null;

  newMaintenanceRequest: MaintenanceRequest = {
    createdDate: new Date(),
    endDate: null,
    issue: '',
    status: StatusType.PENDING,
    personId: 0,
    roomId: 0,
  };

  constructor(
    private confirmationService: ConfirmationService,
    private maintenanceRequestsService: MaintenanceRequestsService
  ) {}

  ngOnInit(): void {
    this.fetchMaintenanceRequests();
  }

  fetchMaintenanceRequests(): void {
    this.maintenanceRequestsService.getMaintenanceRequests().subscribe(
      (data) => {
        this.requests = data;
         this.filteredRequests = data; 
        console.log('Fetched requests:', data);
      },
      (error) => {
        console.error('Error fetching maintenance requests', error);
      }
    );
  }

  createMaintenanceRequest(): void {
    if (
      !this.issueInput ||
      !this.statusInput ||
      !this.selectedPersonId ||
      !this.selectedRoomId
    ) {
      alert('Please fill in all required fields.');
      return;
    }

    this.newMaintenanceRequest = {
      createdDate: new Date(),
      endDate: null,
      issue: this.issueInput,
      status: this.statusInput as StatusType,
      personId: this.selectedPersonId,
      roomId: this.selectedRoomId,
    };

    console.log('Request body:', this.newMaintenanceRequest);

    this.maintenanceRequestsService
      .createMaintenanceRequest(this.newMaintenanceRequest)
      .subscribe(
        (response) => {
          console.log('Created request:', response);
          this.fetchMaintenanceRequests();
          this.resetNewMaintenanceRequest();
        },
        (error) => {
          console.error('Error creating maintenance request:', error);
          alert('Failed to create maintenance request. Please try again.');
        }
      );
  }

  startEdit(request: MaintenanceRequest): void {
    console.log('Editing request:', request); 
    this.selectedRequest = request;
    this.editPopup.display = true;
  }

  saveMaintenanceRequest(editedEntity: EditEntity | MaintenanceRequest) {
    if (this.isMaintenanceRequest(editedEntity)) {
      this.maintenanceRequestsService.updateMaintenanceRequest(editedEntity).subscribe({
        next: (updatedRequest) => {
        },
        error: (error) => console.error('Error updating maintenance request:', error),
      });
    } else {
      console.error('Expected MaintenanceRequest, but got:', editedEntity);
    }
  }
  
  private isMaintenanceRequest(entity: any): entity is MaintenanceRequest {
    return 'maintenanceRequestId' in entity; 
  }

  confirmDelete(request: MaintenanceRequest) {
    this.confirmationService.confirm({
      message: 'Are you sure that you want to delete this maintenance request?',
      accept: () => {
        this.deleteMaintenanceRequest(request); 
      }
    });
  }

  deleteMaintenanceRequest(request: MaintenanceRequest) {
    
    const id = request.maintenanceRequestId; 
    if (id !== undefined) {
      console.log(`Attempting to delete request with ID: ${id}`);

      this.maintenanceRequestsService.deleteMaintenanceRequest(id).subscribe({
        next: () => {
          this.filteredRequests = this.filteredRequests.filter(req => req.maintenanceRequestId !== id);
          this.requests = this.requests.filter(req => req.maintenanceRequestId !== id);
          console.log('Maintenance request deleted successfully');
        },
        error: (error) => {
          console.error('Error deleting maintenance request:', error);
        }
      });
    } else {
      console.error('Invalid maintenance request ID');
    }
  }
  

  resetNewMaintenanceRequest(): void {
    this.issueInput = '';
    this.statusInput = StatusType.PENDING;
    this.selectedPersonId = null;
    this.selectedRoomId = null;
    this.newMaintenanceRequest = {
      createdDate: new Date(),
      endDate: null,
      issue: '',
      status: 'PENDING' as StatusType,
      personId: 0,
      roomId: 0,
    };
  }

  resetSelectedMaintenanceRequest(): void {
    this.selectedRequest = null;
    this.editPopup.display = false;
  }

  handleCancel(): void {
    this.resetSelectedMaintenanceRequest(); 
  }

  handleConfirm(updatedEntity: Person | Building | MaintenanceRequest): void {
    if ('maintenanceRequestId' in updatedEntity) {
      this.selectedRequest = updatedEntity;
        this.maintenanceRequestsService.updateMaintenanceRequest(updatedEntity).subscribe({
        next: (response) => {
          const index = this.filteredRequests.findIndex(request => request.maintenanceRequestId === updatedEntity.maintenanceRequestId);
          if (index !== -1) {
            this.filteredRequests[index] = response; 
          }
        },
        error: (err) => {
          console.error('Update failed:', err);
        }
      });
    } else if ('personId' in updatedEntity) {
    } else if ('buildingId' in updatedEntity) {
    } else {
      console.error('Unhandled entity type');
    }
  
    this.resetSelectedMaintenanceRequest();
  }

  searchMaintenanceRequests(): void {
    if (!this.searchTerm) {
      this.filteredRequests = this.requests; 
      return;
    }

    const lowerCaseTerm = this.searchTerm.toLowerCase();

    this.filteredRequests = this.requests.filter(request => {
      return (
        request.maintenanceRequestId?.toString().includes(lowerCaseTerm) ||
        request.issue.toLowerCase().includes(lowerCaseTerm) ||
        request.status.toLowerCase().includes(lowerCaseTerm) ||
        request.roomId?.toString().includes(lowerCaseTerm) ||
        request.personId?.toString().includes(lowerCaseTerm)
      );
    });
  }
}
