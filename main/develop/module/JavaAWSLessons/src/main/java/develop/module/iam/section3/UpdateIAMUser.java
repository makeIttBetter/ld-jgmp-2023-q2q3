package develop.module.iam.section3;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.UpdateUserRequest;
import software.amazon.awssdk.services.iam.model.UpdateUserResponse;

/**
 * Update an IAM user using the AWS SDK for Java V2
 * <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-iam-users.html">...</a>
 */
public class UpdateIAMUser {
    public static void main(String[] args) {

        try (IamClient iam = IamClient.builder().build()) {
            String oldUsername = "test-user";
            String newUsername = "test-user-new";

            // Create a new IAM user
            updateIAMUser(oldUsername, newUsername);

            System.out.println("\nList of IAM users:");
            ListIAMUsers.listIAMUsers();

            // Revert the username back to the original
            updateIAMUser(newUsername, oldUsername);

            System.out.println("\nList of IAM users:");
            ListIAMUsers.listIAMUsers();

        } catch (IamException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    public static UpdateUserResponse updateIAMUser(String oldUsername, String newUsername) {
        try (IamClient iam = IamClient.builder().build()) {
            UpdateUserRequest updateUserRequest = UpdateUserRequest.builder()
                    .userName(oldUsername)
                    .newUserName(newUsername)
                    .build();
            UpdateUserResponse updateUserResponse = iam.updateUser(updateUserRequest);

            System.out.printf("Username updated from \"%s\" to \"%s\"", oldUsername, newUsername);

            return updateUserResponse;
        } catch (IamException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
        return null;
    }
}
