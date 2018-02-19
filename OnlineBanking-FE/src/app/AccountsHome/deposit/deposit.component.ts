import { GenericService } from '../../Services/generic.service';
import { DepositAmount } from './DepositAmount';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css']
})
export class DepositComponent implements OnInit {
 
  constructor(private genericService: GenericService, private router: Router) { }
  model = new DepositAmount();
  actType: string[] = ['Primary', 'Savings'];
  deposit_status;
  deposit_msg;
  ngOnInit() {
  }
  
  deposit() {
    console.log('mon', this.model.amount);
    this.genericService.depositService(this.model.amount, this.model.acttype).subscribe(
    (resp) => {
    console.log('deposit success', resp);
      this.deposit_status = resp['status'];
      this.deposit_msg = resp['message'];
    }
    );
  }

}
