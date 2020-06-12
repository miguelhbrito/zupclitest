package com.ZupChallengeCovid;

public class ArgsParameters {

    public String processPassedArgs(String[] args) {

        StringBuilder concatenatedArgs = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            if ("country".equals(args[i])) {
                i += 1;
            }
            if (i > 1 && i < args.length && !"country".equals(args[i])) {
                concatenatedArgs.append(" " + args[i]);
            } else if (i < args.length && !"country".equals(args[i])) {
                concatenatedArgs.append(args[i]);
            }
        }

        return concatenatedArgs.toString();
    }
}
