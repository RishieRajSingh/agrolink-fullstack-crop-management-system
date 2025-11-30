import { Crop } from './crop.model';

describe('Crop Model', () => {

  fit('Frontend_Crop_model_should_create_an_instance', () => {
    // Create a sample Crop object
    const crop: Crop = {
      cropName: 'Wheat',
      cropType: 'Cereal',
      description: 'High yield wheat variety',
      plantingDate: '2024-03-15',
      userId: 0
    };

    expect(crop).toBeTruthy();

    expect(crop.cropName).toBe('Wheat');
    expect(crop.cropType).toBe('Cereal');
  });

});
