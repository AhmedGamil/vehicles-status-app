# vehicles-status-app
Java application to monitor vehicles connectivity status

Architecture:    

1- config: Spring cloud Config service to centralize configuration for all services  
2- registry: Spring Cloud Eurkea works as discovery service.  
3- gateway: Spring Cloud Zuul Proxy works as gateway for the whole application  
4- customer-vehicle-service: responisble for managing customers with there vehicles  
5- vehicle-status-service: responsible for keeping vehicle status in the system  
6- vehicle-status-monitor-ng: Angular application to display customer's vehicles with their status. cofigured to be embeded in gateway artifact.  
7- vehicle-simulator: works as simulator for vehicles and send random status to the app. it can be run anyway (I will run on my local pc) and will call the app through the load balancer url.  

![design](https://github.com/AhmedGamil/vehicles-status-app/blob/master/design.jpg)



CI/CD Pipeline:  

I implemented Full pipeline from source to production as follow and I used AWS code pipeline: (All phases have to success)
1- Source phase: webhook lisening to any github push command, and it will go to the next phase
2- Test phase: it will run all unit test cases using maven
3- Build phase: will do the following:  
  a- build the angular app and embed the dist into the gateway static folder
  b- will run maven install to build all jar artifacts
  c- will dockerize all artifacts and build the images
  d- will tag all images as latest
  e- will push all images to AWS ECR  

4- Deployment Phase:  
  a- will run all instances as described in the task definition, 
  b- will test the new deployed packages using the health check defined in the load balanceer
  c- once the status is healthy, it will move the traffic to the new inctances (Replacement) 
  d- and will kill the old instances (Original)
    
