import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FarmerviewchemicalComponent } from './farmerviewchemical.component';

describe('FarmerviewchemicalComponent', () => {
  let component: FarmerviewchemicalComponent;
  let fixture: ComponentFixture<FarmerviewchemicalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FarmerviewchemicalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FarmerviewchemicalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
