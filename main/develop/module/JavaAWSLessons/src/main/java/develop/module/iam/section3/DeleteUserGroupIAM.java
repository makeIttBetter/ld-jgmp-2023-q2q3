package develop.module.iam.section3;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.DeleteGroupRequest;
import software.amazon.awssdk.services.iam.model.DeleteGroupResponse;
import software.amazon.awssdk.services.iam.model.IamException;

public class DeleteUserGroupIAM {
    public static void main(String[] args) {
        deleteUserGroup(CreateIAMUserGroup.TEST_GROUP_NAME);
    }

    public static DeleteGroupResponse deleteUserGroup(String groupName) {
        try (IamClient iam = IamClient.builder().build()) {
            DeleteGroupRequest deleteGroupRequest = DeleteGroupRequest.builder()
                    .groupName(groupName)
                    .build();
            DeleteGroupResponse deleteGroupResponse = iam.deleteGroup(deleteGroupRequest);
            System.out.printf("User group \"%s\" deleted", groupName);
            return deleteGroupResponse;
        } catch (IamException e) {
            System.out.println("Error deleting user group: " + e.getMessage());
        }
        return null;
    }
}
