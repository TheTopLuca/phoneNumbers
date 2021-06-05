import { Component, OnInit } from '@angular/core';
import { Customer } from './models/Customer';
import {PhoneServiceService} from './phone-service.service'

interface ValidSelection {
  value: string;
  viewValue: string;
}

interface CountrySelection {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  implements OnInit
{
  //Validity drop down list items
  Validity: ValidSelection[] = [
    {value: 'All', viewValue: 'All'},
    {value: 'Valid', viewValue: 'Valid'},
    {value: 'Not Valid', viewValue: 'Not Valid'}
  ];
 //Countries drop down list items
  Countries: CountrySelection[] = [
    {value: 'All', viewValue: 'All'},
    {value: 'Cameroon', viewValue: 'Cameroon'},
    {value: 'Ethiopia', viewValue: 'Ethiopia'},
    {value: 'Morocco', viewValue: 'Morocco'},
    {value: 'Mozambique', viewValue: 'Mozambique'},
    {value: 'Uganda', viewValue: 'Uganda'}
  ];

  title = 'Customers';
  //table column names
  displayedColumns: string[] = ['position', 'name', 'Phone Number', 'Country','Valid','CountryCode'];

  users: Customer[]=[];
  dataSource;
  SelectedCountry=this.Countries[0];
  SelectedValidity=this.Validity[0];
constructor(private phoneService: PhoneServiceService){}

  ngOnInit()
  {
    this.getCustomers();
  }
  //calls service that calls the api to get all customers
  getCustomers()
  {

    this.phoneService.getCustomers().subscribe((data)=>
    {
      this.dataSource=data;

    }),
    (error)=>
    {
      alert("erorr")
    }
  }
   //calls service that calls the api to get all customers with a certain state
  selectValidity(value: string)
  {
    this.SelectedCountry=this.Countries[0];
    if(value=="All")
    {
      this.getCustomers();
    }
    else
    {
      this.phoneService.getValidUsers(value).subscribe((data)=>
    {
      this.dataSource=data;
     }),
     (error=>
    {
      alert("error");
    })

    }

  }
 selectCountry(value: string)
 {
  this.SelectedValidity=this.Validity[0];
   if(value=="All")
   {
     this.getCustomers();
   }
   else
   {
   this.phoneService.getCountryUsers(value).subscribe((data)=>
   {
     this.dataSource=data;
   }),(error=>
   {
     alert("error");
   })
 }
 }

}
