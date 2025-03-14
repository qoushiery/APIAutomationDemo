# API Automation DEMO Project

This project is an API automation framework for the website [Reqres.in](https://reqres.in) containing test classes Covering CRUD operations for the System APIs. The framework is built using Java and RestAssured for API testing.

## Project Structure

```
.
├── config
│   └── Config.java
├── models
│   └── User.java
├── services
│   └── UserServices.java
├── tests
│   └── TestUserCRUD.java
├── utils
│   ├── ApiActions.java
│   └── RestAssuredUtil.java
├── pom.xml
└── README.md
```

### `config`

- **Config.java**: Contains configuration constants such as the base URL and test user details.

### `models`

- **User.java**: POJO representing the user model used in the tests.

### `services`

- **UserServices.java**: Contains methods to interact with the user-related endpoints like creating a user and fetching a user by ID.

### `tests`

- **TestUserCRUD.java**: Contains the test class to perform CRUD operations on users and validate the responses.

### `utils`

- **ApiActions.java**: Utility class for sending HTTP requests and handling responses.
- **RestAssuredUtil.java**: Utility class for creating RestAssured request specifications.

## Dependencies

The project uses the following dependencies:

- RestAssured: For API testing.
- TestNG: For test execution and assertions.
- Jackson Databind: For JSON processing.

### Maven Dependencies

Add the following dependencies to your `pom.xml` file:

```xml
<dependencies>
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>4.3.3</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.3.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.11.3</version>
    </dependency>
</dependencies>
```

## How to Run

1. Clone the repository.
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA).
3. Ensure that all dependencies are downloaded.
4. Run the `TestUserCRUD` test class to execute the tests.

## Usage

### Create a User

Use the `createUser` method in `UserServices` to create a new user.

```java
Response response = UserServices.createUser("name", "job");
```

### Get User by ID

Use the `getUserById` method in `UserServices` to fetch a user by ID.

```java
Response response = UserServices.getUserById("userId");
```

### Update User Info

Use the `updateUserInfo` method in `UserServices` to update the User Info By ID.

```java
Response response = UserServices.updateUserInfo(updatedUsername, updatedJob, userId);
```