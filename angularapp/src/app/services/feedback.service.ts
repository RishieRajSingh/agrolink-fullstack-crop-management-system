import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Feedback } from '../models/feedback.model';
import { apiUrl } from '../app.constants';
 
@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
 
  constructor(private readonly http: HttpClient) { }
 
  sendFeedback(feedback: Feedback): Observable<any> {
    return this.http.post<any>(`${apiUrl}/api/feedback`, feedback);
  }
  getAllFeedbacksByUserId(userId: number): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(`${apiUrl}/api/feedback/user/${userId}`);
  }
 
  deleteFeedback(feedbackId: number): Observable<any> {
    return this.http.delete<any>(`${apiUrl}/api/feedback/${feedbackId}`);
  }
 
  getFeedbacks(): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(`${apiUrl}/api/feedback`);
  }
}