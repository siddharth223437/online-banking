import { GenericService } from '../../Services/generic.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgbTypeaheadConfig, NgbTypeahead } from '@ng-bootstrap/ng-bootstrap';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import {Subject} from 'rxjs/Subject';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';

@Component({
  selector: 'app-recipient',
  templateUrl: './recipient.component.html',
  styleUrls: ['./recipient.component.css']
})
export class RecipientComponent implements OnInit {
actType = ['Primary', 'Savings'];
  allRecip = [];
  allRecipEmail: any = [] ;
  transfer_sts;
  transfer_msg;
  selectActType;
  selectEmail;
  selectamt;
  constructor(private genericService: GenericService, config: NgbTypeaheadConfig, private roter: Router) { 
  // config.showHint = true;
  }

  ngOnInit() {
    this.showAllRecipient();
  }
  
  @ViewChild('instance') instance: NgbTypeahead;
  focus$ = new Subject<string>();
  click$ = new Subject<string>();
  
  showAllRecipient() {
    this.genericService.allRecipient().subscribe(
    (resp) => {
    this.allRecip = resp['responseObject'];
      console.log(this.allRecip);
      for ( let i = 0; i < this.allRecip.length; i++) {
        let pu = this.allRecip[i].recipentEmail;
        this.allRecipEmail.push(pu);
        console.log('for loop', this.allRecipEmail);
      }
    }
    );
  }
  
  searchRecip = (text$: Observable<string>) =>
     text$
      .debounceTime(200).distinctUntilChanged()
      .merge(this.focus$)
      .merge(this.click$.filter(() => !this.instance.isPopupOpen()))
      .map(term => (term === '' ? this.allRecipEmail : this.allRecipEmail.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
  
  transferToSomeOneElse() {
    this.genericService.transferToSomeone(this.selectActType, this.selectEmail, this.selectamt).subscribe(
    (resp) => {
    this.transfer_sts = resp['status'];
      this.transfer_msg = resp['message'];
    }
   );
  }
   
}
