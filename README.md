

### **1. Setup the Project:**

- Create a Spring Boot application with Maven/Gradle.
- Add dependencies for Spring Security, JWT, JPA, and a MySQL database.

### **2. Implement Authentication:**

- **Signup API:**
    - Endpoint: `POST /api/auth/signup`
    - Request Body: `{ "username": "string", "password": "string", "email": "string", "fullName": "string", "phoneNumber": "string" }`
    - Register new users with encrypted passwords.
    - add some validation(email should uniq, pasword long atlease 8 char)
- **Login API:**
    - Endpoint: `POST /api/auth/login`
    - Request Body: `{ "username": "string", "password": "string" }`
    - Return a JWT token upon successful authentication.

### **3. Property Management:**

- **CRUD Operations:**
    - **Create Property:**
        - Endpoint: `POST /api/properties`
        - Request Body: `{ "name": "string", "description": "string", "address": "string", "pricePerNight": "decimal", "numberOfBedrooms": "int", "numberOfBathrooms": "int", "isAvailable": "boolean", "drinkAllowed": "boolean", "petAllowed": "boolean", "maxCheckoutTimeInNights": "int", "extraCharges": "decimal" }`
    - **Retrieve All Properties:**
        - Endpoint: `GET /api/properties`
    - **Get Property by ID:**
        - Endpoint: `GET /api/properties/{id}`
    - **Update Property:**
        - Endpoint: `PUT /api/properties/{id}`
        - Request Body: `{ "name": "string", "description": "string", "address": "string", "pricePerNight": "decimal", "numberOfBedrooms": "int", "numberOfBathrooms": "int", "isAvailable": "boolean", "drinkAllowed": "boolean", "petAllowed": "boolean", "maxCheckoutTimeInNights": "int", "extraCharges": "decimal" }`
    - **Delete Property:**
        - Endpoint: `DELETE /api/properties/{id}`
    - **Get User’s Properties:**
        - Endpoint: `GET /api/users/{userId}/properties`
        - Returns all properties owned by the specified user.

### **4. Define the Data Models:**
  
  - **Property Model:**
  - **User Model:**
 
### **5. Implement JWT Authentication:**

- Configure Spring Security to use JWT for securing endpoints.
- Create a filter for validating JWT tokens.
