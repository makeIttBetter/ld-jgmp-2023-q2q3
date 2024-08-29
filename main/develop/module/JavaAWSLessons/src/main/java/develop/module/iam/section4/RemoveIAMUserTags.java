package develop.module.iam.section4;

import develop.module.iam.section3.CreateIAMUser;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.UntagUserRequest;

import java.util.ArrayList;
import java.util.List;

public class RemoveIAMUserTags {
    public static void main(String[] args) {
        IamClient iam = IamClient.builder().build();
        String userName = CreateIAMUser.TEST_USERNAME;
        List<String> tagKeys = new ArrayList<>();
        tagKeys.add(AddTagToUserIAM.TEST_TAG_KEY_1);
        tagKeys.add(AddTagToUserIAM.TEST_TAG_KEY_2);

        UntagUserRequest untagUserRequest = UntagUserRequest.builder()
                .userName(userName)
                .tagKeys(tagKeys)
                .build();

        try {
            iam.untagUser(untagUserRequest);
            System.out.printf("Tags removed for user \"%s\"", userName);
        } catch (IamException e) {
            System.err.println("Error: " + e.awsErrorDetails().errorMessage());
        }
    }
}
