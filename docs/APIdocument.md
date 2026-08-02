# AMS API document

## AMS API

This collection documents the REST API for the **Admissions Management System (AMS)** — a platform designed to manage the full admissions lifecycle, from candidate registration to final admission processing.

### Modules

- **Authentication** — Handles user login, logout, and token refresh to secure access to the system.
    
- **Users** — Manages user accounts, including listing users, updating profile information, and changing passwords.
    
- **Candidates** — Covers the full candidate lifecycle: creating, retrieving, updating, and deleting candidate records.
    
- **Aspirations** — Retrieves the list of aspirations (program preferences) submitted by candidates.
    
- **Majors** — Provides CRUD operations for academic majors, including listing, creating, updating, and deleting major records.
    
- **Combinations** — Manages subject combinations used in the admissions scoring process (list, create, update, delete).
    
- **Admissions** — Triggers and monitors the admission execution process, and supports exporting admission results.
    
- **Imports** — Supports bulk data import by type, with job tracking to monitor import progress and retrieve any errors.
    

### Base URL

All requests are routed through a mock server:  
`https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io` Replace it with your server.

## authentication

### login

**Method:** `POST`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/auth/login`

**Description:** ## Login

Authenticates a user with their credentials and returns access and refresh tokens for use in subsequent authenticated requests.

**Endpoint:** `POST /auth/login`

---

## Request Body

Content-Type: `application/json`

| Parameter  | Type   | Required | Description                        |
|------------|--------|----------|------------------------------------|
| `username` | string | ✅ Yes   | The account username (e.g. `Admin User`) |
| `password` | string | ✅ Yes   | The account password               |

**Example Request Body:**
```json
{
  "username": "Admin User",
  "password": "a_very_secure_admin_password_123!@#"
}
```

---

## Response

On success, the endpoint returns authentication tokens (e.g. access token and refresh token) that must be included in the `Authorization` header for protected endpoints.

See the saved example **`login_respone_example`** for a full response payload reference.

---

## Notes

- Credentials are **case-sensitive** — ensure the username and password match exactly as registered.
- Store tokens **securely** and never expose them in client-side code or logs.
- The access token is typically short-lived; use the refresh token to obtain a new access token when it expires.


**Responses:**

*login_respone_example (Code: 200 OK)*
```json
{
    "success": true,
    "code": 200,
    "message": "Login successful !",
    "data": {
        "id": "9b1deb4d-3b7d-4bad-9bdd-2b0d7b3dcb6d",
        "username": "stevejob",
        "full_name": "Steve Job",
        "email": "stevejob@example.com",
        "is_active": true,
        "roles": [
            {
                "role_id": "7d9a8c1e2f3b4a56c7d8e9f0a1b2c3d4e5f6a7b8",
                "role_name": "admin"
            }
        ]
    },
    "meta": {
        "current_page": 1,
        "total_pages": 5,
        "limit": 10
    }
}
```

---

### logout

**Method:** `POST`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/auth/logout`

**Description:** Logs out the currently authenticated user by invalidating their active session/token.

## Request Headers

| Header | Required | Description |
|--------|----------|-------------|
| `Access-Token` | Yes | The access token issued at login, used to identify and invalidate the current session. |

## Request Body

An empty JSON object `{}` should be sent as the request body.

## Response

On success, the server invalidates the provided access token. The user will need to log in again to obtain a new token.

## Notes

- After a successful logout, the `Access-Token` used in this request will no longer be valid.
- Subsequent requests using the same token will be rejected with an unauthorized error.

**Headers:**
| Key | Value | Description |
| --- | --- | --- |
| Access-Token | 	X4Fm29854lLwEa5wIfy2R5RZW0prt5xFSAWvelDBoCi8zGHQYUvozjFqHha0i8yS |  |

**Request Body:**
```json
{
    
}
```

**Responses:**

*logout_respone_example (Code: 200 OK)*
```json
{
    "success": true,
    "code": 200,
    "message": "Logout successful !"
}
```

---

### refresh-token

**Method:** `POST`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/auth/refresh-token`

**Description:** ## Refresh Token

Obtains a new access token using a valid refresh token, without requiring the user to log in again.

**Endpoint:** `POST /auth/refresh-token`

---

## Request Headers

| Header | Required | Description |
|--------|----------|-------------|
| `Refresh-Token` | Yes | The refresh token issued at login, used to generate a new access token. |

## Request Body

An empty JSON object `{}` should be sent as the request body.

---

## Response

On success, the server issues a new access token (and optionally a new refresh token). Use the new access token in the `Authorization` header for subsequent authenticated requests.

See the saved example **`refresh-token`** for a full response payload reference.

---

## Notes

- The refresh token must be valid and not expired.
- If the refresh token has expired or been invalidated (e.g. after logout), the user must log in again to obtain new tokens.
- Store tokens **securely** and never expose them in client-side code or logs.
- The new access token replaces the previous one — update it in your client accordingly.

**Responses:**

*refresh-token (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Refresh token successful !"
}
```

---

## candidates

### /candidates

**Method:** `GET`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/candidates?offset=0&limit=20&keyword=JoneDoe&status=PENDING`

**Responses:**

*/candidates (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Data query successful!",
    "data": [
        {
            "id": "37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR",
            "identitycard": "1176427658",
            "register_number": "SBD_03621916",
            "fullname": "Nguyen Van A",
            "birthday": "2005-03-17T10:00:00Z",
            "phone_number": "1234567890",
            "gender": "male",
            "enrollment_year": 2023,
            "priority_point": 0.0,
            "folk": "Kinh",
            "birthplace": "Ho Chi Minh",
            "status": "pending",
            "created_at": "2005-12-07T10:00:00Z",
            "updated_at": "2005-12-07T10:00:00Z"
        },
        {
            "id": "02JR1X5KR3J0W8LQUMI5HHX30XIO7CSX",
            "identitycard": "0436427658",
            "register_number": "SBD_12367834",
            "fullname": "Le Thi Thanh B",
            "birthday": "2005-12-07T10:00:00Z",
            "phone_number": "0124356789",
            "gender": "female",
            "enrollment_year": 2023,
            "priority_point": 0.2,
            "folk": "Tay",
            "birthplace": "Cao Bang",
            "status": "pending",
            "created_at": "2005-12-07T10:00:00Z",
            "updated_at": "2005-12-07T10:00:00Z"
        }
    ],
    "meta": {
        "current_page": 1,
        "total_pages": 5,
        "limit": 20
    }
}
```

---

### /candidate/detail/{id}

**Method:** `GET`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/candidates/detail/02JR1X5KR3J0W8LQUMI5HHX30XIO7CSX`

**Responses:**

*/candidate/detail/{id} (Code: 200 OK)*
```json
{
    "success": true,
    "code": 200,
    "message": "Data query successful!",
    "data": {
        "candidate_information": {
            "id": "02JR1X5KR3J0W8LQUMI5HHX30XIO7CSX",
            "identitycard": "0436427658",
            "register_number": "SBD_12367834",
            "fullname": "Le Thi Thanh B",
            "birthday": "2005-12-07T10:00:00Z",
            "phone_number": "0124356789",
            "gender": "female",
            "enrollment_year": 2023,
            "priority_point": 0.2,
            "folk": "Tay",
            "birthplace": "Cao Bang",
            "status": "accepted"
        },
        "subject_score": [
            {
                "subject_name": "MATH",
                "score": 8.0
            },
            {
                "subject_name": "PHY",
                "score": 7.75
            }
        ],
        "aspirations": [
            {
                "priority_number": 2,
                "major": "Software Engineer",
                "combination": "A01",
                "admission_score": 27.7,
                "status": "accepted"
            },
            {
                "priority_number": 1,
                "major": "Information Technology",
                "combination": "A00",
                "admission_score": 24.7,
                "status": "pending"
            }
        ],
        "certificates": [
            {
                "insuer": "IIG",
                "name": "TOIEC 650"
            },
            {
                "insuer": "GD&DT",
                "name": "(HSG) Toan "
            }
        ]
    },
    "meta": {
        "current_page": 1,
        "total_pages": 5,
        "limit": 20
    }
}
```

---

### /canditades

**Method:** `POST`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/candidates`

**Request Body:**
```json
{
    "identitycard" :"1176427658",
    "register_number" :"SBD_03621916",
    "fullname" : "Nguyen Van A",
    "birthday" : "2005-03-17T10:00:00Z",
    "phone_number" :"1234567890",
    "gender" : "male",
    "enrollment_year" : 2023,
    "priority_point" : 0.0,
    "folk" : "Kinh",
    "birthplace" :"Ho Chi Minh"
}
```

**Responses:**

*/canditades (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Resource created successfully",
    "data": {
        "identitycard": "1176427658",
        "register_number": "SBD_03621916",
        "fullname": "Nguyen Van A",
        "birthday": "2005-03-17T10:00:00Z",
        "phone_number": "1234567890",
        "gender": "male",
        "enrollment_year": 2023,
        "priority_point": 0.0,
        "folk": "Kinh",
        "birthplace": "Ho Chi Minh",
        "created_at": "2005-12-07T10:00:00Z",
        "updated_at": "2005-12-07T10:00:00Z"
    }
}
```

---

### /candidates/{id}

**Method:** `PUT`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/candidates`

**Request Body:**
```json
{
    "identitycard" :"1176427658",
    "register_number" :"SBD_03621916",
    "fullname" : "Nguyen Van A",
    "birthday" : "2005-03-17T10:00:00Z",
    "phone_number" :"1234567890",
    "gender" : "male",
    "enrollment_year" : 2023,
    "priority_point" : 0.0,
    "folk" : "Kinh",
    "birthplace" :"Ho Chi Minh"
}
```

**Responses:**

*/candidates/{id} (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Data updated successfully",
    "data": {
        "identitycard": "1176427658",
        "register_number": "SBD_03621916",
        "fullname": "Nguyen Van A(updated)",
        "birthday": "2005-03-17T10:00:00Z",
        "phone_number": "1234567890",
        "gender": "male",
        "enrollment_year": 2023,
        "priority_point": 0.0,
        "folk": "Kinh",
        "birthplace": "Ha Noi",
        "created_at": "2005-12-07T10:00:00Z",
        "updated_at": "2005-12-07T10:00:00Z"
    }
}
```

---

### /candidates{id}

**Method:** `DELETE`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/candidates/02JR1X5KR3J0W8LQUMI5HHX30XIO7CSX`

**Responses:**

*/candidates{id} (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Resource deleted successfully."
}
```

---

## majors

### /majors

**Method:** `GET`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/majors?offset=0&limit=20&keyword=A00&isclosed=true`

**Description:** Retrieve a paginated list of majors. Supports filtering by keyword and closed status.

### Query Parameters

| Parameter  | Type    | Required | Description                                      |
|------------|---------|----------|--------------------------------------------------|
| `offset`   | integer | No       | Number of records to skip. Default: `0`          |
| `limit`    | integer | No       | Maximum number of records to return. Default: `20` |
| `keyword`  | string  | No       | Filter majors by name or code (e.g. `A00`)       |
| `isclosed` | boolean | No       | Filter by closed status. `true` returns only closed majors |

### Response

**`200 OK`**

```json
{
  "data": [
    {
      "id": "string",
      "name": "string",
      "code": "string",
      "isClosed": true
    }
  ],
  "total": 0,
  "offset": 0,
  "limit": 20
}
```

### Notes
- Authentication may be required depending on the environment configuration.
- Use `offset` and `limit` together for pagination.

**Responses:**

*/majors (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Data query successful!",
    "data": [
        {
            "id": "37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR",
            "code": "IT",
            "name": "Information Technology",
            "quota": 100,
            "minimum_score": 20.0,
            "admission_score": 24.7,
            "is_closed": false
        },
        {
            "id": "37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR",
            "code": "NNA",
            "name": "English Language",
            "quota": 150,
            "minimum_score": 23.0,
            "admission_score": 25.5,
            "is_closed": false
        }
    ],
    "meta": {
        "current_page": 1,
        "total_pages": 5,
        "limit": 20
    }
}
```

---

### /majors/detail/{id}

**Method:** `GET`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/majors/detail/37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR`

**Responses:**

*/majors/detail/{id} (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Data query successful!",
    "data": {
        "id": "37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR",
        "code": "IT",
        "name": "Information Technology",
        "quota": 100,
        "minimum_score": 20.0,
        "admission_score": 24.7,
        "is_closed": false,
        "combinations": [
            {
                "combination_code": "A00",
                "combination_name": "Mathematics-Physics-Chemistry",
                "weight_sub_1": 1.2,
                "weight_sub_2": 1.0,
                "weight_sub_3": 1.0,
                "deviation": 0.0
            },
            {
                "combination_code": "A01",
                "combination_name": "Mathematics-Physics-English",
                "weight_sub_1": 1.2,
                "weight_sub_2": 1.0,
                "weight_sub_3": 1.0,
                "deviation": 0.0
            }
        ]
    },
    "meta": {
        "current_page": 1,
        "total_pages": 5,
        "limit": 20
    }
}
```

---

### /majors

**Method:** `POST`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/majors`

**Request Body:**
```json
{
    "id" : "37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR",
    "code" : "IT",
    "name": "Information Technology",
    "quota" : 100 , 
    "minimum_score": 20.0,
    "admission_score": 24.7,
    "combinations_id" : [
        "37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR",
        "37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR",
        "37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR"
    ]

}
```

**Responses:**

*/majors (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Resource created successfully",
    "data": {
        "id": "37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR",
        "code": "IT",
        "name": "Information Technology",
        "quota": 100,
        "minimum_score": 20,
        "admission_score": 24.7,
        "combinations_id": [
            "37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR",
            "37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR",
            "37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR"
        ]
    }
}
```

---

### /majors/{id}

**Method:** `PUT`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/majors/37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR`

**Request Body:**
```json
{
    "name": "Information Technology (updated)",
    "is_closed" : true
}
```

**Responses:**

*/majors/{id} (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Data updated successfully",
    "data": {
        "id": "37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR",
        "code": "IT",
        "name": "Information Technology (updated)",
        "quota": 100,
        "minimum_score": 20.0,
        "admission_score": 24.7,
        "is_closed": true
    }
}
```

---

### /majors/{id}

**Method:** `DELETE`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/majors/37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR`

**Responses:**

*/majors/{id} (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Resource deleted successfully."
}
```

---

## combinations

### /combinations

**Method:** `GET`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/combinations`

**Responses:**

*/combinations (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Data query successful!",
    "data": [
        {
            "id": "37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR",
            "code": "A00",
            "name": "Mathematics, Physics, Chemistry",
            "subjects": [
                {
                    "code": "MAT",
                    "name": "Mathematics"
                },
                {
                    "code": "PHY",
                    "name": "Physics"
                },
                {
                    "code": "CHE",
                    "name": "Chemistry"
                }
            ]
        },
        {
            "id": "37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR",
            "code": "A01",
            "name": "Mathematics, Physics, English",
            "subjects": [
                {
                    "code": "MAT",
                    "name": "Mathematics"
                },
                {
                    "code": "PHY",
                    "name": "Physics"
                },
                {
                    "code": "ENG",
                    "name": "English"
                }
            ]
        }
    ],
    "meta": {
        "current_page": 1,
        "total_pages": 5,
        "limit": 20
    }
}
```

---

### /combinations

**Method:** `POST`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/combinations`

**Request Body:**
```json
{
    "id": "37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR",
    "code": "A00",
    "name": "Mathematics, Physics, Chemistry",
    "subjects": [
        {
            "code": "MAT",
            "name": "Mathematics"
        },
        {
            "code": "PHY",
            "name": "Physics"
        },
        {
            "code": "CHE",
            "name": "Chemistry"
        }
    ]
}
```

**Responses:**

*/combinations (Code: 200 OK)*
```json
{
    "success": true,
    "code": 200,
    "message": "Resource created successfully",
    "data": {
    "id": "37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR",
    "code": "A00",
    "name": "Mathematics, Physics, Chemistry",
    "subjects": [
        {
            "code": "MAT",
            "name": "Mathematics"
        },
        {
            "code": "PHY",
            "name": "Physics"
        },
        {
            "code": "CHE",
            "name": "Chemistry"
        }
    ]
}
```

---

### /combinations/{id}

**Method:** `PUT`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/combinations/37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR`

**Request Body:**
```json
{
    "code": "A00 (updated)",
    "name": "Mathematics, Physics, English",
    "subjects": [
        {
            "code": "MAT",
            "name": "Mathematics"
        },
        {
            "code": "PHY",
            "name": "Physics"
        },
        {
            "code": "ENG",
            "name": "English"
        }
    ]
}
```

**Responses:**

*/combinations (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Data updated successfully",
    "data": {
        "id": "37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR",
        "code": "A00 (updated)",
        "name": "Mathematics, Physics, English",
        "subjects": [
            {
                "code": "MAT",
                "name": "Mathematics"
            },
            {
                "code": "PHY",
                "name": "Physics"
            },
            {
                "code": "ENG",
                "name": "English"
            }
        ]
    }
}
```

---

### /combinations/{id}

**Method:** `DELETE`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/combinations/37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR`

**Request Body:**
```json
{
    "id": "37RPIWN9XZK8UB26XZ8HK9VNQ0JUWCCR",
    "code": "A00",
    "name": "Mathematics, Physics, Chemistry",
    "subjects": [
        {
            "code": "MAT",
            "name": "Mathematics"
        },
        {
            "code": "PHY",
            "name": "Physics"
        },
        {
            "code": "CHE",
            "name": "Chemistry"
        }
    ]
}
```

**Responses:**

*/combinations/{id} (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Resource deleted successfully."
}
```

---

## aspirations

### /aspirations

**Method:** `GET`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/aspirations`

**Responses:**

*/aspirations (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Data query successful!",
    "data": [
        {
            "id": "BAaVmhThkGTt2YS4VAtWdxv8tl5x6Puz",
            "candidate_identitycard": "1176427658",
            "priority_number": 1,
            "major_code": "IT",
            "combination_code": "A00",
            "calculated_admission_score": 24.6,
            "admission_method": "THPT",
            "status": "pending"
        },
        {
            "id": "vW3PKbOWTTljbyGScDEeqTJ94ZVvqUoG",
            "candidate_identitycard": "1176427658",
            "priority_number": 2,
            "major_code": "IT",
            "combination_code": "A01",
            "calculated_admission_score": 23.6,
            "admission_method": "V-SAT",
            "status": "pending"
        },
        {
            "id": "idfnabj39Gd0DbMmxrJBecDDLXtaqfLB",
            "candidate_identitycard": "0436427658",
            "priority_number": 2,
            "major_code": "NNA",
            "combination_code": "A01",
            "calculated_admission_score": 27.5,
            "admission_method": "THPT",
            "status": "pending"
        }
    ],
    "meta": {
        "current_page": 1,
        "total_pages": 5,
        "limit": 20
    }
}
```

---

## users

### /users

**Method:** `GET`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/users`

**Responses:**

*/users (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Data query successful!",
    "data": [
        {
            "id": "9b1deb4d-3b7d-4bad-9bdd-2b0d7b3dcb6d",
            "username": "stevejob",
            "full_name": "Steve Job",
            "email": "stevejob@example.com",
            "is_active": true,
            "roles": [
                {
                    "role_id": "7d9a8c1e2f3b4a56c7d8e9f0a1b2c3d4e5f6a7b8",
                    "role_name": "admin"
                }
            ],
            "created_at": "2005-12-07T10:00:00Z",
            "updated_at": "2005-12-07T10:00:00Z"
        },
        {
            "id": "9b1deb4d-3b7d-4bad-9bdd-2b0d7b3dcb6d",
            "username": "stevejob-2th",
            "full_name": "Steve Job - 2th",
            "email": "stevejob2th@example.com",
            "is_active": true,
            "roles": [
                {
                    "role_id": "7d9a8c1e2f3b4a56c7d8e9f0a1b2c3d4e5f6a7b8",
                    "role_name": "admin"
                }
            ],
            "created_at": "2005-12-07T10:00:00Z",
            "updated_at": "2005-12-07T10:00:00Z"
        }
    ],
    "meta": {
        "current_page": 1,
        "total_pages": 5,
        "limit": 20
    }
}
```

---

### /user/change-password

**Method:** `PUT`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/user/change-password`

**Request Body:**
```json
{
    "password" : "your_old_password",
    "new_password" : "your_new_password",
    "confirm_password" : "your_new_password"
}
```

**Responses:**

*/user/change-password (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Password updated successfully"
}
```

---

### /users/{id}

**Method:** `PUT`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/users/9b1deb4d-3b7d-4bad-9bdd-2b0d7b3dcb6d`

**Request Body:**
```json
{
    "username": "stevejob (updated)",
    "full_name": "Steve Job (updated)",
    "email": "stevejob@example.com",
    "is_active": true,
}
```

**Responses:**

*/users/{id} (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Data updated successfully",
    "data": {
        "id": "9b1deb4d-3b7d-4bad-9bdd-2b0d7b3dcb6d",
        "username": "stevejob (updated)",
        "full_name": "Steve Job (updated)",
        "email": "stevejob123@example.com",
        "is_active": false,
        "roles": [
            {
                "role_id": "7d9a8c1e2f3b4a56c7d8e9f0a1b2c3d4e5f6a7b8",
                "role_name": "admin"
            }
        ],
        "created_at": "2005-12-07T10:00:00Z",
        "updated_at": "2026-10-22T10:29:07Z"
    }
}
```

---

## admissions

### /admissions/execute

**Method:** `POST`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/admissions/execute`

**Request Body:**
```json
{
    "method" : "THPT",
    "admission_year" : 2025
}
```

**Responses:**

*/admissions/execute (Code: 202 Accepted)*
```json
{
    "success": true,
    "code": 202,
    "message": "The system is currently processing admissions for the 'THPT' method for the year '2025'; please wait...",
    "data": {
        "job_id": "uuid-1234-5678",
        "status": "PENDING"
    }
}
```

---

### /admissions/jobs/{job_id}

**Method:** `GET`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/admissions/jobs/uuid-1234-5678`

**Responses:**

*/admissions/jobs/{job_id} processing (Code: 200 )*
```json
{
    "success": true,
    "code": 200,
    "message": "Data query successful!",
    "data": {
        "job_id": "uuid-1234-5678",
        "status": "PROCESSING",
        "processed_count": 8000,
        "total_count": 50000,
        "percentage": 16.0
    }
}
```

*/admissions/jobs/{job_id} completed (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Data query successful!",
    "data": {
        "job_id": "uuid-1234-5678",
        "status": "COMPLETED",
        "processed_count": 50000,
        "total_count": 50000,
        "percentage": 100.0,
        "summary": {
            "total_admitted": 12000,
            "total_rejected": 38000
        },
        "result_url": "/admissions/export/uuid-1234-5678"
    }
}
```

*/admissions/jobs/{job_id} failed (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Data query successful!",
    "data": {
        "job_id": "uuid-1234-5678",
        "status": "FAILED",
        "percentage": 45.0,
        "error_message": "Database connection error on line 22500."
    }
}
```

---

### /admissions/export/{job_id}

**Method:** `GET`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/admissions/export/uuid-1234-5678`

**Responses:**

*/admissions/export/{job_id} (Code: 200 )*
---

## imports

### /imports/{type}

**Method:** `POST`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/imports/{type}`

**Headers:**
| Key | Value | Description |
| --- | --- | --- |
| Content-Type | multipart/form-data |  |

**Responses:**

*/imports/{type} (Code: 202 )*
```json
{
    "success": true,
    "code": 202,
    "message": "The file has been received and placed in the processing queue.",
    "data": {
        "job_id": "job-import-uuid-8899",
        "status": "PENDING"
    }
}
```

---

### /imports/jobs/{job_id}

**Method:** `GET`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/imports/jobs/job-import-uuid-8899`

**Responses:**

*/imports/jobs/{job_id} processing (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Data query successful!",
    "data": {
        "job_id": "job-import-uuid-8899",
        "status": "PROCESSING",
        "processed_rows": 4500,
        "total_rows": 10000,
        "percentage": 45.0
    }
}
```

*/imports/jobs/{job_id} compeled (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Data query successful!",
    "data": {
        "job_id": "job-import-uuid-8899",
        "status": "COMPLETED",
        "processed_rows": 10000,
        "total_rows": 10000,
        "percentage": 100.0,
        "summary": {
            "success_count": 9950,
            "error_count": 50
        },
        "error_file_url": "/imports/jobs/job-import-uuid-8899/errors"
    }
}
```

*/imports/jobs/{job_id} failed (Code:  )*
```json
{
    "success": true,
    "code": 200,
    "message": "Data query successful!",
    "data": {
        "job_id": "job-import-uuid-8899",
        "status": "FAILED",
        "error_message": "Unable to connect to the database, or the file is corrupted."
    }
}
```

---

### /imports/jobs/{job_id}/errors

**Method:** `GET`

**URL:** `https://b2d35b0a-811b-4e7b-8689-21eb2d001d33.mock.pstmn.io/imports/jobs/job-import-uuid-8899/errors`

**Responses:**

*/imports/jobs/{job_id}/errors (Code:  )*
---

