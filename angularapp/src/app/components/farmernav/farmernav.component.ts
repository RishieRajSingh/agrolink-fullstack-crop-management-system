import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { AuthService } from 'src/app/services/auth.service';

@Component({
    selector: 'app-farmernav',
    templateUrl: './farmernav.component.html',
    styleUrls: ['./farmernav.component.css']
})
export class FarmernavComponent implements OnInit {
    
        username: string = sessionStorage.getItem('username');
        constructor(private readonly router: Router, private readonly authService: AuthService, private readonly appComponent: AppComponent) { }
    
        ngOnInit() {
            const logoutBtn = document.getElementById('logoutBtn') as HTMLButtonElement;
            const logoutConfirmPopup = document.getElementById('logoutConfirmPopup') as HTMLDivElement;
            const confirmYes = document.getElementById('confirmYes') as HTMLButtonElement;
            const confirmNo = document.getElementById('confirmNo') as HTMLButtonElement;
    
            logoutBtn.addEventListener('click', (event: Event) => {
                event.preventDefault();
                logoutConfirmPopup.style.display = 'grid';
            });
    
            confirmYes.addEventListener('click', () => {
                logoutConfirmPopup.style.display = 'none';
                this.authService.logout();
                this.appComponent.onLogout();
                this.router.navigate(['/home']);
            });
    
            confirmNo.addEventListener('click', () => {
                logoutConfirmPopup.style.display = 'none';
            });
        }
    
        onCropChange(event: Event) {
            const selectElement = event.target as HTMLSelectElement;
            const value = selectElement.value;
            if (value) {
                this.router.navigate([`/farmer${value}`]);
            }
        }
    
        onFeedbackChange(event: Event) {
            const selectElement = event.target as HTMLSelectElement;
            const value = selectElement.value;
            if (value === 'postfeedback') {
                this.router.navigate(['/farmeraddfeedback']);
            } else if (value === 'viewfeedback') {
                this.router.navigate(['/farmerviewfeedback']);
            }
        }
    }