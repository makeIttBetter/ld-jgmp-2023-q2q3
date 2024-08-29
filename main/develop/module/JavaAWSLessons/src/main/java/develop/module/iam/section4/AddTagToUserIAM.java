package develop.module.iam.section4;

import develop.module.iam.section3.CreateIAMUser;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.Tag;
import software.amazon.awssdk.services.iam.model.TagUserRequest;

import java.util.ArrayList;
import java.util.List;

public class AddTagToUserIAM {

    public static final String TEST_TAG_KEY_1 = "Department";
    public static final String TEST_TAG_KEY_2 = "Project";

    public static void main(String[] args) {
        IamClient iam = IamClient.builder().build();
        String userName = CreateIAMUser.TEST_USERNAME;
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.builder().key(TEST_TAG_KEY_1).value("HR").build());
        tags.add(Tag.builder().key(TEST_TAG_KEY_2).value("Onboarding").build());

        TagUserRequest tagUserRequest = TagUserRequest.builder()
                .userName(userName)
                .tags(tags)
                .build();

        try {
            iam.tagUser(tagUserRequest);
            System.out.printf("Tags added for user \"%s\"", userName);
        } catch (IamException e) {
            System.err.println("Error: " + e.awsErrorDetails().errorMessage());
        }
    }
}
