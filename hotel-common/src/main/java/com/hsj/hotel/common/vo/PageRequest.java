package com.hsj.hotel.common.vo;

public class PageRequest {

    private Integer page;

    private Integer rows;

    public void setPage(Integer page) {
        if (page == null || page < 1) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }

    public void setRows(Integer rows) {
        if (rows == null) {
            this.rows = 10;
        } else {
            this.rows = rows;
        }
    }

    public Integer getPage() {
        if (this.page == null || this.page < 1) {
            return 1;
        } else {
            return page;
        }

    }

    public Integer getRows() {
        if (this.rows == null) {
            return 10;
        } else {
            return rows;
        }
    }
}
