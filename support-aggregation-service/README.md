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
