import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgFor } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ButtonModule } from 'primeng/button';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { ToastModule } from 'primeng/toast';
import { ConfirmationService } from 'primeng/api';
import { RouterModule } from '@angular/router';
import { AccessLog } from '../../types';

@Component({
  selector: 'app-accesslog',
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
  templateUrl: './accesslog.component.html',
  styleUrls: ['./accesslog.component.css'],
})
export class AccesslogComponent implements OnInit {
  newAccessLog: AccessLog = { cardScannerId: 0, badgeId: 0, timestamp: '' };
  accesslogs: AccessLog[] = [];
  total: number = 0;

  constructor(
    private confirmationService: ConfirmationService,
    private http: HttpClient
  ) { }

  ngOnInit(): void {
    this.fetchAccessLogs(); // Fetch logs when the component initializes
  }

  fetchAccessLogs(): void {
    this.http.get<AccessLog[]>('/api/accesslog').subscribe(logs => {
      this.accesslogs = logs;
      this.total = logs.length; // Update total count based on fetched logs
    });
  }

  logAccess(): void {
    this.http.post<AccessLog>('/api/accesslog/log', this.newAccessLog).subscribe(response => {
      if (response) {
        this.accesslogs.push(response); // Add new access log to the list
        this.resetNewAccessLog(); // Reset form
      }
    });
  }

  deleteAccessLog(id: number): void {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete this access log?',
      accept: () => {
        this.http.delete(`/api/accesslog/${id}`).subscribe(() => {
          this.accesslogs = this.accesslogs.filter(log => log.accessLogId !== id); // Remove log from array
        });
      }
    });
  }

  resetNewAccessLog(): void {
    this.newAccessLog = { cardScannerId: 0, badgeId: 0, timestamp: '' }; // Reset form fields
  }

}