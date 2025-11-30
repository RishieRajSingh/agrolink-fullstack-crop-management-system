import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AgroChemical } from 'src/app/models/agrochemical.model';
import { AgroChemicalService } from 'src/app/services/agro-chemical.service';

@Component({
  selector: 'app-sellerviewchemical',
  templateUrl: './sellerviewchemical.component.html',
  styleUrls: ['./sellerviewchemical.component.css']
})
export class SellerviewchemicalComponent implements OnInit {
  agrochemicals: AgroChemical[] = [];
  filteredAgrochemicals: AgroChemical[] = [];
  searchText: string = '';
  errorMessage: string = '';
  id = +sessionStorage.getItem('id');
  selectedImage: string | null = null;

  constructor(
    private readonly service: AgroChemicalService,
    private readonly router: Router
  ) { }

  ngOnInit(): void {
    this.getAllAgroChemicals();
  }

  getAllAgroChemicals(): void {
    this.service.getAllAgroChemicals().subscribe(
      (data) => {
        this.agrochemicals = data;
        this.filteredAgrochemicals = data;
      },
      (error) => console.error('Error loading agrochemicals', error)
    );
  }

  editChemical(agroChemicalId: number): void {
    this.router.navigate(['/sellereditchemical'], { queryParams: { id: agroChemicalId } });
  }

  deleteChemical(chemical: AgroChemical): void {
    if (confirm('Are you sure you want to delete this agrochemical?')) {
      this.service.deleteAgroChemical(chemical.agroChemicalId).subscribe(
        () => {
          this.agrochemicals = this.agrochemicals.filter(c => c.agroChemicalId !== chemical.agroChemicalId);
          this.filteredAgrochemicals = this.filteredAgrochemicals.filter(c => c.agroChemicalId !== chemical.agroChemicalId); // Updating filteredAgrochemicals
          alert('Agrochemical deleted successfully');
        },
        (error) => console.error('Error deleting agrochemical', error)
      );
    }
  }

  filterChemicals(): void {
    this.filteredAgrochemicals = this.agrochemicals.filter(agrochemical => 
      agrochemical.name.toLowerCase().includes(this.searchText.toLowerCase()) ||
      agrochemical.brand.toLowerCase().includes(this.searchText.toLowerCase()) ||
      agrochemical.category.toLowerCase().includes(this.searchText.toLowerCase()) ||
      agrochemical.description.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }
  showImage(chemical: AgroChemical): void {
    this.selectedImage = chemical.image;
  }

  closePopup(): void {
    this.selectedImage = null;
  }
}
