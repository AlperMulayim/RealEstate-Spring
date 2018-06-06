import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  countries: object;
  countryList =[];

  country={
    counrtyname:''
  }
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get('https://restcountries.eu/rest/v2/name/turkey').subscribe(countries=>{
      this.countries = countries;
      this.countryList.push(this.countries[0]);
    });
  }

  onSubmit(theForm : NgForm){
    this.country.counrtyname = theForm.value.countryname;
    console.log(this.country);

    this.http.get('https://restcountries.eu/rest/v2/name/' + this.country.counrtyname).subscribe(countries=>{
      this.countries = countries;
      this.countryList.push(this.countries[0]);
      console.log(this.countries);
    });


  }
}
