# User Manual
JobCompare is an Android app that compares user's inputted job offers with different benefits. This manual serves as a user guide to all the functions of this app.

## Main Menu

<img src=/GroupProject/Docs/_images/Home.PNG width="300"/><img src=/GroupProject/Docs/_images/MainMenuBlur.PNG width="300"/>

This is the starting page when entering the app. 4 functions are listed in this page: 
1. Current Job
2. Job Offer
3. Comparison Settings
4. Compare Jobs (This part is not available until there are at least two jobs to compare.)

## Function 1: Current Job

<img src=/GroupProject/Docs/_images/UM_CurrentJobEmpty.PNG width="300"/><img src=/GroupProject/Docs/_images/CurrentJobEmptyErrors.PNG width="300"/>

This page allows user inputs his/her current job details. All input fields are mandatory. Click "SAVE" button to save all the details onto the database. Click "CANCEL" button to discard changes and return to Main Menu.

## Function 2: Job Offer

Below is an example of the Job offer screen and some possible errors.

<img src=/GroupProject/Docs/_images/UM_JobOfferEmpty.PNG width="300"/><img src=/GroupProject/Docs/_images/JobOfferEmptyError.PNG width="300"/><img src=/GroupProject/Docs/_images/JobOfferUtilityFilled.PNG width="300"/>

This page allows user input his/her job offer details. All input fields are mandatory. Click "SAVE" button to save all the details onto the database. Click "CANCEL" button to discard changes and return to Main Menu.

Once a job offer has saved, a page showing all the input details with 3 buttons at the bottom will be displayed.
   - Button 1: ADD ANOTHER OFFER
	   - This function re-directs to a blank Job Offer page for user to input another job offer detail.
   - Button 2: RETURN TO MAIN MENU
	   - This function returns to Main Menu page
   - Button 3: COMPARE WITH CURRENT JOB
	   - This function compares user's inputted job offer with user's current job. A comparison result page will be displayed.


## Function 3: Comparison Settings.

Below is an example of the comparison screen and some possible errors.

<img src=/GroupProject/Docs/_images/UM_ComparisonResults.PNG width="300"/><img src=/GroupProject/Docs/_images/ComparisonSettingsErrorPositive.PNG width="300"/><img src=/GroupProject/Docs/_images/ComparisonSettingsErrorEmpty.PNG width="300"/>

This page allows user to adjust the weight of 5 categories, 
1. Yearly salary
2.  Yearly bonus
3.  Allowed weekly telework days
4.  Retirement benefits
5.  Leave time

Default initial weight are all 1. User can assign integer weights to any of the categories and click "SAVE" to save change(s), or "CANCEL" to discard change(s).

## Function 4: Compare Jobs

<img src=/GroupProject/Docs/_images/CompareRankBlur.PNG width="300"/><img src=/GroupProject/Docs/_images/CompareRankComplete.PNG width="300"/><img src=/GroupProject/Docs/_images/ComparisonPage.PNG width="300"/>

This page shows a ranking list for all the jobs (including current job) entered by the user. Jobs are ranked from the highest score to the lowest. The score is calculated based on a weighted sum formula (according to the weights set by the user in comparison settings).

The option to compare is not available until the jobs are selected

User can select 2 jobs among the list of offers and click "COMPARE" to see the comparison result, or "CANCEL" to return to the Main Menu.

Note that current job contains a "`*`" symbol in front of the job title.

This comparison result page shows details of the 2 selected job offers. User can click "PERFORM ANOTHER COMPARISON" to select another pairs of job offer and proceed comparison or "RETURN TO MAIN MENU" to return to Main Menu.
