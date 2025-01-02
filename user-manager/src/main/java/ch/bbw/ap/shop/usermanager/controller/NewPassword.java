package ch.bbw.ap.shop.usermanager.controller;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class NewPassword {

    @NotBlank
    @Length(min = 12)
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
