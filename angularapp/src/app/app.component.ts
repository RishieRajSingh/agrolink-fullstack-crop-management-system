import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'AgroLink';

  isLoggedIn: boolean;
  isSeller: boolean;
  isFarmer: boolean;

  constructor(private readonly router: Router, private readonly authService: AuthService) {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.isSeller = this.authService.isSeller();
    this.isFarmer = this.authService.isFarmer();
  }

  reloadCurrentRoute() {
    const currentUrl = this.router.url;
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentUrl]);
    });
  }

  onLogin() {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.isSeller = this.authService.isSeller();
    this.isFarmer = this.authService.isFarmer();
    this.reloadCurrentRoute();
  }

  onLogout() {
    this.authService.logout();
    this.isLoggedIn = false;
    this.isSeller = false;
    this.isFarmer = false;
    this.reloadCurrentRoute();
  }
}
