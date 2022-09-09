package com.ntrungthanh1.subjectTest.dto;

import com.ntrungthanh1.subjectTest.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String userName;
    private String fullName;
    private String email;
    private String gender;
    private Date dateOfBirth;
    private String phone;

    public UserDTO(User user){
        this.id = user.getId();
        this.userName = user.getUsername();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.dateOfBirth = user.getDateOfBirth();
        this.phone = user.getPhone();
    }
}
