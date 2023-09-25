# Jenkins and Docker Configuration Breakdown

In this document, we will breakdown each step in your Jenkins pipeline and Docker configuration, providing insights and explanations for each section.

## Table of Contents

- [Step 1: Setting Up Minikube](#step-1-setting-up-minikube)
- [Step 2: Understanding the Jenkins Pipeline Configuration](#step-2-understanding-the-jenkins-pipeline-configuration)
   - [Initiation of Jenkins Pipeline](#initiation-of-jenkins-pipeline)
   - [Execution of the Jenkinsfile](#execution-of-the-jenkinsfile)
- [Dockerfile Explanation](#dockerfile-explanation)
- [Step 3: Kubernetes Configuration (kube-config)](#step-3-kubernetes-configuration-kube-config)
- [Step 4: Creating a Service in Kubernetes and Obtaining the URL](#step-4-creating-a-service-in-kubernetes-and-obtaining-the-url)

---

## **Step 1: Setting Up Minikube**

Before you even start with your Jenkins pipeline, you need to have your Minikube running. Minikube is a tool that makes it easy to run Kubernetes locally. You can start Minikube on your machine using the following command:

```sh
minikube start
```
This command sets up a virtual machine on your system and installs the necessary components to run a single-node Kubernetes cluster. You can interact with your cluster using kubectl, a command-line tool for managing Kubernetes clusters.

# Jenkins, Docker, and Kubernetes Integration Guide

## **Table of Contents**
1. [Understanding the Jenkins Pipeline Configuration](#step-2-understanding-the-jenkins-pipeline-configuration)
2. [Dockerfile Explanation](#dockerfile-explanation)
3. [Kubernetes Configuration (kube-config)](#step-3-kubernetes-configuration-kube-config)
4. [Creating a Service in Kubernetes and Obtaining the URL](#step-4-creating-a-service-in-kubernetes-and-obtaining-the-url)

---

## **Step 2: Understanding the Jenkins Pipeline Configuration**

### **1. Initiation of Jenkins Pipeline**

When you trigger the execution of your Jenkins pipeline, the script specified in the `main\develop\module\module6\Jenkinsfile` (as defined in your pipeline job configuration) is fetched and initiated. This Jenkinsfile contains the scripted pipeline that Jenkins will execute step by step.

### **2. Execution of the Jenkinsfile**

#### **2.1.1. Jenkins Agent Configuration**

```Jenkins
agent any
```
Explanation: This line specifies that the Jenkins pipeline can run on any available agent in the Jenkins environment. An agent is a system that is capable of executing a Jenkins pipeline.

#### **2.1.2 Environment Variables Setup**
Code Example:
```Jenkins
environment {
    DOCKERHUB_CREDENTIALS = 'jenkins-docker-jgmp'
    DOCKER_IMAGE_NAME = "dockeruser111111111/jgmp-test:${env.BUILD_ID}"
    NAMESPACE = 'module6'
    DEPLOYMENT_NAME = 'my-web-app'
}
```
Explanation:
In this section, we are defining several environment variables that will be used throughout the pipeline.

DOCKERHUB_CREDENTIALS: The identifier for the credentials to access Docker Hub. This credential should be pre-configured in Jenkins with this ID.
DOCKER_IMAGE_NAME: The name of the Docker image to be built, including a tag that uses the build ID from Jenkins to make each build unique.
NAMESPACE: This specifies the namespace (in this case, 'module6') to be used when interacting with Kubernetes later in the pipeline.
DEPLOYMENT_NAME: Specifies the name of the Kubernetes deployment, which will be created in a later stage.
#### **2.2 First Pipeline Stage: 'Prepare and Copy Artifacts'**
Code Example:
``` Jenkins
stage('Prepare and Copy Artifacts') {
    steps {
        script {
            // Copy artifacts from 'continuous' job
            copyArtifacts projectName: 'continuous', filter: 'main/develop/module/builders/web/*.war'
        }
    }
}
```
Explanation:
In this first stage, the copyArtifacts step, a functionality from the Copy Artifact Plugin (which needs to be installed beforehand in Jenkins), is utilized. This step copies the artifact (specifically, .war files) from the 'continuous' job workspace, from the directory main/develop/module/builders/web/. These copied artifacts are then placed into the same folder structure in the current pipeline build workspace. This action facilitates the next stage where the artifact will be utilized within the Docker image, as dictated by a command in the Dockerfile.

#### **2.2 Stage: 'Build Docker Image'**
```Jenkins
stage('Build Docker Image') {
    steps {
        script {
            checkout scm
            def dockerfilePath = 'main/develop/module/module6/Dockerfile'
            def appImage = docker.build("${DOCKER_IMAGE_NAME}", "-f ${dockerfilePath} .")
        }
    }
}
```
Explanation:
In this stage, the source code management (SCM) is checked out to bring in the necessary files for the build. After this, the Docker image is built using the Docker build command. The -f option specifies the location of the Dockerfile, and the . indicates the current directory as the build context. The built image is tagged with the name specified in the DOCKER_IMAGE_NAME environment variable.

#### **2.3 Stage: 'Push Docker Image'**
```Jenkins
stage('Push Docker Image') {
    steps {
        script {
            def appImage = docker.image("${DOCKER_IMAGE_NAME}")
            docker.withRegistry('https://registry.hub.docker.com', "${DOCKERHUB_CREDENTIALS}") {
                appImage.push("${env.BUILD_ID}")
            }
        }
    }
}
```
Explanation:
In this stage, the previously built Docker image is pushed to Docker Hub. The docker.image command loads the built image (specified by DOCKER_IMAGE_NAME) into the pipeline context. The docker.withRegistry command then pushes this image to the specified registry (Docker Hub), using the credentials specified in DOCKERHUB_CREDENTIALS. The image is tagged with the build ID.

#### **2.4 Stage: 'Deploy to Kubernetes'**
```Jenkins
stage('Deploy to Kubernetes') {
    steps {
        script {
            bat """
            set KUBECONFIG=main/develop/module/module6/kube-config
            kubectl create namespace %NAMESPACE% --dry-run=client -o yaml | kubectl apply -f -
            kubectl create deployment %DEPLOYMENT_NAME% --image=nginx --namespace=%NAMESPACE% --dry-run=client -o yaml | kubectl apply -f -
            kubectl set image deployment/%DEPLOYMENT_NAME% nginx=%DOCKER_IMAGE_NAME% --namespace=%NAMESPACE%
            """
        }
    }
}
```
Explanation:
Here, the deployment to a Kubernetes cluster is executed. The bat command is used to run a series of commands in a batch script:

set KUBECONFIG: This command specifies the Kubernetes configuration file to use.
kubectl create namespace: This command creates a new namespace (specified by NAMESPACE) in the Kubernetes cluster. The --dry-run=client -o yaml | kubectl apply -f - flags ensure that the command generates the configuration in YAML format and applies it immediately.
kubectl create deployment: This creates a new deployment (specified by DEPLOYMENT_NAME) using the nginx image, in the namespace created previously, and applies the configuration immediately.
kubectl set image: This command updates the image of the created deployment to the Docker image built earlier (specified by DOCKER_IMAGE_NAME).

### **Dockerfile Explanation**
#### **1. Base Image Specification**
```Dockerfile
# Use the official Tomcat image as the base image
FROM tomcat:9-jdk11
```
Explanation: This line indicates that the base image for your Docker container is the official Tomcat image with version 9 and JDK 11 installed.

#### **2. Removing Default Tomcat Apps**
```Dockerfile
# Remove default Tomcat apps to clear the webapps directory
RUN rm -rf /usr/local/tomcat/webapps/*
```
Explanation: This line executes a command within the Docker image to remove the default applications present in the Tomcat webapps directory. This step is generally done to clear unnecessary files and only deploy the necessary applications.

#### **3. Copying War File**
```Dockerfile
# Copy the .war from the Jenkins workspace into the Tomcat webapps directory
COPY main/develop/module/builders/web/*.war /usr/local/tomcat/webapps/ROOT.war
```
Explanation: Here, the war file generated in the Jenkins workspace (during a previous build stage) is copied into the Tomcat webapps directory within the Docker container. This makes the application accessible when the Docker container is running.

#### **4. Exposing Port**
```Dockerfile
# Expose port 8080 for the Tomcat server
EXPOSE 8080
```
Explanation:
This command exposes port 8080 in the Docker container, allowing for communication with the Tomcat server running within the container.

## **Step 3: Kubernetes Configuration (kube-config)**
In this section, the focus shifts to the configuration of Kubernetes. A Kubernetes configuration file, often named kube-config, is used to specify the cluster configurations, contexts, and authentication details. It is essential to have this file set up correctly to interact with the Kubernetes cluster and orchestrate the deployment of your application.

Understanding kube-config: The kube-config file contains information regarding the cluster such as API server details, user credentials, and namespaces. It is crucial for kubectl to understand how to communicate with the cluster and perform various operations.

Setting up kube-config in Jenkins: In your Jenkins pipeline, the KUBECONFIG environment variable is set to the path of your kube-config file, which allows Jenkins to interact with the Kubernetes cluster using the kubectl command. Ensure the kube-config file has the correct permissions and is located in a secure location in your Jenkins workspace.

## **Step 4: Creating a Service in Kubernetes and Obtaining the URL**
At this stage, after deploying your application to the Kubernetes cluster, the next step is to expose the application to the outside world. This is done through the creation of a Kubernetes service.

Creating a Service: Use the kubectl command to create a service. A Kubernetes service is a resource that abstracts access to a set of pods and provides load balancing. You can create a service using a command such as kubectl expose.

Code Example:
```sh
kubectl expose deployment my-web-app --type=LoadBalancer --name=my-web-app-service --namespace=module6
```
Obtaining the URL: After the service is created, you can obtain the URL to access your application by describing the service and extracting the IP and port details.

Code Example:
```sh
minikube service my-web-app-service --namespace=module6 --url
```
Explanation: This command retrieves the IP address of the service named "my-service" in the "module6" namespace. You can use this IP along with the service port to access your application.

Feel free to adjust the content and structure according to your preferences and requirements. It should serve as a comprehensive guide explaining the various steps and code snippets in your Jenkins, Docker, and Kubernetes integration setup.