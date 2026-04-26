#!/bin/bash

# Exit on any error
set -e

echo "🚀 Starting Spring Service deployment..."

# 1. Build the Docker image
echo "📦 Building Docker image..."
docker build -t spring-service:latest ./my-spring-service/

# 2. Import it into k3d cluster
echo "🚚 Importing image into k3d cluster 'learnk8s'..."
k3d image import spring-service:latest -c learnk8s

# 3. Force Kubernetes to restart the pods to pick up the new image
echo "🔄 Rolling out new version in Kubernetes..."
kubectl rollout restart deployment spring-service -n apps

# 4. Wait for the rollout to complete
echo "⏳ Waiting for rollout to finish..."
kubectl rollout status deployment spring-service -n apps

echo "✅ Deployment complete! Your app is updated at http://spring.localtest.me:8080"
