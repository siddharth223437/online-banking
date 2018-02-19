import { LoginFields } from '../login/loginfields';
 import { HttpHeaders } from '@angular/common/http';
 import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
// import { Http, Headers, RequestOptions, Response} from '@angular/http';
import 'rxjs/add/operator/catch';
import 'rxjs/add/Observable/throw';
import 'rxjs/add/operator/map';

@Injectable()
export class AuthService {

  constructor(private httpClient: HttpClient) {      
  }
  
  LocalURL = 'http://localhost:9090';
  
 public login(user: any) {
    let response;
    console.log('user is', user);
   let head = new HttpHeaders();
    head = head.append('Accept', 'application/json');
   let base64Credential: string = btoa( user.username + ':' + user.password);
    head = head.append('Authorization', 'Basic ' + base64Credential);
    return this.httpClient.get(this.LocalURL + '/account/login', {
      headers: head,
    }).map(
    (response) => {
      console.log('above jwt', response);
    let jwt = response['principal'].username;
       console.log('jwt iis', jwt);
    let userIsActive = response['principal'].active;
      if (jwt) {
        localStorage.setItem('baseToken', base64Credential);
        localStorage.setItem('userPricipal', jwt);
        localStorage.setItem('userActive', userIsActive);
        return response;
      } else {
        console.log('token not found');
        return false;
      }
    }
    ).catch(this.handleError);
  }

  handleError(error) {
    console.log('Error in Login Service');
    return Observable.throw(error);
    
  }


}
