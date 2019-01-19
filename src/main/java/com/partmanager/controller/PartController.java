package com.partmanager.controller;

import com.partmanager.utility.PageTitle;
import com.partmanager.utility.PartNameNotFoundException;
import com.partmanager.model.Part;
import com.partmanager.service.PartService;
import com.partmanager.utility.PartValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PartController {
    private PartService partService;
    private int sortType = 0;
    private Integer page = null;
    @Autowired
    private PartValidation validator;

    @Autowired(required = true)
    @Qualifier(value = "partService")
    public void setPartService(PartService partService) {
        this.partService = partService;
    }

   @RequestMapping(value = "parts", method = RequestMethod.GET)
   public String getListParts(HttpServletRequest request, Model model,Integer page) {
       this.page = page;
       modelFill(page,new Part(), model,sortType,0);
       return "parts";
   }
    @RequestMapping(value = "/parts/add", method = RequestMethod.POST)
    public String addPart(@ModelAttribute("part") @Valid Part part,Model model, BindingResult result){

        validator.validate(part, result);
        if (result.hasErrors()) {
            modelFill(page,part, model,sortType,1);
            return "parts";
       }else{

            if (part.getId() == 0) {
                partService.addPart(part);
            } else {
                partService.updatePart(part);
            }
            return "redirect:/parts";
       }

    }

    @RequestMapping("/remove/{id}")
    public String removePart(@PathVariable("id") int id){
        partService.removePart(id);

        return "redirect:/parts";
    }

    @RequestMapping("edit/{id}")
    public String editPart(@PathVariable("id") int id, HttpServletRequest request, Model model) {
        Part part = partService.getPartById(id);
        modelFill(page, part , model,sortType,1);

        return "parts";
    }

    @RequestMapping("partdata/{id}")
    public String partData(@PathVariable("id") int id, Model model){
        model.addAttribute("part", partService.getPartById(id));
        model.addAttribute("title", partService.getTitle(PageTitle.PART_DETAILS,false));
        model.addAttribute("mainTitle", partService.getTitle(PageTitle.PART_DETAILS,true));
        return "partdata";
    }
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public String searchParts(HttpServletRequest request, Model model){
        String partName=request.getParameter("partName");
        List<Part> partList = partService.loadPartsByPartName(partName);
        model.addAttribute("listParts",partList);
        model.addAttribute("title", partService.getTitle(PageTitle.PART_SEARCH,false));
        model.addAttribute("mainTitle", partService.getTitle(PageTitle.PART_SEARCH,true));
        return "partdata";
    }

    @RequestMapping(value = "/sort",method = RequestMethod.GET)
    public String sortParts(HttpServletRequest request, Model model){
        String sortParts = request.getParameter("sortParts");
        sortType = getSortType(sortParts);
        modelFill(null,new Part(), model, sortType,0);
        return "parts";

    }
    @RequestMapping(value="/startadd", method = RequestMethod.POST)
    public String startAddAction(HttpServletRequest request, Model model){
       modelFill(page,new Part(), model, sortType,1);

       return "parts";
    }

    private int getSortType(String sortParts) {
        int sort = 0;
        try {
            if(sortParts!=null)
                sort =  Integer.parseInt(sortParts);
        } catch (NumberFormatException e) {
        }

        return  sort;
    }
    private PagedListHolder<Part> getPagedListHolder( List<Part> listParts, Integer page ){
        PagedListHolder<Part> pagedListHolder = new PagedListHolder<Part>(listParts);
        pagedListHolder.setPageSize(10);
        if(page==null || page < 1 || page > pagedListHolder.getPageCount())
            page = 1;
        if(page>pagedListHolder.getPageCount()){
            pagedListHolder.setPage(page);
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
        }
        return pagedListHolder;
    }
    private Model modelFill(Integer page, Part part, Model model, int sortType, int openWindow) {

        List<Part> listParts = partService.loadPartsByPartNeed(sortType);
        PagedListHolder<Part> pagedListHolder = getPagedListHolder(listParts, page);
        model.addAttribute("part", part);
        model.addAttribute("maxPages", pagedListHolder.getPageCount());
        model.addAttribute("page", page);
        model.addAttribute("listParts", pagedListHolder.getPageList());
        model.addAttribute("curChecked", sortType);
        model.addAttribute("compCount", partService.getComputerCount());
        model.addAttribute("title", partService.getTitle(PageTitle.PART_LIST, false));
        model.addAttribute("mainTitle", partService.getTitle(PageTitle.PART_LIST, true));
        model.addAttribute("openModalWindow", openWindow);
        return  model;
    }

}
