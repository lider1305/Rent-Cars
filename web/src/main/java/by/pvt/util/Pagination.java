package by.pvt.util;

import by.pvt.DTO.PaginationDTO;
import by.pvt.constants.Constants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static by.pvt.constants.Constants.*;

@Component("pagination")
public class Pagination {
    private static final int MIN_ROWS_ON_PAGE = 5;
    private static final int FIRST_PAGE = 1;
    private static final int PAGE_FOR_PAGINATION = 1;

    public Pagination() {
    }

    /**
     * the method receives a  number of records per page
     *
     * @param request
     * @return
     */
    public int getItemPerPage(HttpServletRequest request) {
        int perPage;
        //checks whether the  number of records to output to the page
        if (request.getParameter(Constants.PER_PAGES) == null || request.getParameter(Constants.PER_PAGES).length() == 0) {
            perPage = MIN_ROWS_ON_PAGE;
        } else {
            perPage = Integer.parseInt(request.getParameter(Constants.PER_PAGES));
        }
        request.setAttribute(PER_COUNT, perPage);
        return perPage;
    }

    /**
     * the method gets the record number from which to read the value from the database
     *
     * @param request
     * @return
     */
    public int getStartRow(HttpServletRequest request) {
        //default params to read
        int perPage = getItemPerPage(request);
        int currentRowFromDatabase = FIRST_PAGE;
        int currentPage = currentRowFromDatabase;
        //checks the current page number on the user interface
        if (request.getParameter(START_PAGE) != null) {
            currentPage = Integer.parseInt(request.getParameter(START_PAGE));
            currentRowFromDatabase = Integer.parseInt(request.getParameter(START_PAGE)) * perPage - perPage + 1;
        }
        request.setAttribute(START_PAGE, currentRowFromDatabase);
        request.setAttribute(COUNTER, currentPage);
        return currentRowFromDatabase;
    }

    /**
     * check the total number of records on the multiplicity of the minimum number of records per page,
     * if the number is not a multiple, add another page
     *
     * @param pagesCount- number of pages
     * @return
     */
    public int getPagesCount(int pagesCount) {
        if (pagesCount % MIN_ROWS_ON_PAGE != 0) {
            pagesCount += PAGE_FOR_PAGINATION;
        }
        return pagesCount;
    }

    /**
     * the method get pagination params from user interface
     *
     * @param request
     * @return
     */
    public PaginationDTO getPaginationParams(HttpServletRequest request) {
        PaginationDTO pagination = new PaginationDTO();
        pagination.setPages((Integer) request.getAttribute("start"));
        pagination.setPerPage((Integer) request.getAttribute("perPages"));
        return pagination;
    }
}
