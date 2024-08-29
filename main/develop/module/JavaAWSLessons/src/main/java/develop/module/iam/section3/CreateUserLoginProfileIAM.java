package develop.module.iam.section3;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreateLoginProfileRequest;
import software.amazon.awssdk.services.iam.model.CreateLoginProfileResponse;

/**
 * Create a login profile for a user using the AWS SDK for Java
 * <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-iam-users.html">...</a>
 */
public class CreateUserLoginProfileIAM {
    public static void main(String[] args) {
        String userName = CreateIAMUser.TEST_USERNAME;
        String password = "Nnnn123!";

        createUserLoginProfile(userName, password);
    }

    /**
     * Create a login profile for a user
     *
     * @param userName The name of the user
     * @param password The password for the user
     * @return The response object
     */
    public static CreateLoginProfileResponse createUserLoginProfile(String userName, String password) {
        try (IamClient iam = IamClient.builder().build()) {
            CreateLoginProfileRequest createLoginProfileRequest = CreateLoginProfileRequest.builder()
                    .userName(userName)
                    .password(password)
                    .passwordResetRequired(false)
                    .build();

            CreateLoginProfileResponse createLoginProfileResponse = iam.createLoginProfile(createLoginProfileRequest);

            System.out.printf("User login profile created for user \"%s\"", userName);
            return createLoginProfileResponse;
        } catch (Exception e) {
            System.out.println("Error creating user login profile: " + e.getMessage());
        }
        return null;
    }
}
