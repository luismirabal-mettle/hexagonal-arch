# Shopping Basket

Create a shopping basket http application that allows you to do the following:
- Add items to the basket. They have: name, quantity and unit price
- Calculate total price of the basket

### Notes
- The application is about a single basket
- You can use ints to represent money

### Exercise baseline

A basic micronaut application is provided so that we can focus on the exercise rather than having to work out details
about how to get a micronaut application to work.

It provides the following:
- Micronaut application class that serves as the entrypoint
- Basic http controller with a POST and a GET operation
- DB repository that can talk to an H2 DB.
- Db schema generator so that the required tables can be created without requiring any complex setup.