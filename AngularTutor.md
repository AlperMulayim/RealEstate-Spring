
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
