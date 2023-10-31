# patient-hub

1. Created three controller classes
   BookingSlotController.class -- Contains apis to book slots with doctors.
   DoctorController.class  -- Contains CRUD operations related apis for Doctors.
   PatientController.class  -- Contains CRUD operations related apis for Patients.

2. Used H2 In memory database, after server startup you can login with below details
   http://localhost:8080/h2-console/
   username : sa
   password: password
   connection url:  jdbc:h2:mem:mydb

3. Enabled Swagger to do api operations. http://localhost:8080/swagger-ui/index.html

4. Handled exceptions using Global Exception Handler
   PatientHubExceptionHandler.class

5. Written testcases.
   DoctorControllerTest.class
   PatientControllerTest.class



## Revised Java Problem Statement- Java Developer.pdf
named document has asked problems for which I have provided the solution in this code.
   
