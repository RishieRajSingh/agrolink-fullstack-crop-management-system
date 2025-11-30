import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private readonly authService: AuthService) {}
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    
    if(this.authService.isLoggedIn()){
      let jwtToken = sessionStorage.getItem('token');
      const cloneRequest=request.clone({
        setHeaders:{
          'Authorization':`Bearer ${jwtToken}`
        }
      });
      return next.handle(cloneRequest);
    }
    return next.handle(request);
  }
}
