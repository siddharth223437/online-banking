import { GenericService } from '../../Services/generic.service';
import { NavbarService } from '../../Services/navbar.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-primarytransaction',
  templateUrl: './primarytransaction.component.html',
  styleUrls: ['./primarytransaction.component.css']
})
export class PrimarytransactionComponent implements OnInit {

  constructor(private genericService: GenericService, public nav: NavbarService, private router: Router) { }
  
  loggedUser = localStorage.getItem('userPricipal');
  user_arr: any = [];
  transactionArr: any = [];
  public isCollapsed = false;

  ngOnInit() {
    this.showAccountPage();
    this.showTransactions();
  }
  
  showAccountPage() {
    console.log('showAccount', this.loggedUser);
    this.genericService.showWelcomeService(this.loggedUser).subscribe(
    (resp) => {
    this.user_arr = resp['responseObject'];
    localStorage.setItem('act_num', resp['responseObject'].primaryAccount.accountNumber);
    this.user_arr = Array.of(this.user_arr);
    }
    );
  }
  
    showTransactions() {
    this.genericService.transactionService().subscribe(
    (resp) => {
    this.transactionArr = resp['responseObject'];
      console.log('transactionList', resp);
    }
    );
  }

}
