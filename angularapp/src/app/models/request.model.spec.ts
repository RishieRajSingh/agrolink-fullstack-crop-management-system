import { Request } from './request.model';

describe('Request Model', () => {

  fit('Frontend_Request_model_should_create_an_instance', () => {
    // Create a sample Request object
    const request: Request = {
      quantity: 50,
      status: 'Pending',
      requestDate: '2024-07-11'
    };

    expect(request).toBeTruthy();

    expect(request.status).toBe('Pending');
  
  });

});
