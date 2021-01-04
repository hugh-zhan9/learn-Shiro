package com.hugh.springboot_shiro_thymeleaf.entity;

import lombok.*;

import java.io.Serializable;

/**
 * Created by hugh on 2021/1/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Perms implements Serializable {
    private String id;
    private String name;
    private String url;
}
