package com.reidlo.fo4.userInfo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String nickname;
    private int level;
    private String pvpDivisionName;
    private String coachDivisionName;
    private Date pvpAchievementDate;
    private Date coachAchievementDate;
}
