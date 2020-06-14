package com.ZupChallengeCovid;

public class ArgsParameters {

    public String processPassedArgs(String[] args) {

        StringBuilder concatenatedArgs = new StringBuilder();
        int countCountry = 0;

        for (int i = 0; i < args.length; i++) {
            if ("country".equals(args[i])) {
                countCountry += 1;
                i += 1;
            }
            if (isASecondOrMoreCountryName(args, i)) {
                concatenatedArgs.append(" " + args[i]);
            } else if (isAFirstCountryName(args, countCountry, i)) {
                concatenatedArgs.append(args[i]);
            }
        }

        if(countCountry > 1){
            return null;
        }
        return concatenatedArgs.toString();
    }

    public boolean isAFirstCountryName(String[] args, int countCountry, int i) {
        return (i < args.length && !"country".equals(args[i]) && countCountry > 0);
    }

    public boolean isASecondOrMoreCountryName(String[] args, int i) {
        return (i > 1 && i < args.length && !"country".equals(args[i]));
    }
}
