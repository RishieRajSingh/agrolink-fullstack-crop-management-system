import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FarmermyrequestComponent } from './farmermyrequest.component';

describe('FarmermyrequestComponent', () => {
  let component: FarmermyrequestComponent;
  let fixture: ComponentFixture<FarmermyrequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FarmermyrequestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FarmermyrequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
