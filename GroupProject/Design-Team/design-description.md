# Design Description

1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (<a name="footnote">disabled if no job offers were entered yet</a><sup>[This functionality will be enabled if there are either (1) at least two job offers, in case there is no current job, or (2) at least one job offer, in case there is a current job.](#footnote)</sup>).
   
   >A 'MainMenu' class that contains 4 methods to cater the above-listed 4 functions is shown below:
   >|Method|Function|
   >|--- | ---|
   >|editOrCreateCurrentJob(): void |Enter or edit current job details (depending on whether the 'JobListUtil' utility class can find a stored current job within the 'jobList' attribute)|
   >|addJobOffer(): void  |Enter job offers|
   >|comparisonSettings(): void  |Adjust the comparison settings|
   >|compareJobOffers(): void  |Compare job offers (available only when the 'JobListUtil' utility class can find at least two stored jobs within the 'jobList' attribute)|

2. When choosing to enter current job details, a user will:
   1. Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
      1. Title
      2. Company
      3. Location (entered as city and state)
      4. Cost of living in the location (expressed as an [index](https://www.expatistan.com/cost-of-living/index/north-america))
      5. Yearly salary
      6. Yearly bonus
      7. Allowed weekly telework days (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5)
      8. Retirement benefits (as percentage matched)
      9. Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)    
      
      >```The user interface will be handled by GUI implementation. The data fields listed above are represented by the 'CurrentJob' class, which is extended from the 'Job' class. When the 'CurrentJob' class is accessed from 'MainMenu', the data fields above will be blank if no current job exists. Otherwise, the user interface will use the 'Job' object returned by the 'findCurrentJob()' method in the 'JobListUtil' class to display the data.```
      
   2. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
      >```The 'CurrentJob' class has a 'saveCurrentJob()' method for saving job details as a 'Job' object, with its 'isCurrentJob' attribute set to 'True', to the 'jobList' in 'MainMenu'. The 'CurrentJob' class also has a 'cancelCurrentJob()' method for cancelling and exiting without saving. Both methods have on click finish() to return to 'MainMenu'.```

3. When choosing to enter job offers, a user will:
   1. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
      >```The user interface will be handled by GUI implementation. The details of job offers are represented by the 'JobOffer' class, which is extended from the 'Job' class. When the 'JobOffer' class is accessed from 'MainMenu', all of its data fields will be blank, waiting for input.```
      
   2. Be able to either save the job offer details or cancel.
      >```The 'JobOffer' class has a 'saveJobOffer()' method to save the offer details as a 'Job' object, with its 'isCurrentJob' attribute set to 'False', to the 'jobList' in 'MainMenu', and this method has on click finish() to redirect to 'JobOfferUtil'. The 'JobOffer' class also has A 'cancelJobOffer()' method to cancel the input details by clearing all data fields.```
      
   3. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).
      >The 'JobOffersUtil' class contains 3 methods to perform the above-listed functions, as shown below:
      >|Method|Function|
      >|--- | ---|
      >|addJobOffer(): void|Enter another offer. It is realized by redirecting to a new instance of 'JobOffer' class with blank data fields.|
      >|returnMainMenu(): void|Return to the main menu|
      >|compareWithCurrent(job1, job2): void|Compare the offer (if the user saved it) with the current job details (if present). It is realized by passing the saved job offer as variable job1 and the current job as variable job2, to the 'ComparisonResult' class for result viewing.|
   
4. When adjusting the comparison settings, the user can assign integer weights to:
   1. Yearly salary
   2. Yearly bonus
   3. Allowed weekly telework days
   4. Retirement benefits
   5. Leave time  
  If no weights are assigned, all factors are considered equal.
      >```The user interface will be handled by GUI implementation. The comparison settings are represented by the 'ComparisonSettings' class. When the 'ComparisonSettings' class is accessed from 'MainMenu', its five weight attributes (initially set to 1) can be modified. The 'ComparisonSettings' class has a 'saveSettings()' method to save the current attribute values and return to 'MainMenu' with on click finish().```

5. When choosing to compare job offers, a user will:
   1. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
      >```When the 'compareJobOffers()' method in 'MainMenu' is triggered, the 'jobList' stored in the 'MainMenu' will be used in the 'CompareJobOffers' class. This class will run the 'rankJobs(jobList)' method to set the index of the jobs according to their scores calculated by the 'computeJobScore(job)' method and display the 'rankedJobList'. If a 'Job' object within the 'rankedJobList' has its 'isCurrentJob' attribute set to 'True', this object will be indicated as the current job using some GUI elements.```
   
   2. Select two jobs to compare and trigger the comparison.
      >```The 'compareJobs(job1, job2)' function will compare the selected two jobs by passing them to the 'ComparisonResult' class for result viewing.```
   
   3. Be shown a table comparing the two jobs, displaying, for each job:
      1. Title
      2. Company
      3. Location
      4. Yearly salary adjusted for cost of living
      5. Yearly bonus adjusted for cost of living
      6. Allowed weekly telework days
      7. Retirement benefits (as percentage matched)
      8. Leave time
      >```The 'ComparisonResult' class has a 'displayResult(job1, job2)' method to trigger a display of the above eight attributes for both job1 and job2.```
      
   4. Be offered to perform another comparison or go back to the main menu.
      >```The 'ComparisonResult' class has a 'performAnotherComparison()' method to redirect back to the 'CompareJobOffers' class for comparing another pair of jobs. It also has a 'returnMainMenu()' method to go back to the main menu.```
   
6. When ranking jobs, a job’s score is computed as the **weighted** sum of:

   AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)

   where:  
   AYS = yearly salary adjusted for cost of living  
   AYB = yearly bonus adjusted for cost of living  
   RBP = retirement benefits percentage  
   LT = leave time  
   RWT = telework days per week  
   The rationale for the RWT subformula is:  
    1. value of an employee hour = (AYS / 260) / 8  
    2. commute hours per year (assuming a 1-hour/day commute) =  
      1 * (260 - 52 * RWT)  
    3. therefore **travel-time cost = (260 - 52 * RWT) * (AYS / 260) / 8** 

   For example, if the weights are 2 for the yearly salary, 2 for the retirement benefits, and 1 for all other factors, the score would be computed as:

   2/7 * AYS + 1/7 * AYB + 2/7 * (RBP * AYS) + 1/7 * (LT * AYS / 260) - 1/7 * ((260 - 52 * RWT) * (AYS / 260)/8)

   >```The 'computeJobScore(job)' method in the 'CompareJobOffers' class will handle the above calculation, where the weights are feeded from the 'ComparisonSettings' class, and the variables AYS and AYB are directly obtained from the derived attributes '/adjustedYearlySalary' and '/adjustedYearlyBonus' within the 'Job' class, respectively.```
   
7. The user interface must be intuitive and responsive.
   >```This is not represented in our design, as it will be handled entirely within the GUI implementation.```

8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).
   >```This is not represented in our design, as no communication or saving between devices is necessary.```
   
