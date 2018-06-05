
# Angular Tutorial 
### Button Create 

app-component.html 
```<button (click)="greetPerson()">Click</button> ``` 
app-component.ts 
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
