export class AppointCreate {
  constructor(
               public name: string = '',
               public accountNum = localStorage.getItem('act_num'),
               public appointDate: string = '',
               public reason: string = ''
              ) {}
    
}
