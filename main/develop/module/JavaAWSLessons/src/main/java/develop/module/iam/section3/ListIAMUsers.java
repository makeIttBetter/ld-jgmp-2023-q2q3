package develop.module.iam.section3;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.ListUsersRequest;
import software.amazon.awssdk.services.iam.model.ListUsersResponse;

/**
 * List IAM users from the account using the AWS SDK for Java V2
 * <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-iam-users.html">...</a>
 */
public class ListIAMUsers {
    public static void main(String[] args) {
        listIAMUsers();
    }

    public static ListUsersResponse listIAMUsers() {
        try (IamClient iam = IamClient.builder().build()) {

            ListUsersRequest listUsersRequest = ListUsersRequest.builder().build();
            ListUsersResponse listUsersResponse = iam.listUsers(listUsersRequest);

            listUsersResponse.users().forEach(user ->
                    System.out.println(user.userName()));

            return listUsersResponse;
        } catch (Exception e) {
            System.out.println("Error listing users: " + e.getMessage());
        }
        return null;
    }
}
