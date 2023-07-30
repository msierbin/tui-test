# Project Title

This project aims to provide an API for listing all Github repositories for a given username which are not forks. The API also handles some specific scenarios such as handling non-existing Github users and unsupported Accept headers.

## Building the project

Project can be build using maven command:

`mvn clean install`

## Running the project

You can run project using below command:

`mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8085`

## Getting Started

To use the API, you should make a GET request to the `/repos` endpoint with the Github username as a parameter. You should also provide the header `Accept: application/json`.

### Example

`GET /users/${your-github-username}/repos`

## Responses

### Success

The API returns a JSON object containing a list of all non-fork repositories owned by the provided Github username. Each repository object contains:

- Repository Name
- Owner Login
- A list of all branches with their names and last commit SHA.

### Failure

The API can return two types of errors:

1. If the provided Github username does not exist, the API will return a `404` status code along with a JSON object in the following format:

```json
{
    "status": 404,
    "Message": "Username ${username} cannot be found"
}
```

2. If the `Accept` header is not set to `application/json` (for example, `application/xml`), the API will return a `406` status code along with a JSON object in the following format:

```json
{
    "status": 406,
    "Message": "Unsupported header type"
}
