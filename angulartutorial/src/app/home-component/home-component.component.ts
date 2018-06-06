import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-home-component',
  templateUrl: './home-component.component.html',
  styleUrls: ['./home-component.component.css']
})
export class HomeComponentComponent implements OnInit {

  firstName :string;
  lastName : string;
  status :boolean;
  posts: object[];

  user = {
    username:'',
    password:''
  };

  car={
    model:''
  };

  constructor(){
    this.firstName = 'alper';
    this.setUserStatus();
    this.posts = [
      {title: 'Post 1'},
      {title: 'Post 2'},
      {title: 'Post 3'},
      {title: 'Post 4'},
      {title: 'Post 5'},
    ]
  }

  ngOnInit(){}
  greetPerson(){
    alert("Hello From Angular");
  }
  setUserStatus(){
    this.status = false;
  }
  onSubmit(theForm: NgForm){
    this.user.username = theForm.value.username;
    this.user.password = theForm.value.password;
    console.log(this.user);
  }

  carSubmit(carForm: NgForm){
    this.car.model = carForm.value.model;
    console.log(this.car);
  }
}
