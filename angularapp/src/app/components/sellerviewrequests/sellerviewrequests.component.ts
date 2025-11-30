import { Component, OnInit } from '@angular/core';
import { Request } from 'src/app/models/request.model';
import { RequestService } from 'src/app/services/request.service';
import { AuthService } from 'src/app/services/auth.service';
 
@Component({
  selector: 'app-sellerviewrequests',
  templateUrl: './sellerviewrequests.component.html',
  styleUrls: ['./sellerviewrequests.component.css']
})
export class SellerviewrequestsComponent implements OnInit {
  requests: Request[] = [];
  filteredRequests: Request[] = [];
  selectedRequest: Request | null = null;
  showMorePopup: boolean = false;
 
  constructor(
    private readonly requestService: RequestService,
    private readonly authService: AuthService
  ) {}
 
  ngOnInit(): void {
    this.getAllRequests();
  }
 
  getAllRequests() {
    this.requestService.getAllRequest().subscribe((requests) => {
      this.requests = requests;
      this.filteredRequests = [...this.requests];
      console.log(this.requests);
    });
  }
 
  approveRequest(request: Request) {
    request.status = 'Approved';
    console.log('Request approved:', request);
    this.filterByStatus(this.currentStatusFilter);
  }
 
  rejectRequest(request: Request) {
    request.status = 'Rejected';
    console.log('Request rejected:', request);
    this.filterByStatus(this.currentStatusFilter);
  }
 
  currentStatusFilter: string = 'All';
 
  filterByStatus(status: string) {
    this.currentStatusFilter = status;
    if (status === 'All') {
      this.filteredRequests = [...this.requests];
    } else {
      this.filteredRequests = this.requests.filter(request => request.status === status);
    }
  }
 
  showProfileMore(request: Request) {
    this.selectedRequest = request;
    this.showMorePopup = true;
  }
 
  closeShowMorePopup() {
    this.showMorePopup = false;
    this.selectedRequest = null;
  }
}