package develop.module.iam.utils;

import develop.module.iam.section3.ListIAMPolicies;
import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.ListPoliciesResponse;
import software.amazon.awssdk.services.iam.model.Policy;

/**
 * Find a policy by name using the AWS SDK for Java V2
 */
public class FindPolicyByName {

    /**
     * Find a policy by name
     *
     * @param policyName The name of the policy to find
     * @return The policy object
     */
    public static Policy findPolicyByName(String policyName) {
        try {
            // Use the listPolicies method to get all policies
            ListPoliciesResponse listPoliciesResponse = ListIAMPolicies.listIAMPolicies();
            for (Policy policy : listPoliciesResponse.policies()) {
                if (policy.policyName().equals(policyName)) {
                    System.out.println("Found Policy: " + policy.policyName() + ", ARN: " + policy.arn());
                    return policy;
                }
            }

            System.out.println("Policy not found.");
        } catch (IamException e) {
            System.out.println("Error finding policy: " + e.getMessage());
        }
        return null;
    }


}
