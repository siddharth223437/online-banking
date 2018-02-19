import { GenericService } from '../../Services/generic.service';
import { AppointCreate } from './appointcreate';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css']
})
export class AppointmentComponent implements OnInit {

  model = new AppointCreate();
  app_sts;
  app_msg;
  
  constructor(private genericService: GenericService, private router: Router) { }

  ngOnInit() {
  }
  
  createAppointment() {
    console.log(this.model.accountNum);
    console.log(this.model.appointDate);
    console.log(this.model);
    this.genericService.saveAppointmentServiceTest(this.model.name, this.model.accountNum, this.model.appointDate, this.model.reason).subscribe(
    (resp) => {
      console.log('create appointment', resp);
    this.app_msg = resp['message'];
      this.app_sts = resp['status'];
    }
    );
  }
  
  viewUserApp() {
    this.router.navigate(['/userappointent']);
  }

}
