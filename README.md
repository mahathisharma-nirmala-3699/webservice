# Receipt Processor Web Service

  

## Overview

  

This web service processes receipts and calculates points based on certain criteria. The API allows you to submit receipts for processing and retrieve points for processed receipts.

  
  

## Prerequisites

  

- Java 11 or higher

- Maven

- (Optional) Postman for testing

  

## Getting Started

  
  

### Clone the Repository

  
  

```

git clone <repository_url>

  

cd <repository_directory>

  

```

  

### Build the Application

  

```

  

mvn clean install

  

```

  

### Run the Application

  

```

  

mvn spring-boot:run

  

```

  

The application will start on http://localhost:8080.

  

  

### API Endpoints

  

  

### 1. Submit a Receipt for Processing

  

**URL: /receipts/process

Method: POST

Content-Type: application/json

Request Body:**

  

```

{

  

"retailer": "Target",

  

"purchaseDate": "2022-01-01",

  

"purchaseTime": "13:01",

  

"items": [

  

{

  

"shortDescription": "Mountain Dew 12PK",

  

"price": "6.49"

  

},{

  

"shortDescription": "Emils Cheese Pizza",

  

"price": "12.25"

  

},{

  

"shortDescription": "Knorr Creamy Chicken",

  

"price": "1.26"

  

},{

  

"shortDescription": "Doritos Nacho Cheese",

  

"price": "3.35"

  

},{

  

"shortDescription": " Klarbrunn 12-PK 12 FL OZ ",

"price": "12.00"

}

  

],

  

"total": "35.35"

}

```

**Response:**

```

{"id": "153d3191-169f-499b-81b9-c8df60acac92"}

```

### 2. Get Points for a Receipt

  

**URL: /receipts/{id}/points**

**Method**: **GET**

**Response:**

```

{

"points": 100

}

```

  

## Testing with Postman

  

### Import API Collection

  

1) Open Postman.

2) Create a New Collection:

  

Click on Collections in the left panel.

  

Click on + New Collection.

  

Name the collection Receipt Processor API.

  

  

## Create Requests in the Collection

  

### Process Receipt

1. Create a New Request:

Click on + Add Request.

Name the request Process Receipt.

2. Set the Request Type to POST:

3. Change the request method to POST.

4. Set the URL:

5. Enter http://localhost:8080/receipts/process in the URL field.

6. Set the Headers:

Click on the Headers tab.

Add a new header with Key as Content-Type and Value as application/json.

7. Set the Body:

Click on the Body tab.

Select raw and choose JSON from the dropdown.

Paste the following JSON into the body:

```

{

"retailer": "Example Store",
"purchaseDate": "2024-07-15",
"purchaseTime": "14:30",
"items": [
{
"shortDescription": "Item 1",
"price": 2.50
},
{
"shortDescription": "Item 2",
"price": 2.50
}
],
"total": 5.00
}

```

  

8. Save the Request:

Click on Save to save the request in the Receipt Processor API collection.

  

### Get Points for a Receipt

  

1. Create a New Request:

Click on + Add Request.

Name the request Get Points.

2. Set the Request Type to GET:

Change the request method to GET.

3. Set the URL:

Enter http://localhost:8080/receipts/{id}/points in the URL field. Replace {id} with the actual receipt ID after running the Process Receipt request.

4. Save the Request:

Click on Save to save the request in the Receipt Processor API collection.

## Running on Docker

A `Dockerfile` is provided with the project. Follow these steps to build and run the Docker container.

### Build the Docker Image

Run the following command to build your Docker image. Ensure you are in the directory containing your  `Dockerfile`.

```docker build -t webservice .```

### Run the Docker Container

Once the image is built, run the Docker container with the following command:

```docker run -p 8080:8080 webservice```

## Testing with Docker
  Run the below commands that has the requests included for the response. 

#### Submit a Receipt for Processing and generate ID. 
```
curl -X POST http://localhost:8080/receipts/process \

-H "Content-Type: application/json" \

-d '{

"retailer": "Target",

"purchaseDate": "2022-01-01",

"purchaseTime": "13:01",

"items": [

{

"shortDescription": "Mountain Dew 12PK",

"price": "6.49"

},

{

"shortDescription": "Emils Cheese Pizza",

"price": "12.25"

},

{

"shortDescription": "Knorr Creamy Chicken",

"price": "1.26"

},

{

"shortDescription": "Doritos Nacho Cheese",

"price": "3.35"

},

{

"shortDescription": " Klarbrunn 12-PK 12 FL OZ ",

"price": "12.00"

}

],

"total": "35.35"

}'
```

  
  
```
curl -X POST http://localhost:8080/receipts/process \

-H "Content-Type: application/json" \

-d '{

"retailer": "M&M Corner Market",

"purchaseDate": "2022-03-20",

"purchaseTime": "14:33",

"items": [

{

"shortDescription": "Gatorade",

"price": "2.25"

},

{

"shortDescription": "Gatorade",

"price": "2.25"

},

{

"shortDescription": "Gatorade",

"price": "2.25"

},

{

"shortDescription": "Gatorade",

"price": "2.25"

}

],

"total": "9.00"

}'
```
  

```
curl -X POST http://localhost:8080/receipts/process \

-H "Content-Type: application/json" \

-d '{

"retailer": "Walgreens",

"purchaseDate": "2022-01-02",

"purchaseTime": "08:13",

"total": "2.65",

"items": [

{

"shortDescription": "Pepsi - 12-oz",

"price": "1.25"

},

{

"shortDescription": "Dasani",

"price": "1.40"

}

]

}'
```
  
  
```
curl -X POST http://localhost:8080/receipts/process \

-H "Content-Type: application/json" \

-d '{

"retailer": "Walgreens",

"purchaseDate": "2022-01-02",

"purchaseTime": "08:13",

"total": "2.65",

"items": [

{

"shortDescription": "Pepsi - 12-oz",

"price": "1.25"

},

{

"shortDescription": "Dasani",

"price": "1.40"

}

]

}'
```
  
  
  #### Get Points for Receipt 

```curl -X GET http://localhost:8080/receipts/{id}/points```
  

### Notes

- The application does not persist data between restarts and uses

in-memory storage for receipt data.

- The API follows the documented specification and can be tested using

the provided Postman collection.
