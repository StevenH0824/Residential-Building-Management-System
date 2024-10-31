import { HttpContext, HttpHeaders, HttpParams } from "@angular/common/http";

export interface Options {
    headers?: HttpHeaders | {
        [header: string]: string | string[];
    };
    observe?: 'body';
    context?: HttpContext;
    params?: HttpParams | {
        [param: string]: string | number | boolean | ReadonlyArray<string | number | boolean>;
    };
    reportProgress?: boolean;
    responseType?: 'json';
    withCredentials?: boolean;
    transferCache?: {
        includeHeaders?: string[];
    } | boolean;
}

export type EditEntity = Person | Building;



export interface Buildings {
    items: Building[];
    total: number;
    page: number;
    perPage: number;
    totalPages: number;
}

export interface Building {
    buildingId?: number;
    name: string;
    address: string;
    floors?: Floor[];
}

    export interface Room {
        roomId: number;
        roomNumber: string;
        roomDescription: string;
        floorId: number;
        floorDescription: string;
        buildingId: number;
        buildingName: string;
        buildingAddress: string;
    }

export interface Floor {
    floorId?: number;
    number: string;
    description: string;
    building: Building;
    roomIds: Room[];
}

export interface Person {
    personId: number;
    floorId?: number;
    email: string;
    firstName: string;
    lastName: string;
    phoneNumber: string;
}

export interface ControlGroup{
    controlGroupId: number; 
    name: string;            
    description: string;     
    controlGroupAccessControls?: ControlGroupAccessControl[]; 
    controlGroupPersons?: ControlGroupPerson[]; 
  }
  
  export interface ControlGroupAccessControl {
    accessControlId: number;
  }
  
  export interface ControlGroupPerson {
    personId: number; 
    startDate: Date;  
    expirationDate: Date; 
  }

export interface PaginationParams {
    [param: string]: string | number | boolean | ReadonlyArray<string | number | boolean>;
    page: number;
    perPage: number;
}

export interface AccessLog {
    accessLogId?: number;
    cardScannerId: number;
    badgeId: number;
    timestamp: string;
}

export interface PaginatedAccessLogs {
    items: AccessLog[];
    total: number;
    page: number;
    perPage: number;
    totalPages: number;
}

export interface MaintenanceRequest {
    maintenanceRequestId?: number;
    createdDate: string;
    endDate?: string;
    issue: string;
    status: StatusType;
    personId: number;
    roomId: number;
}

export interface MaintenanceResponse {
    maintenanceRequestId: number;
    createdDate: string;
    endDate?: string;
    issue: string;
    status: StatusType;
    personFirstName: string;
    personLastName: string;
    roomNum: string;
}

export interface PaginatedMaintenanceRequests {
    items: MaintenanceRequest[];
    total: number;
    page: number;
    perPage: number;
    totalPages: number;
}

export type StatusType = 'PENDING' | 'IN_PROGRESS' | 'COMPLETED' | 'CANCELLED';
