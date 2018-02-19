import { GenericService } from '../../Services/generic.service';
import { NavbarService } from '../../Services/navbar.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-savingstransaction',
  templateUrl: './savingstransaction.component.html',
  styleUrls: ['./savingstransaction.component.css']
})
export class SavingstransactionComponent implements OnInit {
  
  loggedUser = localStorage.getItem('userPricipal');
  user_arr: any = [];
  transactionArr: any = [];

  constructor(private genericService: GenericService, public nav: NavbarService, private router: Router) { }

  ngOnInit() {
    this.showTransactions();
    this.showAccountPage();
  }
  
  showAccountPage() {
    console.log('showAccount', this.loggedUser);
    this.genericService.showWelcomeService(this.loggedUser).subscribe(
    (resp) => {
    this.user_arr = resp['responseObject'];
    localStorage.setItem('act_num', resp['responseObject'].savingsAccount.accountNumber);
    this.user_arr = Array.of(this.user_arr);
    }
    );
  }
  
  showTransactions() {
    this.genericService.transactionServiceSavings().subscribe(
    (resp) => {
    this.transactionArr = resp['responseObject'];
      console.log('transactionList', resp);
    }
    );
  }

}
