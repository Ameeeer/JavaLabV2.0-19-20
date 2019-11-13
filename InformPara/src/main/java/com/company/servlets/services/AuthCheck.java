package com.company.servlets.services;

import com.company.servlets.models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthCheck {
    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public boolean checkEmail(User user) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(user.getEmail());
        return matcher.matches();
    }
}
