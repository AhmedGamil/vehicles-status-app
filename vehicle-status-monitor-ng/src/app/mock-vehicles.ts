import { Vehicle } from './vehicle';
import { CUSTOMERS } from './mock-customer';


export const VEHICLES: Vehicle[] = [
    { vin: "123", regNumber: "ABC123", customer: CUSTOMERS[0], status: "connected" },
    { vin: "456", regNumber: "EFG456", customer: CUSTOMERS[0], status: "disconnected" },
    { vin: "789", regNumber: "HIJ456", customer: CUSTOMERS[1], status: "disconnected" },
    { vin: "85", regNumber: "KLM956", customer: CUSTOMERS[1], status: "connected" },
];