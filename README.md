# TeamTrivia

## Problem Statement
Are you an Information Technology instructor/professor? Are you too busy with grading students work and not enjoying the hip instructor/professor life style? Do you want to enjoy your teaching time with some fun trivia games but just don’t have enough time out of your busy days to come up with COOL questions?

Well, look no further! Team Trivia’s RESTful web service is perfect for all you busy IT instructors/professors that want to have a little fun in the classroom. The RESTful web service will enable you to choose from:
* Different categories like JAVA, JavaScript, PHP, etc.
* Amount of question to generate
* Select types like short answer or true/false
* Select difficulty for questions

Team Trivia’s RESTful web service will help you generate great challenging question to make your students and yourself have fun again in the classroom. So let Team Trivia’s RESTful web service do all the worries and have fun with your students, PLUS Team Trivia will also let you take all the credit.


## Project Objectives
* GET by Category
* GET by Type
* GET by Difficulty

* Team name generator (stretch objective)

## Project Plan
Week 9
- [ ] Create database
- [ ] Populate database (come up with q's, categories, etc)
- [ ] Project structure
- [ ] Start to create Entities/Daos

Week 10
- [ ] Complete entities/daos
- [ ] Determine POST functionality/feasability
- [ ] Create API Functions/Endpoints ??
- [ ] Create simple web app that will consume our API

Week 11
- [ ] Complete API functions
- [ ] POST functions

Week 12
- [ ] Create team name generator
- [ ] Finish up stuff
- [ ] Make presentation

## Database (Tables/Columns)
* Questions
  * QuestionID
  * Question
  * CategoryID
  * DifficultyID
  * TypeID
  * Answer
* Categories
  * CategoryID
  * CategoryName
* Difficulty
  * DifficultyID
  * DifficultyName
* Type
  * TypeID
  * TypeName

## API EndPoints & Usage
Base Url: http://localhost:8080/teamtriviaapi/teamTrivia

### Questions
#### POST - teamTrivia/questions/HTML/create or teamTrivia/questions/JSON/create
  * Returns added item in HTML or JSON
  * Returns id, question, answer

#### teamTrivia/questions/all
  * Returns all items in HTML
  * Returns id, question, answer
  * Option parameters include amount, difficulty, type, category
  * Ex. teamTrivia/questions/all?difficulty=Medium&type=T/F&category=Java Hibernate&amount=10

#### teamTrivia/questions/< id >
  * Returns specific item in HTML
  * Returns all item fields, including id, question, answer, difficulty, type, and category

#### teamTrivia/questions/JSON/all
  * Returns all items in JSON
  * Returns id, question, answer
  * Option parameters include amount, difficulty, type, category
  * Ex. teamTrivia/questions/JSON/all?difficulty=Medium&type=T/F&category=Java Hibernate&amount=10

#### teamTrivia/questions/JSON/< id >
  * Returns specific item in JSON
  * Returns all item fields, including id, question, answer, difficulty, type, and category

#### Notes
  * Filtering on multiple values is not supported. For example, teamTrivia/questions/all?difficulty=Medium,Hard (selecting questions with difficulty of Medium or Hard) will return no results.

### Categories

#### teamTrivia/category/all
  * Returns all items in HTML

#### teamTrivia/category/< id >
  * Returns specific item in HTML

#### teamTrivia/category/JSON/all
  * Returns all items in JSON

#### teamTrivia/category/JSON/< id >
  * Returns specific item in JSON


### Types

#### teamTrivia/type/all
  * Returns all items in HTML

#### teamTrivia/type/< id >
  * Returns specific item in HTML

#### teamTrivia/type/JSON/all
  * Returns all items in JSON

#### teamTrivia/type/JSON/< id >
  * Returns specific item in JSON

### Difficulties

#### teamTrivia/difficulty/all
  * Returns all items in HTML

#### teamTrivia/difficulty/< id >
  * Returns specific item in HTML

#### teamTrivia/difficulty/JSON/all
  * Returns all items in JSON

#### teamTrivia/difficulty/JSON/< id >
  * Returns specific item in JSON

