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
import me.zhengjie.modules.system.domain.Employee;
import me.zhengjie.modules.system.repository.EmployeeRepository;
import me.zhengjie.modules.system.service.AttendanceService;
import me.zhengjie.modules.system.service.EmployeeService;
import me.zhengjie.modules.system.service.dto.EmployeeDto;
import me.zhengjie.modules.system.service.dto.EmployeeQueryCriteria;
import me.zhengjie.modules.system.service.dto.UserQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.EmployeeMapper;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import me.zhengjie.utils.ValidationUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "data")
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto findById(long id) {
        Employee employee = employeeRepository.findById(id).orElseGet(Employee::new);
        ValidationUtil.isNull(employee.getId(), "Employee", "id", id);
        return employeeMapper.toDto(employee);
    }

    @Override
    public void create(Employee resources) {
        employeeRepository.save(resources);
    }

    @Override
    public void update(Employee resources){
        employeeRepository.save(resources);
    }

    @Override
    public void delete(Set<Long> ids) {
        employeeRepository.deleteAllByIdIn(ids);
    }

    @Override
    public Object queryAll(EmployeeQueryCriteria criteria, Pageable pageable) {
        Page<Employee> page = employeeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(employeeMapper::toDto));
    }

    @Override
    public List<EmployeeDto> queryAll(EmployeeQueryCriteria criteria) {
        return null;
    }

    @Override
    public void download(List<EmployeeDto> queryAll, HttpServletResponse response) throws IOException {

    }
}
