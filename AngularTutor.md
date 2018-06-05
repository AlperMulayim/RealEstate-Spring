
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
