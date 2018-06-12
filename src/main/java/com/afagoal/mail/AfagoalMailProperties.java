package com.afagoal.mail;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by BaoCai on 18/6/12.
 * Description:
 */
@Getter
@Setter
public class AfagoalMailProperties {

    //messagePart
    private String organization = "AFAGOAL虚拟币行情";
    private String from;
    private String baseContent = "<div style='float:right;'><a class='btn  btn-primary btn-block' href=http://118.24.27.218:8080/login>联系我们</a></div>";
}
