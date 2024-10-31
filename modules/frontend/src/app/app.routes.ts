import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { FloorListComponent } from './components/floor-list/floor-list.component';
import { PersonComponent } from './components/person/person.component';
import { AccessibleRoomsComponent } from './components/accessible-rooms/accessible-rooms.component';
import { AccesslogComponent } from './components/accesslog/accesslog.component';
import { MaintenancerequestComponent } from './components/maintenance-request/maintenance-request.component';

export const routes: Routes = [
    { path: '', redirectTo: '', pathMatch: 'full' }, // Redirect empty path to /home
    { path: '', component: HomeComponent },
    { path: 'floors/:buildingId', component: FloorListComponent },
    { path: 'person', component: PersonComponent },
    { path: 'person/:id/spaces', component: AccessibleRoomsComponent },
    { path: 'accesslog', component: AccesslogComponent },
    { path: 'maintenancerequest', component: MaintenancerequestComponent },
    { path: '**', redirectTo: 'home', pathMatch: 'full' }, // Redirect any undefined route to /home
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
