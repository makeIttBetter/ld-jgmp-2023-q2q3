package develop.module.iam.section3;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreateGroupRequest;
import software.amazon.awssdk.services.iam.model.CreateGroupResponse;
import software.amazon.awssdk.services.iam.model.IamException;

/**
 * Create an IAM user group using the AWS SDK for Java V2
 * <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-iam-groups.html">...</a>
 */
public class CreateIAMUserGroup {

    public static final String TEST_GROUP_NAME = "test-group";

    public static void main(String[] args) {

        createIAMUserGroup();
    }

    /**
     * Create an IAM user group using the AWS SDK for Java V2
     * <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-iam-groups.html">...</a>
     */
    public static CreateGroupResponse createIAMUserGroup() {
        try (IamClient iam = IamClient.builder().build()) {

            CreateGroupRequest createGroupRequest = CreateGroupRequest.builder().groupName(TEST_GROUP_NAME).build();
            CreateGroupResponse createGroupResponse = iam.createGroup(createGroupRequest);

            System.out.println("User group created: " + createGroupResponse.group().arn());

            return createGroupResponse;
        } catch (IamException e) {
            System.out.println("Error creating user group: " + e.getMessage());
        }
        return null;
    }
}
