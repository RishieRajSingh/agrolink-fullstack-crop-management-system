import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { FeedbackService } from 'src/app/services/feedback.service';
 
@Component({
  selector: 'app-farmerviewfeedback',
  templateUrl: './farmerviewfeedback.component.html',
  styleUrls: ['./farmerviewfeedback.component.css']
})
export class FarmerviewfeedbackComponent implements OnInit {
 
  feedbacks: any[] = [];
  showPopup = false;
  feedbackToDelete: number| null = null;
  userId:number = +sessionStorage.getItem('id');
  constructor(private readonly service: FeedbackService,private readonly authService:AuthService) {}
  ngOnInit(): void {
    // this.userId = this.authService.getUserId();
    this.getFeedbacks();
  }
 
  getFeedbacks() {
    this.service.getAllFeedbacksByUserId(this.userId).subscribe((data) => {

      this.feedbacks = data;
      
      console.log(this.feedbacks);
      
    });
  }
 
  openPopup(feedbackId: number): void {
    this.showPopup = true;
    this.feedbackToDelete = feedbackId;
  }
 
  closePopup(): void {
    window.location.reload();
    this.showPopup = false;
    this.feedbackToDelete = null;
  }
 
  confirmDelete(): void {
    if (this.feedbackToDelete !== null) {
      this.service.deleteFeedback(this.feedbackToDelete).subscribe(() => {
        this.getFeedbacks();
        this.closePopup();
      });
    }
  }
}