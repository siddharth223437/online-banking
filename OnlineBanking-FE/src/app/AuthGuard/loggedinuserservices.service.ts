import { AuthService } from '../Services/auth.service';
import { LoginComponent } from '../login/login.component';
import { Injectable } from '@angular/core';
import {CanActivate, Router } from '@angular/router';

@Injectable()
export class LoggedinuserservicesService {

  constructor(private rouetr: Router, private authSer: LoginComponent) { }
  
  canActivate() {
    // remeber to use function like ()
    if (this.authSer.checkLoggedInUsers()) {
      console.log('can activate true');
      return true;
    } else {
      this.rouetr.navigate(['/login']);
      return false;
    }
  }

}
