import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Customer } from './models/Customer';
import { Observable } from 'rxjs';
import { environment } from '../../src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class PhoneServiceService {

  BASE_URL= environment.url;
  constructor(private httpClient : HttpClient)
  {
  }
  //Get All Customers
  getCustomers():Observable<any>{
    return this.httpClient.get<any>(this.BASE_URL+"customers/getAll");

  }
  //Get Customers Of a certain state
  getValidUsers(validity : string):Observable<any>{
    if(validity=="Valid")
    return this.httpClient.get<any>(this.BASE_URL+"customers/valid/"+true);
    else
      return this.httpClient.get<any>(this.BASE_URL+"customers/valid/"+false);

  }
  //Get Country of a certain country
  getCountryUsers(country : string):Observable<any>{
    return this.httpClient.get<any>(this.BASE_URL+"customers/country/"+country);
  }
}
