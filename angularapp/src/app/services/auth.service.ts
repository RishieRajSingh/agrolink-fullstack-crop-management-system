import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';
import { Login } from '../models/login.model';
import { apiUrl } from '../app.constants';
 
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  constructor(private readonly httpClient:HttpClient) { }
 
  register(user:User):Observable<any>{
    return this.httpClient.post<any>(`${apiUrl}/api/register`,user);
  }
  login(login:Login):Observable<any>{
    return this.httpClient.post<any>(`${apiUrl}/api/login`,login);
  }
 
  isLoggedIn(): boolean{
    return !!sessionStorage.getItem('token');
  }
 
  isFarmer(): boolean{
    const role = sessionStorage.getItem('role');
    return role==='ROLE_FARMER';
  }
 
  isSeller(): boolean{
    const role = sessionStorage.getItem('role');
    return role === 'ROLE_SELLER';
  }
 
  public getUserDetailsById(userId: number): Observable<User> {
    return this.httpClient.get<User>(`${apiUrl}/api/users/${userId}`);
  }
  public getUserId():number{
    return +sessionStorage.getItem('id');
  }
  logout() {
    sessionStorage.clear();
  }
}