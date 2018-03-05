package com.yiguo.offer100.search.serviceImpl;

import java.util.List;

import com.yiguo.offer100.search.bean.Job;
import com.yiguo.offer100.search.repository.JobSearchRepository;
import com.yiguo.offer100.search.service.JobSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("jobSearchService")
public class JobSearchServiceImpl implements JobSearchService {

    @Autowired
    private JobSearchRepository searchJobRepository;

    @Override
    public void saveJob(Job job) {
        try {
            searchJobRepository.save(job);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Job> search(Job job, int start, int size) {
        try {
            return searchJobRepository.search(job, start, size);
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
    public void saveJobs(List<Job> jobs) {
        try {
            searchJobRepository.save(jobs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
