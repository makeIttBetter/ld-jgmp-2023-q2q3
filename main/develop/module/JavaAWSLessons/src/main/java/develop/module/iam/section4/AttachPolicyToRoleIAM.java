package develop.module.iam.section4;

import develop.module.iam.section3.CreateIAMPolicies;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.AttachRolePolicyRequest;
import software.amazon.awssdk.services.iam.model.AttachRolePolicyResponse;
import software.amazon.awssdk.services.iam.model.CreatePolicyResponse;
import software.amazon.awssdk.services.iam.model.IamException;

/**
 * Attach a policy to a role in AWS IAM using the AWS SDK for Java V2
 * <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-iam-policies.html">...</a>
 */
public class AttachPolicyToRoleIAM {
    public static void main(String[] args) {
        // create a new policy FIRST
        String policyName = "MyNewJavaPolicy";
        String policyDocument = "{" +
                "\"Version\": \"2012-10-17\"," +
                "\"Statement\": [{" +
                "\"Effect\": \"Allow\"," +
                "\"Action\": \"s3:GetObject\"," +
                "\"Resource\": \"arn:aws:s3:::parwiz-foroqh-12/*\"" +
                "}]" +
                "}";
        String policyDescription = "My S3 read-only policy";

        CreatePolicyResponse createPolicyResponse = CreateIAMPolicies.createCustomIAMPolicy(policyName, policyDocument, policyDescription);

        // attach the policy to the role SECOND
        String roleName = CreateRoleIAM.ROLE_NAME;
        String policyArn = createPolicyResponse.policy().arn();
        attachPolicyToRole(roleName, policyArn);

    }

    /**
     * Attach a policy to a role
     *
     * @param roleName  The name of the role
     * @param policyArn The ARN of the policy
     * @return The response object
     */
    public static AttachRolePolicyResponse attachPolicyToRole(String roleName, String policyArn) {
        try (IamClient iam = IamClient.builder().build()) {
            AttachRolePolicyRequest attachRolePolicyRequest = AttachRolePolicyRequest.builder()
                    .roleName(roleName)
                    .policyArn(policyArn).build();
            AttachRolePolicyResponse attachRolePolicyResponse = iam.attachRolePolicy(attachRolePolicyRequest);
            System.out.printf("Policy \"%s\" attached to role \"%s\"", policyArn, roleName);
            return attachRolePolicyResponse;
        } catch (IamException e) {
            System.out.println("Error attaching policy to role: " + e.getMessage());
        }
        return null;
    }
}
