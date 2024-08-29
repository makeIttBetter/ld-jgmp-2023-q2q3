package develop.module.iam.section3;

import develop.module.iam.utils.FindPolicyByName;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.*;

/**
 * Attach a policy to an IAM user using the AWS SDK for Java V2
 * <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-iam-policies.html">...</a>
 */
public class AttachPolicyToUserIAM {

    public static void main(String[] args) {

        String userName = CreateIAMUser.TEST_USERNAME;

        Policy foundPolicy = FindPolicyByName.findPolicyByName(CreateIAMPolicies.CUSTOM_JAVA_POLICY_NAME);
        assert foundPolicy != null;
        String policyArn = foundPolicy.arn();

        attachPolicyToUser(userName, policyArn);
    }

    /**
     * Attach a policy to a user
     *
     * @param userName The name of the user
     * @param policyArn The ARN of the policy
     * @return The response object
     */
    public static AttachUserPolicyResponse attachPolicyToUser(String userName, String policyArn) {
        try (IamClient iam = IamClient.builder().build()) {

            AttachUserPolicyRequest attachUserPolicyRequest = AttachUserPolicyRequest.builder()
                    .userName(userName)
                    .policyArn(policyArn)
                    .build();

            AttachUserPolicyResponse attachUserPolicyResponse = iam.attachUserPolicy(attachUserPolicyRequest);

            System.out.printf("Policy \"%s\" attached to user \"%s\"", policyArn, userName);

            return attachUserPolicyResponse;
        } catch (Exception e) {
            System.out.println("Error attaching policy to user: " + e.getMessage());
        }
        return null;
    }
}
