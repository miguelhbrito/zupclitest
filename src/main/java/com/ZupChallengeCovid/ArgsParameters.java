package com.ZupChallengeCovid;

public class ArgsParameters {

    public String processPassedArgs(String[] args) {

        StringBuilder concatenatedArgs = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("country")) {
                i += 1;
            }
            if (i > 1) {
                concatenatedArgs.append(" " + args[i]);
            } else {
                concatenatedArgs.append(args[i]);
            }
        }
        return concatenatedArgs.toString();
    }
}
