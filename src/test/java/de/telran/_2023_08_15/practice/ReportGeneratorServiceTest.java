package de.telran._2023_08_15.practice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ReportGeneratorServiceTest {
    @InjectMocks
    private ReportGeneratorService reportGeneratorService;
    @Mock
    private IReportGenerator reportGenerator;
    @Captor
    private ArgumentCaptor<ReportEntity> reportCaptor;

    @SuppressWarnings("deprecation")
    @Test
    public void test() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2016, 11, 25);
        Calendar endDate = Calendar.getInstance();
        endDate.set(9999, 12, 31);
        String reportContent = "Empty Report";
        reportGeneratorService.generateReport(startDate.getTime(), endDate.getTime(), reportContent.getBytes());

        Mockito.verify(reportGenerator).generateReport(reportCaptor.capture());

        ReportEntity report = reportCaptor.getValue();

        assertEquals(startDate.getTime().getYear(),  report.getStartDate().getYear());
        assertEquals(startDate.getTime().getMonth(), report.getStartDate().getMonth());
        assertEquals(startDate.getTime().getDay(), report.getStartDate().getDay());

        assertEquals(endDate.getTime().getYear(),  report.getEndDate().getYear());
        assertEquals(endDate.getTime().getMonth(), report.getEndDate().getMonth());
        assertEquals(endDate.getTime().getDay(), report.getEndDate().getDay());

        assertEquals("Empty Report", new String(report.getContent()));
    }
}
