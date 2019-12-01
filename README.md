**By default all tests run in Chrome. 
You can set up a specific browser by adding -D options to CLI commands:**

-Dbrowser=chrome
-Dbrowser=firefox`


**To run all tests:**
gradle clean test 

1. **Run Articles tests:**
   gradle test --tests "ArticlesTests"

2. **Run Users tests:**
   gradle test --tests "UsersTests"
   
   **Run single test:**
   
gradle test --tests "ArticlesTests.countArticlesTest"

 

   **Create Allure report after tests:**
   
gradle allureReport




