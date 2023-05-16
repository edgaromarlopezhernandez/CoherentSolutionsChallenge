package com.coherentSolutions.challenge.util;

import com.coherentSolutions.challenge.dtos.PageInfo;
import org.springframework.stereotype.Component;

/**
 * This class serves as a helper to perform pagination calculations for controllers that query resources using
 * the HTTP GET verb.
 * The values provided are: total number of records, total number of pages, page size, previous page,
 * current page and next page.
 */
@Component
public class PageableHelper {
    public PageInfo helper (Integer pageNumber, Integer pageSize, Long records){
        PageInfo pageInfo = new PageInfo();
        if(pageSize < 1){
            pageSize = 1;
        }
        if(pageSize > 50){
            pageSize = 50;
        }
        Double totalPages = Math.ceil((records*1.0) / pageSize);
        if(pageNumber > totalPages){
            pageNumber = new Double(totalPages).intValue();
        }
        pageNumber = pageNumber - 1;
        if(pageNumber < 1){
            pageNumber = 0;
        }
        Integer nextPage;
        if(pageNumber >= totalPages -1){
            nextPage = 1;
        }
        else{
            nextPage = pageNumber + 2;
        }
        Integer previousPage;
        if(pageNumber < 1){
            previousPage = new Double(totalPages).intValue();
        }else{
            previousPage = pageNumber;
        }
        pageInfo.setTotalRecords(records);
        pageInfo.setCurrentPage(pageNumber + 1);
        pageInfo.setTotalPages((new Double(totalPages)).longValue());
        pageInfo.setPageSize(pageSize);
        pageInfo.setNextPage(nextPage);
        pageInfo.setPreviousPage(previousPage);
        return pageInfo;
    }
}