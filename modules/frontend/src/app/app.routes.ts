import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { FloorComponent } from './components/floor/floor.component';
import { NgModule } from '@angular/core';
import { FloorListComponent } from './components/floor-list/floor-list.component';

export const routes: Routes = [
    {
        path: '',
        component: HomeComponent,
    },
    {
        path: 'floors/:buildingId', component: FloorListComponent,
    },

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
