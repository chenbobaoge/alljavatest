package org.bobochen.common;

import java.util.List;

public class Page<T> {
    public Page() {
    }

    public Page(long currentPage, long totalPages, long totalItems, long itemsPerPage, List<T> items) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
        this.itemsPerPage = itemsPerPage;
        this.items = items;
    }

    private long currentPage ;

    /// <summary>
    /// 总页数。
    /// </summary>
    private long totalPages ;

    /// <summary>
    /// 总记录数。
    /// </summary>
    private long totalItems ;

    /// <summary>
    /// 每页记录数。
    /// </summary>
    private long itemsPerPage ;



    /// <summary>
    /// 当前页记录。
    /// </summary>
    private List<T> items ;



    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public long getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(long itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
