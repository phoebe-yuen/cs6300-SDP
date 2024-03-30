package edu.gatech.seclass.jobcompare6300;

import java.util.Comparator;

// The "JobScoreSorter" Java class is for sorting jobs by their scores
public class JobScoreSorter implements Comparator<Job>{
    @Override
    public int compare(Job o1, Job o2) {
        return Double.compare(o1.getJobScore(), o2.getJobScore());
    }
}
