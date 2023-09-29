# Helm Project Guide

## Introduction
(Setting up project)

### What is Helm and Why Do We Need It?
Helm is a package manager for Kubernetes. It allows developers to distribute, install, and manage Kubernetes applications. With Helm, users can easily define, install, and upgrade even the most complex Kubernetes applications.

## Helm Commands Guide

### Creating and Installing Projects
To create and install your projects using Helm, follow the given commands:

1. **Installing Helm:**

   Download and install Helm: https://helm.sh/docs/intro/install/
Create a New Project:
```shell
helm create [your_project_name]
## [your_project_name] -> spring-petclinic
```
Install Your Helm Chart:
```shell
helm install [release_name] [chart_directory] -n [namespace]
## helm install spring-petclinic-release main/develop/module/module7/spring-petclinic-main/helm/spring-petclinic -n module7
```
Configuration and Upgrading
Changing Configurations:
Edit the values.yaml file in your chart directory to make configuration changes.

Upgrading Running Instances:
After making changes, upgrade your running instance using:
```shell
helm upgrade [release_name] [chart_directory]
```
Understanding Helm Files
Values File (**values.yaml**)
The **values.yaml** file is used for defining default configuration options.

Deployment File (**deployment.yaml**)
The **deployment.yaml** file defines the desired state of your application deployment on Kubernetes.

Explanation of Deployment Configurations
Your **deployment.yaml** contains several configurations.

**Volumes:**
Volumes are used at the Pod level to mount ConfigMaps into containers within that Pod.

```
volumes:
- name: config-volume
  configMap:
  name: {{ .Release.Name }}-config
  items:
  - key: "interval"
  path: "interval"
  - key: "example.property"
  path: "example.property"
```
  
**Volume Mounts:**
  Volume mounts define where and how volumes are mounted into containers.
```
volumeMounts:
- name: config-volume
  mountPath: /etc/config
  readOnly: true
```
  **Environment Variables from ConfigMap:**
  Environment variables can be set using values from ConfigMaps.
```
envFrom:
- configMapRef:
  name: {{ .Release.Name }}-config
  env:
- name: INTERVAL
  valueFrom:
  configMapKeyRef:
  name: {{ .Release.Name }}-config
  key: interval
```


  **Conclusion**
  Understanding and effectively using Helm can significantly simplify Kubernetes application deployment and management tasks. Refer to the official Helm documentation for more detailed and advanced uses.



## Upgrading Running Deployments

### When to Upgrade?
Once you make any changes to the configuration files like `values.yaml` or `deployment.yaml`, you would need to upgrade your running deployment for the changes to take effect.

### Steps to Upgrade Deployment

1. **Make Configuration Changes:**
   Edit the necessary configuration files within your project's Helm chart directory.

2. **Upgrade Helm Release:**
   After making the changes, run the following command to upgrade your Helm release. Replace `[release_name]` with the name of your existing release, and `[chart_directory]` with the path to your chart directory.

   ```shell
   helm upgrade [release_name] [chart_directory] -n [namespace]
   ## helm upgrade spring-petclinic-release main/develop/module/module7/spring-petclinic-main/helm/spring-petclinic -n module7
   ## helm upgrade my-app ./my-helm-chart -n my-namespace
   ```

Important Points:
Ensure that you are in the correct namespace where the release is deployed.
Review the changes and configurations before applying the upgrade to prevent any disruptions or issues in the running applications.
Rollback if Needed:
In case there are issues with the upgrade, Helm provides a way to rollback to a previous release version. Use the following command:

```
helm rollback [release_name] [revision_number] -n [namespace]
## helm rollback spring-petclinic-release 1 -n module7
## helm rollback my-app 1 -n my-namespace
```

[revision_number] is the version of the release you want to rollback to. You can find this number by listing the release history using helm history [release_name] -n [namespace].

Conclusion
Upgrading deployments is a common task when managing applications on Kubernetes with Helm. Being familiar with how to apply and rollback changes efficiently is crucial for maintaining the reliability and stability of your services.


**To see the list of all helm projects running:**
```
helm list -n module7
```
**To get url for deployment (service) needed:**
```
minikube service spring-petclinic-service --namespace=module7 --url
```
