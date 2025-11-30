import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelleraddchemicalComponent } from './selleraddchemical.component';

describe('SelleraddchemicalComponent', () => {
  let component: SelleraddchemicalComponent;
  let fixture: ComponentFixture<SelleraddchemicalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelleraddchemicalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SelleraddchemicalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
