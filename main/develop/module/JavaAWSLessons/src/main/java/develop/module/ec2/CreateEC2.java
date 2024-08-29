package develop.module.ec2;

import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

public class CreateEC2 {
    public static void main(String[] args) {

        String instanceProfileName = "MyNewJavaProfile";
        String amiId = "ami-0ddda618e961f2270";
        String instanceType = "t2.micro";

        createEC2(instanceProfileName, amiId, instanceType);
    }

    public static void createEC2(String instanceProfileName, String amiId, String instanceType) {
        RunInstancesRequest runInstancesRequest = RunInstancesRequest.builder()
                .imageId(amiId)
                .instanceType(InstanceType.fromValue(instanceType))
                .minCount(1)
                .maxCount(1)
                .iamInstanceProfile(IamInstanceProfileSpecification.builder().name(instanceProfileName).build())
                .build();

        try (Ec2Client ec2 = Ec2Client.builder().build()) {
            RunInstancesResponse response = ec2.runInstances(runInstancesRequest);
            Instance instance = response.instances().get(0);
            System.out.printf("Successfully started EC2 with Instance ID %s and Instance Type %s, by \"%s\" Instance Profile.", instance.instanceId(), instanceType, instanceProfileName);
        } catch (Exception e) {
            System.out.println("Error starting EC2 instance: " + e.getMessage());
        }
    }
}
