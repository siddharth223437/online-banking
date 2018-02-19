
import { RegisterComponent } from './Register/register/register.component';
import { AccounthomeComponent } from './AccountsHome/accounthome/accounthome.component';
import { AddrecipientComponent } from './AccountsHome/addrecipient/addrecipient.component';
import { DepositComponent } from './AccountsHome/deposit/deposit.component';
import { PrimarytransactionComponent } from './AccountsHome/primarytransaction/primarytransaction.component';
import { RecipientComponent } from './AccountsHome/recipient/recipient.component';
import { SavingstransactionComponent } from './AccountsHome/savingstransaction/savingstransaction.component';
import { TransferbetactComponent } from './AccountsHome/transferbetact/transferbetact.component';
import { WithdrawComponent } from './AccountsHome/withdraw/withdraw.component';
import { AdminwelcomeComponent } from './Admin/adminwelcome/adminwelcome.component';
import { FulluserdetailComponent } from './Admin/fulluserdetail/fulluserdetail.component';
import { LoggedinuserservicesService } from './AuthGuard/loggedinuserservices.service';
import { VerifyadminguardService } from './AuthGuard/verifyadminguard.service';
import { VerifyuserguardService } from './AuthGuard/verifyuserguard.service';
import { ErrorpageComponent } from './ErrorPage/errorpage/errorpage.component';
import { AppointmentComponent } from './appointment/appointment/appointment.component';
import { ViewallappointmentComponent } from './appointment/viewallappointment/viewallappointment.component';
import { ViewuserappointmentComponent } from './appointment/viewuserappointment/viewuserappointment.component';
import { LoginComponent } from './login/login.component';
import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

export const router: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full'},
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  {path: 'account', component: AccounthomeComponent, canActivate: [LoggedinuserservicesService, VerifyadminguardService]},
  {path: 'admin', component: AdminwelcomeComponent, canActivate: [LoggedinuserservicesService, VerifyuserguardService]},
  {path: 'fulldetailuser/:usname', component: FulluserdetailComponent, canActivate: [LoggedinuserservicesService, VerifyuserguardService]},
  {path: 'deposit', component: DepositComponent, canActivate: [LoggedinuserservicesService, VerifyadminguardService]},
  {path: 'withdraw', component: WithdrawComponent, canActivate: [LoggedinuserservicesService, VerifyadminguardService]},
  {path: 'primarytransaction', component: PrimarytransactionComponent, canActivate: [LoggedinuserservicesService, VerifyadminguardService]},
  {path: 'savingstransaction', component: SavingstransactionComponent, canActivate: [LoggedinuserservicesService, VerifyadminguardService]},
  {path: 'betact', component: TransferbetactComponent, canActivate: [LoggedinuserservicesService, VerifyadminguardService]},
  {path: 'sendwithemail', component: RecipientComponent, canActivate: [LoggedinuserservicesService, VerifyadminguardService]},
  {path: 'add/recipient', component: AddrecipientComponent, canActivate: [LoggedinuserservicesService, VerifyadminguardService]},
  {path: 'appointment', component: AppointmentComponent, canActivate: [LoggedinuserservicesService, VerifyadminguardService]},
  {path: 'userappointent', component: ViewuserappointmentComponent, canActivate: [LoggedinuserservicesService, VerifyadminguardService]},
  {path: 'allappointments', component: ViewallappointmentComponent, canActivate: [LoggedinuserservicesService, VerifyuserguardService]},
  {path: 'error', component: ErrorpageComponent}
];

export const routes: ModuleWithProviders = RouterModule.forRoot(router);
