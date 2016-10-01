package by.pvt.DTO;

/**
 * Class for getting pagination params from user pages
 */
public class PaginationDTO {
    private int id;
    private int pages;
    private int perPages;

    public PaginationDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPerPages() {
        return perPages;
    }

    public void setPerPage(int perPage) {
        this.perPages = perPage;
    }
}
