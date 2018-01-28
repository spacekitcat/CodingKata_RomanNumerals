[Back](../README)

> Using the example that you provided above, tell us about a significant 
decision you made to solve a technical challenge. Give details of 
technologies that you chose and why you chose them.  
(1000 character limit):

The most significant decision I faced during this project was whether to reuse, rewrite or construct a facade around the legacy Java Persistence Api (JPA) entities. The legacy entities are in a complex, hierarchical class model with lots of legacy Hibernate annotations and kludges aren't necessary in Hibernate 5.  
  
Reusing the legacy JPA model as-is simply isn't possible. The existing library uses Hibernate 3 annotations that do not work with Hibernate 5, but the library can't be modified because it's a dependency for the legacy application. I didn't like the idea of forking and modifying the model because of the amount of code duplication involved. Another aspect was that the JPA layer was setup in such a way that it also added the old Hibernate 3 dependencies and configuration files to the classpath. We did start down this path to see if we could abstract the bare model into a project that only has knowledge of JPA and Hibernate annotations, but it became obvious very quickly the sort of risk we were setting the project up for.  
  
I ended up defining brand new JPA entities for my module and selling idea to my colleagues as the correct approach. On the surface it sounds like it might lead to lots of code duplication, but my point was that the only applications that should be anywhere near those database tables should be the API responsible for managing it. Another advantage is that the class model ends up being much smaller for each Microservice and superfluous definitions can be stripped away. The solution is fairly simple when the assumption that we actually needed a shared entity model, but it was only obvious after some experimentations and in depth conversations with the my colleagues. 
