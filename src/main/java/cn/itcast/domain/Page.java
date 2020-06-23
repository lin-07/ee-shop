package cn.itcast.domain;

import java.util.List;

/**
 * @author lin-PC
 */
public class Page<T> {

    private int pageNumber;
    private int pageSize;
    private int startIndex;
    private int totalPage;
    private int totalRecord;
    private List<T> data;
    private int start;
    private int end;

    // 计算开始和结束的索引
    private void jisuan(){
        start = 1;
        end = 10;
        //1.总页数不满足于十页
        //totalPage 如果直接写属性 没有初始化 没有计算的过程 手动调用get方法 将总页数初始化
        if(getTotalPage() < 10){
            end = totalPage;
        }else{
            //2.总页数大于十页
            start = pageNumber - 4;
            end = pageNumber + 5;
            //2.1 start有可能小于1
            if(start < 1){
                start = 1;
                end = 10;
            }
            //2.2 end 有可能大于总页数
            if(end > totalPage){
                end= totalPage;
                start = totalPage - 9;
            }

        }
    }

    public int getStart() {
        jisuan();
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        jisuan();
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Page(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartIndex() {
        startIndex = (pageNumber - 1) * pageSize;
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalPage() {
        if (totalRecord % pageSize == 0) {
            totalPage = totalRecord / pageSize;
        }else {
            totalPage = totalRecord / pageSize + 1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
