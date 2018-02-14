package foter.com.httppost.server;

public class RequestObject {

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getMinCount() {
        return minCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    private String startDate, endDate;
    private int minCount, maxCount;

    public RequestObject(String startDate, String endDate, int minCount, int maxCount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.minCount = minCount;
        this.maxCount = maxCount;
    }



}
