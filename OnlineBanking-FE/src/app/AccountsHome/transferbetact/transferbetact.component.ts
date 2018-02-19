import { GenericService } from '../../Services/generic.service';
import { NavbarService } from '../../Services/navbar.service';
import { ActdrpdownService } from './actdrpdown.service';
import { TransferToAct } from './transfertoact';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from './account';
import { BasedOnAccount } from './basedonaccount';

@Component({
  selector: 'app-transferbetact',
  templateUrl: './transferbetact.component.html',
  styleUrls: ['./transferbetact.component.css']
})
export class TransferbetactComponent implements OnInit {
  model = new TransferToAct();
 actType: any = ['Primary', 'Savings'];
  selectedAccount: number;
  accounts: Account[];
  basedOnActDrop: BasedOnAccount[];
  transefr_sts;
  transfer_msg;
  constructor(private genericService: GenericService, public nav: NavbarService, private router: Router, private actDropdownService: ActdrpdownService) {
  
   }

  ngOnInit() {
    this.accounts = this.actDropdownService.getAccounts();
  }
  
  onSelect(accountId) {
    console.log('in select', accountId);
    let count = accountId;
    console.log('co', count);
   // console.log('drp', this.actDropdownService.getDropdownAct());
    this.basedOnActDrop = this.actDropdownService.getDropdownAct().filter((item) => item.accountId == count);
    console.log('based', this.basedOnActDrop);
  }
  
  Transfer() {
    console.log('act144', this.selectedAccount);
    let selctActVal = this.actDropdownService.getAccounts().filter((item) => item.id == this.selectedAccount);
    let select_act_name = selctActVal[0].name;
    this.genericService.transferBetActService(select_act_name, this.model.act2, this.model.amount).subscribe(
    (resp) => {
     this.transefr_sts = resp['status'];
      this.transfer_msg = resp['message'];
     // console.log('act1', this.model.act1);
      console.log('act2', this.model.act2);
      console.log(this.model.amount);
    }
    );
  }

}
