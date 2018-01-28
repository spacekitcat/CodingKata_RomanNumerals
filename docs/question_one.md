[Back](../README)

> We're looking for people with a real passion for collaboratively creating great software.
> Please give an example of a software component you have designed and written from concept
> to deployment, outlining the steps you took.  
> (1000 character limit):
  
To give a little bit of a background, my team recently started a large project to replace a large, monolithic legacy web application with a micro service architecture. The team I'm a member of is usually divided into two or three smaller autonomous groups on an ad hoc basis. I've been working with 2 other developers to design and implement the new micro services, while the other 3 other developers on my team have been working with a 3rd party vendor to design and build a lightweight frontend to consume the new services.  
  
The component I mentioned earlier provides a REST API for finding and accessing legal and marketing documents for each of the policy configurations we offer. I was responsible for designing and developing this component, although the lead developer did offer his input along with code reviews. I worked very closely with the other two developers to ensure that the new services provided a consistent interface as well as using common core technologies such as Spring Boot, Hibernate, Swagger etc.  
  
The specification for the new API was created through discussion and collaboration with the business decision makers, business analysts, 3rd party vendors and the rest of my team. The 3rd party vendor along with my own colleagues needed to be involved with the requirements gathering process because, ultimately they know what information they'll need to access. We worked through this discussing how the new front end is going to work and how they envision the integration with the new APIs. Once we had that agreed, we worked with the business analyst to define the core requirements, which we then refined through an iterative, feedback based process.  
  
The Document API was developed with a heavy emphasis on Test Driven Development and the use of the latest tools and frameworks. My first step was laying down a basic Maven project with the core libraries for database access, logging, automatic coverage and quality reporting tools, environment profile switching and an inversion of Control (IoC) container. Once I had a skeleton running and had implemented the simplest endpoint possible, I worked towards getting the project into version control, continuous integration and creating an automated deploy process to ensure that we knew how to deploy to test and pre-production environments. The continuous integration included a nightly Sonar scan to highlight any issues early on. The deploy process runs on the Urban Deploy platform, which installs the web application to a pre-configured Linux server running Tomcat (we have a standard image, vetted by the internal data security team).  
  
During development, I made a point of setting up frequent demos with my own team, the 3rd party vendor, business analysts and the test engineers to ensure that their needs shaped the development as much as possible.  
  
When the application was cleared for test, I worked very closely with the test engineer to clarify how the API behaved, how it should behave and the business logic that shaped its behavior. The application got signed off by test with 1 minor defect that I was able to fix and redeploy and get it tested by the test engineer before the end of the sprint. The application isn't in production proper yet, but has been deployed to the pre-production environment for the new micro services and front end.
  
  
