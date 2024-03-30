# Design Description

1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (<a name="footnote">disabled if no job offers were entered yet</a><sup>[This functionality will be enabled if there are either (1) at least two job offers, in case there is no current job, or (2) at least one job offer, in case there is a current job.](#footnote)</sup>).
   
   >A ‘MainMenu’ class contains 4 methods to cater the above-listed 4 functions, shown as below:
   >|Method|Function|
   >|--- | ---|
   >|editOrCreateCurrentJob(): CurrentJob |enter or edit current job details|
   >|addJobOffer(): JobOffers  |enter job offers|
   >|comparisonSettings(): ComparisonSettings  |adjust the comparison settings|
   >|compareJobOffers(): CompareJobOffers  |compare job offers (disabled if no job offers were entered)|

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
      
      >```The user interface will be handled by GUI implementation. The data fields listed above are represented by the ‘CurrentJob’ class, which is extended from ‘Job’ class. If no current job exists, ‘MainMenu’ will create a new ‘CurrentJob’ object. The user interface will use the ‘CurrentJob’ object returned by ‘editOrCreateCurrentJob()’ method in  ‘MainMenu’ to display the data.```
      
   2. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
      >```The ‘CurrentJob’ class has ‘saveJob()’ method for saving job details and ‘cancelJob()’ method for cancelling and exiting without saving. Both methods have on click finish() to return to ‘MainMenu’.```

3. When choosing to enter job offers, a user will:
   1. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
      >```The user interface will be handled by GUI implementation. The details of job offers are represented by the ‘JobOffers’ class, which is extended from ‘Job’ class. ```
      
   2. Be able to either save the job offer details or cancel.
      >```The ‘JobOffers’ class has a ‘saveJobOffer()’ method to save the offer details. This method creates a new ‘JobOffers’ object and returns the object to ‘MainMenu’. A ‘cancelJobOffer()’ method is to cancel the input details.```
      
   3. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).
      >The ‘JobOffersUtil’ class contains 3 methods to perform the above-listed functions, shown as below:
      >|Method|Function|
      >|--- | ---|
      >|addJobOffer(): void|Enter another offer (redirect to add job offer page)|
      >|areturnMainMenu(): void|Return to the main menu|
      >|compareJobOffers(jobOffer, currentJob): void|Compare the offer (if the user saved it) with the current job details (if present)|
   
4. When adjusting the comparison settings, the user can assign integer weights to:
   1. Yearly salary
   2. Yearly bonus
   3. Allowed weekly telework days
   4. Retirement benefits
   5. Leave time  
  If no weights are assigned, all factors are considered equal.
      >```The user interface will be handled by GUI implementation. The comparison settings are represented by the ‘ComparisonSettings’ class. The ‘WeightSettings’ attribute in ‘MainMenu’ class stores all the settings and will be passed to the comparison setting page when calling ‘comparisonSettings()’ function in ‘MainMenu’ class.```

5. When choosing to compare job offers, a user will:
   1. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
      >```The ‘compareJobOffer()’ function in ‘MainMenu’ class will be triggered and the currentJob and jobOfferList Object stored in ‘MainMenu’ class will be passed to the Compare Job Offer page. The page will run the ‘jobRanking()’ function to set the index of the jobs according to the weighted sum of each job calculated by the ‘weightedSum()’ function and display the list of job offers.```
   
   2. Select two jobs to compare and trigger the comparison.
      >```The ‘compare(job1, job2)’ function will compare the selected two jobs.```
   
   3. Be shown a table comparing the two jobs, displaying, for each job:
      1. Title
      2. Company
      3. Location
      4. Yearly salary adjusted for cost of living
      5. Yearly bonus adjusted for cost of living
      6. Allowed weekly telework days
      7. Retirement benefits (as percentage matched)
      8. Leave time
      
      >```The user interface will be handled by GUI implementation.```  
      
   4. Be offered to perform another comparison or go back to the main menu.
      >```The user interface will be handled by GUI implementation. ‘compare(job1, job2)’ function will be called when the user performs another comparison. ‘returnMainMenu()’ function will be called when the user click go back to the main menu button.```
   
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

   >```‘weightedSum(job)’ function will perform the above calculation.```
   
7. The user interface must be intuitive and responsive.
   >```This is not represented in my design, as it will be handled entirely within the GUI implementation.```

8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).
   >```This is not represented in my design, as no communication or saving between devices is necessary.```
   
