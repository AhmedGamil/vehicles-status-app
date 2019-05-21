import { Component, OnInit } from '@angular/core';
import { Vehicle } from '../vehicle';
import { VEHICLES } from '../mock-vehicles';
import { CUSTOMERS } from '../mock-customer';
import { Customer } from '../customer';
import { CustomerVehicleService } from '../customer-vehicle.service';
import { VehicleStatusService } from '../vehicle-status.service';

@Component({
  selector: 'app-vehicles',
  templateUrl: './vehicles.component.html',
  styleUrls: ['./vehicles.component.css']
})
export class VehiclesComponent implements OnInit {
  customers: Customer[];
  vehicles: Vehicle[];
  customer="";
  status="";


  constructor(private customerVehicleService: CustomerVehicleService, 
    private vehicleStatusService: VehicleStatusService) { }

  ngOnInit() {
    this.getCustomers();
    this.getVehicles();
  }

  getCustomers(): void {
    this.customerVehicleService.getCustomers().subscribe(customers => this.customers = customers); 
  }

  getVehicles(): void {
    this.customerVehicleService.getVehicles().subscribe(vehicles => {this.vehicles = vehicles;
      this.getConnectedVehicles();
      setInterval(() => { 
        this.getConnectedVehicles(); 
      }, 5000);
    }); 
  }

  getConnectedVehicles(): void {
     this.vehicleStatusService.getConnectedVehicles().subscribe(connectedVec => this.fillVehiclesStatus(connectedVec));
  }

  fillVehiclesStatus(connectedVehicles: String[]): void{
    this.vehicles.forEach(vehicle => {
      if(connectedVehicles.includes(vehicle.vin)){
        vehicle.status = 'connected'
      }else{
        vehicle.status = 'disconnected'
      }
    });
  }


}
