import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-contact-component',
  templateUrl: './contact-component.component.html',
  styleUrls: ['./contact-component.component.css']
})
export class ContactComponentComponent implements OnInit {

  user = {
    username:'',
    password:''
  };

  post = {
    postheader:'',
    postbody:''

  };
  constructor(private http: HttpClient) { }

  ngOnInit() {
  }
  onSubmit(theForm: NgForm){
    this.user.username = theForm.value.username;
    this.user.password = theForm.value.password;
    console.log(this.user);

    this.http.post('https://jsonplaceholder.typicode.com/posts',{

      userId: Math.random(),
      id: Math.random(),
      title: theForm.value.postheader,
      body: theForm.value.postbody

    }).subscribe(res=>{
      console.log(res);
    },errno=>{
      console.log( ' Error : ' ,  errno);
    });
  }

  sendPost(postForm: NgForm){

    this.http.post('https://jsonplaceholder.typicode.com/posts',{

      userId: Math.random(),
      id: Math.random(),
      title: postForm.value.postheader,
      body: postForm.value.postbody

    }).subscribe(res=>{
      console.log(res);
    },errno=>{
      console.log( ' Error : ' ,  errno);
    });
  }



}
