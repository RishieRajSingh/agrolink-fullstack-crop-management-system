import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';
 
@Component({
  selector: 'app-farmeraddfeedback',
  templateUrl: './farmeraddfeedback.component.html',
  styleUrls: ['./farmeraddfeedback.component.css']
})
export class FarmeraddfeedbackComponent implements OnInit {
 
  feedbackForm: FormGroup;
  feedback: Feedback;
  constructor(private readonly fb: FormBuilder,private readonly feedbackService:FeedbackService,private readonly router:Router){ }

  ngOnInit(): void {
    this.feedbackForm = this.fb.group({
      feedbackText: ['', [Validators.required, Validators.pattern('^[A-Za-z\s0-9]+$')]]
    });
  }
 
  onSubmit(): void {
    if(this.feedbackForm.valid) {
      this.feedback = this.feedbackForm.value;
      this.feedback.userId = +sessionStorage.getItem('id');      
      this.feedback.date = new Date();
      console.log(this.feedback);     
      this.feedbackService.sendFeedback(this.feedback).subscribe(()=>{
       
        this.router.navigate(['/farmerviewfeedback']);
      });
    }
  }
 
  get f(){
    return this.feedbackForm.controls;
  }
}