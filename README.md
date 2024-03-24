
# Authentication API Gateway - Microservices

Description:
The Authentication API Gateway Microservices is a robust and secure solution
designed to handle user authentication and authorization within a distributed microservices'
architecture.
Leveraging modern technologies such as Eureka, Spring Cloud Netflix for service discovery,
and JWT (JSON Web Tokens) for secure communication,
this system ensures seamless authentication across all connected services.

Key Components:

Eureka Service Discovery: The system utilizes Eureka for service registration and discovery.
This allows the microservices to dynamically locate and communicate with each other,
ensuring scalability and fault tolerance.

Spring Cloud Netflix:
Integrated with Eureka,
Spring Cloud Netflix provides additional functionalities such as load balancing, circuit breaking,
and intelligent routing.
These features enhance the reliability and performance of the authentication system.

JWT (JSON Web Tokens): JWT is employed for secure user authentication.
When a user logs in, they receive a JWT containing encoded information about their identity and access rights.
This token is then used for subsequent requests,
eliminating the need for constant re-authentication while ensuring security.

Microservices Architecture:
The authentication system is composed of multiple microservices,
each responsible for specific tasks such as user authentication, token generation, and access control.
This modular approach allows for easier maintenance, scalability, and flexibility in adding new features.

Filtering and Authentication: The system employs dedicated microservices for filtering and authenticating users. These microservices intercept incoming requests, validate user credentials, and enforce access control policies based on the provided JWT. Unauthorized requests are rejected, while authenticated users are granted access to the requested resources.

How to Use:

Service Registration: Each microservice registers itself with the Eureka server upon startup.
This allows other services to discover and communicate with it seamlessly.

User Authentication: When a user attempts to access a protected resource, they provide their credentials
(e.g. username and password).
The authentication microservice verifies these credentials and issues a JWT upon successful authentication.

JWT Authorization: The JWT is included in subsequent requests as an authorization token.
The authentication filter intercepts incoming requests, extracts the JWT, and validates its authenticity and integrity.

Access Control: Based on the information contained within the JWT,
the system determines the user's access rights and permissions.
If the user is authorized to access the requested resource,
the request is forwarded to the appropriate microservice for further processing.

Error Handling: In case of invalid credentials or expired tokens,
appropriate error responses are returned to the client, ensuring transparency and security.


# Controller Swagger Info:

Package: com.ibetar.controller
Description: Endpoints for user authentication
Endpoint: POST /auth/register
Description: Register a new user
Response: AuthResponse