# Notes App

A simple RESTful application built with [Spark](http://sparkjava.com) Java micro-framework (not to be confused with [Apache Spark](http://spark.apache.org/)). It allows creating, retrieving, and querying notes.

### System requirements:
- Java 8

### Setting Up

Clone the repository:
```
git clone https://github.com/elbernante/notes-app.git
```

### Launching the App

The repository comes with a precompiled `.jar` file that is ready to launch.

Go to the root directory of the cloned repository and issue the command:

```
java -jar target/notesapp-1.0.jar
```

The app should launch in Spark’s built-in Jetty server and can be accessed at:

```
http://localhost:8888
```

### Compiling the App (Optional)

A ready-to-launch `.jar` file is included in the repository for minimal setup. If you like to compile the app yourself, there is an additional system requirement:

- [Maven >= 3.3](https://maven.apache.org)

After Maven is installed, go to the root directory of the cloned repository and issue the command:

```
mvn clean package
```

This should create `notesapp-1.0.jar` file located inside `target/` directory. Follow the instruction in *“Launching the App.”*


### Backend API

The backend API has the following end-points:

##### Create a New note

```
POST http://localhost:888/api/notes

BODY a note

Returns: a saved note...
```

Example:

```
curl -i -H "Content-Type: application/json" -X POST -d '{"body" : "Pick up milk!"}' http://localhost:8888/api/notes
```

Returns:
```
{
  "id" : 0,
  "body" : "Pick up milk!"
}
```

##### Get an Existing Note
Retrieve a note with a specified ID. Returns null with status `404` if the ID does not exists.

```
GET http:/localhost:8888/api/notes/{id}

Returns: the requested note..
````

Example:

```
curl -i -H "Content-Type: application/json" -X GET http://localhost:8888/api/notes/1
```
Returns:

```
{
  "id" : 1,
  "body" : "Ask Larry about the TPS reports."
}
```

##### Retrieve All Notes:
Returns a list of all crated notes.

```
GET http:/localhost:8888/api/notes

Returns: A list of all notes
```

Example:

```
curl -i -H "Content-Type: application/json" -X GET http://localhost:8888/api/notes
```

Returns:

```
[
  {
    "id" : 0,
    "body" : "Pick up milk!"
  },
  {
    "id" : 1,
    "body" : "Ask Larry about the TPS reports."
  }
]
```

An optional parameter `query` can be passed in to return only the notes that contain the query:

```
curl -i -H "Content-Type: application/json" -X GET http://localhost:8888/api/notes?query=milk
```

will return only the notes that contain the word "milk" in it:
```
[
  {
    "id" : 0,
    "body" : "Pick up milk!"
  }
]
```

### Frontend
The frontend is a single-page app and also served by the same server as the backend. It can be accessed at:

```
http://localhost:8888/index.html
```

It shows the all the Github issues of [AngularJS](https://github.com/angular/angular/issues) repository for the previous 7 days. Only 10 issues are shown at a time. You can navigate through the list of issues using the "Newer" and "Older" buttons. Only the issues created in the past 7 days will be shown.
