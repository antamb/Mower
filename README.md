# Mower
Quiet simple automatic lawn mower engine

## Quick start

![Alt text](https://s-media-cache-ak0.pinimg.com/236x/af/ea/1d/afea1d4b9541696576638b7dbf47c45a.jpg)

### Build the project

This is a maven project so to built it:
```
$ mvn clean install
```
### Run the program

You can simply run the program and it will take by default the TEST file in the resources folder

```
$ mvn exec:java
```

### Run the tests

The project contains some cucumber tests that you can execute as follow:

```
$ mvn test
```

REMARK: You will find the feature file for the cucumber tests in the resources folder of the test package
        You can modify the input information in this file as you wish

