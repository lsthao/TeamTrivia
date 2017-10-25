# TeamTrivia

## Problem Statement
Are you an Information Technology instructor/professor? Are you too busy with grading students work and not enjoying the hip instructor/professor life style? Do you want to enjoy your teaching time with some fun trivia games but just don’t have enough time out of your busy days to come up with COOL questions?

Well, look no further! Team Trivia’s RESTful web service is perfect for all you busy IT instructors/professors that want to have a little fun in the classroom. The RESTful web service will enable you to choose from:
* Different categories like JAVA, JavaScript, PHP, etc.
* Amount of question to generate
* Select types like multiple choice or true/false
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
* Answers
  * AnswerID
  * QuestionID
  * Answer
  * Correct
* Categories
  * CategoryID
  * CategoryName
* Difficulty
  * DifficultyID
  * DifficultyName
* Type
  * TypeID
  * TypeName
