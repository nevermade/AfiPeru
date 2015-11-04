package com.example.dp2.afiperu.interactor;

import com.example.dp2.afiperu.R;
import com.example.dp2.afiperu.domain.Document;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

/**
 * Created by Nevermade on 20/10/2015.
 */
public class PeriodReportInteractorImpl implements PeriodReportInteractor {


    @Override
    public ArrayList<Document> getAllPeriodReports() {
        ArrayList<Document> documents = new ArrayList<>();
        /*Calendar calendar;
        calendar = new GregorianCalendar(2015, 3, 22, 15, 21);
        documents.add(new Document("Reporte 2015-0.pdf", R.drawable.ic_docs_pdf, 0.2, calendar.getTime().getTime()));
        calendar = new GregorianCalendar(2015, 8, 21, 12, 05);
        documents.add(new Document("Reporte 2015-1.pdf", R.drawable.ic_docs_pdf,1.2, calendar.getTime().getTime()));
        calendar = new GregorianCalendar(2015, 12, 18, 13, 14);
        documents.add(new Document("Reporte 2015-2.pdf", R.drawable.ic_docs_pdf, 0.01, calendar.getTime().getTime()));

        Collections.sort(documents);*/

        return documents;
    }
}
