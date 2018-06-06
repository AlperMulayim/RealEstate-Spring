
# Angular Tutorial 
### Button Create 

app-component.html 
```<button (click)="greetPerson()">Click</button> ``` 
<br> app-component.ts 
```
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  greetPerson(){
    alert("Hello FRom Angular");
  }
}

```
### Two Way Binding 
app-component.html 
```
<input [(ngModel)]="lastName" type="text">
<hr>
{{lastName}}
<hr>
```
<br> app-component.ts 
```
export class AppComponent {
  lastName : string
}
```
### If Condition
```
<h3> if condition</h3>
<hr>
<h4 *ngIf="status; else notFound"> {{ firstName }}</h4>

<ng-template #notFound>
  <p> user not found </p>
</ng-template>    
```

```
  setUserStatus(){
    this.status = false;
  }
```
### Loops 
```
<ul>
  <li *ngFor="let post of posts"> {{post.title}}</li>
</ul>
```
```
export class AppComponent {

  firstName = 'Alper';
  lastName : string;
  status :boolean;
  posts: object[];

  constructor(){
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
}
```
<br> printing the index 
```
<ul>
  <li *ngFor="let post of posts ; let i = index " >{{i}} . {{post.title}}</li>
</ul>
```
### Style 
<br> Static Styling 
```
<h3 [ngStyle]="{color : status ? 'green' : 'red'}" >  {{firstName}}</h3>
```
<br>  Loading Style CLass 
```
<h3 [ngClass]="{isActive : status}">{{lastName}}</h3>
```
<br> styles.css 
```
.isActive{
  color : green;
}
```

### Form Creation 
app-component.html 
```

<form>
  <div class="form-group">
    <label for="username"> Username</label>
    <input type="text" id="username" class="form-control" placeholder="Enter Name">
  </div>

  <div class="form-group">
    <label for="password">Password</label>
    <input type="password" id="password" class="form-control" placeholder="Enter Password">
  </div>

  <button type="submit" class="btn btn-primary"> Submit </button>
</form>
```
#### Form Binding 

```
<form (ngSubmit)="onSubmit(theForm)" #theForm="ngForm">
  <div class="form-group">
    <label for="username"> Username</label>
    <input ngModel name="username" type="text" id="username" class="form-control" placeholder="Enter Name">
  </div>

  <div class="form-group">
    <label for="password">Password</label>
    <input ngModel name="password" type="password" id="password" class="form-control" placeholder="Enter Password">
  </div>

  <button type="submit" class="btn btn-primary"> Submit </button>
</form>
```
```
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
```
### HTTP REQUEST
#### HTTP GET REQUEST
```
export class EmployeeComponent implements OnInit {

  posts: object;
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get('https://jsonplaceholder.typicode.com/posts').subscribe(posts=>{
      this.posts = posts;
    });
  }

}
```
```
<div class="card" *ngFor="let post of posts">
  <h6 class="card-header">USER - {{post.id}}</h6>
  <div class="card-body">
    <h5 class="card-title">{{post.title}}</h5>
    <p class="card-text">{{post.body}}</p>
    <a href="#" class="btn btn-primary">Go somewhere</a>
  </div>
</div>
```





```
<form (ngSubmit)="carSubmit(carForm)" #carForm="ngForm">
  <div class="form-group">
      <input ngModel name="model" type="text" id="model" class = "form-control" placeholder="Car Model">

  </div>
  <button type="submit" class="btn btn-primary"> Submit  Car </button>
</form>
```
### Routing Pages


<br> app.component.html 
```
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" routerLink="/">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" routerLink="/contact">Contact</a>
      </li>
    </ul>
  </div>
</nav>


<div class="container">
  <router-outlet></router-outlet>
</div>
```

<br> homecomponent.html  this is the home page 
```
<p>
  home-component works!
</p>

<h3>Button</h3>
<button (click)="greetPerson()">Click</button>


<h3> Two Way Binding </h3>
<hr>
<input [(ngModel)]="lastName" type="text">

<hr>
{{lastName}}
<hr>

<h3> if condition</h3>
<hr>
<h4 *ngIf="status; else notFound"> {{ firstName }}</h4>

<ng-template #notFound>
  <p> user not found </p>
</ng-template>

<hr>

<ul>
  <li *ngFor="let post of posts"> {{post.title}}</li>
</ul>

<ul>
  <li *ngFor="let post of posts ; let i = index " >{{i}} . {{post.title}}</li>
</ul>

<hr>
<h3 [ngStyle]="{color : status ? 'green' : 'red'}" >  {{firstName}}</h3>

<hr>
<h3 [ngClass]="{isActive : status}">{{lastName}}</h3>


<button class="btn-primary"> Button </button>



<form (ngSubmit)="carSubmit(carForm)" #carForm="ngForm">
  <div class="form-group">
    <input ngModel name="model" type="text" id="model" class = "form-control" placeholder="Car Model">
  </div>
  <button type="submit" class="btn btn-primary"> Submit  Car </button>
</form>
```
<br> contactcomponent.html contact page 
```
<p>contact-component works!</p>
<form (ngSubmit)="onSubmit(theForm)" #theForm="ngForm">
  <div class="form-group">
    <label for="username"> Username</label>
    <input ngModel name="username" type="text" id="username" class="form-control" placeholder="Enter Name">
  </div>

  <div class="form-group">
    <label for="password">Password</label>
    <input ngModel name="password" type="password" id="password" class="form-control" placeholder="Enter Password">
  </div>

  <button type="submit" class="btn btn-primary"> Submit </button>
</form>
<hr>
```


<br>  app.module.ts  Router Module should add and all pages add as component to Routes
```
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {RouterModule, Routes} from "@angular/router";

import { HomeComponentComponent } from './home-component/home-component.component';
import { ContactComponentComponent } from './contact/contact-component.component';

const routes: Routes =[
  {path: '', component: HomeComponentComponent},
  {path: 'contact', component: ContactComponentComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponentComponent,
    ContactComponentComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
```
