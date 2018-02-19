import { GenericService } from '../../Services/generic.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-fulluserdetail',
  templateUrl: './fulluserdetail.component.html',
  styleUrls: ['./fulluserdetail.component.css']
})
export class FulluserdetailComponent implements OnInit {

  DataArr: any = [];
  first_name;
  last_name;
  approve_msg;
  approve_status;
  userStatus;
  account_num;
  model_actNum;
  user_deny_status;
  constructor(
  private _activatedRoutes: ActivatedRoute,
  private router: Router,
  private genericService: GenericService
  ) { }

  ngOnInit() {

    this.showUser();
    }
  
  showUser() {
    let u_name = this._activatedRoutes.snapshot.params['usname'];
    console.log('param ', u_name);
    this.genericService.findOneUserService(u_name).subscribe(
    (resp) => {
    this.DataArr = resp['responseObject'];
    this.DataArr = Array.of(this.DataArr);
     this.first_name = resp['responseObject'].fName;
      this.last_name = resp['responseObject'].lName;
      this.account_num = resp['responseObject'].primaryAccount.accountNumber;
      console.log(this.DataArr);
    }
    );  
  }
  
  approveUser() {
    let u_name = this._activatedRoutes.snapshot.params['usname'];
    this.genericService.approveUserService(u_name).subscribe(
    (resp) => {
    this.approve_msg = resp['message'];
      this.approve_status = resp['status'];
      this.userStatus = resp['responseObject'].userStatus;
      this.showUser();
    }
    );
  }
  
  assignActNum() {
    let u_name = this._activatedRoutes.snapshot.params['usname'];
    this.genericService.updateActNumService(u_name, this.model_actNum).subscribe(
    (resp) => {
    console.log('account number assignrd', resp);
      this.showUser();
    }
    );
  }
  
  denyUser() {
    let u_name = this._activatedRoutes.snapshot.params['usname'];
    this.genericService.denyUserService(u_name).subscribe(
    (resp) => {
    this.user_deny_status = resp['status'];
      this.userStatus = resp['responseObject'].userStatus;
      this.showUser();
    }
    );
  }
}



