package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Duration;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.iam.IManagedPolicy;
import software.amazon.awscdk.services.iam.ManagedPolicy;
import software.amazon.awscdk.services.iam.Role;
import software.amazon.awscdk.services.lambda.AssetImageCodeProps;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.DockerImageCode;
import software.amazon.awscdk.services.lambda.DockerImageFunction;
import software.amazon.awscdk.services.lambda.DockerImageFunctionProps;
import software.amazon.awscdk.services.lambda.EcrImageCode;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.FunctionProps;
import software.amazon.awscdk.services.lambda.Runtime;

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
                .build());
    }
}
