import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Crop } from 'src/app/models/crop.model';
import { CropService } from 'src/app/services/crop.service';
 
@Component({
  selector: 'app-farmeraddcrop',
  templateUrl: './farmeraddcrop.component.html',
  styleUrls: ['./farmeraddcrop.component.css']
})
export class FarmeraddcropComponent implements OnInit {
  isEditing: boolean = false;
  cropForm: FormGroup;
  errorMessage: string = '';
  crop: Crop = {
    cropId: 0,
    cropName: '',
    cropType: '',
    description: '',
    plantingDate: '',
    userId: 0
  };
  id: number;
  todayDate: string;
  constructor(private readonly cropService: CropService, private readonly formBuilder: FormBuilder, private readonly router: ActivatedRoute, private readonly route: Router) { }
 
  ngOnInit(): void {
    this.router.queryParams.subscribe(params => {
      this.id = params['id'];
    });
    if (this.id) {
      this.isEditing = true;
      this.loadCrop(this.id);
    }
    this.cropForm = this.formBuilder.group({
      cropName: ['', [Validators.required, Validators.pattern('^[A-Za-z\\s]+$')]],
      cropType: ['', Validators.required],
      description: ['', [Validators.required, Validators.pattern('^[A-Za-z\\s0-9]+$')]],
      plantingDate: ['', Validators.required]
    });
    this.setTodayDate();
  }
 
  setTodayDate(): void {
    const today = new Date();
    const day = String(today.getDate()).padStart(2, '0');
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const year = today.getFullYear();
    this.todayDate = `${year}-${month}-${day}`;
  }
 
  loadCrop(cropId: number): void {
    this.cropService.getCropById(cropId).subscribe(
      (data) => {
        this.crop = data;
        this.cropForm.patchValue(this.crop);
      },
      (error) => (this.errorMessage = 'Error loading crop')
    );
  }
 
  addOrUpdateCrop(): void {
    if (this.isEditing) {
      this.cropService.updateCrop(this.crop.cropId, this.cropForm.value).subscribe(
        () => this.closeModal(),
        (error) => (this.errorMessage = 'Error updating crop')
      );
    } else if(this.cropForm.valid){
        this.crop = this.cropForm.value;
        this.crop.userId = +sessionStorage.getItem('id');
        console.log(this.crop);
       
        this.cropService.addCrop(this.crop).subscribe(() => {
          this.route.navigate(['/farmerviewcrop']);
        })   
    }
  }
 
  closeModal(): void {
    alert(this.isEditing ? 'Crop updated successfully' : 'Crop added successfully');
    this.route.navigate(['/farmerviewcrop']);
  }
 
  get f() {
    return this.cropForm.controls;
  }
}