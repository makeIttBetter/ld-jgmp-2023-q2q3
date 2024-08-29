package develop.module.iam.section3;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreatePolicyRequest;
import software.amazon.awssdk.services.iam.model.CreatePolicyResponse;
import software.amazon.awssdk.services.iam.model.IamException;

/**
 * Create a custom IAM policy using the AWS SDK for Java V2
 * <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-iam-policies.html">...</a>
 */
public class CreateIAMPolicies {

    public static final String CUSTOM_JAVA_POLICY_NAME = "CustomJavaPolicy";

    public static void main(String[] args) {

        String customPolicies = """
                {
                    "Version": "2012-10-17",
                    "Statement": [
                        {
                            "Effect": "Allow",
                            "Action": [
                                "s3: ListBucket",
                                "s3:ListAllMyBuckets"
                            ],
                            "Resource": "arn:aws:s3:::*"
                        }
                    ]
                }""";
        String customPolicyDescription = "Custom policy created using the AWS SDK for Java V2";

        createCustomIAMPolicy(CUSTOM_JAVA_POLICY_NAME, customPolicies, customPolicyDescription);

    }

    public static CreatePolicyResponse createCustomIAMPolicy(String policyName, String policyRulesJson, String policyDescription) {

        try (IamClient iam = IamClient.builder().build()) {

            CreatePolicyRequest createPolicyRequest = CreatePolicyRequest.builder()
                    .policyName(policyName)
                    .policyDocument(policyRulesJson)
                    .description(policyDescription)
                    .build();

            CreatePolicyResponse createPolicyResponse = iam.createPolicy(createPolicyRequest);

            System.out.println("Policy created: " + createPolicyResponse.policy().arn());

            return createPolicyResponse;
        } catch (IamException e) {
            System.out.println("Error creating custom policy: " + e.getMessage());
        }
        return null;
    }

}
