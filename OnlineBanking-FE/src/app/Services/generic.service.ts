import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/map';


@Injectable()
export class GenericService {

  constructor(private httpClient: HttpClient) { }
  
   LocalURL = 'http://localhost:9090';
  
  registerService(user) {
    let head = new HttpHeaders();
    return this.httpClient.post(this.LocalURL + '/account/register', user).map(
    (response) => response
    );
  }
  
  showCountryServuce() {
    return this.httpClient.get(this.LocalURL + '/world/country').map(
    (response) => response
    );
  }


showWelcomeService(username) {
  return this.httpClient.get(this.LocalURL + '/account/welcome/' + username, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
  }).map(
  (response) => response
  
  );
  }
  
  allUsersByRoleService(role) {
    return this.httpClient.get(this.LocalURL + '/account/all/users/' + role, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  findOneUserService(username) {
    return this.httpClient.get(this.LocalURL + '/account/showsingleuser/' + username, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  approveUserService(username) {
    return this.httpClient.get(this.LocalURL + '/account/approve/' + username, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  updateUser(user) {
    return this.httpClient.put(this.LocalURL + '/account/update', user, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  updateActNumService(username, actnum) {
    return this.httpClient.get(this.LocalURL + '/account/assign/act/' + username + '/' + actnum, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  denyUserService(username) {
    return this.httpClient.get(this.LocalURL + '/account/deny/' + username, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  depositService(amount, acttype) {
    return this.httpClient.get(this.LocalURL + '/account/deposit/' + amount + '/' + acttype, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  transactionService() {
    return this.httpClient.get(this.LocalURL + '/account/transaction/primary' , {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  transactionServiceSavings() {
    return this.httpClient.get(this.LocalURL + '/account/transaction/savings' , {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  withdrawService(amount, acttype) {
    return this.httpClient.get(this.LocalURL + '/account/withdraw/' + amount + '/' + acttype, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  transferBetActService(act1, act2, amount) {
    return this.httpClient.get(this.LocalURL + '/account/transfer/' + act1 + '/' + act2 + '/' + amount, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  allRecipient() {
    return this.httpClient.get(this.LocalURL + '/recipient/all', {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  addRecipientService(email, name) {
    return this.httpClient.get(this.LocalURL + '/recipient/add/' + email + '/' + name, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  transferToSomeone(actType, email, amount) {
    return this.httpClient.get(this.LocalURL + '/recipient/transfer/' + actType + '/' + email + '/' + amount, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
    saveAppointmentService(user) {
    let head = new HttpHeaders();
    return this.httpClient.post(this.LocalURL + '/appointment/create', user, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  saveAppointmentServiceTest(name, actnum, date, reason) {
    return this.httpClient.get(this.LocalURL + '/appointment/create/' + name + '/' + actnum + '/' + date + '/' + reason, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  viewUserAppointmentService() {
    return this.httpClient.get(this.LocalURL + '/appointment/find/user', {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  viewAllAppointmentsService() {
      return this.httpClient.get(this.LocalURL + '/appointment/find/all', {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  searchNameAndStatusService(user) {
    return this.httpClient.post(this.LocalURL + '/appointment/find/name', user, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  findAppointmentById(id) {
    return this.httpClient.get(this.LocalURL + '/appointment/find/' + id, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
   approveAppointmentService(id) {
    return this.httpClient.get(this.LocalURL + '/appointment/approve/' + id, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
   cancelAppointmentService(id) {
    return this.httpClient.get(this.LocalURL + '/appointment/cancel/' + id, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  deleteAppointmentByIdService(id) {
    return this.httpClient.get(this.LocalURL + '/appointment/delete/' + id, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
  generateStatementsService(fromDate, toDate, username) {
    return this.httpClient.get(this.LocalURL + '/account/generatestatement/' + fromDate + '/' + toDate + '/' + username, {
    headers: new HttpHeaders().set('Authorization', 'Basic ' + localStorage.getItem('baseToken')),
    }).map(
    (response) => response
    );
  }
  
}
