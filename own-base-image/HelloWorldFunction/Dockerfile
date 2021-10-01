#FROM arm64v8/maven as build-image
#
#WORKDIR "/task"
#COPY src/ src/
#COPY pom.xml ./
#
#RUN mvn -q clean install
#RUN mvn dependency:copy-dependencies -DincludeScope=compile
#
#FROM public.ecr.aws/lambda/java:11-arm64
#
#COPY --from=build-image /task/target/classes /var/task/
#COPY --from=build-image /task/target/dependency /var/task/lib
## Command can be overwritten by providing a different command in the template directly.
#CMD ["helloworld.App::handleRequest"]

# we'll use Amazon Linux 2 + Corretto 11 as our base
FROM arm64v8/amazoncorretto:11 as base

# configure the build environment
FROM base as build
RUN yum install -y maven
WORKDIR /src

# cache and copy dependencies
ADD pom.xml .
RUN mvn dependency:go-offline dependency:copy-dependencies

# compile the function
ADD . .
RUN mvn package

# copy the function artifact and dependencies onto a clean base
FROM base
WORKDIR /function

COPY --from=build /src/target/dependency/*.jar ./
COPY --from=build /src/target/*.jar ./

# configure the runtime startup as main
ENTRYPOINT [ "/usr/bin/java", "-cp", "./*", "com.amazonaws.services.lambda.runtime.api.client.AWSLambda" ]
# pass the name of the function handler as an argument to the runtime
CMD ["helloworld.App::handleRequest"]