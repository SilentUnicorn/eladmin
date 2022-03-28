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
import me.zhengjie.modules.system.domain.Title;
import me.zhengjie.modules.system.domain.Visitor;
import me.zhengjie.modules.system.service.VisitorService;
import me.zhengjie.modules.system.service.dto.VisitorQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "系统：访客管理")
@RestController
@RequestMapping("/api/visitor")
@RequiredArgsConstructor
public class VisitorController {
    private final VisitorService visitorService;

    @Log("新增访客")
    @ApiOperation("新增访客")
    @PostMapping
    @PreAuthorize("@el.check('visitor:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Visitor resources){
        visitorService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改访客")
    @ApiOperation("修改访客")
    @PutMapping
    @PreAuthorize("@el.check('visitor:edit')")
    public ResponseEntity<Object> update(@Validated(Title.Update.class) @RequestBody Visitor resources) throws Exception {
        visitorService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("查询访客")
    @GetMapping
    @PreAuthorize("@el.check('visitor:list')")
    public ResponseEntity<Object> query(VisitorQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(visitorService.queryAll(criteria,pageable),HttpStatus.OK);
    }
}
