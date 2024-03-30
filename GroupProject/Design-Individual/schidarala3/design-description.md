#Design Description Rationale

1.	When the app is started, the user is presented with the main menu, which allows the user to 
(1) enter or edit current job details, 
(2) enter job offers, 
(3) adjust the comparison settings, or 
(4) compare job offers (disabled if no job offers were entered yet ).  

**Even though these are presented as operations, they should be presented as classes because there are corresponding attributes and operations to each of these capabilities. These are illustrated in the model through the different classes. They all stem from the main menu class which was given a userID.**

2.	When choosing to enter current job details, a user will:
a.	Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
i.	Title
ii.	Company
iii.	Location (entered as city and state)
iv.	Cost of living in the location (expressed as an index)
v.	Yearly salary
vi.	Yearly bonus
vii.	Allowed weekly telework days (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5)
viii.	Retirement benefits (as percentage matched)
ix.	Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)
b.	Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

**During this step the following attributes were added to the enterEditCurrentJobDetails class as the respective attributes were assigned a relationship and data type pertaining to their appropriate function. I have also added two operations saveJobDetails() and cancelAndExitWithoutSaving() to reflect given criteria.**

3.	When choosing to enter job offers, a user will:
a.	Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
b.	Be able to either save the job offer details or cancel.
c.	Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

**For the enterJobOffers class, I have added the same attributes from the earlier class to this class. In addition, I have added operations saveJobDetails(), cancelAndExitWithoutSaving(), enterAnotherOffer(), returnToMainMenu() and compareOffer() to reflect given criteria.**




4.	When adjusting the comparison settings, the user can assign integer weights to:
a.	Yearly salary
b.	Yearly bonus
c.	Allowed weekly telework days
d.	Retirement benefits
e.	Leave time
If no weights are assigned, all factors are considered equal.

**I have accounted for this step through class creation of AdjustComparisonSettings class. I have also added these respective attributes with pertaining data types (integer). I added a returnToMainMenu() operation here as it would suck to get stuck on this screen without another option.**

5.	When choosing to compare job offers, a user will:
a.	Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
b.	Select two jobs to compare and trigger the comparison.
c.	Be shown a table comparing the two jobs, displaying, for each job:
i.	Title
ii.	Company
iii.	Location
iv.	Yearly salary adjusted for cost of living
v.	Yearly bonus adjusted for cost of living
vi.	Allowed weekly telework days
vii.	Retirement benefits (as percentage matched)
viii.	Leave time
d.	Be offered to perform another comparison or go back to the main menu.

**I have accounted for this step through creation of CompareJobOffers and by adding respective attributes and datatypes. Ranking would need to be done based off best salary adjusted for cost of living. As a result, I have added these respective field attributes to reflect this.**


6.	When ranking jobs, a job’s score is computed as the weighted sum of:

AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)

where:
AYS = yearly salary adjusted for cost of living
AYB = yearly bonus adjusted for cost of living
RBP = retirement benefits percentage
LT = leave time
RWT = telework days per week
The rationale for the RWT subformula is:
a.	value of an employee hour = (AYS / 260) / 8
b.	commute hours per year (assuming a 1-hour/day commute) =
1 * (260 - 52 * RWT)
c.	therefore travel-time cost = (260 - 52 * RWT) * (AYS / 260) / 8

For example, if the weights are 2 for the yearly salary, 2 for the retirement benefits, and 1 for all other factors, the score would be computed as:

2/7 * AYS + 1/7 * AYB + 2/7 * (RBP * AYS) + 1/7 * (LT * AYS / 260) - 1/7 * ((260 - 52 * RWT) * (AYS / 260) / 8)

**I have taken the calculated weighted sum of a job score into account by adding a jobScore derived attribute to the CompareJobOffers class. More initiative will be taken in the next steps of the implementation process to account for the formula calculation**

7.	The user interface must be intuitive and responsive.

**I have taken this step into account by keeping the class diagram as simple as possible as I have created only four classes while reflecting as many of the requirements possible.**

8.	For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

**I have taken this step into account by imagining myself navigate through the pages of the virtual application. To not get stuck on any given window, I have added a returnToMainMenu() operation on each of the class pages. More initiative will be taken with this step during the GUI implementation.**

**In terms of relationships, the class operations can be performed in any order, as a result the UML class diagram was structured in more of a map rather than a tree structure. In addition, the relationship type was a simple association instead of a unary or n-ary relationship because the pages are simply related to each other. These are not objects to compare and correlate amounts to. This was reflected by drawing relationships from each of the functionalities back to the main menu. In addition, a relationship was added between EnterJobOffers and CompareJobOffers to reflect the respective operations and function of this page of the application. **
