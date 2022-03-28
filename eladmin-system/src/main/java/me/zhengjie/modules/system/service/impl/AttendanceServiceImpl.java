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
package me.zhengjie.modules.system.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.Attendance;
import me.zhengjie.modules.system.repository.AttendanceRepository;
import me.zhengjie.modules.system.service.AttendanceService;
import me.zhengjie.modules.system.service.dto.AttendanceDto;
import me.zhengjie.modules.system.service.dto.AttendanceQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.AttendanceMapper;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import me.zhengjie.utils.ValidationUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "data")
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;

    @Override
    public AttendanceDto findById(long id) {
        Attendance attendance = attendanceRepository.findById(id).orElseGet(Attendance::new);
        ValidationUtil.isNull(attendance.getId(), "Attendance", "id", id);
        return attendanceMapper.toDto(attendance);
    }

    @Override
    public void create(Attendance resources) {
        attendanceRepository.save(resources);
    }

    @Override
    public void update(Attendance resources){
        attendanceRepository.save(resources);
    }

    @Override
    public void delete(Set<Long> ids) {
        attendanceRepository.deleteAllByIdIn(ids);
    }

    @Override
    public Object queryAll(AttendanceQueryCriteria criteria, Pageable pageable) {
        Page<Attendance> page = attendanceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(attendanceMapper::toDto));
    }
}
