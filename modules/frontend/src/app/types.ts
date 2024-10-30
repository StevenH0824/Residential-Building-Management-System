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
    number: string;
    description: string;
    floor: Floor
}

export interface Floor {
    floorId?: number;
    number: string;
    description: string;
    buildingId: Building[];
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


