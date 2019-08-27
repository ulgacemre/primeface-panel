package com.github.adminfaces.starter.bean;

import org.primefaces.model.chart.PieChartModel;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class JobMarketBean {
    private PieChartModel model;

    @PostConstruct
    public void init() {
        model = new PieChartModel();
        model.set("Java", 62);//jobs in thousands
        model.set("Python", 46);
        model.set("JavaScript", 38);
        model.set("C++", 31);
        model.set("C#", 27);
        model.set("PHP", 14);
        model.set("Perl", 14);

        //followings are some optional customizations:
        //set title
        model.setTitle("2018 Jobs for top languages");
        //set legend position to 'e' (east), other values are 'w', 's' and 'n'
        model.setLegendPosition("e");
        //enable tooltips
        model.setShowDatatip(true);
        //show labels inside pie chart
        model.setShowDataLabels(true);
        //show label text  as 'value' (numeric) , others are 'label', 'percent' (default). Only one can be used.
        model.setDataFormat("value");
        //format: %d for 'value', %s for 'label', %d%% for 'percent'
        model.setDataLabelFormatString("%dK");
        //pie sector colors
        model.setSeriesColors("aaf,afa,faa,ffa,aff,faf,ddd");
    }

    public PieChartModel getModel() {
        return model;
    }
}