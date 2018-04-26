import { Component, OnInit } from '@angular/core';
import {Car} from "../car.model";
import {CarService} from "../car.service";

@Component({
  selector: 'app-cars-list',
  templateUrl: './cars-list.component.html',
  styleUrls: ['./cars-list.component.css']
})
export class CarsListComponent implements OnInit {

  cars: Car[]=[];

  constructor(private carService: CarService) { }

  ngOnInit() {
    return this.carService.getCars()
      .subscribe(
        (cars:any[])=> {
          this.cars = cars;
        },
        (error)=>console.log(error)
      );

  }

}
