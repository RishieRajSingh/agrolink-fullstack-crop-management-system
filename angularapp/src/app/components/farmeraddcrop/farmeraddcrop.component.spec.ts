import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FarmeraddcropComponent } from './farmeraddcrop.component';

describe('FarmeraddcropComponent', () => {
  let component: FarmeraddcropComponent;
  let fixture: ComponentFixture<FarmeraddcropComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FarmeraddcropComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FarmeraddcropComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
