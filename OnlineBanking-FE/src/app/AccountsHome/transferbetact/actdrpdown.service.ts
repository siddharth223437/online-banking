import { Injectable } from '@angular/core';
import { Account } from './account';
import { BasedOnAccount } from './basedonaccount';

@Injectable()
export class ActdrpdownService {

  constructor() { }
  
  getAccounts() {
    return[
    new Account(1, 'Primary'),
    new Account(2, 'Savings')
    ];
  }
  
  getDropdownAct() {
    return[
    new BasedOnAccount(1, 1, 'Savings'),
    new BasedOnAccount(2, 2, 'Primary')
    ];
  }

}
