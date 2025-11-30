import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SellerviewfeedbackComponent } from './sellerviewfeedback.component';

describe('SellerviewfeedbackComponent', () => {
  let component: SellerviewfeedbackComponent;
  let fixture: ComponentFixture<SellerviewfeedbackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SellerviewfeedbackComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SellerviewfeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
