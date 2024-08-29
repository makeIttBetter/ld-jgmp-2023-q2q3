package develop.module.iam.section3;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.DeleteAccessKeyRequest;
import software.amazon.awssdk.services.iam.model.DeleteLoginProfileRequest;
import software.amazon.awssdk.services.iam.model.DeleteUserRequest;
import software.amazon.awssdk.services.iam.model.RemoveUserFromGroupRequest;

/**
 * Delete a user using the AWS SDK for Java
 * <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-iam-users.html">...</a>
 */
public class DeleteUserIAM {

    public static void main(String[] args) {
        deleteUser(CreateIAMUser.TEST_USERNAME);
    }

    /**
     * Delete a user
     * With the username, the method will remove the user from the group, delete the login profile, delete the access key, and delete the user
     *
     * @param userName The name of the user
     */
    public static void deleteUser(String userName) {
        try (IamClient iam = IamClient.builder().build()) {

            // Remove the user from the group
            RemoveUserFromGroupRequest removeUserFromGroupRequest = RemoveUserFromGroupRequest.builder()
                    .groupName(CreateIAMUserGroup.TEST_GROUP_NAME)
                    .userName(userName)
                    .build();
            iam.removeUserFromGroup(removeUserFromGroupRequest);
            System.out.printf("User \"%s\" removed from group \"%s\"", userName, CreateIAMUserGroup.TEST_GROUP_NAME);

            // Delete the login profile first
            DeleteLoginProfileRequest deleteLoginProfileRequest = DeleteLoginProfileRequest.builder()
                    .userName(userName)
                    .build();
            iam.deleteLoginProfile(deleteLoginProfileRequest);
            System.out.printf("Login profile deleted for user \"%s\"", userName);

            // Delete the access key
            DeleteAccessKeyRequest deleteAccessKeyRequest = DeleteAccessKeyRequest.builder()
                    .userName(userName)
                    .build();
            iam.deleteAccessKey(deleteAccessKeyRequest);
            System.out.printf("Access key deleted for user \"%s\"", userName);

            // Delete the user
            DeleteUserRequest deleteUserRequest = DeleteUserRequest.builder()
                    .userName(userName)
                    .build();
            iam.deleteUser(deleteUserRequest);
            System.out.printf("User \"%s\" deleted", userName);
        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }
}
