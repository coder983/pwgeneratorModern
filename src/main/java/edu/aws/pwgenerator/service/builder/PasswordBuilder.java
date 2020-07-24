package edu.aws.pwgenerator.service.builder;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class PasswordBuilder {

    private String password;
    private PasswordData passwordData;

    public PasswordBuilder(PasswordData passwordData) {

        this.passwordData = passwordData;

    }

    public String builder() {
        long type = passwordData.getType();
        String password = "";

        switch ((int)type) {
            case 0:
                password = passwordData.getFirstname() +
                        passwordData.getSeperator() +
                        passwordData.getPlace() +
                        passwordData.getSeperator() +
                        passwordData.getYear();
                break;
            case 1:
                password = passwordData.getFirstname() +
                        passwordData.getLastname() +
                        passwordData.getSeperator() +
                        passwordData.getPlace() +
                        passwordData.getSeperator() +
                        passwordData.getYear();
                break;
            case 2:
                password = passwordData.getYear() +
                        passwordData.getSeperator() +
                        passwordData.getFirstname();
                break;
            case 3:
                password = passwordData.getYear() +
                        passwordData.getSeperator() +
                        passwordData.getFirstname() +
                        passwordData.getLastname();
                break;
            case 4:
                password = passwordData.getYear().substring(0, 2) +
                        passwordData.getSeperator() +
                        passwordData.getFirstname() +
                        passwordData.getSeperator() +
                        passwordData.getYear().substring(2);
                break;
            case 5:
                password = passwordData.getYear().substring(0, 2) +
                        passwordData.getSeperator() +
                        passwordData.getFirstname() +
                        passwordData.getLastname() +
                        passwordData.getSeperator() +
                        passwordData.getYear().substring(2);
                break;
            case 6:
                password = passwordData.getYear() +
                        passwordData.getSeperator() +
                        passwordData.getEvent();
                break;
            case 7:
                password = passwordData.getYear().substring(0, 2) +
                        passwordData.getSeperator() +
                        passwordData.getEvent() +
                        passwordData.getSeperator() +
                        passwordData.getYear().substring(2);
                break;
            default:
                password = "Invalid Password Type";
                break;
        }

        if (password.length() < passwordData.getPasswordLength()) {
            long padLength = passwordData.getPasswordLength() - password.length();
            if (padLength > 0) {
                String padding = passwordData.getPaddingMap().get(String.valueOf(padLength));
                password = password + padding;
            }
        }
        return password;
    }

}
