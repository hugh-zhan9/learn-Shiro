package com.hugh.springboot_shiro_thymeleaf.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hugh on 2021/1/1
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User implements Serializable {
    private String id;
    private String username;
    private String password;
    private String salt;

    // 角色信息
    private List<Role> roles;

}
