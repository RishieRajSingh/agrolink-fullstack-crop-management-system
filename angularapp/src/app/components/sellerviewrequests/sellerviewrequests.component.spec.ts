import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SellerviewrequestsComponent } from './sellerviewrequests.component';

describe('SellerviewrequestsComponent', () => {
  let component: SellerviewrequestsComponent;
  let fixture: ComponentFixture<SellerviewrequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SellerviewrequestsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SellerviewrequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
