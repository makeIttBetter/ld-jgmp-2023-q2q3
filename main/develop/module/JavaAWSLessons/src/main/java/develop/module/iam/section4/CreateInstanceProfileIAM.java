package develop.module.iam.section4;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreateInstanceProfileRequest;
import software.amazon.awssdk.services.iam.model.CreateInstanceProfileResponse;
import software.amazon.awssdk.services.iam.model.IamException;

/**
 * Create an instance profile using the AWS SDK for Java V2
 * <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-iam-instance-profiles.html">...</a>
 */
public class CreateInstanceProfileIAM {
    public static final String TEST_INSTANCE_PROFILE_NAME = "MyNewJavaProfile";

    public static void main(String[] args) {
        createInstanceProfile(TEST_INSTANCE_PROFILE_NAME);
    }

    /**
     * Create an instance profile using the AWS SDK for Java V2
     *
     * @param instanceProfileName The name of the instance profile
     * @return The response from the create instance profile request
     */
    public static CreateInstanceProfileResponse createInstanceProfile(String instanceProfileName) {
        try (IamClient iam = IamClient.builder().build()) {
            CreateInstanceProfileRequest createInstanceProfileRequest = CreateInstanceProfileRequest.builder()
                    .instanceProfileName(instanceProfileName)
                    .build();
            CreateInstanceProfileResponse createInstanceProfileResponse = iam.createInstanceProfile(createInstanceProfileRequest);
            System.out.printf("Instance profile \"%s\" created", instanceProfileName);
            return createInstanceProfileResponse;
        } catch (IamException e) {
            System.out.println("Error creating instance profile: " + e.getMessage());
        }
        return null;
    }
}
