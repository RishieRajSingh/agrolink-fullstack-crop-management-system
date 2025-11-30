import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Request } from '../models/request.model';
import { Observable } from 'rxjs';
import { apiUrl } from '../app.constants';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(private readonly http: HttpClient) { }

  addRequest(request: Request): Observable<Request> {
    return this.http.post<Request>(`${apiUrl}/api/request`, request);
  }

  getRequestByUserId(userId: number): Observable<Request[]> {
    return this.http.get<Request[]>(`${apiUrl}/api/request/user/${userId}`);
  }

  deleteRequest(requestId: number): Observable<void> {
    return this.http.delete<void>(`${apiUrl}/api/request/${requestId}`);
  }

  updateRequestStatus(requestId: number, request: Request): Observable<Request> {
    return this.http.put<Request>(`${apiUrl}/api/request/${requestId}`, request);
  }

  getAllRequest(): Observable<Request[]> {
    return this.http.get<Request[]>(`${apiUrl}/api/request`);
  }
}
