package com.myorg;

import software.amazon.awscdk.core.App;

import java.util.Arrays;

public class CdkSamExampleApp {
    public static void main(final String[] args) {
        App app = new App();

        new CdkSamExampleStack(app, "CdkSamExampleStack");

        app.synth();
    }
}
