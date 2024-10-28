import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { FloorListComponent } from './components/floor-list/floor-list.component';
import { PersonComponent } from './components/person/person.component';

export const routes: Routes = [
    { path: '', component: HomeComponent},
    { path: 'floors/:buildingId', component: FloorListComponent},
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    { path: 'person', component: PersonComponent },

    // { path: 'about', component: AboutComponent },
    // { path: 'contact', component: ContactComponent },

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
