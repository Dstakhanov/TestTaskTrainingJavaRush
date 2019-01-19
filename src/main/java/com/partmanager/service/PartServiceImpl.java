package com.partmanager.service;

import com.partmanager.utility.PageTitle;
import com.partmanager.utility.PartNameNotFoundException;
import com.partmanager.dao.PartDao;
import com.partmanager.model.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PartServiceImpl implements PartService {

    @Autowired
    private PartDao partDao;

    public void setPartDao(PartDao partDao) {
        this.partDao= partDao;
    }
    
    @Override
    @Transactional
    public void addPart(Part part) {
        partDao.addPart(part);
    }
    
    @Override
    @Transactional
    public void updatePart(Part part) {
        partDao.updatePart(part);
    }
    
    @Override
    @Transactional
    public void removePart(int id) {
        partDao.removePart(id);
    }
    
    @Override
    @Transactional
    public Part getPartById(int id) {
        return partDao.getPartById(id);
    }
    
    @Override
    @Transactional
    public List<Part> getListParts() {
        return partDao.getListParts();
    }
    
    @Override
    @Transactional
    public List<Part> loadPartsByPartName(String partName) throws DataAccessException {
        return partDao.findPartByName(partName);
    }
    
    @Override
    @Transactional
    public List<Part> loadPartsByPartNeed(int need) throws DataAccessException {
        return partDao.findPartBуNeed(need);
    }

    @Override
    public String getTitle(PageTitle pageTitle, Boolean isMainTitle) {
        if(pageTitle==PageTitle.PART_DETAILS) return isMainTitle ? "Part data" : "Part details";
        if(pageTitle==PageTitle.PART_SEARCH) return isMainTitle ? "Part data" : "Search results";
        if(pageTitle==PageTitle.PART_LIST) return isMainTitle ? "Part page" : "Part list";
        if(pageTitle==PageTitle.MISTAKE_PAGE) return isMainTitle ? "Mistake page" : "Not found part with such name";
        return "";
    }
    
    @Override
    @Transactional
    public int getComputerCount() {
        int OnlyNeedPart = 1;
        Map<String,Integer> mapParts = new HashMap<String,Integer>();
        List<Part> listNeedParts = partDao.findPartBуNeed(OnlyNeedPart);
        for (Part part: listNeedParts) {
            String partName = part.getPartName();
            int partCount = part.getPartAmount();
           if(mapParts.containsKey(partName)){
               mapParts.put(partName, mapParts.get(partName) + partCount);
            }else
               mapParts.put(partName,partCount);
        }
        int min = Collections.min(mapParts.values());
        return min;
    }
}
