import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AirlinesComponent } from './airlines/airlines.component';
import { BookingsComponent } from './bookings/bookings.component';
import { DiscountsComponent } from './discounts/discounts.component';
import { FlightsComponent } from './flights/flights.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  { path: 'airlines', component: AirlinesComponent },
  { path: 'discounts', component: DiscountsComponent },
  { path: 'bookings', component: BookingsComponent },
  { path: 'flights', component: FlightsComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
