import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

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
