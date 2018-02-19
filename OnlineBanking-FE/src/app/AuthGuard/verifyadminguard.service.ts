import { LoginComponent } from '../login/login.component';
import { Injectable } from '@angular/core';
import {CanActivate, Router } from '@angular/router';

@Injectable()
export class VerifyadminguardService {

  constructor(private rouetr: Router, private authSer: LoginComponent) { }
  
  canActivate() {
    if (this.authSer.verifyAdminRoles()) {
      console.log('can activate admin cannot access URL');
      localStorage.removeItem('userPricipal');
       this.rouetr.navigate(['/error']);
      return false;
    } else {
      console.log('can activate admin is allowed to access URL');
      return true;
    }
  }

}
