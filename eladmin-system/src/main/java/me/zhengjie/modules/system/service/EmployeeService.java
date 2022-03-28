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

import me.zhengjie.modules.system.domain.Employee;
import me.zhengjie.modules.system.service.dto.EmployeeDto;
import me.zhengjie.modules.system.service.dto.EmployeeQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface EmployeeService {

    EmployeeDto findById(long id);

    void create(Employee resources);

    void update(Employee resources);

    void delete(Set<Long> ids);

    Object queryAll(EmployeeQueryCriteria criteria, Pageable pageable);

    List<EmployeeDto> queryAll(EmployeeQueryCriteria criteria);

    void download(List<EmployeeDto> queryAll, HttpServletResponse response) throws IOException;

}
