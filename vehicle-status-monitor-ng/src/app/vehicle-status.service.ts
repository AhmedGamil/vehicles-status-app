import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VehicleStatusService {

  private serviceURL = "/vehiclestatus/"

  constructor(private http: HttpClient) {}

  getConnectedVehicles(): Observable<String[]> {
    return this.http.get<String[]>(this.serviceURL+'connectedVehicles/');
  }
}
