import { GenericService } from '../../Services/generic.service';
import { DepositAmount } from '../deposit/DepositAmount';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-withdraw',
  templateUrl: './withdraw.component.html',
  styleUrls: ['./withdraw.component.css']
})
export class WithdrawComponent implements OnInit {
  
  constructor(private genericService: GenericService, private router: Router) { }
  
  model = new DepositAmount();
  actType: string[] = ['Primary', 'Savings'];
  withdrw_sts;
  withdraw_msg;

  ngOnInit() {
  }
  
  withdrwa() {
      console.log('withdrwa model amount', this.model.amount);
    this.genericService.withdrawService(this.model.amount, this.model.acttype).subscribe(
    (resp) => {
    console.log('deposit success', resp);
      this.withdrw_sts = resp['status'];
      this.withdraw_msg = resp['message'];
    }
    );
  }

}
