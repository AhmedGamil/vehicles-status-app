# Vehicles Status App
Java application to monitor vehicles connectivity status

## Projects:    

1- config: Spring cloud Config service to centralize configuration for all services  
2- registry: Spring Cloud Eurkea works as discovery service.  
3- gateway: Spring Cloud Zuul Proxy works as gateway for the whole application  
4- customer-vehicle-service: responisble for managing customers with their vehicles  
5- vehicle-status-service: responsible for keeping vehicle status in the system  
6- vehicle-status-monitor-ng: Angular application to display customer's vehicles with their status. cofigured to be embeded in gateway artifact.  
7- vehicle-simulator: works as simulator for vehicles and send random status to the app. it can be run anyway (I will run on my local pc) and will call the app through the load balancer url.  

## Cloud Design:    

![design](https://github.com/AhmedGamil/vehicles-status-app/blob/master/design.jpg)

I have used AWS ECS Fargate for cloud deployment. It's Container Management Service with all options we could need

1- I have created the following components:  
1- ECS cluster for our application  
2- VPC for networking management with 2 subnets in 2 different availability zones  
3- Task definition which describe what are the images to be deployed and the dependancy between them  
4- Fargate Service to run the tasks/ mange networking/ manage the deployments and auto scaling  
5- http Load balancer with 2 target groups one for the original and the other for replacement during auto-deploy process. it's    responisble for load balancening and checking the services health too and will replace any unhealthy task.  
(Note: I am running only one task, because this service is not free)  

## CI/CD Pipeline:  

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
  a- pull images from AWS ECR
  b- will run all instances as described in the task definition,  
  c- will test the new deployed packages using the health check defined in the load balancer  
  d- once the status is healthy, it will move the traffic to the new inctances (Replacement)   
  d- and will kill the old instances (Original)  
    
## How to Run:    
1- You can check the portal from this url:  
http://ec2co-ecsel-1cmpxr74dz3to-1272201801.us-east-2.elb.amazonaws.com/

2- I have created aws user with read capabilitis. Please use the following url to login:  
https://905806897463.signin.aws.amazon.com/console
  
Username: alten-user   
Password: 'a|T4d&3RI2W  

3- After Login: you can open the cluster view from this url so you can check the running instances:  
https://us-east-2.console.aws.amazon.com/ecs/home?region=us-east-2#/clusters/alten/services  

4- You can check the pipeline workflow from here from here:  
https://us-east-2.console.aws.amazon.com/codesuite/codepipeline/pipelines/alten-vehicles-ci-cd-pipel/view  
as mentioned the pipeline will start with any push command to the git repo  


