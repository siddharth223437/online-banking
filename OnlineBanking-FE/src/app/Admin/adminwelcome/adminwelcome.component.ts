import { ResisterUser } from '../../Register/register/resisteruser';
import { GenericService } from '../../Services/generic.service';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-adminwelcome',
  templateUrl: './adminwelcome.component.html',
  styleUrls: ['./adminwelcome.component.css']
})
export class AdminwelcomeComponent implements OnInit {
  usersArr: any = [];
 model = new ResisterUser();
  closeResult: string;
  update_status;
  update_message;
  constructor(private router: Router, private genericService: GenericService, private _activatedRoutes: ActivatedRoute, private modalService: NgbModal) { }

  ngOnInit() {
    this.showAllUsers();
  }
  
  showAllUsers() {
    this.genericService.allUsersByRoleService('USER').subscribe(
    (resp) => {
    this.usersArr = resp['responseObject'];
      console.log('all users list', this.usersArr);
    }
   );
  }
  
  updateValues(username) {
    this.genericService.findOneUserService(username).subscribe(
    (resp) => {
    this.model.username = resp['responseObject'].username;
    this.model.address = resp['responseObject'].address;
    this.model.city = resp['responseObject'].city;
    this.model.country = resp['responseObject'].country;
    this.model.dob = resp['responseObject'].dob;
    this.model.fName = resp['responseObject'].fName;
    this.model.lName = resp['responseObject'].lName;
    this.model.phoneNumber = resp['responseObject'].phoneNumber;
    this.model.state = resp['responseObject'].state;
     console.log('edit model', this.model);
    }
    );
  }
  
  // for model
 
  open(c) {
    this.modalService.open(c);
  }
  
  update() {
    this.genericService.updateUser(this.model).subscribe(
    (resp) => {
    this.update_status = resp['status'];
      this.update_message = resp['message'];
      console.log('updated response is', resp);
      this.showAllUsers();
      
    }
    );
  }

}
