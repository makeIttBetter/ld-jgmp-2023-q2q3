## Variables exists:
```
     environment {
            DEPLOYMENT_NAME = 'spring-petclinic'
            NAMESPACE = 'module7'
            DOCKERHUB_CREDENTIALS = 'jenkins-docker-jgmp'
            DOCKER_IMAGE_NAME = "dockeruser111111111/jgmp-test:${DEPLOYMENT_NAME}-${env.BUILD_ID}"
            CHART_PATH = 'main/develop/module/module7/spring-petclinic-main/helm/spring-petclinic'
            RELEASE_NAME = 'spring-petclinic-release'
        }
```



### Setting KUBECONFIG Environment Variable
```
set KUBECONFIG=main/develop/module/module7/spring-petclinic-main/kube-config
```

**Explanation:**
This command sets the `KUBECONFIG` environment variable to point to the Kubernetes configuration file's location. The kubeconfig file provides the necessary details to communicate with the cluster, such as cluster API server's address, authentication details, and the default namespace.

### Kubernetes Namespace Creation
```
kubectl create namespace %NAMESPACE% --dry-run=client -o yaml | kubectl apply -f -
```
**Explanation:**
This series of commands accomplishes the following tasks:

`kubectl create namespace %NAMESPACE% --dry-run=client -o yaml` generates a YAML definition for a namespace named` %NAMESPACE%` without applying it.

The generated YAML is then piped `(|)` and passed to the `kubectl apply -f -` command.

`kubectl apply -f -` applies the received YAML definition, effectively creating the namespace if it does not already exist.

### Updating Helm Chart Dependencies
```
helm dependency update %CHART_PATH%
```

**Explanation:**
The helm dependency update `%CHART_PATH%` command inspects the **Chart.yaml** file located at `%CHART_PATH%` and downloads the specified chart dependencies from the respective repositories. It ensures that the required dependencies are present and up to date before deploying the chart.

### Helm Upgrade --install Command
```
helm upgrade --install %RELEASE_NAME% %CHART_PATH% --namespace %NAMESPACE% --set image.repository=%DOCKER_IMAGE_NAME% --set image.tag=%DEPLOYMENT_NAME%-%BUILD_ID%
```

**Explanation:**
This command installs or upgrades a Helm release:

`--install`: This flag allows the command to install the release if it does not already exist, making the upgrade command idempotent.

`%RELEASE_NAME%`: Specifies the name of the release.

`%CHART_PATH%`: Provides the path to the Helm chart to be installed or upgraded.

`--namespace %NAMESPACE%`: Deploys the release into the specified namespace. If the namespace does not exist, Helm will create it.

`--set image.repository=%DOCKER_IMAGE_NAME%`: Overrides the `image.repository` value in the chart's values.yaml file with `%DOCKER_IMAGE_NAME%`.

`--set image.tag=%DEPLOYMENT_NAME%-%BUILD_ID%`: Overrides the `image.tag` value in the chart's **values.yaml** file with `%DEPLOYMENT_NAME%-%BUILD_ID%`.

In Summary:
The helm `upgrade --install` command is used to install or upgrade a Helm release with specified values, ensuring the release is deployed with the desired Docker image and tag. The `--set flags` are used to override specific values in the chart's default **values.yaml** file.



### Another explanations you can find in this file:
`helm/spring-petclinic/README.md`
