package controller.service.pagination;


public class Carriage {
    private int startIdx;
    private int entityAmount;
    private int lastPage;
    private int currentPage;

    public Carriage(int startIdx, int entityAmount, int lastPage, int currentPage) {
        this.startIdx = startIdx;
        this.entityAmount = entityAmount;
        this.lastPage = lastPage;
        this.currentPage = currentPage;
    }

    public int getStartIdx() {
        return startIdx;
    }

    public void setStartIdx(int startIdx) {
        this.startIdx = startIdx;
    }

    public int getEntityAmount() {
        return entityAmount;
    }

    public void setEntityAmount(int entityAmount) {
        this.entityAmount = entityAmount;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
