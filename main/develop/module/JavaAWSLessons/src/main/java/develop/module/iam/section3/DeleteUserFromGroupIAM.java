package develop.module.iam.section3;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.RemoveUserFromGroupRequest;
import software.amazon.awssdk.services.iam.model.RemoveUserFromGroupResponse;

/**
 * Remove a user from a user group using the AWS SDK for Java V2
 * <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-iam-users.html">...</a>
 */
public class DeleteUserFromGroupIAM {

    public static void main(String[] args) {

        String userName = CreateIAMUser.TEST_USERNAME;
        String groupName = CreateIAMUserGroup.TEST_GROUP_NAME;

        deleteUserFromUserGroup(userName, groupName);
    }

    /**
     * Remove a user from a user group
     *
     * @param userName The name of the user
     * @param groupName The name of the group
     */
    public static RemoveUserFromGroupResponse deleteUserFromUserGroup(String userName, String groupName) {
        try (IamClient iam = IamClient.builder().build()) {
            RemoveUserFromGroupRequest removeUserFromGroupRequest = RemoveUserFromGroupRequest.builder()
                    .groupName(groupName)
                    .userName(userName)
                    .build();
            RemoveUserFromGroupResponse removeUserFromGroupResponse = iam.removeUserFromGroup(removeUserFromGroupRequest);

            System.out.printf("User \"%s\" removed from group \"%s\"", userName, groupName);
            return removeUserFromGroupResponse;
        } catch (IamException e) {
            System.out.println("Error removing user from the userGroup: " + e.getMessage());
        }
        return null;
    }
}
