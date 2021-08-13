# AODocs Selenium technical test

This project is about assessing QA automation abilities.

## Getting Started

### Prerequisites
* Java JDK: 10 or higher
* Maven: 3.3.9 or higher
* For safari allows remote control via development menu

## Test Objectives
The marketing team ask you to write an automation test to validate how prospect access to our website, and the form to request a demo of the product.
 
The test steps are:
 1. Search AODocs in Google
 2. In the result, open the website www.aodocs.com
 3. Into the website click on the "Request a demo" button
 4. Fill the form with:
     * your first name
     * set empty in the "Last Name" field
     * fill a random string in the "Your Email" field
     * choose a value in Company Size
 5. Check the error messages

## Notes
In this project you can find a utility class "WebDriverUtility" to help you to start a browser locally.
To write the test, you need to use Junit 5 already configured in pom.xml

## Notes 2 :
An error occurs when launch tests by Junit instead of Maven : org.junit.platform.commons.JUnitException: TestEngine with ID 'junit-vintage' failed to discover tests...
Solution of this issue below :
 => Replace "AfterAll" and "BeforeAll" annotations by "After" and "Before" + remove "@TestInstance(TestInstance.Lifecycle.PER_CLASS)
" and update/manage imports (remove "import org.junit.jupiter.api.*;" and use "import org.junit.*;")

## TODO Notes 3 : Several ways to execute tests => Cli via "mvn clean test", Run config (maven or Junit
configuration), Maven panel, Right click in project explorer then 'Run'
