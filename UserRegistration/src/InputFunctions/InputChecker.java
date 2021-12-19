package InputFunctions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputChecker implements InputCheckFunctions{
    private static final String EMAILREGEX = "^([a-z0-9_\\.-]{3,20})@([a-z0-9_\\.-]{2,10})\\.([a-z\\.]{2,6})$";
    private static final String NAMEREGEX = "^[A-Za-zА-Яа-я]{2,20}$";
    private static final String SURNAMEREGEX = "^[A-Za-zА-Яа-я]{2,25}$";
    private static final String PHONEREGEX = "^375[0-9]{9}$";

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public boolean emailCheck(String email) {
        pattern = Pattern.compile(EMAILREGEX);
        matcher = pattern.matcher(email);
        return matcher.find();
    }

    @Override
    public boolean nameCheck(String name) {
        pattern = Pattern.compile(NAMEREGEX);
        matcher = pattern.matcher(name);
        return matcher.find();
    }

    @Override
    public boolean surnameCheck(String surname) {
        pattern = Pattern.compile(SURNAMEREGEX);
        matcher = pattern.matcher(surname);
        return matcher.find();
    }

    @Override
    public boolean phoneCheck(String phone) {
        pattern = Pattern.compile(PHONEREGEX);
        matcher = pattern.matcher(phone);
        return matcher.find();
    }
}
