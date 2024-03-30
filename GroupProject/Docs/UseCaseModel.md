
# Use Case Model
**Author**: \<Fung Yi YUEN\>

Version v2: Updated user cases scenarios.

Version v1: Created the initial User Case Model.

## 1 Use Case Diagram
[//]: # (https://lucid.app/lucidchart/invitations/accept/1f807bad-8b01-4faa-ae3f-e886d461f73a)
![UseCaseDiagram](/GroupProject/Docs/_images/UseCaseDiagram.jpeg)


## 2 Use Case Descriptions

1. Enter Current Job
    - Requirements: Allow user to enter current job details.
    - Pre-conditions: Current job does not exist in "Job" database.
    - Post-conditions: Current job details are added to "Job" database.
    - Scenarios:
        - Normal case:
	      1. User clicks "Current Job" button on "Main Menu" page to go to "Current Job" page.
	      2. User sees blank current job details on "Current Job" page.
	      3. User enters current job details on "Current Job" page.
	      4. If user clicks "Save" button on "Current Job" page, current job details will be added to "Job" database. Return to "Main Menu" page.
	      5. If user clicks "Cancel" button on "Current Job" page, nothing will be saved to "Job" database. Return to "Main Menu" Page.
        - Exceptional case:
	      If user clicks "Save" button on "Current Job" page but misses any one of the following details or enters some invalid values, error message(s) will be prompted.
	      1. Title
	      2. Company
	      3. Location
	      4. Cost of living index
	      5. Remote work time
	      6. Yearly salary
	      7. Yearly bonus
	      8. Retirement benefits
	      9. Leave time

2. Edit Current Job (an extension of "Enter Current Job")
    - Requirements: Allow user to edit current job details.
    - Pre-conditions: Current job exists in "Job" database.
    - Post-conditions: Current job details are updated in "Job" database.
    - Scenarios:
        - Normal case:
	      1. User clicks "Current Job" button on "Main Menu" page to go to "Current Job" page.
	      2. User sees previously saved current job details retrived from "Job" database on "Current Job" page.
	      3. User modifies current job details on "Current Job" page.
	      4. If user clicks "Save" button on "Current Job" page, current job details will be updated in "Job" database. Return to "Main Menu" page.
	      5. If user clicks "Cancel" button on "Current Job" page, "Job" database will not be updated. Return to "Main Menu" page.
        - Exceptional case:
	      - If user clicks "Save" button on "Current Job" page but leaves any one of current job details blank or enters some invalid values, error message(s) will be prompted.

3. Add Job Offer
    -  Requirements: Allow user to add job offer details.
     - Pre-conditions: None
     - Post-conditions: Job offer details are added to "Job" database.
   - Scenarios:
      - Normal case:
	      1. User clicks "Job Offer" button on "Main Menu" page to go to "Job Offer" page.
	      2. User sees blank job offer details on "Job Offer" page.
	      3. User enters job offer details on "Job Offer" page.
	      4. If user clicks "Save" button on "Job Offer" page, job offer details will be added to "Job" database. Redirect to "Job Offer Utility" page.
	          - If user clicks "Add Another Offer" button on "Job Offer Utility" page, redirect to "Job Offer" page with a blank form for user to enter another job offer.
		      - If user clicks "Return To Main Menu" button on "Job Offer Utility" page, return to "Main Menu" page.
		      - If user clicks "Compare With Current Job" button on "Job Offer Utility" page, redirect to "Comparison Result" page which shows a table comparing the most recently entered job offer and the current job.
		        1. If user clicks "Perform Another Comparison" button on "Comparison Result" page, redirect to "Compare Jobs" page.
		          2. If user clicks "Return To Main Menu" button on "Comparison Result" page, return to "Main Menu" page.
	      5. If user clicks "Cancel" button on "Job Offer" page, return to "Main Menu" page.
	  - Exceptional case: 
		 - If job offer form contains missing field(s) or invalid values, error message(s) will be prompted when user clicks "save". Nothing will be added to "Job" database.
		 - If current job does not exist in "Job" database, "Compare With Current Job" button on "Job Offer Utility" page will be disabled.

4. Adjust Comparison Settings
    - Requirements: Allow user to assign weights to following categories:
	    1. Yearly salary
	    2. Yearly bonus
	    3. Retirement benefits
	    4. Leave time
	    5. Remote work time
     - Pre-conditions: default weights are all 1 in "Weight" database.
     - Post-conditions: updated weights are updated in "Weight" database.
   - Scenarios:
	  - Normal case: 
	     1. User clicks "Comparison Settings" button on "Main Menu" page to go to "Comparison Settings" page.
	     2. User adjusts the weights of each category according to user's preference.
	     3. If user clicks "Save" button on "Comparison Settings" page, new weights will be updated in "Weight" database, and a toast message will appear to confirm this.
	     4. If user clicks "Cancel" button on "Comparison Settings" page, "Weight" database will not be updated. Return to "Main Menu" page.
	  - Exceptional case: 
	    - If user does not enter value or enters zero in any of the fields, corresponding error message(s) will be prompted.

5. Compare Job Offers
    - Requirements: Allow user to compare 2 jobs' details, possibly including current job (if present).
     - Pre-conditions: 
	     - System contains at least 2 jobs (incluing current job) in "Job" database.
	     - System lists all the jobs from "Job" database and ranks them from best to worst.
     - Post-conditions: System shows a table comparing the 2 jobs selected.
   - Scenarios:
	   - Normal case: 
	     1. User clicks "Compare Jobs" button on "Main Menu" page to go to "Compare Jobs" page.
	     2. User selects 2 jobs from the ranked list.
	     3. User clicks "Compare" button on "Compare Jobs" page to see the result on "Comparison Result" page.
	     	- If user clicks "Perform Another Comparison" button on "Comparison Result" page, redirect to "Compare Jobs" page where user can select and compare 2 jobs again.
	     	- If user clicks "Return To Main Menu" button on "Comparison Result" page, return to "Main Menu" page.
	     4. User clicks "Cancel" button on "Compare Jobs" page to return to "Main Menu" page.
	   - Exceptional case: 
		   - If user does not select exactly 2 jobs from the ranked list, "Compare" button on "Compare Jobs" page will be disabled.		  

6. Rank Job Offers
    -  Requirements: Allow user to see a list of all jobs (including current job) ranking from best to worst.
     - Pre-conditions: At least 2 jobs (possibly including current job) exist in "Job" database.
     - Post-conditions: Display a ranked list of jobs.
   - Scenarios:
	   - Normal Case:
		   1. User clicks "Compare Jobs" button on "Main Menu" page to go to "Compare Jobs" page.
		   2. App calculates scores for all jobs stored in "Job" database using the provided formula and weights retrieved from "Weight" database, and ranks them from the highest to the lowest.
		   3. App displays the ranked list on "Compare Jobs" page.
		- Exceptional case: 
			- If less than 2 jobs exists in the "Job" database, "Compare Jobs" button on "Main Menu" page will be disabled.
