**Design Description**

1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).


*To realize this requirement, I first created a conceptual 'Database' class that is used to store detailed information of the current job as well as those of all the job offers. This 'Database' will have dependency relationships with multiple classes in order to feed data to them.*

*Then I established an entry point of the system, specifically, a class called 'Main Menu' with no attributes and four operations: enterCurrentJobDetails(Database), enterJobOffers(), adjustComparisonSettings(), and compareJobOffers(Database). Note that the first operation will determine whether to load a current job or start from scratch based on if there exists a current job in the 'Database'. Similarly, the fourth operation will only become available if at least two entries can be found in the 'Database', where the two entries can be either two job offers or one current job and a job offer.*


2. When choosing to enter current job details, a user will:
* a. Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
  * i. Title
  * ii. Company
  * iii. Location (entered as city and state)
  * iv. Cost of living in the location (expressed as an index)
  * v. Possibility to work remotely (expressed as the number of days a week one could work remotely, between 1 and 5)
  * vi. Yearly salary
  * vii. Yearly bonus
  * viii. Retirement benefits (as percentage matched)
  * ix. Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)
* b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.


*To realize this requirement, I created a 'Current Job' class, with the above nine detailed pieces of information listed as attributes of the class. Besides, two derived attributes 'adjustedYearlySalary' and 'adjustedYearlyBonus' are calculated by 'livingCostIndex', 'yearlySalary', and 'yearlyBonus'. Adding these two derived attributes will make the computing of job scores later on more convenient.*

*The 'Current Job' class has three methods associated with it: editValue() to enter or modify value of any of the nine attributes, saveAndReturn() to save the 'Current Job' to the database and return to the 'Main Menu', and cancelAndReturn() to ignore any changes that has not been saved and return to the 'Main Menu'.*

*The 'Database' class has a one to one composition relationship with respect to the 'Current Job' class, since we only need one database and only one job can be saved as the current job. In addition, I added a dependency relationship between 'Main Menu' and 'Current Job' to insinuate that one of the method enterCurrentJobDetails() in the former class will invoke the latter class.*


3. When choosing to enter job offers, a user will:
* a. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
* b. Be able to either save the job offer details or cancel.
* c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer with the current job details (if present).


*To realize this requirement, I created a 'Job Offer' class, with the same attributes as in the 'Current Job' class. Five methods are included in this class: editValue() to enter values, save() to save the entered values to the database, cancel() to clear all the entered values without saving, enterAnotherOffer() to create a new instance of 'Job Offer' class to start afresh, returnToMain() to return to the 'Main Menu', and compareWithCurrent(Database) to compare the values entered with the 'Current Job' if and only if there is a 'Current Job' stored in the database.*

*I also established a 'Comparison Result' class, since we might need to compare an offer with the current job here. Within 'Comparison Result', there are two attributes: 'firstJob' that is of the 'Job Offer' class, and 'secondJob' that is of the 'Current Job' class. It also has an operation called displayResult() that is used to show the comparison result between two jobs which contains the seven attributes mentioned in Requirement 5.*

*The 'Database' class has a one to many composition relationship with respect to the 'Job Offer' class, because multiple job offers may exist at the same time. An dependency relationship between 'Main Menu' and 'Job Offer' is created in correspondance to the enterJobOffers() operation in 'Main Menu'. Finally, a dependency relationships is added between 'Job Offer' and 'Comparison Result', in correspondance to the compareWithCurrent(Database) operation in the 'Job Offer' class.*


4. When adjusting the comparison settings, the user can assign integer weights to:
* a. Possibility to work remotely
* b. Yearly salary
* c. Yearly bonus
* d. Retirement benefits
* e. Leave time
If no weights are assigned, all factors are considered equal.


*To realize this requirement, I created a 'Comparison Settings' class with five attributes as shown in my design, that represent the five weights. These attributes are all of integer type and assigned initial value of 1, so that it can still be applied even if we have not tried to change the weights. An operation called assignWeight() is used to change the weight distribution if needed. I added another operation returnToMain() for the sake of self-containment, although it is not mentioned in the requirement.*

*An dependency relationship between 'Main Menu' and 'Comparison Settings' is created in correspondance to the adjustComparisonSettings() operation in 'Main Menu'.*


5. When choosing to compare job offers, a user will:
* a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
* b. Select two jobs to compare and trigger the comparison.
* c. Be shown a table comparing the two jobs, displaying, for each job:
  * i. Title
  * ii. Company
  * iii. Location
  * iv. Yearly salary adjusted for cost of living
  * v. Yearly bonus adjusted for cost of living
  * vi. Retirement benefits (as percentage matched)
  * vii. Leave time
* d. Be offered to perform another comparison or go back to the main menu.


*To realize this requirement, I created a 'Compare Job Offers' class with two attributes: 'listOfJobOffers' that is a list attribute of 'Job Offer' class, and currentJob is of 'Current Job' class that can has either zero or one instance. Five operations are defined: computerJobScore() will use the rule provided in Requrement 6 to computer all the jobs' scores, rankJobOffers() will rank all the job offers together with a possible current job from best to worst using the job score, displayJobOffers() will display the ranked jobs using Title and Company, compareJobs() will trigger an instance of 'Comparison Result' class with two selected jobs, and returnToMain() will return to the 'Main Menu'.*

*Some revisions are in order for the 'Comparison Results' class. First, the two attributes can now both be either of 'Current Job' class or 'Job Offer' class. Second, the performAnotherComparison() and returnToMain() operations are now created according to the requirement.*

*Many dependency relationships have been added, such as between 'Main Menu' and 'Compare Job Offers', between 'Comparison Settings' and 'Compare Job Offers', between 'Compare Job Offers' and 'Comparison Result', and between 'Comparison Result' and 'Main Menu'.*


6. When ranking jobs, a job’s score is computed as the weighted sum of:
AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8),
where:
AYS = yearly salary adjusted for cost of living,
AYB = yearly bonus adjusted for cost of living,
RBP = retirement benefits percentage,
LT = leave time,
RWT = remote work days per week.
The rationale for the RWT subformula is:
* a. value of an employee hour = (AYS / 260) / 8
* b. commute hours per year (assuming a 1-hour/day commute) = 1 * (260 - 52 * RWT)
* c. therefore commute-time cost = (260 - 52 * RWT) * (AYS / 260) / 8.
For example, if the weights are 2 for the yearly salary, 2 for the retirement benefits, and 1 for all other factors, the score would be computed as:
2/7 * AYS + 1/7 * AYB + 2/7 * (RBP * AYS) + 1/7 * (LT * AYS / 260) - 1/7 * ((260 - 52 *
RWT) * (AYS / 260) / 8)


*This requirement is not explicitly demonstrated in my UML design. However, the formula here will be implemented to the backend coding during the development of the app.*


7. The user interface must be intuitive and responsive.


*This requirment is not shown in my design, since all the considerations regarding interfaces are left for the next phase of the project.*


8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).


*I have incorporated this requirement into my design, and only a single system is presented.*
