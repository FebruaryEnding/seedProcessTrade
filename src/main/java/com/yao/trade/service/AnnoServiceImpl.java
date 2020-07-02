package com.yao.trade.service;

import com.yao.trade.domain.AnnouncementEntity;
import com.yao.trade.domain.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AnnoServiceImpl implements IAnnoService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Override
    public String getAnno() {
        List<AnnouncementEntity> all = announcementRepository.findAll();
        if(CollectionUtils.isEmpty(all)){
            return "";
        }else {
            return all.get(0).getAnnouncement();
        }
    }
}
