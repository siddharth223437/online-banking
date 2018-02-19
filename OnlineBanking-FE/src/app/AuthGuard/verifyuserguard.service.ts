import { LoginComponent } from '../login/login.component';
import { Injectable } from '@angular/core';
import {CanActivate, Router } from '@angular/router';

@Injectable()
export class VerifyuserguardService {

  constructor(private rouetr: Router, private authSer: LoginComponent) { }
  
  canActivate() {
    if (this.authSer.verifyUserRole()) {
      console.log('can activate user as role cannot access URL');
      localStorage.removeItem('userPricipal');
       this.rouetr.navigate(['/error']);
      return false;
    } else {
     console.log('can activate user as role is allowed to access URL');
      return true;
    }
  }

}
