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
import me.zhengjie.modules.system.domain.Title;
import me.zhengjie.modules.system.repository.TitleRepository;
import me.zhengjie.modules.system.service.TitleService;
import me.zhengjie.modules.system.service.dto.TitleDto;
import me.zhengjie.modules.system.service.dto.TitleQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.TitleMapper;
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
public class TitleServiceImpl implements TitleService {

    private final TitleRepository titleRepository;
    private final TitleMapper titleMapper;

    @Override
    public TitleDto findById(long id) {
        Title title = titleRepository.findById(id).orElseGet(Title::new);
        ValidationUtil.isNull(title.getId(), "Title", "id", id);
        return titleMapper.toDto(title);
    }

    @Override
    public void create(Title resources) {
        titleRepository.save(resources);
    }

    @Override
    public void update(Title resources){
        titleRepository.save(resources);
    }

    @Override
    public void delete(Set<Long> ids) {
        titleRepository.deleteAllByIdIn(ids);
    }

    @Override
    public Object queryAll(TitleQueryCriteria criteria, Pageable pageable) {
        Page<Title> page = titleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(titleMapper::toDto));
    }
}
