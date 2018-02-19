import { GenericService } from '../../Services/generic.service';
import { SearchModel } from './searchmodel';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-viewallappointment',
  templateUrl: './viewallappointment.component.html',
  styleUrls: ['./viewallappointment.component.css']
})
export class ViewallappointmentComponent implements OnInit {

  allApp: any = [];
  showTables;
  searchResult: any = [];
  search_sts;
  search_msg;
  model = new SearchModel();
  approve_sts;
  approve_msg;
  cancel_sts;
  cancel_msg;
  delete_sts;
  delete_msg;
  constructor(private genericService: GenericService, private router: Router, private modalService: NgbModal) { }

  ngOnInit() {
    this.viewAllAppointment();
  }
  
  viewAllAppointment() {
    this.showTables = true;
    this.genericService.viewAllAppointmentsService().subscribe(
    (resp) => {
    this.allApp = resp['responseObject'];
      console.log('all app', this.allApp);
    }
    );
  }
  
  search() {
    this.showTables = false;
    console.log('ththhht', this.model.appointmentDate);
    this.genericService.searchNameAndStatusService(this.model).subscribe(
    (resp) => {
    this.searchResult = resp['responseObject'];
     console.log(this.searchResult.length);
      if ( this.searchResult.length === 0) {
        console.log('in if condidition');
        this.search_sts = false;
        this.search_msg = 'No Records Found';
      } else {
        this.search_sts = true;

      }
    }
    );
  }
  
  back() {
    this.showTables = true;
    this.search_sts = true;
    this.model.appointmentDate = '';
    this.model.name = '';
    this.model.status = '';
    
  }
  
  findAppointment(id) {
    this.genericService.findAppointmentById(id).subscribe(
    (resp) => console.log('Appointment By Id is', resp)
    );
  }
  
  approveAppointment(id) {
    this.genericService.approveAppointmentService(id).subscribe(
    (resp) => {
    this.approve_sts = resp['status'];
      this.approve_msg = resp['message'];
      console.log('approved', resp);
      this.viewAllAppointment();
    }
    );
  }
  
    cancelAppointment(id) {
    this.genericService.cancelAppointmentService(id).subscribe(
    (resp) => {
    this.cancel_sts = resp['status'];
      this.cancel_msg = resp['message'];
      console.log('Canceled', resp);
      this.viewAllAppointment();
    }
    );
  }
  
  deleteAppointment(id) {
    console.log('delete id is', id);
    this.genericService.deleteAppointmentByIdService(id).subscribe(
    (resp) => {
    this.delete_sts = resp['status'];
      this.delete_msg = resp['message'];
      this.viewAllAppointment();
    }
   );
  }
  
    open(c) {
    this.modalService.open(c);
  }
  
  editAppointment(id) {
    this.genericService.findAppointmentById(id).subscribe(
    (resp) => {
    this.model.accountNumber = resp['responseObject'].accountNumber;
      this.model.appointmentDate = resp['responseObject'].appointmentDate;
      this.model.name = resp['responseObject'].name;
      this.model.reason = resp['responseObject'].reason;
      this.model.status = resp['responseObject'].status;
    }
    );
  }
  
  update() {
    
  }

}
