import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vehicle } from './vehicle';
import { Customer } from './customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerVehicleService {

  private serviceURL = "/customervehicle/"

  constructor(private http: HttpClient) {}

  getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.serviceURL+'customer/');
  }

  getVehicles(): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(this.serviceURL+'vehicle/');
  }
  
}
