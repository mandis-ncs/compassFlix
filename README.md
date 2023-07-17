# compassFlix
CompassFlix is a movie rental themed RESTful API for the UOL Compass Challenge 2. Our API can handle data operations using HTTP verbs (GET, POST, PUT, DELETE). In the following steps, you will see how easy it is to register, consult, update, and delete a movie!

### 🛠️ Technologies
The following technologies were used in this project:

* [Java 17](https://www.oracle.com/br/java/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [MongoDB](https://www.mongodb.com/)
* [Git](https://git-scm.com/)


## 🚀 Starting
To test this project on your computer, you should have the following tools installed:
* Git Bash, to clone this project
* MongoDB Compass, to store the database
* An IDE to run the Spring Java code (like IntelliJ or Eclipse)
* A software testing tool (like Postman or Insomnia)

Next, you can clone this repository with this command:

```bash
git clone https://github.com/mandis-ncs/compassFlix.git
```

Now, you can open the directory in your IDE and navigate to the main class called 'CompassflixApplication' and press the 'Run' icon to execute the code! Next, you can open your preferred API testing tool and try the HTTP operations.

(Note: the main class is placed in "br/com/compass/pb/asynchers/compassflix/CompassflixApplication.java")

### ✅ Testing HTTP: Step by step ###
These are the commands you can try in your browser or testing software. We recommend using Postman and following the steps in order.

* ▶️ GET ALL
  With this first command, you can see all the registered movies. Don't worry, we have already prepared the database with them for you.
```bash
http://localhost:8080/compassflix/movies
```
(Note: if you can't see any movies, you can proceed to the POST step to add a new one)

* ▶️ GET BY ID
  To search by ID, you have to copy one of the IDs that you found above and replace where the {id} symbol is:
```bash
http://localhost:8080/compassflix/movies/{id}
```

Here is an example of how the URI will look like:
```bash
http://localhost:8080/compassflix/movies/64b55d94b2f73b6be6056c2d
```

* ▶️ GET BY NAME
  To search by name, just replace the {name} with the movie's name. Here is the command:
```bash
http://localhost:8080/compassflix/movies/{name}
```

If you want, you can try this to search for the 'Peter Pan' film:
```bash
http://localhost:8080/compassflix/movies/search?name=Peter%20Pan
```

* ▶️ POST
  Now, you will register a new movie. You have to use the following URL:
```bash
http://localhost:8080/compassflix/movies
```
Also, send a body with the information of the movie. It accepts JSON Raw Body. You can try the following example:
```bash
{
	"name" : "Encanto",
	"description" : "About the Madrigals, an extraordinary family who live in the charmed place Encanto",
	"genre" : "Animated",
	"duration" : 120,
	"releaseDate" : "2021-11-25",
	"pgRating" : "pg-12"
}
```

* ▶️ PUT
  You have to put the ID of the movie you want to change and also the update body:
```bash
http://localhost:8080/compassflix/movies/{id}
```
```bash
{
	"name" : "Encanto of Disney",
	"description" : "In Madrigal family, we don't talk about Bruno!",
	"genre" : "Animated",
	"duration" : 120,
	"releaseDate" : "2021-11-25",
	"pgRating" : "pg-12"
}
```

* ▶️ DELETE
  To delete a movie, use the same logic as the 'Get By ID'; just copy and paste the ID of the one you want to delete:
```bash
http://localhost:8080/compassflix/movies/{id}
```

### ❌ Exceptions
If you send a bad request or search by an ID that doesn't exist, we provide some advice to prevent errors. For example, if you try the following URI, the error message "That movie doesn't exist!" will appear.
```bash
http://localhost:8080/compassflix/movies/invalid
```

### 💡 Methodologies

In regards of organization:
* This project was planned with Agile Scrum Methodology
* The Trello website was used to monitor and simplify tasks to be done with the Kanban methodology

### 🧪 Tests
To test the funcionality of the CompassFlix API it was used [JUnit](https://junit.org/junit5/) and [Mockito](https://site.mockito.org/). We did single tests for the classes of 'Controller' and 'Service' and Integration Testing (I&T) for the logic of all the code.
* To run the tests in your repository, you can go to the 'CompassflixApplicationTests', press the right button above the name of the class and choose [Run 'CompassflixApplicationTests' with Coverage].
* Otherwise, you can run wich test class separatly.

(Note: the test classes are placed in "src/test")

### 📄 Documentation
The documentation was automatically generated using Swagger. To know more about the CompassFlix API, you can access it by copying the following URL in your browser:
```bash
http://localhost:8080/swagger-ui.html
```

(Note: You can only access it when the project is running)

### ‎😃 Creators
You can see more about us in our profile:
* [Amanda](https://github.com/mandis-ncs)
* [Gabriel](https://github.com/gabriel0silva)
* [Luan](https://github.com/LuanKuhlmann)
* [Pedro](https://github.com/PedroNevesHespanhol)