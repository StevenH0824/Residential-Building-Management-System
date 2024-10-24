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

export interface PaginatedBuildingResponse {
    items: Building[];
    total: number;
    page: number;
    perPage: number;
    totalPages: number;
}

export interface Room {
    roomId: number;
    number: string;
    description: string;
    floor: Floor
}

export interface Floor {
    floorId: number;
    number: string;
    description: string;
    building: Building;
    rooms: Room[];
}

export interface Building{
    buildingId: number;
    name: string;
    address: string;
    floors: Floor[];
}

export interface PaginationParams {
    [param: string]: string | number | boolean | ReadonlyArray<string | number | boolean>;
    page: number;
    perPage: number;
}
    