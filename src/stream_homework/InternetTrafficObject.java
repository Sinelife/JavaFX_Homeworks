package stream_homework;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InternetTrafficObject {

    private Date date;
    private double networkInput;
    private double networkOutput;
    private double uaixInput;
    private double uaixOutput;
    private double internetInput;
    private double internetOutput;
    private String formattedDate;


    public InternetTrafficObject(Date date, double networkInput, double networkOutput, double uaixInput, double uaixOutput, double internetInput, double internetOutput) {
        this.date = date;
        this.networkInput = networkInput;
        this.networkOutput = networkOutput;
        this.uaixInput = uaixInput;
        this.uaixOutput = uaixOutput;
        this.internetInput = internetInput;
        this.internetOutput = internetOutput;
        this.formattedDate = formatDate.format(date);
    }

    public InternetTrafficObject() {
    }


    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getNetworkInput() {
        return networkInput;
    }

    public void setNetworkInput(double networkInput) {
        this.networkInput = networkInput;
    }

    public double getNetworkOutput() {
        return networkOutput;
    }

    public void setNetworkOutput(double networkOutput) {
        this.networkOutput = networkOutput;
    }

    public double getUaixInput() {
        return uaixInput;
    }

    public void setUaixInput(double uaixInput) {
        this.uaixInput = uaixInput;
    }

    public double getUaixOutput() {
        return uaixOutput;
    }

    public void setUaixOutput(double uaixOutput) {
        this.uaixOutput = uaixOutput;
    }

    public double getInternetInput() {
        return internetInput;
    }

    public void setInternetInput(double internetInput) {
        this.internetInput = internetInput;
    }

    public double getInternetOutput() {
        return internetOutput;
    }

    public void setInternetOutput(double internetOutput) {
        this.internetOutput = internetOutput;
    }


    @Override
    public String toString() {
        return "InternetTrafficObject{" +
                "date=" + date +
                ", networkInput=" + networkInput +
                ", networkOutput=" + networkOutput +
                ", uaixInput=" + uaixInput +
                ", uaixOutput=" + uaixOutput +
                ", internetInput=" + internetInput +
                ", internetOutput=" + internetOutput +
                '}';
    }

    private SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");


}