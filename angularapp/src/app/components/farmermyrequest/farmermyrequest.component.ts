import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RequestService } from 'src/app/services/request.service';
import { Request } from 'src/app/models/request.model';

@Component({
  selector: 'app-farmermyrequest',
  templateUrl: './farmermyrequest.component.html',
  styleUrls: ['./farmermyrequest.component.css']
})
export class FarmermyrequestComponent implements OnInit {
  requestdata: Request[] = [];

  userId = +sessionStorage.getItem('id');
  constructor(private readonly service: RequestService, private readonly router: Router) {}

  ngOnInit(): void {
    this.getAllRequests();
  }

  getAllRequests() {
    this.service.getRequestByUserId(this.userId).subscribe(data => {
      this.requestdata = data;
      console.log(this.requestdata);
      
    });
  }

  deleteRequest(requestId: number) {                
    this.service.deleteRequest(requestId).subscribe(() => {
        this.getAllRequests();                  
      }
    );
    }
}

