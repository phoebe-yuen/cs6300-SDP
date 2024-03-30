package edu.gatech.seclass.jobcompare6300;

import android.annotation.SuppressLint;

// The "Job" Java class is used to save job details for both the current job and the job offers
public class Job {

    // Initialize attributes
    private final int ID;
    private final String title;
    private final String company;
    private final String location;
    private final int costOfLivingIndex;
    private final double yearlySalary;
    private final double yearlyBonus;
    private final int remoteWorkTime;
    private final double retirementBenefitsPercentage;
    private final int leaveTime;
    private final double adjustedYearlySalary;
    private final double adjustedYearlyBonus;
    private final int isCurrentJob;
    private double jobScore;

    // constructor
    public Job(int ID, String title, String company, String location, int costOfLivingIndex, double yearlySalary, double yearlyBonus, int remoteWorkTime, double retirementBenefitsPercentage, int leaveTime, int isCurrentJob) {
        this.ID = ID;
        this.title = title;
        this.company = company;
        this.location = location;
        this.costOfLivingIndex = costOfLivingIndex;
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.remoteWorkTime = remoteWorkTime;
        this.retirementBenefitsPercentage = retirementBenefitsPercentage;
        this.leaveTime = leaveTime;
        this.adjustedYearlySalary =  (100.0 / (double) costOfLivingIndex) * yearlySalary;
        this.adjustedYearlyBonus = (100.0 / (double) costOfLivingIndex) * yearlyBonus;
        this.isCurrentJob = isCurrentJob;
        this.jobScore = 0.0;
    }

    // toString for outputting necessary info
    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        return "Job Title: " + title + ", Company: " + company + ", Score: " + String.format("%.2f", jobScore);
    }

    // getter functions
    public int getID() {
        return ID;
    }
    public String getTitle() {
        return title;
    }
    public String getCompany() {
        return company;
    }
    public String getLocation() {
        return location;
    }
    public int getCostOfLivingIndex() {
        return costOfLivingIndex;
    }
    public double getYearlySalary() {
        return yearlySalary;
    }
    public double getYearlyBonus() {
        return yearlyBonus;
    }
    public int getRemoteWorkTime() {
        return remoteWorkTime;
    }
    public double getRetirementBenefitsPercentage() {
        return retirementBenefitsPercentage;
    }
    public int getLeaveTime() {
        return leaveTime;
    }
    public double getAdjustedYearlySalary() {
        return adjustedYearlySalary;
    }
    public double getAdjustedYearlyBonus() {
        return adjustedYearlyBonus;
    }
    public int getIsCurrentJob() {
        return isCurrentJob;
    }
    public double getJobScore() {
        return jobScore;
    }

    // setter function
    public void setJobScore(double jobScore) {
        this.jobScore = jobScore;
    }
}
