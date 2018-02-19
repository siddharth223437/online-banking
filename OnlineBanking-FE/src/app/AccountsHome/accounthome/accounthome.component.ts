import { GenericService } from '../../Services/generic.service';
import { NavbarService } from '../../Services/navbar.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-accounthome',
  templateUrl: './accounthome.component.html',
  styleUrls: ['./accounthome.component.css']
})
export class AccounthomeComponent implements OnInit {
  loggedUser = localStorage.getItem('userPricipal');
  user_arr: any = [];
  transactionArr: any = [];
  constructor(private genericService: GenericService, public nav: NavbarService, private router: Router) { }

  ngOnInit() {
    this.nav.show();
    this.showAccountPage();
   
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
  
  
  primaryTransactionList() {
    this.router.navigate(['/primarytransaction']);
  }
  
  savingsTransactionList() {
    this.router.navigate(['/savingstransaction']);
  }

}
