[Back](../README)

> Using the example that you provided above, tell us about how you ensured your software was fit for purpose and of high quality. What did you learn and what would you do differently next time to do a better job?  
(1000 character limit):  
  
To ensure that the module was fit for purpose, I took test driven development very seriously from the beginning.  I put a lot of work into writing integration tests to verify the Spring configuration and to test that the correct resources got exposed with the correct methods (GET is exposed, DELETE is not for instance). I also wrote a checklist for anything I couldn't write an automated test for, so that my developer testing was careful and methodical before each deploy to the environment. The code was also verified by a test engineer who found only 1 minor defect during testing.  
  
If I were to write the API over again, I would have wrote better integration tests around the database access code. I would have liked to have developed integration tests that could configure an embedded database with the same schema as production, so that I could verify the hibernate mappings and database queries worked as expected. The minor defect would have been prevented by a better integration test. I didn't do this in the first place because I didn't have enough time to spend setting it up and getting it to run on the integration test runner.  
  
Another thing I would change, would be the input field values accepted by the API. Some of the parameters are just arbitrary text fields, but it might have been possible to create a sort of canonical master table in the database for some of the values. The API could have limited the range of accepted inputs and the Swagger automatic documentation library could have documented it. The master tables could have populated automatically by the nightly data sync job reads from the system that defines those values in the first place.  
  
  



