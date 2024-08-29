package develop.module.iam.section3;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreateUserRequest;
import software.amazon.awssdk.services.iam.model.CreateUserResponse;
import software.amazon.awssdk.services.iam.model.IamException;

/**
 * Create an IAM user using the AWS SDK for Java V2
 * <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-iam-users.html">...</a>
 */
public class CreateIAMUser {

    // The username of the user to create
    public static final String TEST_USERNAME = "test-user";
    public static void main(String[] args) {

        createIAMUser();

    }

    public static CreateUserResponse createIAMUser() {
        try (IamClient iam = IamClient.builder().build()) {

            CreateUserRequest createUserRequest = CreateUserRequest.builder().userName("test-user").build();
            CreateUserResponse createUserResponse = iam.createUser(createUserRequest);

            System.out.println("User created: " + createUserResponse.user().arn());

            return createUserResponse;
        } catch (IamException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
        return null;
    }
}