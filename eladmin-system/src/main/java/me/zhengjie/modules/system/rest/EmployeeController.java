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
package me.zhengjie.modules.system.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.system.domain.Employee;
import me.zhengjie.modules.system.service.EmployeeService;
import me.zhengjie.modules.system.service.dto.EmployeeQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "系统：员工管理")
@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @Log("新增考勤")
    @ApiOperation("新增员工")
    @PostMapping
    @PreAuthorize("@el.check('employee:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Employee resources){
        employeeService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改考勤")
    @ApiOperation("修改员工")
    @PutMapping
    @PreAuthorize("@el.check('employee:edit')")
    public ResponseEntity<Object> update(@Validated(Employee.Update.class) @RequestBody Employee resources) throws Exception {
        employeeService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("查询员工")
    @GetMapping
    @PreAuthorize("@el.check('employee:list')")
    public ResponseEntity<Object> query(EmployeeQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(employeeService.queryAll(criteria,pageable),HttpStatus.OK);
    }
}
