package cn.edu.ecnu.model.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Rate {

    Integer id;

    Double rate;

    String comment;

    Date rateTime;

    Movie movie;
}
