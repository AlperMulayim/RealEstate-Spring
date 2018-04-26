import { Component, OnInit } from '@angular/core';
import {Car} from "../car.model";

@Component({
  selector: 'app-cars-list',
  templateUrl: './cars-list.component.html',
  styleUrls: ['./cars-list.component.css']
})
export class CarsListComponent implements OnInit {

  cars: Car[]=[];

  constructor() { }

  ngOnInit() {
    this.cars.push(new Car(1,"Opel Astra","2345ASD","Mamut",1234));
    this.cars.push(new Car(1,"Opel Astra","2345ASD","Mamut",1234));
    this.cars.push(new Car(1,"Opel Astra","2345ASD","Mamut",1234));

  }

}
