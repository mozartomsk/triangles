(c) Konstantinov Pavel, 2017.

Triangles project is realization of TradeShift task.

Task:

> Triangle challenge
> Write a program that will determine the type of a triangle. It should take the lengths of the triangle's three sides as input, and return whether the triangle is equilateral, isosceles or scalene.
> We are looking for solutions that showcase problem solving skills and structural considerations that can be applied to larger and potentially more complex problem domains. Pay special attention to tests, readability of code and error cases.
> The resulting code will be used as the basis for broader discussions during the interview. Therefore you should be prepared to discuss your choices.




Project structure:

- Triangle core: The core functional module, containing spring beans, that implements logic, tests.
- Runners: modules to run functional in some certain environment.
  - Triangles agrs runner: run program as a command line application that gets triangle sides as application arguments.
    to run: exec command: "java -cp 'argsrunner/*:argsrunner/lib/*' ru.mozar.triangle.TrianglesCommandLineRunner 1 2 3"
