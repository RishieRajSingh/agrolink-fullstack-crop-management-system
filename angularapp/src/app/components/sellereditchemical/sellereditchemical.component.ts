import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AgroChemicalService } from 'src/app/services/agro-chemical.service';

@Component({
  selector: 'app-sellereditchemical',
  templateUrl: './sellereditchemical.component.html',
  styleUrls: ['./sellereditchemical.component.css']
})
export class SellereditchemicalComponent implements OnInit {
  updateChemicalForm:FormGroup;
  agroChemicalId:number;

  constructor(private readonly formBuilder:FormBuilder,private readonly service:AgroChemicalService,private readonly router:Router,private readonly activatedRoute:ActivatedRoute) {
    this.updateChemicalForm= this.formBuilder.group({
      name:['', [Validators.required, Validators.pattern('^[A-Za-z\s]+$')]],
      brand:['', [Validators.required, Validators.pattern('^[A-Za-z\s]+$')]],
      category:['', Validators.required],
      description:['', [Validators.required, Validators.pattern('^[A-Za-z\s0-9]+$')]],
      unit:['', Validators.required],
      pricePerUnit:['', [Validators.required,Validators.min(1)]],
      image:['', Validators.required]
    })
    this.agroChemicalId = +this.activatedRoute.snapshot.paramMap.get('id');
   }
  ngOnInit(): void {
    this.getAgroChemicalById(this.agroChemicalId);
  }
  getAgroChemicalById(agroChemicalId){
    this.service.getAgroChemicalById(agroChemicalId).subscribe((chemical) => {
      this.updateChemicalForm.patchValue({
        name:chemical.name,
        brand:chemical.brand,
        category:chemical.category,
        description:chemical.description,
        unit:chemical.unit,
        pricePerUnit:chemical.pricePerUnit,
        image:chemical.image
      });
    })
  }

  updateAgroChemical(): void {
    if (this.updateChemicalForm.valid) {
      console.log("update works");
      this.service.updateAgroChemical(this.agroChemicalId, this.updateChemicalForm.value).subscribe(() => {
        this.router.navigate(['/sellerviewchemical']);
      })
    }
  }

  get f() {

    return this.updateChemicalForm.controls;
    
  }

}
