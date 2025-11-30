import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CropService } from 'src/app/services/crop.service';

@Component({
  selector: 'app-farmereditcrop',
  templateUrl: './farmereditcrop.component.html',
  styleUrls: ['./farmereditcrop.component.css']
})
export class FarmereditcropComponent implements OnInit {
  
  cropForm: FormGroup;
  cropId: number;
  
  constructor(private readonly formBuilder:FormBuilder, private readonly cropService:CropService, private readonly router:Router, private readonly route:ActivatedRoute) {
    this.cropForm = this.formBuilder.group({
      cropName: ['', [Validators.required, Validators.pattern('^[A-Za-z\s]+$')]],
      cropType: ['', Validators.required],
      description: ['', [Validators.required, Validators.pattern('^[A-Za-z\s0-9]+$')]],
      plantingDate: ['', Validators.required]
    });
    this.cropId = +this.route.snapshot.paramMap.get('cropId');
  }

  ngOnInit(): void {
    this.getCropById(this.cropId);
  }

  getCropById(id:number): void {
    this.cropService.getCropById(id).subscribe((crop) => {
      this.cropForm.patchValue({
        cropName: crop.cropName,
        cropType: crop.cropType,
        description: crop.description,
        plantingDate: crop.plantingDate
      });
    })
  }

  updateCrop(): void {
    if (this.cropForm.valid) {
      console.log("update works");
      this.cropService.updateCrop(this.cropId, this.cropForm.value).subscribe(() => {
        this.router.navigate(['/farmerviewcrop']);
      })
    }
  }

  get f() {
    return this.cropForm.controls;
  }

}
