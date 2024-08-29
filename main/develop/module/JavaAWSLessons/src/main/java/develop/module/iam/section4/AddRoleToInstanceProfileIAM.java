package develop.module.iam.section4;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.AddRoleToInstanceProfileRequest;
import software.amazon.awssdk.services.iam.model.IamException;

/**
 * Add a role to an instance profile using the AWS SDK for Java V2
 * <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-iam-instance-profiles.html">...</a>
 */
public class AddRoleToInstanceProfileIAM {

    public static void main(String[] args) {
        addRoleToInstanceProfile(CreateRoleIAM.ROLE_NAME, CreateInstanceProfileIAM.TEST_INSTANCE_PROFILE_NAME);
    }

    /**
     * Add a role to an instance profile using the AWS SDK for Java V2
     *
     * @param roleName            The name of the role to add
     * @param instanceProfileName The name of the instance profile to add the role to
     * @return True if the role was added to the instance profile, otherwise false
     */
    public static boolean addRoleToInstanceProfile(String roleName, String instanceProfileName) {
        try (IamClient iam = IamClient.builder().build()) {
            AddRoleToInstanceProfileRequest addRoleToInstanceProfileRequest = AddRoleToInstanceProfileRequest.builder().instanceProfileName(instanceProfileName).roleName(roleName).build();
            iam.addRoleToInstanceProfile(addRoleToInstanceProfileRequest);
            System.out.printf("Role \"%s\" added to instance profile \"%s\"", roleName, instanceProfileName);
            return true;
        } catch (IamException e) {
            System.out.println("Error adding role to instance profile: " + e.getMessage());
        }
        return false;
    }
}
