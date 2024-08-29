package develop.module.iam.section4;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreatePolicyRequest;
import software.amazon.awssdk.services.iam.model.CreatePolicyResponse;
import software.amazon.awssdk.services.iam.model.IamException;

public class CreateConditionalPolicyIAM {
    public static final String TEST_POLICY_NAME = "MyNewConditionalJavaPolicy";

    public static void main(String[] args) {
        // Create a conditional policy
        String currentDateString = java.time.LocalDate.now().toString();

        String startTime = currentDateString + "T01:00:00Z";
        String endTime = currentDateString + "T03:00:00Z";

        String policyDocument = "{" +
                "\"Version\": \"2012-10-17\"," +
                "\"Statement\": [{" +
                "\"Effect\": \"Allow\"," +
                "\"Action\": \"s3:GetObject\"," +
                "\"Resource\": \"arn:aws:s3:::parwiz-foroqh-12/*\"," +
                "\"Condition\": {" +
                "\"DateGreaterThan\": {\"aws:CurrentTime\": \"" + startTime + "\"}," +
                "\"DateLessThan\": {\"aws:CurrentTime\": \"" + endTime + "\"}" +
                "}" +
                "}]" +
                "}";


        createConditionalPolicy(TEST_POLICY_NAME, policyDocument);

    }

    public static CreatePolicyResponse createConditionalPolicy(String policyName, String policyDocument) {
        // Create a conditional policy
        try (IamClient iam = IamClient.builder().build()) {
            CreatePolicyRequest createPolicyRequest = CreatePolicyRequest.builder()
                    .policyName(policyName)
                    .policyDocument(policyDocument)
                    .build();

            CreatePolicyResponse createPolicyResponse = iam.createPolicy(createPolicyRequest);
            System.out.printf("Policy \"%s\" created", createPolicyRequest.policyName());
            return createPolicyResponse;
        } catch (IamException e) {
            System.out.println("Error creating policy: " + e.getMessage());
        }
        return null;
    }
}
