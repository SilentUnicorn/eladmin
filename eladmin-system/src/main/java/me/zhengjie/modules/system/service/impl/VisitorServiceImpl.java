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
import me.zhengjie.modules.system.domain.Visitor;
import me.zhengjie.modules.system.repository.VisitorRepository;
import me.zhengjie.modules.system.service.TitleService;
import me.zhengjie.modules.system.service.VisitorService;
import me.zhengjie.modules.system.service.dto.VisitorDto;
import me.zhengjie.modules.system.service.dto.VisitorQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.VisitorMapper;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import me.zhengjie.utils.ValidationUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "data")
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository visitorRepository;
    private final VisitorMapper visitorMapper;

    @Override
    public VisitorDto findById(long id) {
        Visitor visitor = visitorRepository.findById(id).orElseGet(Visitor::new);
        ValidationUtil.isNull(visitor.getId(), "Visitor", "id", id);
        return visitorMapper.toDto(visitor);
    }

    @Override
    public void create(Visitor resources) {
        visitorRepository.save(resources);
    }

    @Override
    public void update(Visitor resources){
        visitorRepository.save(resources);
    }

    @Override
    public void delete(Set<Long> ids) {
        visitorRepository.deleteAllByIdIn(ids);
    }

    @Override
    public Object queryAll(VisitorQueryCriteria criteria, Pageable pageable) {
        Page<Visitor> page = visitorRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(visitorMapper::toDto));
    }
}
