package develop.module.iam.section4;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.*;

import java.util.List;

public class ListIAMUserTags {
    public static void main(String[] args) {
        IamClient iam = IamClient.builder().build();
        try {
            ListUsersRequest listUsersRequest = ListUsersRequest.builder().build();
            ListUsersResponse listUsersResponse = iam.listUsers(listUsersRequest);

            for (User user : listUsersResponse.users()) {
                String userName = user.userName();
                ListUserTagsRequest listUserTagsRequest = ListUserTagsRequest.builder().userName(userName).build();
                ListUserTagsResponse listUserTagsResponse = iam.listUserTags(listUserTagsRequest);

                List<Tag> tags = listUserTagsResponse.tags();
                System.out.println("User: " + userName + ", Tags: ");

                if (!tags.isEmpty()) {
                    for (Tag tag : tags) {
                        System.out.println(tag.key() + " = " + tag.value());
                    }
                } else {
                    System.out.println("No tags found");
                }
                System.out.println();
            }
        } catch (IamException e) {
            System.err.println("Error: " + e.awsErrorDetails().errorMessage());
        }
    }
}
