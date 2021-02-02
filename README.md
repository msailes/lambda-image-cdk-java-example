mkdir cdk-sam-example
cd cdk-sam-example
cdk init app --language java

add the lambda dependency to the pom.xml

```xml
    <dependencies>
    ...
        <dependency>
            <groupId>software.amazon.awscdk</groupId>
            <artifactId>lambda</artifactId>
            <version>${cdk.version}</version>
        </dependency>
    ...
    </dependencies>
```

Use SAM to create an Lambda function (Image packaging & Java 11)

```bash
$ sam init 
Which template source would you like to use?
        1 - AWS Quick Start Templates
        2 - Custom Template Location
Choice: 1
What package type would you like to use?
        1 - Zip (artifact is a zip uploaded to S3)      
        2 - Image (artifact is an image uploaded to an ECR image repository)
Package type: 2

Which base image would you like to use?
        1 - amazon/nodejs12.x-base
        2 - amazon/nodejs10.x-base
        3 - amazon/python3.8-base
        4 - amazon/python3.7-base
        5 - amazon/python3.6-base
        6 - amazon/python2.7-base
        7 - amazon/ruby2.7-base
        8 - amazon/ruby2.5-base
        9 - amazon/go1.x-base
        10 - amazon/java11-base
        11 - amazon/java8.al2-base
        12 - amazon/java8-base
        13 - amazon/dotnetcore3.1-base
        14 - amazon/dotnetcore2.1-base
Base image: 10

Which dependency manager would you like to use?
        1 - maven
        2 - gradle
Dependency manager: 1

Project name [sam-app]: lamda-image-support
```

Add the Lambda function in lambda-image-support/src/main/java/com/myorg/CdkSamExampleStack

```java

        new DockerImageFunction(this, "MyFunction", DockerImageFunctionProps.builder()
                .code(DockerImageCode.fromImageAsset("./lambda-image-support/HelloWorldFunction"))
                .timeout(Duration.seconds(20))
                .memorySize(1024)
                .build());
```

Deploy to AWS

```bash
cdk deploy
```
