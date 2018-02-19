import { Account } from './account';
export class TransferToAct {
  constructor(
              public amount: string = '',
              public act1: string = '',
              public act2: string = '',
              public actNum: string = localStorage.getItem('act_num')
              ) {}
}
