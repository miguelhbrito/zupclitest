package com.ZupChallengeCovid;

public class ZupChallengeApplication {
    public static void main(String[] args) {
        CLIApp cliApp = new CLIApp();
        System.out.println(cliApp.runCLI(args));
    }
}
