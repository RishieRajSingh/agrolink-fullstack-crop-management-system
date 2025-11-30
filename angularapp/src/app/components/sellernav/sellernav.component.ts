import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-sellernav',
  templateUrl: './sellernav.component.html',
  styleUrls: ['./sellernav.component.css']
})
export class SellernavComponent{
  
  username: string = sessionStorage.getItem('username');
  constructor(private readonly authService: AuthService, private readonly router: Router, private appComponent: AppComponent) { }

  onCropChange(event: Event): void {
    const selectElement = event.target as HTMLSelectElement;
    const value = selectElement.value;
    if (value) {
      this.router.navigate([`/seller${value}`]);
    }
  }
  logout() {
    this.authService.logout();
    this.appComponent.onLogout();
    this.router.navigate(['/home']);
  }
}
