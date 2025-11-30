import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AgroChemical } from 'src/app/models/agrochemical.model';
import { AgroChemicalService } from 'src/app/services/agro-chemical.service';

@Component({
  selector: 'app-selleraddchemical',
  templateUrl: './selleraddchemical.component.html',
  styleUrls: ['./selleraddchemical.component.css']
})
export class SelleraddchemicalComponent implements OnInit {
  selleraddForm: FormGroup;
  imageToString: string;
  agrochemical: AgroChemical = { name: '', brand: '', category: '', description: '', unit: '', pricePerUnit: 0, image: '' };
  isEditing: boolean = false;
  id: number;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly agroChemicalService: AgroChemicalService,
    private readonly router: ActivatedRoute,
    private readonly route: Router
  ) { }

  ngOnInit(): void {
    this.router.queryParams.subscribe(params => {
      this.id = params['id'];
    });

    this.selleraddForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.pattern('^[A-Za-z\s]+$')]],
      brand: ['', [Validators.required, Validators.pattern('^[A-Za-z\s]+$')]],
      category: ['', Validators.required],
      description: ['', [Validators.required, Validators.pattern('^[A-Za-z\s0-9]+$')]],
      unit: ['', Validators.required],
      pricePerUnit: [1,[Validators.required,Validators.min(1)]]
    });

    if (this.id) {
      this.isEditing = true;
      this.loadAgroChemical(this.id);
    }
  }

  loadAgroChemical(agroChemicalId: number): void {
    this.agroChemicalService.getAgroChemicalById(agroChemicalId).subscribe(
      (data) => {
        this.agrochemical = data;
        this.selleraddForm.patchValue(this.agrochemical);
      },
      (error) => console.error('Error loading agrochemical', error)
    );
  }

  onFileSelected(event: any): void {
    const file: File = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        this.imageToString = reader.result as string;
      };
      reader.readAsDataURL(file);
    }
  }

  onSubmit(): void {
    if (this.selleraddForm.valid) {
      this.agrochemical = this.selleraddForm.value;
      this.agrochemical.image = this.imageToString;
      console.log(this.agrochemical);
      

      if (this.isEditing) {
        this.agroChemicalService.updateAgroChemical(this.id, this.agrochemical).subscribe(
          () => this.closeModal(),
          (error) => console.error('Error updating agrochemical', error)
        );
      } else {
        this.agroChemicalService.addAgroChemical(this.agrochemical).subscribe(
          () => this.route.navigate(['/sellerviewchemical']),
          (error) => console.error('Error adding agrochemical', error)
        );
      }
    }
  }

  closeModal(): void {
    alert(this.isEditing ? 'Agrochemical updated successfully' : 'Agrochemical added successfully');
    this.route.navigate(['/sellerviewchemical']);
  }

  get f() {
    return this.selleraddForm.controls;
  }
}