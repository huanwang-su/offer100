package com.yiguo.offer100.search.serviceImpl;

import java.util.List;

import com.yiguo.offer100.search.bean.Talent;
import com.yiguo.offer100.search.repository.TalentSearchRepository;
import com.yiguo.offer100.search.service.TalentSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("talentSearchService")
public class TalentSearchServiceImpl implements TalentSearchService {

    @Autowired
    private TalentSearchRepository searchTalentRepository;

    @Override
    public void saveTalent(Talent talent) {
        try {
            searchTalentRepository.save(talent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Talent> search(Talent talent, int start, int end) {
        try {
            return searchTalentRepository.search(talent, start, end);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            searchTalentRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(List<String> id) {
        try {
            searchTalentRepository.deleteByIds(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveTalents(List<Talent> talents) {
        try {
            searchTalentRepository.save(talents);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
