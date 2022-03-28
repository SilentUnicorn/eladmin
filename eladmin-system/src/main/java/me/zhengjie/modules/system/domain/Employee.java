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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="a_employee")
public class Employee extends BaseEntity implements Serializable {

    @Id
    @Column(name = "employee_id")
    @NotNull(groups = Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID", hidden = true)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @ApiModelProperty(value = "用户岗位")
    @JoinTable(name = "sys_users_jobs",
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "job_id",referencedColumnName = "job_id")})
    private Set<Job> jobs;

    @OneToOne
    @JoinColumn(name = "dept_id")
    @ApiModelProperty(value = "用户部门")
    private Dept dept;

    @OneToOne
    @JoinColumn(name = "title_id")
    @ApiModelProperty(value = "职务")
    private Title title;

    @NotBlank
    @Column(unique = true)
    @ApiModelProperty(value = "工号")
    private String employeeNo;

    @NotBlank
    @Column(unique = true)
    @ApiModelProperty(value = "身份证号")
    private String idNo;

    @NotBlank
    @ApiModelProperty(value = "姓名")
    private String name;

    @NotBlank
    @Column(unique = true)
    @ApiModelProperty(value = "紧急联系人电话")
    private String emergencyPhone;

    @NotBlank
    @Column(unique = true)
    @ApiModelProperty(value = "证件照片")
    private String idPhoto;

    @Email
    @NotBlank
    @ApiModelProperty(value = "邮箱")
    private String email;

    @NotBlank
    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "用户性别")
    private String gender;

    @NotNull
    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;
}
