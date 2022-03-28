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
package me.zhengjie.modules.system.service;

import me.zhengjie.modules.system.domain.Attendance;
import me.zhengjie.modules.system.service.dto.AttendanceDto;
import me.zhengjie.modules.system.service.dto.AttendanceQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface AttendanceService {

    AttendanceDto findById(long id);

    void create(Attendance resources);

    void update(Attendance resources);

    void delete(Set<Long> ids);

    Object queryAll(AttendanceQueryCriteria criteria, Pageable pageable);

}
