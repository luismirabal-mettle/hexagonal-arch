# Bank Account

Create a bank account http application that allows you to do the following:
- Deposit funds
- Withdraw funds
- Get the statement including: time, amount, and running balance for each movement

### Notes
- The application is about a single account
- You can use ints to represent money

### Stretch goals
- Write tests
- Fail withdrawal if not enough funds
- Notify via email when the balance is greater than 1000, including: time and balance

### Exercise baseline

A basic micronaut application is provided so that we can focus on the exercise rather than having to work out details
about how to get a micronaut application to work.

It provides the following:
- Micronaut application class that serves as the entrypoint
- Basic http controller with a POST and a GET operation
- DB repository that can talk to an H2 DB.
- Db schema generator so that the required tables can be created without requiring any complex setup.