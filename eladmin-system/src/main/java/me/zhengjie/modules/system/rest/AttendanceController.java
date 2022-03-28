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
import me.zhengjie.modules.system.domain.Attendance;
import me.zhengjie.modules.system.service.AttendanceService;
import me.zhengjie.modules.system.service.dto.AttendanceQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "系统：考勤管理")
@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Log("新增考勤")
    @ApiOperation("新增考勤")
    @PostMapping
    @PreAuthorize("@el.check('attendance:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Attendance resources){
        attendanceService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改考勤")
    @ApiOperation("修改考勤")
    @PutMapping
    @PreAuthorize("@el.check('attendance:edit')")
    public ResponseEntity<Object> update(@Validated(Attendance.Update.class) @RequestBody Attendance resources) throws Exception {
        attendanceService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("查询考勤")
    @GetMapping
    @PreAuthorize("@el.check('attendance:list')")
    public ResponseEntity<Object> query(AttendanceQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(attendanceService.queryAll(criteria,pageable),HttpStatus.OK);
    }

}
