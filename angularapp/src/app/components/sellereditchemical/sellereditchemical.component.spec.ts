import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SellereditchemicalComponent } from './sellereditchemical.component';

describe('SellereditchemicalComponent', () => {
  let component: SellereditchemicalComponent;
  let fixture: ComponentFixture<SellereditchemicalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SellereditchemicalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SellereditchemicalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
