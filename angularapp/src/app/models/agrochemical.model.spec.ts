import { AgroChemical } from './agrochemical.model';

describe('AgroChemical Model', () => {

  fit('Frontend_AgroChemical_model_should_create_an_instance', () => {
    // Create a sample AgroChemical object
    const agroChemical: AgroChemical = {
      name: 'Pesticide X',
      brand: 'AgroBrand',
      category: 'Pesticides',
      description: 'Effective against a wide range of pests',
      unit: 'Liter',
      pricePerUnit: 25.99,
    };

    expect(agroChemical).toBeTruthy();

    expect(agroChemical.name).toBe('Pesticide X');
  });

});
