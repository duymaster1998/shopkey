package vn.nuce.core.web.command;

import java.util.List;

public class AbstractCommand<T> {
    protected T pojo;
    private String crudaction;
    private List<T> listResult;
    private String tableId = "tableList";
    private Integer maxPageItems;
    private Integer totalPage;
    private Integer totalItem;
    private int firstItem = 0;
    private String sortExpression;
    private String sortDirection;
    private String[] checkList;
    private String messageRespone;
    private Integer page;
    private String urlType;

    public Integer getTotalItem() {
        return totalItem;
    }

    public Integer getMaxPageItems() {
        return maxPageItems;
    }

    public void setMaxPageItems(Integer maxPageItems) {
        this.maxPageItems = maxPageItems;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public T getPojo() {
        return pojo;
    }

    public void setPojo(T pojo) {
        this.pojo = pojo;
    }

    public String getCrudaction() {
        return crudaction;
    }

    public void setCrudaction(String crudaction) {
        this.crudaction = crudaction;
    }

    public List<T> getListResult() {
        return listResult;
    }

    public void setListResult(List<T> listResult) {
        this.listResult = listResult;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public int getFirstItem() {
        return firstItem;
    }

    public void setFirstItem(int firstItem) {
        this.firstItem = firstItem;
    }

    public String getSortExpression() {
        return sortExpression;
    }

    public void setSortExpression(String sortExpression) {
        this.sortExpression = sortExpression;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String[] getCheckList() {
        return checkList;
    }

    public void setCheckList(String[] checkList) {
        this.checkList = checkList;
    }

    public String getMessageRespone() {
        return messageRespone;
    }

    public void setMessageRespone(String messageRespone) {
        this.messageRespone = messageRespone;
    }


    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }
}
