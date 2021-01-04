package com.hugh.springboot_shiro_thymeleaf.entity;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hugh on 2021/1/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Role implements Serializable {
    private String id;
    private String name;

    // 权限信息
    private List<Perms> perms;
}
