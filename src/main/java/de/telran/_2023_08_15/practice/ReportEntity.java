package de.telran._2023_08_15.practice;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class ReportEntity {
    private Long reportId;
    private Date startDate;
    private Date endDate;
    private byte[] content;

    public ReportEntity(Date startDate, Date endDate, byte[] content) {
        this.reportId = getRandomNumber(1, 100000000);
        this.startDate = startDate;
        this.endDate = endDate;
        this.content = content;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public byte[] getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportEntity that = (ReportEntity) o;
        return Objects.equals(reportId, that.reportId) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Arrays.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(reportId, startDate, endDate);
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }

    @Override
    public String toString() {
        return "ReportEntity{" +
                "reportId=" + reportId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", content=" + Arrays.toString(content) +
                '}';
    }

    private long getRandomNumber(int min, int max) {
        return (long) ((Math.random() * (max - min)) + min);
    }

}
