# AWS Lambda Function in Java for Storing Events into an RDS Database

This guide will walk you through creating an AWS Lambda function in Java that receives an event and stores it into an Amazon RDS database.

## Prerequisites

- AWS account
- AWS CLI configured
- Java Development Kit (JDK) installed
- Maven installed
- AWS SDK for Java
- RDS instance running (MySQL, PostgreSQL, etc.)

## Project Setup

1. **Create a new Maven project**:
    ```sh
    mvn archetype:generate -DgroupId=com.example -DartifactId=EventLambdaRDS -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
    cd EventLambdaRDS
    ```

4. **Package the Lambda Function**:
    ```sh
    mvn clean package
    ```

5. **Deploy the Lambda Function**:
    - Zip the packaged JAR:
        ```sh
        zip function.zip target/original-AWSLambda-1.0
        ```

    - Create an IAM role with the necessary permissions for Lambda and RDS.

    - Create the Lambda function using the AWS CLI:
        ```sh
        aws lambda create-function --function-name EventLambdaRDS \
        --zip-file fileb://function.zip --handler com.example.lambda.EventLambdaHandler::handleRequest \
        --runtime java11 --role arn:aws:iam::your-account-id:role/your-lambda-role
        ```
## Conclusion

You now have a fully functional AWS Lambda function in Java that receives an event and stores it into an Amazon RDS database. This setup can be expanded or modified based on specific requirements, such as handling different types of events or connecting to other database systems.
