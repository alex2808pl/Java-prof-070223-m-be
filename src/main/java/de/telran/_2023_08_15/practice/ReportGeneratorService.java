package de.telran._2023_08_15.practice;

import java.util.Date;

public class ReportGeneratorService {

    IReportGenerator reportGenerator;

    public void generateReport(Date startDate, Date endDate, byte[] content) {
        ReportEntity reportEntity = new ReportEntity(startDate, endDate, content);
        reportGenerator.generateReport(reportEntity);
    }
}
