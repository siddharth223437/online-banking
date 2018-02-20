import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { routes } from './app.router';
import { FormsModule, FormGroup, FormControl } from '@angular/forms';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './Register/register/register.component';
import { AuthService } from './Services/auth.service';
import { GenericService } from './Services/generic.service';
import { NavbarService } from './Services/navbar.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import {HttpModule} from '@angular/http';
import { AccounthomeComponent } from './AccountsHome/accounthome/accounthome.component';
import { LoggedinuserservicesService } from './AuthGuard/loggedinuserservices.service';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { AdminwelcomeComponent } from './Admin/adminwelcome/adminwelcome.component';
import { NavbarComponent } from './Navbar/navbar/navbar.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { FulluserdetailComponent } from './Admin/fulluserdetail/fulluserdetail.component';
import { DepositComponent } from './AccountsHome/deposit/deposit.component';
import { VerifyadminguardService } from './AuthGuard/verifyadminguard.service';
import { VerifyuserguardService } from './AuthGuard/verifyuserguard.service';
import { ErrorpageComponent } from './ErrorPage/errorpage/errorpage.component';
import { WithdrawComponent } from './AccountsHome/withdraw/withdraw.component';
import { PrimarytransactionComponent } from './AccountsHome/primarytransaction/primarytransaction.component';
import { SavingstransactionComponent } from './AccountsHome/savingstransaction/savingstransaction.component';
import { ActdrpdownService } from './AccountsHome/transferbetact/actdrpdown.service';
import { TransferbetactComponent } from './AccountsHome/transferbetact/transferbetact.component';
import { RecipientComponent } from './AccountsHome/recipient/recipient.component';
import { AddrecipientComponent } from './AccountsHome/addrecipient/addrecipient.component';
import { AppointmentComponent } from './appointment/appointment/appointment.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { ViewuserappointmentComponent } from './appointment/viewuserappointment/viewuserappointment.component';
import { ViewallappointmentComponent } from './appointment/viewallappointment/viewallappointment.component';
import { GeneratestatementsComponent } from './Admin/generatestatements/generatestatements.component';





@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AccounthomeComponent,
    AdminwelcomeComponent,
    NavbarComponent,
    FulluserdetailComponent,
    DepositComponent,
    ErrorpageComponent,
    WithdrawComponent,
    PrimarytransactionComponent,
    SavingstransactionComponent,
    TransferbetactComponent,
    RecipientComponent,
    AddrecipientComponent,
    AppointmentComponent,
    ViewuserappointmentComponent,
    ViewallappointmentComponent,
    GeneratestatementsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    routes,
    FormsModule,
    HttpModule,
    NgbModule.forRoot(),
    NgxPaginationModule,
    OwlDateTimeModule, 
    OwlNativeDateTimeModule,
    BrowserAnimationsModule
  ],
  providers: [
    GenericService,
    AuthService,
    NavbarService,
    LoginComponent,
    LoggedinuserservicesService,
    VerifyadminguardService,
    VerifyuserguardService,
    ActdrpdownService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
