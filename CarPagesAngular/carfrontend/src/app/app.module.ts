import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { CarsComponent } from './cars/cars.component';
import { CarsListComponent } from './cars/cars-list/cars-list.component';
import { CarsAddComponent } from './cars/cars-add/cars-add.component';


@NgModule({
  declarations: [
    AppComponent,
    CarsComponent,
    CarsListComponent,
    CarsAddComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
