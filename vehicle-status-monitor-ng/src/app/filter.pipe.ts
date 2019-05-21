import { Pipe, PipeTransform } from '@angular/core';
@Pipe({
  name: 'vehiclesFilter'
})
export class FilterPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    if (!args) {
      return value;
    }
    return value.filter((val) => {
      let rVal = (val.status == args) || 
      (val.customer.name == args);
      return rVal;
    })

  }

}