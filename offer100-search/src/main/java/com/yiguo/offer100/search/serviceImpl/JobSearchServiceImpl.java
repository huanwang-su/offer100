package com.yiguo.offer100.search.serviceImpl;

import java.util.List;

import com.yiguo.offer100.common.page.PageInfo;
import com.yiguo.offer100.search.bean.SearchJob;
import com.yiguo.offer100.search.repository.JobSearchRepository;
import com.yiguo.offer100.search.service.JobSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("jobSearchService")
public class JobSearchServiceImpl implements JobSearchService {

    @Autowired
    private JobSearchRepository searchJobRepository;

    @Override
    public void saveJob(SearchJob searchJob) {
        try {
            searchJobRepository.save(searchJob);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PageInfo<SearchJob> search(SearchJob searchJob, PageInfo<SearchJob> pageInfo, String sortKey, Boolean asc) {
        try {
            return searchJobRepository.search(searchJob, pageInfo, sortKey, asc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            searchJobRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(List<String> id) {
        try {
            searchJobRepository.deleteByIds(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveJobs(List<SearchJob> searchJobs) {
        try {
            searchJobRepository.save(searchJobs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
