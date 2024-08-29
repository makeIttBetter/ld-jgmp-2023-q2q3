package develop.module.iam.section3;

import develop.module.iam.utils.FindPolicyByName;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.DetachUserPolicyRequest;
import software.amazon.awssdk.services.iam.model.DetachUserPolicyResponse;
import software.amazon.awssdk.services.iam.model.Policy;

/**
 * Detach a policy from a user using the AWS SDK for Java V2
 * <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-iam-policies.html">...</a>
 */
public class DetachPolicyFromUserIAM {

    public static void main(String[] args) {

        String userName = CreateIAMUser.TEST_USERNAME;

        Policy foundPolicy = FindPolicyByName.findPolicyByName(CreateIAMPolicies.CUSTOM_JAVA_POLICY_NAME);
        assert foundPolicy != null;
        String policyArn = foundPolicy.arn();

        detachPolicyFromUser(userName, policyArn);
    }

    /**
     * Attach a policy to a user
     *
     * @param userName  The name of the user
     * @param policyArn The ARN of the policy
     * @return The response object
     */
    public static DetachUserPolicyResponse detachPolicyFromUser(String userName, String policyArn) {
        try (IamClient iam = IamClient.builder().build()) {

            DetachUserPolicyRequest detachUserPolicyRequest = DetachUserPolicyRequest.builder()
                    .userName(userName)
                    .policyArn(policyArn)
                    .build();

            DetachUserPolicyResponse attachUserPolicyResponse = iam.detachUserPolicy(detachUserPolicyRequest);

            System.out.printf("Policy \"%s\" detached from user \"%s\"", policyArn, userName);

            return attachUserPolicyResponse;
        } catch (Exception e) {
            System.out.println("Error detaching policy from user: " + e.getMessage());
        }
        return null;
    }

}
