import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CinemaComponent} from "./cinema/cinema.component";

const routes: Routes = [
  {
    path: 'cinemas',
    component: CinemaComponent,
  },
  { path: '',   redirectTo: 'cinemas', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
