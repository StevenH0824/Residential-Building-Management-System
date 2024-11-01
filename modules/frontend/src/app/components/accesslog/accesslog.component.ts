import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule, NgFor } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ButtonModule } from 'primeng/button';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { ToastModule } from 'primeng/toast';
import { ConfirmationService } from 'primeng/api';
import { RouterModule } from '@angular/router';
import { AccessLog, AccessLogResponseDTO } from '../../types';
import { AccessLogsService } from '../../services/access-logs.service';

@Component({
  selector: 'app-accesslog',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ButtonModule,
    ConfirmPopupModule,
    ToastModule,
    NgFor,
  ],
  providers: [ConfirmationService],
  templateUrl: './accesslog.component.html',
  styleUrls: ['./accesslog.component.css'],
})
export class AccesslogComponent implements OnInit {
  newAccessLog: AccessLog = {
    cardScannerId: 0,
    personId: 0,
    accessTime: new Date(),
  };
  accessLogs: AccessLogResponseDTO[] = [];
  filteredLogs: AccessLogResponseDTO[] = [];
  searchTerm: string = '';

  constructor(
    private confirmationService: ConfirmationService,
    private accessLogService: AccessLogsService
  ) {}

  ngOnInit(): void {
    this.fetchAccessLogs();
  }

  fetchAccessLogs(): void {
    this.accessLogService.getAllAccessLogs().subscribe(
      (logs: AccessLogResponseDTO[]) => {
        console.log('Fetched logs:', logs); // Debug log
        this.accessLogs = logs.map(log => ({
          ...log,
          timestamp: log.accessTime // Keep it as a string
        }));
        this.filteredLogs = [...this.accessLogs]; // Initialize filteredLogs with all logs
      },
      (error) => {
        console.error('Error fetching access logs', error);
      }
    );
  }

  searchAccessLogs(): void {
    console.log('Search Term:', this.searchTerm); // Log the search term

  if (!this.searchTerm) {
    this.filteredLogs = this.accessLogs; 
    console.log('Filtered Logs Reset:', this.filteredLogs); // Log after resetting
    return;
  }

  const lowerCaseTerm = this.searchTerm.toLowerCase();
  
  this.filteredLogs = this.accessLogs.filter(log => {
    const matchFound = (
      log.accessLogId?.toString().includes(lowerCaseTerm) ||
      log.cardScannerId?.toString().includes(lowerCaseTerm) ||
      (log.floorDescription && log.floorDescription.toLowerCase().includes(lowerCaseTerm)) || // Add null check
      (log.roomNumber && log.roomNumber.toLowerCase().includes(lowerCaseTerm)) || // Add null check
      (log.buildingName && log.buildingName.toLowerCase().includes(lowerCaseTerm)) || // Add null check
      (log.fullName && log.fullName.toLowerCase().includes(lowerCaseTerm)) || // Add null check
      new Date(log.accessTime).toLocaleString().includes(lowerCaseTerm) // Assuming accessTime is always valid
    );
    
    if (matchFound) {
      console.log('Match Found:', log); // Log matched logs
    }
    
    return matchFound;
  });

  console.log('Filtered Logs:', this.filteredLogs); // Log after filtering
  }

  logAccess(): void {
    this.accessLogService.createAccessLog(this.newAccessLog).subscribe(response => {
      if (response) {
        const newAccessLogResponse: AccessLogResponseDTO = {
          accessLogId: response.accessLogId,
          cardScannerId: response.cardScannerId,
          floorDescription: response.floorDescription,
          roomNumber: response.roomNumber,
          buildingName: response.buildingName,
          accessTime: response.accessTime, 
          personId: response.personId,
          fullName: response.fullName || 'Unknown', 
        };
  
        this.accessLogs.push(newAccessLogResponse); // Add to accessLogs
        this.filteredLogs.push(newAccessLogResponse); // Also add to filteredLogs
        this.resetNewAccessLog(); // Reset the form fields
        console.log('Response from createAccessLog:', response);
      }
    });
  }

  deleteAccessLog(log: AccessLogResponseDTO): void {
    const id = log.accessLogId; 
    if (id !== undefined) {
      console.log(`Attempting to delete access log with ID: ${id}`);
  
      this.accessLogService.deleteAccessLog(id).subscribe({
        next: () => {
          // Update the filteredLogs and accessLogs arrays after deletion
          this.filteredLogs = this.filteredLogs.filter(existingLog => existingLog.accessLogId !== id);
          this.accessLogs = this.accessLogs.filter(existingLog => existingLog.accessLogId !== id);
          console.log('Access log deleted successfully');
        },
        error: (error) => {
          console.error('Error deleting access log:', error);
        }
      });
    } else {
      console.error('Invalid access log ID');
    }
  }

  resetNewAccessLog(): void {
    this.newAccessLog = {
      cardScannerId: 0,
      personId: 0,
      accessTime: new Date(),
    };
  }

  editAccessLog(log: AccessLogResponseDTO): void {
    console.log('Editing access log:', log);
    this.newAccessLog = {
      cardScannerId: log.cardScannerId,
      personId: log.personId,
      accessTime: new Date(log.accessTime),
    };
  }
}
