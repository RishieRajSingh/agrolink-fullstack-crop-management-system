import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FarmeraddfeedbackComponent } from './farmeraddfeedback.component';

describe('FarmeraddfeedbackComponent', () => {
  let component: FarmeraddfeedbackComponent;
  let fixture: ComponentFixture<FarmeraddfeedbackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FarmeraddfeedbackComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FarmeraddfeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
