# Support Aggregation Hub

Support Aggregation Hub is an application that is used internally by engineers to manage CRM cases.

## Installation

Run the jar file under the target folder:

```bash
java -jar target/customer-relationship-management-service-0.0.1-SNAPSHOT.jar
```

## Execution

Run the client from:

http://localhost:8080/ 

## Information

The Support Aggregation Hub service groups together cases based on common denominators, to ensure that engineers
are not working in parallel on two separate cases which are essentially the same issue.

The grouping is done by provider and by error code, meaning that cases with same provider ID and error code will be grouped to one entry.

Users can filter the aggregated cases by provider ids and by error codes, as well as to display open cases or closed cases. 

## About the implementation

Since I had limited time working on this project, I had to decide where to focus and what I want to represent.
I decided to not implement the integration with the external CRM systems, but if I would, I would get the CRM cases by rest, and
persist to the DB.
I also decided not to integrate with any DB, but if I would, I would have the aggregation done on the DB level (and not in the service itself 
like I did).
I also didn't implement tests, although I believe it is an important aspect of development.
 
What did I implement? I created some mocked data, that was read by a repository service class.
I implemented front end UI (entirely new for me to learn) that consists of a toolbar with filters of provider, error code and case status, and a list of aggregated cases.
I implemented an aggregation service that gets the cases and aggregates them by provider and error code.


