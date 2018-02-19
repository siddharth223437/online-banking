import { NavbarService } from './Services/navbar.service';
import { LoginComponent } from './login/login.component';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  
  constructor() {}
    title = 'app';
  
  ngOnInit() {
   
    
  }
  
 

}
