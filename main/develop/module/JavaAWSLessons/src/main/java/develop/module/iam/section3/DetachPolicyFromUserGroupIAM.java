package develop.module.iam.section3;

import develop.module.iam.utils.FindPolicyByName;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.*;

/**
 * Attach a policy to a user group using the AWS SDK for Java V2
 * <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-iam-policies.html">...</a>
 */
public class DetachPolicyFromUserGroupIAM {

    public static void main(String[] args) {
        Policy policy = FindPolicyByName.findPolicyByName(CreateIAMPolicies.CUSTOM_JAVA_POLICY_NAME);
        detachPolicyFromUserGroup(CreateIAMUserGroup.TEST_GROUP_NAME, policy.arn());
    }

    public static DetachGroupPolicyResponse detachPolicyFromUserGroup(String groupName, String policyArn) {
        try (IamClient iam = IamClient.builder().build()) {
            DetachGroupPolicyRequest detachGroupPolicyRequest = DetachGroupPolicyRequest.builder()
                    .groupName(groupName)
                    .policyArn(policyArn).build();
            DetachGroupPolicyResponse detachGroupPolicyResponse = iam.detachGroupPolicy(detachGroupPolicyRequest);
            System.out.printf("Policy \"%s\" attached to user group \"%s\"", policyArn, groupName);
            return detachGroupPolicyResponse;
        } catch (IamException e) {
            System.out.println("Error attaching policy to user group: " + e.getMessage());
        }
        return null;
    }
}
