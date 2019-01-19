package com.partmanager.service;

import com.partmanager.utility.PageTitle;
import com.partmanager.utility.PartNameNotFoundException;
import com.partmanager.model.Part;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface PartService {
    void addPart(Part part);
    void updatePart(Part part);
    void removePart(int id);
    Part getPartById(int id);
    List<Part> getListParts();
    List<Part> loadPartsByPartName(String partName) throws DataAccessException;
    List<Part> loadPartsByPartNeed(int need) throws DataAccessException;
    String getTitle(PageTitle PageTitle, Boolean isMainTitle);
    int getComputerCount();
}
