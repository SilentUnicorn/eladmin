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

@Entity
@Getter
@Setter
@Table(name="a_attendance")
public class Attendance extends BaseEntity implements Serializable {

    @Id
    @Column(name = "attendance_id")
    @NotNull(groups = Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID", hidden = true)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    @ApiModelProperty(value = "员工")
    private Employee employee;

    @NotBlank
    @ApiModelProperty(value = "类型")
    private Integer type;

    @NotBlank
    @ApiModelProperty(value = "操作类型")
    private Integer operateType;

    @Email
    @NotBlank
    @ApiModelProperty(value = "扩展字段")
    private String extend;
}
