import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { FeedbackService } from 'src/app/services/feedback.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-sellerviewfeedback',
  templateUrl: './sellerviewfeedback.component.html',
  styleUrls: ['./sellerviewfeedback.component.css']
})
export class SellerviewfeedbackComponent implements OnInit {
  feedbackData: any[] = [];
  selectedUser: any = null;
  isModalOpen = false;

  constructor(
    private readonly router: Router,
    private readonly feedbackService: FeedbackService,
    private readonly authService: AuthService,
    private readonly cdRef: ChangeDetectorRef
  ) { }

  ngOnInit(): void {
    this.feedbackService.getFeedbacks().subscribe((data) => {
      this.feedbackData = data;
      console.log(this.feedbackData);
    });
  }

  showProfile(feedback: any, userId: number): void {
    console.log(userId);
    this.authService.getUserDetailsById(userId).subscribe((user) => {
      this.selectedUser = user;
      console.log(this.selectedUser); 
      this.isModalOpen = true;
      console.log(this.isModalOpen); 
      this.cdRef.detectChanges(); 
    });
  }

  closeModal(): void {
    this.isModalOpen = false;
    this.selectedUser = null;
  }
}