#!/bin/bash

# URL
MICROSERVICE_URL="http://localhost:8080/vector/"

# POST request to retrieve the vector ID
VECTOR_ID=$(curl -X POST -H "Content-Type: application/json" -d '{}' "${MICROSERVICE_URL}generate")

# Check if the POST request was successful
if [ $? -eq 0 ]; then
    echo "Retrieved Vector ID: $VECTOR_ID"
    
    # Make a GET request to your microservice using the retrieved ID
    curl -X GET "${MICROSERVICE_URL}${VECTOR_ID}/statistics"
else
    echo "Failed to retrieve Vector ID."
fi
