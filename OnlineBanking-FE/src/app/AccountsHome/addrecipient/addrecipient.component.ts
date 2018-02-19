import { GenericService } from '../../Services/generic.service';
import { AddRecip } from './addrecip';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-addrecipient',
  templateUrl: './addrecipient.component.html',
  styleUrls: ['./addrecipient.component.css']
})
export class AddrecipientComponent implements OnInit {
  model = new AddRecip();
  add_recip_sts;
  add_recip_msg;
  constructor(private genericService: GenericService , private roter: Router) { }

  ngOnInit() {
  }
  
  addRecipient() {
    this.genericService.addRecipientService(this.model.email, this.model.name).subscribe(
    (resp) => {
    console.log(resp['responseObject']);
      this.add_recip_sts = resp['status'];
      this.add_recip_msg = resp['message'];
    }
    );
  }
  
  

}
