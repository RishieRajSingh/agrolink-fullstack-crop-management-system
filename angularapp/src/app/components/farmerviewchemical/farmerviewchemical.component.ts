
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AgroChemical } from 'src/app/models/agrochemical.model';
import { AgroChemicalService } from 'src/app/services/agro-chemical.service';
import { CropService } from 'src/app/services/crop.service';
import { Crop } from 'src/app/models/crop.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { RequestService } from 'src/app/services/request.service';
import { Request } from 'src/app/models/request.model';
 
@Component({
  selector: 'app-farmerviewchemical',
  templateUrl: './farmerviewchemical.component.html',
  styleUrls: ['./farmerviewchemical.component.css']
})
export class FarmerviewchemicalComponent implements OnInit {
  chemicals: AgroChemical[] = [];
  farmerchemical: FormGroup;
  selectedChemical: AgroChemical | null = null;
  showMorePopup: boolean = false;
  requestPopup: boolean = false;
  crops: Crop[] = [];
  id: number = +sessionStorage.getItem('id');
  req:Request;
 
  constructor(
    private readonly service: AgroChemicalService,
    private readonly cropService: CropService,
    private readonly requestService: RequestService,
    private readonly router: Router,
    private readonly fb: FormBuilder,
    private readonly authservice: AuthService
  ) {}
 
  ngOnInit(): void {
       
    this.getAllAgroChemicals();
    console.log("hello");
   
    this.getCrops();
    this.farmerchemical = this.fb.group({
      cropId: ['', Validators.required],
      quantity: ['', [Validators.required, Validators.min(1)]]
    });
  }
 
  getAllAgroChemicals() {
    this.service.getAllAgroChemicals().subscribe(data => {
      this.chemicals = data;
    });
  }
 
  getCrops() {
    this.cropService.getAllCrops(this.id).subscribe(data => {
      this.crops = data;
      console.log(data);
    });
  }
 
  myRequests() {
    this.router.navigate(['/farmermyrequest']);
    this.getAllAgroChemicals();
  }
 
  showMore(chemical: AgroChemical) {
    this.selectedChemical = chemical;
    this.showMorePopup = true;
  }
 
  closeShowMorePopup() {
    this.showMorePopup = false;
  }
 
  request(chemical: AgroChemical) {
    console.log("hello");
    console.log(chemical);
   
   
    this.selectedChemical = chemical;
    this.requestPopup = true;
  }
 
  closeRequestPopup() {
    this.requestPopup = false;
  }
 
  onSubmit() {
    if (this.farmerchemical.valid) {
      console.log("farmer submitted")
      console.log(this.farmerchemical.value)
      const request: Request = {
        agroChemicalId: this.selectedChemical ? this.selectedChemical.agroChemicalId : null,
        userId: +this.id,
        cropId: +this.farmerchemical.value.cropId,
        quantity: +this.farmerchemical.value.quantity,
        status: 'Pending',
        requestDate: new Date().toISOString()
      };
      console.log(request);
     
 
      this.requestService.addRequest(request).subscribe(
        response => {
          console.log('Request added successfully', response);
          this.myRequests();
          this.closeRequestPopup();
        },
        error => {
          console.error('Error adding request', error);
        }
      );
    }
  }
  get f(){
    return this.farmerchemical.controls;
  }
}
 
 
 