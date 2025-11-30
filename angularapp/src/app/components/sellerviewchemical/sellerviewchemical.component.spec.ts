import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SellerviewchemicalComponent } from './sellerviewchemical.component';

describe('SellerviewchemicalComponent', () => {
  let component: SellerviewchemicalComponent;
  let fixture: ComponentFixture<SellerviewchemicalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SellerviewchemicalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SellerviewchemicalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
