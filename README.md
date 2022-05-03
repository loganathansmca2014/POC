# Content Optimizer Web/UI Tests #

This is a Web/UI test suite for the Content Optimizer product.

###Project setup
* Clone the repository using following command: `git clone` [Git](https://gitbucket.tvlport.com/git/loganat.sengottaiyan/ACM_UI_Automation.git)
* Follow google coding standard

### Project structure
```
project-root
|
|-- src/
|   |-- test/
|       |-- java/
|               |-- AirPageLocators -page objects and interactions Airl Rules 
|               |-- BrowserControl - Browser Control framework and environment
|               |-- com.travelport.contentOptimizer.Automation.FunctionlLibrary
|                      |--Air-Air Rule bussiness logic
|                      |--Hotel- hotel Rule bussiness logic
|                      |--Utillity- Scenario creation,Hooks,TM4J file upload,Screenshot,Report Generation
|               |-- HotelPageLocators-page objects and interactions Hotel Rules
|               |-- runner - declared pre-test and post-test actions, hooks
|               |-- steps - step definitions
|               |-- web - page objects and interactions
|       |-- resources/
|           |-- Feature - test scenarios repository
|           |-- Attachment - files translations
|           |-- features/ - test scenarios repository
|           |-- Reporting/ -TM4J information
|           |-- Browser/-environment properties 

```

### Test user credentials
User credentials are stored in property files in `resources/UserCredentials` folder in following format:
`<userName>=<login>/<password>`
* `userName` - user identification within framework
* `login` - user login e.g. email address
* `password` - Base64 encoded password

**Note!** Passwords with special characters e.g. `#`, `!` etc. might throw exceptions when running the tests.
To avoid such cases, convert special characters to `Unicode` and then apply Base64 encoding.

e.g. Original password: `Test#Test` > Unicode password: `Testu0023Test` > Encoded password: `VGVzdHUwMDIzVGVzdA==`

### How do I run the tests via IntelliJ?
* Go to `Run` > `Edit Configurations` > Click on `+` > `TestNg`

Supply following information:

Field     | Value
--------- |---------
Class     | Runner.TestRunner


### Supported configurations

#### Browsers
    - Chrome

#### Environments
    - dev
    - qa
    - PP
#### Test Reports
    - CO-reports
    

#### Browser  Details

\-                 | Accepted values
---------          | ---------    
ChromeBrowser      |  `Yes`
FirefoxBrowser     |  `No`  
InternetExplorer   |  `No`
EdgeBrowser        |  `No`
Environment        |  `dev`, `qa`,`pp`
#### Environment Details

Environment             | Accepted values
---------          | ---------    
DEV      |  `https://acm-qa.tvlport.com`
QA       |  `https://acm-dev.tvlport.com`
PP       |  `https://contentoptimizer-pp.travelport.com`

### Background

Cucumber is a test automation tool following the principles of Behavioural Driven Design and living documentation. Specifications are written in a concise human readable form and executed in TestNg/Maven Goal.

This project allows you to publish the results of a cucumber run as pretty html reports. In order for this to work you must generate a cucumber json report. The project converts the json report into an overview html linking to separate feature files with stats and results.
###Feature Report:

![Tag report](./TestReport.png)

![img_3.png](img_3.png)
![img_4.png](img_4.png)
![img.png](img.png)

![img_1.png](img_1.png)

![img_2.png](img_2.png)
