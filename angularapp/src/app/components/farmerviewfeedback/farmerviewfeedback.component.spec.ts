import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FarmerviewfeedbackComponent } from './farmerviewfeedback.component';

describe('FarmerviewfeedbackComponent', () => {
  let component: FarmerviewfeedbackComponent;
  let fixture: ComponentFixture<FarmerviewfeedbackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FarmerviewfeedbackComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FarmerviewfeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
