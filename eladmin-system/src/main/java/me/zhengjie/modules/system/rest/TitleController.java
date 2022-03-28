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
import me.zhengjie.modules.system.service.TitleService;
import me.zhengjie.modules.system.service.dto.TitleQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "系统：职务管理")
@RestController
@RequestMapping("/api/title")
@RequiredArgsConstructor
public class TitleController {
    private final TitleService titleService;

    @Log("新增职务")
    @ApiOperation("新增职务")
    @PostMapping
    @PreAuthorize("@el.check('title:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Title resources){
        titleService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改职务")
    @ApiOperation("修改职务")
    @PutMapping
    @PreAuthorize("@el.check('title:edit')")
    public ResponseEntity<Object> update(@Validated(Title.Update.class) @RequestBody Title resources) throws Exception {
        titleService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("查询职务")
    @GetMapping
    @PreAuthorize("@el.check('title:list')")
    public ResponseEntity<Object> query(TitleQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(titleService.queryAll(criteria,pageable),HttpStatus.OK);
    }
}
