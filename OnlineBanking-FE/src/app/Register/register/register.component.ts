import { GenericService } from '../../Services/generic.service';
import { ResisterUser } from './resisteruser';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import {NgbModal, ModalDismissReasons, NgbTypeaheadConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  
  model = new ResisterUser();
 public country: any;
  public state: any;
  public city: any;
  public userRegisterStatus;
  constructor(private genericService: GenericService, config: NgbTypeaheadConfig, private roter: Router) {
  config.showHint = true;
   }

  ngOnInit() {
    this.showCountry();
  }
  
  register() {
    this.genericService.registerService(this.model).subscribe(
    (resp) => {
    let registerResp = resp['responseObject'];
      this.userRegisterStatus = resp['status'];
      console.log('register response is', this.userRegisterStatus);
      console.log('respnse is ', resp);
    }
    
    );
  }
  
  showCountry() {
    this.genericService.showCountryServuce().subscribe(
    (resp) => {
    let showCountryResp = resp['responseObject'];
      for ( let i = 0; i < showCountryResp.length; i++) {
        this.country = showCountryResp[i].country;
        this.state = showCountryResp[i].state;
        this.city = showCountryResp[i].city;
      }
      console.log(this.city);
    }
    );
  }
  
  searchState = (text$: Observable<string>) =>
    text$
      .debounceTime(200)
      .distinctUntilChanged()
      .map(term => term.length < 2 ? []
        : this.state.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10))

  searchCountry = (text$: Observable<string>) =>
    text$
      .debounceTime(200)
      .distinctUntilChanged()
      .map(term => term.length < 2 ? []
        : this.country.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10))
  
  searchCity = (text$: Observable<string>) =>
    text$
      .debounceTime(200)
      .distinctUntilChanged()
      .map(term => term.length < 2 ? []
        : this.city.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10))
  
     Reset() {
      this.model.username = '';
       this.model.passsword = '';
       this.model.address = '';
       this.model.city = '';
       this.model.country = '';
       this.model.fName = '';
       this.model.lName = '';
       this.model.phoneNumber = '';
       this.model.ssn = '';
       this.model.state = '';
       this.model.zipcode = '';
    }
  
  Cancel() {
    this.roter.navigate(['/login']);
  }
}


