package com.partmanager.dao;

import com.partmanager.model.Part;

import java.util.List;

public interface PartDao {
    void addPart(Part part);
    void updatePart(Part part);
    void removePart(int id);
    Part getPartById(int id);
    List<Part> getListParts();
    List <Part> findPartByName(String name);
    List <Part> findPartBуNeed(int need);
}
