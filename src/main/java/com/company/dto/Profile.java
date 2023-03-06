package com.company.dto;

import com.company.step.Profile_Step;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Profile {
    private Integer id;
    private Long profile_id;
    private String name;
    private String surname;
    private String phone;
    private String location;
    private LocalDate date;
    private Profile_Step step;


    public Profile(Long profile_id) {
        this.profile_id = profile_id;
    }

    public String writableString() {
        return id + "#" + name + "#" + surname + "#" + phone + "#" + date + "#" + step;
    }
}
