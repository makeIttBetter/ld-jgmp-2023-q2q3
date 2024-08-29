package develop.module.iam.section4;

import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreateRoleRequest;
import software.amazon.awssdk.services.iam.model.CreateRoleResponse;
import software.amazon.awssdk.services.iam.model.IamException;

/**
 * Create an IAM role using the AWS SDK for Java V2
 * <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-iam-roles.html">...</a>
 */
public class CreateRoleIAM {
    public static final String ROLE_NAME = "MyNewJavaRole";

    public static void main(String[] args) {
        String trustPolicyDocument = """
                {
                    "Version": "2012-10-17",
                    "Statement": [
                        {
                            "Effect": "Allow",
                            "Principal": {
                                "Service": "ec2.amazonaws.com"
                            },
                            "Action": "sts:AssumeRole"
                        }
                    ]
                }""";


        createIAMRole(ROLE_NAME, trustPolicyDocument);
    }

    /**
     * Create an IAM role using the AWS SDK for Java V2
     *
     * @param roleName            The name of the role to create
     * @param trustPolicyDocument The trust policy document
     * @return The response from the createRole call
     */
    public static CreateRoleResponse createIAMRole(String roleName, String trustPolicyDocument) {
        try (IamClient iam = IamClient.builder().build()) {

            CreateRoleRequest createRoleRequest = CreateRoleRequest.builder()
                    .roleName(roleName)
                    .assumeRolePolicyDocument(trustPolicyDocument)
                    .build();

            CreateRoleResponse createRoleResponse = iam.createRole(createRoleRequest);
            System.out.println("Role created: " + createRoleResponse.role().arn());
            return createRoleResponse;
        } catch (IamException e) {
            System.out.println("Error creating role: " + e.getMessage());
        }
        return null;
    }
}
