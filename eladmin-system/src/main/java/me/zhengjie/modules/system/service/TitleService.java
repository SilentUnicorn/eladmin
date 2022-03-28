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

import me.zhengjie.modules.system.domain.Title;
import me.zhengjie.modules.system.service.dto.EmployeeQueryCriteria;
import me.zhengjie.modules.system.service.dto.TitleDto;
import me.zhengjie.modules.system.service.dto.TitleQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface TitleService {

    TitleDto findById(long id);

    void create(Title resources);

    void update(Title resources);

    void delete(Set<Long> ids);

    Object queryAll(TitleQueryCriteria criteria, Pageable pageable);

}
