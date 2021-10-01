package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Duration;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.iam.IManagedPolicy;
import software.amazon.awscdk.services.iam.ManagedPolicy;
import software.amazon.awscdk.services.iam.Role;
import software.amazon.awscdk.services.lambda.*;
import software.amazon.awscdk.services.lambda.Runtime;

import java.util.Collections;

public class CdkSamExampleStack extends Stack {
    public CdkSamExampleStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public CdkSamExampleStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        new DockerImageFunction(this, "MyFunction", DockerImageFunctionProps.builder()
                .code(DockerImageCode.fromImageAsset("./lambda-image-support/HelloWorldFunction"))
                .timeout(Duration.seconds(20))
                .memorySize(1024)
                .architectures(Collections.singletonList(Architecture.ARM_64))
                .build());
    }
}
