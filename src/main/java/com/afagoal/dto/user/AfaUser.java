package com.afagoal.dto.user;

import com.afagoal.utils.json.CustomDateDeserialize;
import com.afagoal.utils.json.CustomDateSerialize;
import com.afagoal.utils.json.CustomDateTimeDeserialize;
import com.afagoal.utils.json.CustomDateTimeSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by BaoCai on 2019/3/16. Description:
 */
@Getter
@Setter
public class AfaUser implements Serializable {

    public static final AfaUser EMPTY_USER = new AfaUser();

    public static final String PREFIX_TOKEN = "afagoal ";

    private String token;

    private Long id;

    private String openId;

    private String unionId;

    private String userName;

    private String nickName;

    private String mobile;

    @JsonDeserialize(using = CustomDateDeserialize.class)
    @JsonSerialize(using = CustomDateSerialize.class)
    private LocalDate birthday;

    private String avatarUrl;

    private Byte gender;

    private String country;

    private String province;

    private String city;

    private String county;

    private String town;

    private String village;

    private String group;

    private int loginTimes;

    @JsonDeserialize(using = CustomDateTimeDeserialize.class)
    @JsonSerialize(using = CustomDateTimeSerialize.class)
    private LocalDateTime lastLoginTime;

    @JsonDeserialize(using = CustomDateTimeDeserialize.class)
    @JsonSerialize(using = CustomDateTimeSerialize.class)
    private LocalDateTime createdAt;


}
