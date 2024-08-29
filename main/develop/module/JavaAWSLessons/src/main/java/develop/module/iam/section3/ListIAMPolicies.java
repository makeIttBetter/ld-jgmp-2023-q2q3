package develop.module.iam.section3;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.ListPoliciesRequest;
import software.amazon.awssdk.services.iam.model.ListPoliciesResponse;

public class ListIAMPolicies {
    public static void main(String[] args) {
        try (IamClient iam = IamClient.builder().build()) {

            System.out.println("\nList of IAM policies:");
            listIAMPolicies();

        } catch (Exception e) {
            System.out.println("Error listing policies: " + e.getMessage());
        }
    }

    public static ListPoliciesResponse listIAMPolicies() {
        try (IamClient iam = IamClient.builder().build()) {
            ListPoliciesRequest listPoliciesRequest = ListPoliciesRequest.builder().build();
            ListPoliciesResponse listPoliciesResponse = iam.listPolicies(listPoliciesRequest);

            listPoliciesResponse.policies().forEach(policy ->
                    System.out.println(policy.policyName()));

            return listPoliciesResponse;
        } catch (IamException e) {
            System.out.println("Error listing policies: " + e.getMessage());
        }
        return null;
    }
}
