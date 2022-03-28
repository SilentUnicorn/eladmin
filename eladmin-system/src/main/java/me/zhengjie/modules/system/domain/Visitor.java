/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package me.zhengjie.modules.system.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import me.zhengjie.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name="a_visitor")
public class Visitor extends BaseEntity implements Serializable {

    @Id
    @Column(name = "visitor_id")
    @NotNull(groups = Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID", hidden = true)
    private Long id;

    @NotBlank
    @JoinColumn(name = "user_id",referencedColumnName = "employee_id")
    @ApiModelProperty(value = "拜访人")
    private Employee employee;

    @NotBlank
    @ApiModelProperty(value = "姓名")
    private String name;

    @NotBlank
    @Column(unique = true)
    @ApiModelProperty(value = "证件照片")
    private String idPhoto;

    @NotBlank
    @ApiModelProperty(value = "电话号码")
    private String phone;

    @NotBlank
    @Column(unique = true)
    @ApiModelProperty(value = "身份证号")
    private String idNo;
}
