import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Crop } from 'src/app/models/crop.model';
import { CropService } from 'src/app/services/crop.service';

@Component({
  selector: 'app-farmerviewcrop',
  templateUrl: './farmerviewcrop.component.html',
  styleUrls: ['./farmerviewcrop.component.css']
})
export class FarmerviewcropComponent implements OnInit {

  crops: Crop[] = [];
  filteredCrops: Crop[] = [];
  searchText: string = '';
  errorMessage: string = '';
  id = +sessionStorage.getItem('id');

  constructor(private readonly service: CropService, private readonly router: Router) {}

  ngOnInit(): void {
    this.loadCrops();
  }

  loadCrops(): void {
    this.service.getAllCrops(this.id).subscribe(
      (data) => {
        this.crops = data;
        this.filteredCrops = data;
      },
      (error) => (this.errorMessage = 'Error loading crops')
    );
  }

  filterCrops(): void {
    this.filteredCrops = this.crops.filter(crop => 
      crop.cropName.toLowerCase().includes(this.searchText.toLowerCase()) ||
      crop.cropType.toLowerCase().includes(this.searchText.toLowerCase()) ||
      crop.description.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

  editCrop(cropId: number): void {
    this.router.navigate(['/farmeraddcrop'], { queryParams: { id: cropId } });
  }
 
  deleteCrop(cropId: number): void {
    if (confirm('Are you sure you want to delete this crop?')) {
      this.service.deleteCropById(cropId).subscribe(
        (response) => {
          this.crops = this.crops.filter(crop => crop.cropId !== cropId);
          this.filterCrops(); 
          alert(response.message);
        },
        (error) => {
          this.errorMessage = 'Error deleting crop';
          console.error('Delete crop error', error);
        }
      );
    }
  }
}
