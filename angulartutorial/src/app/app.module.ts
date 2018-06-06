import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {RouterModule, Routes} from "@angular/router";
import {HttpClient, HttpClientModule} from "@angular/common/http";

import { HomeComponentComponent } from './home-component/home-component.component';
import { ContactComponentComponent } from './contact/contact-component.component';
import { EmployeeComponent } from './employee/employee.component';

const routes: Routes =[
  {path: '', component: HomeComponentComponent},
  {path: 'contact', component: ContactComponentComponent},
  {path: 'employee',component:EmployeeComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponentComponent,
    ContactComponentComponent,
    EmployeeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
