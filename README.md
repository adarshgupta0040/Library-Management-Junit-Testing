# Junit Testing Interview Question Zero to Advanced 
# Junit Testing-

# Imp of testing- 
Bugs free, s/w meets Req, Quality Assurance(Improve Quality), performance, Ensure Working Functionality, Reduce cost.

# Type of s/w testing - 
Functional, Non-Functional, Usability(Ease to use app), Regression(testing done again & again till bugs not found), Acceptance(tested by clients)

Functional Testing- system is tested against the functional req. and specifications irrespect of the code. Each functional testing is tested by providing correct input, expected o/p and then comparing the actual o/p with expected o/p.
What we test - Basic usability, Functions, Error Conditions

# Type of Functn Testing-
Unit Testing- the indivisual unit/module/method/fn of app are tested
Integration - combined indivisual unit are testing as a group
Smoke - basic functionality or feature of app is tested as it ensure that imp fn work properly
Acceptance- same

Example of functional testing - login functionality, Form Validation

# Non Functn Testing - 
verify the way software works and how well it works. eg. Test the performance, user experience
Eg. Security, Reliablity, Available,performance, scalabilty.

Differnces - (Function vs Non Functional)
1. Checks operations and actions of app - checks behavior
2. Based on req. of clients - expectation of clients
3. Test what product does - how 
4. ensure s/w free of bugs - how it perform at high level

..................................................
# Junit Framework-
Junit test is open source framework  for java prog lang and easily integrate with maven.
java developer use this to write and execute automated tests.
2 major version in use - junit4 & 5.

# Annotation used- 
@Test- It is used to mark the method as a test method.
@BeforeEach - It runs before each test method
@BeforeAll - Runs once before all the test methods in a class.
@AfterEach- Runs after each test method(cleanup after each tests)
@AfterAll- Runs once after all test methods in the class(Cleanup after all tests)
@disabled - ignores a test method or class

# Assertion to test expected results-
AssertEquals(Expected,actual)- check if 2 val are equal.
AssertTrue(condn)/False - test cond is true/false
assertNull(obj) - test that an obj is null.
assertNotNull(Object) -  Test that an obj is not null.
assertThrows(expectedType, Executable)-  Test a specific exception is thrown.

# Pattern - Arrange,Act,assert

Arrange the input & expected o/p, Actual o/p, assertion to check Expected & actual.
.....................................................
# Mocking - 
Process of developing the objects that act as the mock or clone of real object.
Mock obj returns a dummy data to some dummy input passed to it.

# Type of mock object- 
1. Stub- used to provide a fixed o/p or response for method calls
2. Mock- object with built in assertion to verify behaviour as expected. They are created by open source framework like Mockito etc

# Mokito - 
is java based mocking framework used for junit testing of java application. The main purpose of using this Mockito FW is to simply the development of test by mocking external dependencies and use them in test code
- Enable Mockito annotation - (Else we Get Exception of Null ptr)
@ExtendWith(MockitoExtension.Class ) at Class level
MockitoAnnotations.openMocks()/initMocks() in BeforeEach.

org.mockito dependency for Springmvc app and starter test for Boot

# Mockito Annotations - 
1. @Mock - used to create mock instance/obj of classes & dependencies.(repo in service)
2. @InjectMock- helps in creating the instances of the class under test and inject the mocks annotated with @Mock into it.(service class)
It will inject all the dependency with mock objects and then it will create mock obj of actual class.

Mockito When/Then - used to define the behavior of mock objects.Like if we are in service class and we use repo class then we dont want to execute actual repo call(create a real object) because it may lead to change in DB.
- When is used to specify the method call on mock object that we want to stub.
- Then is used to specify the value that should be return a specific response when method call specified in when clause is made.
..................................................

# Testing Controller Layer-

@ExtendWith(MockitoExtension.Class)
@WebMVCTests(controllers=AuthorController.Class)
@MockBean
AuthorService autherService - create and inject the mock instances of class in a spring application for controller.
@Autowired MockMvc mockMvc - inject the mockmvc instance, which is used to perform requests to the controller.
MockMVC is class provided by spring to test MVC applications without starting a full http server.

# Steps-
#arranging expected user
Mockito.when - mocking the call userservice method and what should be expected return
MockMvc is used to mock ur http call method
first send get req. to controller layer and then whatever o/p is return we can check what is status, content using mockMvcResultMatcher
