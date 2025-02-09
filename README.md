# camel-code-gen
Java 17 camel code generator

How to Use?

1. Download the code by Code -> Download Zip option
2. Unzip the code
3. Use any of these command processor tools: Linux Terminal or Git Bash tool 
4. Navigate to the root folder of the project in command processor tool using "cd" command
   Eg: cd /c/apps/camel-code-gen
5. Open converer.properties and update below properties as per tech. requirments and naming conventions. Avoid space and special characters.
   organization=mycompany
   portfolio=p1
   module=mymodule
   service=test  
6. Type below command and click on enter in terminal window:
   ./converter.sh   
7. Find the Camel generated code under the root folder. Import the project in IntelliJ or Eclipse with Java 17 setting
8. Run the application and see you got below service working in chrome/IE browser
   http://localhost:8080/camel/api/sample
   Result:
   {
     "status": "success",
     "message": "Hello. Welcome to Camel World!"
   }

# Additional Connectors
9. Refer special-connectors branch for more connectors
    9.1 SQL Server: Update the SQL Server connector properties in application-local.yaml. SampleProcessor.java has sample JdbcTemplate code.
    9.2 Salesforce: Update the Salesforce connector properteis in application-local.yaml. SampleRoute.java has sample platform event code.
   


