package develop.module.iam.section3;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.AddUserToGroupRequest;
import software.amazon.awssdk.services.iam.model.AddUserToGroupResponse;

/**
 * Attach a policy to an IAM user using the AWS SDK for Java V2
 * <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-iam-policies.html">...</a>
 */
public class AddUserToGroupIAM {

    public static void main(String[] args) {

        String userName = CreateIAMUser.TEST_USERNAME;
        String groupName = CreateIAMUserGroup.TEST_GROUP_NAME;

        addUserToUserGroup(userName, groupName);
    }


    public static AddUserToGroupResponse addUserToUserGroup(String userName, String groupName) {
        try (IamClient iam = IamClient.builder().build()) {

            AddUserToGroupRequest addUserToGroupRequest = AddUserToGroupRequest.builder()
                    .userName(userName)
                    .groupName(groupName)
                    .build();

            AddUserToGroupResponse addUserToGroupResponse = iam.addUserToGroup(addUserToGroupRequest);

            System.out.printf("User \"%s\" added to group \"%s\"", userName, groupName);
            return addUserToGroupResponse;
        } catch (Exception e) {
            System.out.println("Error attaching policy to user: " + e.getMessage());
        }
        return null;
    }
}
