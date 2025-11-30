import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FarmerviewcropComponent } from './farmerviewcrop.component';

describe('FarmerviewcropComponent', () => {
  let component: FarmerviewcropComponent;
  let fixture: ComponentFixture<FarmerviewcropComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FarmerviewcropComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FarmerviewcropComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
