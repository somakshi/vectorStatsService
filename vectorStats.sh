#!/bin/bash

# URL
MICROSERVICE_URL="http://localhost:8080/vector/"

# POST request to retrieve the vector ID
VECTOR_ID=$(curl -X POST -H "Content-Type: application/json" -d '{}' "${MICROSERVICE_URL}generate")

# Check if the POST request was successful
if [ $? -eq 0 ]; then
    echo "Retrieved Vector ID: $VECTOR_ID"
    
    # Make GET request with the retrieved vector id to the microservice 
    curl -X GET "${MICROSERVICE_URL}${VECTOR_ID}/statistics"
else
    echo "Failed to retrieve Vector ID."
fi
