import { Feedback } from './feedback.model';

describe('Feedback Model', () => {

  fit('Frontend_Feedback_model_should_create_an_instance', () => {
    // Create a sample Feedback object
    const feedback: Feedback = {
      feedbackText: 'Great service and support!',
      date: new Date('2024-07-10')
    };

    expect(feedback).toBeTruthy();

    expect(feedback.feedbackText).toBe('Great service and support!');
   
  });

});
