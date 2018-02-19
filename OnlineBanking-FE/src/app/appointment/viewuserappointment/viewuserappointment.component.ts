import { GenericService } from '../../Services/generic.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-viewuserappointment',
  templateUrl: './viewuserappointment.component.html',
  styleUrls: ['./viewuserappointment.component.css']
})
export class ViewuserappointmentComponent implements OnInit {

  userApp: any = [];
  cancel_sts;
  cancel_msg;
  constructor(private genericService: GenericService, private router: Router) { }

  ngOnInit() {
    this.viewUserAppointment();
  }
  
  viewUserAppointment() {
    this.genericService.viewUserAppointmentService().subscribe(
    (resp) => {
    this.userApp = resp['responseObject'];
      console.log('user app list', this.userApp);
    }
    );
  }
  
  cancelAppointment(id) {
    this.genericService.cancelAppointmentService(id).subscribe(
    (resp) => {
    this.cancel_sts = resp['status'];
      this.cancel_msg = resp['message'];
      console.log('Canceled', resp);
      this.viewUserAppointment();
    }
    );
  }

}
