import { AuthService } from '../Services/auth.service';
import { GenericService } from '../Services/generic.service';
import { LoginFields } from './loginfields';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  model = new LoginFields();
   user = localStorage.getItem('userPricipal');
  login_status;
  login_error;
  login_error_sts;
  constructor(private rouetr: Router, private authService: AuthService, private http: HttpClient) {
  
   }

  ngOnInit() {
    
  }
  
  login() {
    console.log('in login');
    this.authService.login(this.model).subscribe(
    (resp) => {
      console.log('logggggin', resp);
      this.login_status = localStorage.getItem('userActive');
      console.log('login aaa', this.login_status);
      console.log('login component success', resp);
      let rol: string = resp['principal'].role;
      localStorage.setItem('ROLE', rol);
      console.log('role comp is', rol);
      if (rol === 'ADMIN') {
        console.log('redirecting to admin psge', rol);
        this.rouetr.navigate(['/admin']);
      } else if ( rol === 'USER') {
        console.log('redirecting to User page', rol);
        this.rouetr.navigate(['/account']);
      } else {
        console.log('role is null');
      }
    }, (error) => {
    console.log('Error in login Component');
      this.login_error_sts = true;
      this.login_error = 'Invalid Username/Password';
    });
  }
  
  checkLoggedInUsers(): boolean {
    console.log('*****Checking logged in users');
    this.login_status = localStorage.getItem('userActive');
    this.user = localStorage.getItem('userPricipal');
    console.log('logged', this.login_status);
    if (this.login_status === 'true' && this.user != null) {
      
      console.log('Login is Valid');
      return true;
    } else {
      console.log('user login is null ', this.user);
      console.log('login is invalid ');
      return false;
    }
  }
   
   verifyAdminRoles(): boolean {
     console.log('**** In verify roles'); 
       let role = localStorage.getItem('ROLE');
     if ( role === 'ADMIN') {
       console.log('verify admin comp', role);
       return true;
     } else {
       console.log('Admin comp cannot access url');
       return false;
     }
   }
  
  verifyUserRole(): boolean {
    console.log('**** In verify roles'); 
       let role = localStorage.getItem('ROLE');
     if ( role === 'USER') {
       return true;
     } else {
       console.log('USER cannot access url');
       return false;
     }
  }
  
  Register() {
    this.rouetr.navigate(['/register']);
  }

}
