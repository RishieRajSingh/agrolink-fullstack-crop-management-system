import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { AgroChemicalService } from './agro-chemical.service';

describe('AgrochemicalService', () => {
  let service: AgroChemicalService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(AgroChemicalService);
  });

  fit('Frontend_should_create_agrochemical_service', () => {
    expect(service).toBeTruthy();
  });
});
