# PokemonAssignment

Language: java 8

Instructions for running:
1. Execute /src/main/java/com/skawatra/compass/part_1/PokemonSchema.sql on sqlite3 command line
2. Uncomment test files
 - /src/test/java/com/skawatra/compass/part_2/PokemonPushTest.java
 - /src/test/java/com/skawatra/compass/part_3/PokemonPullTest.java
3. Execute test files in order mentioned below
 - /src/test/java/com/skawatra/compass/part_2/PokemonPushTest.java
  - Test whenInvalidValidNumPokemonThenFalse() will not push data to db and program will throw out false successfully
  - Test whenValidNumPokemonThenTrue() will push data to db created in Step 1.
 - /src/test/java/com/skawatra/compass/part_3/PokemonPullTest.java
  - Test whenStoredPokemonThenQ1Results() - Q1 resulted in 6 types of pokemon types. The test just checks the size of the output
  - Test whenStoredPokemonThenQ2Results() - Q2 resulted in 6 types of pokemon types. The test just checks the size of the output
  - Test whenStoredPokemonThenQ3Results() - Q3 resulted in 15 types of pokemon. The test just checks the size of the output
  - In all the cases - results arraylist variable has an output for the questions.
