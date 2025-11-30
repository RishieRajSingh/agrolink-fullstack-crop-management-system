import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{

  loginForm: FormGroup;
  username: string;
  constructor(private formBuilder: FormBuilder, private readonly service: AuthService, private readonly router: Router, private readonly authService: AuthService) {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
  }
 
  get f() { return this.loginForm.controls; }
 
  onSubmit() {
    if (this.loginForm.invalid) {
      return;
    }
    console.log(this.loginForm.value);
    
    this.service.login(this.loginForm.value).subscribe((response)=>{
      const token = response.token;
      
      const base64Url = token.split('.')[1];
      const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
      const jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
      }).join(''));
      const payload = JSON.parse(jsonPayload);

      this.authService.getUserDetailsById(response.userId).subscribe((data)=>this.username=data.username);
      sessionStorage.setItem('token',token);
      sessionStorage.setItem('id',response.userId);
      sessionStorage.setItem('email',payload.sub);
      sessionStorage.setItem('username',response.username);
      sessionStorage.setItem('role',response.role);
      this.router.navigate(['/home']).then(() => {
        location.reload();
      });
    });
  }
}