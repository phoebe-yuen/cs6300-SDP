Requirements
============

1.  When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).  

**Req:** To realize this requirement I included 4 functions on this page. One for each page that the user may direct themselves to after the landing page.
-   currentJob() will check if the current user has a boolean value set for the isCurrentJob boolean value and will direct the user to the enter or edit job details based on whether they had previously set this value. (by the saveJob() function in the CurrentJobForm component)
-   jobOffer() will direct the user to the JobOfferForm component to enter job details. 
-   settings() will direct the user to the ComparisonSettings component.
-   compare() will direct the user to the ComparisonSelection component. This functionality will be disabled if there haven't been enough offers entered to compare. (>1)

2.  When choosing to enter current job details, a user will:
	a.  Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
		i.  Title
		ii.  Company
		iii.  Location (entered as city and state)
		iv.  Cost of living in the location (expressed as an [index](https://www.expatistan.com/cost-of-living/index/north-america))
		v.  Yearly salary
		vi.  Yearly bonus
		vii.  Allowed weekly telework days (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5)
		viii.  Retirement benefits (as percentage matched)
		ix.  Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)
	b.  Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

**Req:** This requirement was filled with the CurrentJobForm class. This page will either display fields to be entered for a user who does not have a current job entered or display the data for the users current job and allow edit of details. I have this requirement in mind to be filled with the following functions. saveJob(), enterOrEdit(), and cancelMain().
-   SaveJob() will save the data entered as well as set the custom hidden value of isCurrentJob in the jobDetails table to be true. 
-   enterOrEdit() will allow a user to enter new information into the fields for their job details. 
-   cancelMain() will cancel all operations and save no values. Then redirect back to the main page.

3.  When choosing to enter job offers, a user will:
	a.  Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
	b.  Be able to either save the job offer details or cancel.
	c.  Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

**Req:** This requirement is filled with the JobOfferForm class. This page will display fields for a user to enter job details about the offer they are entering. Additionally on the page there will be ui components that will trigger various functions described below.
-   saveOffer() will save the data entered.
-   addOffer() will save the data entered and refresh the form with blank fields to allow another entry.
-   cancelMain() will not save data and redirect back to main
-   compareOffer will redirect to the ComparisonPage class with the recently entered job offer and current job details. This function will check to see if there has been no job entered with the isCurrentJob value set to true and will alert the user to enter a current job in order to compare.

4.  When adjusting the comparison settings, the user can assign integer weights to:
	a.  Yearly salary
	b.  Yearly bonus
	c.  Allowed weekly telework days
	d.  Retirement benefits
	e.  Leave time
If no weights are assigned, all factors are considered equal.

**Req:** This requirement is filled with the ComparisonSettings class. This page will display various attributes in the weights package. Default initial value for these attributes in weights are 1 to keep all things considered equal if the user does not modify them.

5.  When choosing to compare job offers, a user will:
	a.  Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
	b.  Select two jobs to compare and trigger the comparison.
	c.  Be shown a table comparing the two jobs, displaying, for each job:
		i.  Title
		ii.  Company
		iii.  Location
		iv.  Yearly salary adjusted for cost of living
		v.  Yearly bonus adjusted for cost of living
		vi.  Allowed weekly telework days
		vii.  Retirement benefits (as percentage matched)
		viii.  Leave time
	d.  Be offered to perform another comparison or go back to the main menu.

**Req:** This requirement is fulfilled by the ComparisonSelection class as well as the ComparisonPage class.
-   ComparisonSelection class will use both the Job Details and Weights package. Also it will use the following functions. 
	-   Rank() will use the algorithm in step 6 below to rank the job offers. 
	-   compareSelection() will take the two jobs selected and redirect them to the comparison page to view the job details of each. 
-   ComparisonPage class will use the jobDetails page to display the details of the jobs that had been selected to compare. This page may be reached through the compareOffer() function (JobOfferForm) or the compareSelection() function (ComparisonSelection) .
	-   compareAnother() will return the user to the ComparisonSelection page to perform another selection. 
	-   cancelMain() will return to the main menu

6.  When ranking jobs, a job's score is computed as the weighted sum of:

    AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)

    where:\
    AYS = yearly salary adjusted for cost of living\
    AYB = yearly bonus adjusted for cost of living\
    RBP = retirement benefits percentage\
    LT = leave time\
    RWT = telework days per week\
    The rationale for the RWT subformula is:

	1.  value of an employee hour = (AYS / 260) / 8
	2.  commute hours per year (assuming a 1-hour/day commute) = 1 * (260 - 52 * RWT)
	3.  therefore travel-time cost =  (260 - 52 * RWT) * (AYS / 260) / 8

For example, if the weights are 2 for the yearly salary, 2 for the retirement benefits, and 1 for all other factors, the score would be computed as:

2/7 * AYS + 1/7 * AYB + 2/7 * (RBP * AYS) + 1/7 * (LT * AYS / 260) - 1/7 * ((260 - 52 * RWT) * (AYS / 260) / 8)

**Req:** This requirement is fulfilled by the rank() function in the ComparisonSelection class. This uses the Weights and JobDetails packages.

7.  The user interface must be intuitive and responsive.

**Req:** This requiremnt is not displayed on the UML document as it is purely a UI requiremnt. 

8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).