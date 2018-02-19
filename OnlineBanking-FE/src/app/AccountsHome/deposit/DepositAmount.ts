export class DepositAmount {
  constructor(
              public amount: string = '',
              public acttype: string = '',
              public actNum: string = localStorage.getItem('act_num')
             ) {}
}
