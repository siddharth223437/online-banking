import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-errorpage',
  templateUrl: './errorpage.component.html',
  styleUrls: ['./errorpage.component.css']
})
export class ErrorpageComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
    this.redirectToLogin();
  }
  
  redirectToLogin() {
     console.log('wait 5 sec');
    setTimeout(() => {
    this.router.navigate(['/login']);
    }, 5000);
  }

}
