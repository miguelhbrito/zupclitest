package com.ZupChallengeCovid;

import java.io.IOException;

public class ZupChallengeApplication {
    public static void main(String[] args) throws IOException {
        CLIApp cliApp = new CLIApp();
        System.out.println(cliApp.runCLI(args));
    }
}
