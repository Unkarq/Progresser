package com.javagda17.progresser.model.dto;

import com.javagda17.progresser.model.Gender;
import com.javagda17.progresser.model.Specialization;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class AppUserUpdateRequestDto {
    private Long idUserEdited;
    private String nameUserEdited;
    private String surnameUserEdited;
    @Email
    private String emailUserEdited;
    private String cityUserEdited;
    private String phonenumberUserEdited;
}
