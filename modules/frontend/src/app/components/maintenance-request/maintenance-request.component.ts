import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule, NgFor } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { ToastModule } from 'primeng/toast';
import { ConfirmationService } from 'primeng/api';
import { RouterModule } from '@angular/router';
import {
  MaintenanceRequest,
  MaintenanceResponse,
  StatusType,
} from '../../types'; // Import your types
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
    EditPopupComponent,
  ],
  providers: [ConfirmationService],
  templateUrl: './maintenance-request.component.html',
  styleUrls: ['./maintenance-request.component.css'],
})
export class MaintenancerequestComponent implements OnInit {
  requests: MaintenanceResponse[] = [];
  selectedRequest?: MaintenanceRequest;
  editPopup = { display: false };
  searchTerm: string = '';
  issueInput: string = '';
  statusInput: StatusType = StatusType.PENDING;
  selectedPersonId: number | null = null;
  selectedRoomId: number | null = null;

  newMaintenanceRequest: MaintenanceRequest = {
    createdDate: new Date(),
    endDate: null,
    issue: '',
    status: 'PENDING' as StatusType,
    personId: 0,
    roomId: 0,
  };

  constructor(
    private confirmationService: ConfirmationService,
    private maintenanceRequestsService: MaintenanceRequestsService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.fetchMaintenanceRequests();
  }

  fetchMaintenanceRequests(): void {
    this.maintenanceRequestsService.getMaintenanceRequests().subscribe(
      (data) => {
        this.requests = data;
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
    this.selectedRequest = request;
    this.editPopup.display = true;
  }

  saveMaintenanceRequest(maintenanceRequest: MaintenanceRequest): void {
    if (maintenanceRequest) {
      this.maintenanceRequestsService
        .updateMaintenanceRequest(maintenanceRequest)
        .subscribe({
          next: (updatedRequest) => {
            const index = this.requests.findIndex(
              (req) =>
                req.maintenanceRequestId === updatedRequest.maintenanceRequestId
            );
            if (index !== -1) {
              this.requests[index] = updatedRequest;
            }
            this.resetSelectedMaintenanceRequest();
          },
          error: (error) =>
            console.error('Error saving maintenance request:', error),
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
            this.requests = this.requests.filter(
              (request) => request.maintenanceRequestId !== id
            );
          },
          error: (error) => {
            console.error('Error deleting maintenance request:', error);
          },
        });
      },
    });
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
    this.selectedRequest = undefined;
    this.editPopup.display = false;
  }

  searchMaintenanceRequests(): void {}
}
