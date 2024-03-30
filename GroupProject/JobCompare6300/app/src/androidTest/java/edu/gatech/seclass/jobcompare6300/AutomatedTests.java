package edu.gatech.seclass.jobcompare6300;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

/* This is the automation test file for the JobCompare6300 App */
@RunWith(AndroidJUnit4.class)
public class AutomatedTests {

    String placeholderCurrentTitle="Temp Title", placeholderCurrentCompany="Temp Company", placeholderCurrentLocation="Temp Location", placeholderCurrentCostOfLivingIndex = "100", placeholderCurrentYearlySalary = "0.0",
           placeholderCurrentYearlyBonus = "0.0", placeholderCurrentRetirementBenefitsPercentage = "0.0", placeholderCurrentLeaveTime = "0", placeholderCurrentRemoteWorkTime = "1";
    String currentTitle="Current Title", currentCompany="Current Company", currentLocation="Toronto, ON", currentCostOfLivingIndex="177", currentYearlySalary="80000.0", currentYearlyBonus="40000.0", currentRetirementBenefitsPercentage="0.5",
           currentLeaveTime="60", currentRemoteWorkTime="5", currentAdjustedYearlySalary="45197.74", currentAdjustedYearlyBonus="22598.87", currentJobScoreModifiedWeight="13327.54";
    String offerTitle1="Offer Title 1", offerCompany1="Offer Company 1", offerLocation1="San Francisco, CA", offerCostOfLivingIndex1="232", offerYearlySalary1="100000.0", offerYearlyBonus1="20000.0", offerRetirementBenefitsPercentage1="0.2",
           offerLeaveTime1="30", offerRemoteWorkTime1="3", offerAdjustedYearlySalary1="43103.45", offerAdjustedYearlyBonus1="8620.69", offerJobScoreModifiedWeight1="6355.00";
    String offerTitle2="Offer Title 2", offerCompany2="Offer Company 2", offerLocation2="New York City, NY", offerCostOfLivingIndex2="235", offerYearlySalary2="120000.0", offerYearlyBonus2="30000.0", offerRetirementBenefitsPercentage2="0.35",
           offerLeaveTime2="45", offerRemoteWorkTime2="2", offerAdjustedYearlySalary2="51063.83", offerAdjustedYearlyBonus2="12765.96", offerJobScoreModifiedWeight2="9761.05";
    String offerTitle3="Offer Title 3", offerCompany3="Offer Company 3", offerLocation3="Washington D.C.", offerCostOfLivingIndex3="206", offerYearlySalary3="130000.0", offerYearlyBonus3="10000.0", offerRetirementBenefitsPercentage3="0.15",
           offerLeaveTime3="38", offerRemoteWorkTime3="3", offerAdjustedYearlySalary3="63106.80", offerAdjustedYearlyBonus3="4854.37", offerJobScoreModifiedWeight3="8155.34";
    String offerTitle4="Offer Title 4", offerCompany4="Offer Company 4", offerLocation4="Philadelphia, PA", offerCostOfLivingIndex4="162", offerYearlySalary4="90000.0", offerYearlyBonus4="34000.0", offerRetirementBenefitsPercentage4="0.45",
           offerLeaveTime4="22", offerRemoteWorkTime4="4", offerAdjustedYearlySalary4="55555.56", offerAdjustedYearlyBonus4="20987.65", offerJobScoreModifiedWeight4="12292.66";
    String offerTitle5="Offer Title 5", offerCompany5="Offer Company 5", offerLocation5="Baltimore, MD", offerCostOfLivingIndex5="157", offerYearlySalary5="110000.0", offerYearlyBonus5="15000.0", offerRetirementBenefitsPercentage5="0.22",
           offerLeaveTime5="10", offerRemoteWorkTime5="1", offerAdjustedYearlySalary5="70063.69", offerAdjustedYearlyBonus5="9554.14", offerJobScoreModifiedWeight5="7410.75";
    String AYSWeightModified="1", AYBWeightModified="2", RBPWeightModified="3", LTWeightModified="4", RWTWeightModified="5";

    @Rule
    public ActivityTestRule<MainMenu> mActivityRule = new ActivityTestRule<>(MainMenu.class);

    // Check whether the "Main Menu" UI is displayed when the app starts.
    @Test
    public void test01() {
        Assert.assertEquals(mActivityRule.getActivity().getClass(), MainMenu.class);
    }

    // Check whether the four buttons on the "Main Menu" UI are displayed when the app starts.
    @Test
    public void test02() {
        onView(withId(R.id.MainMenuCurrentJobButton)).check(matches(isDisplayed()));
        onView(withId(R.id.MainMenuJobOfferButton)).check(matches(isDisplayed()));
        onView(withId(R.id.MainMenuComparisonSettingsButton)).check(matches(isDisplayed()));
        onView(withId(R.id.MainMenuCompareJobsButton)).check(matches(isDisplayed()));
    }

    // Check whether the "Compare Jobs" button on the "Main Menu" UI is disabled when there is no jobs.
    @Test
    public void test03() {
        onView(withId(R.id.MainMenuCompareJobsButton)).check(matches(not(isEnabled())));
    }

    // Check whether the "Job Offer" UI is launched after clicking the "Job Offer" button on the "Main Menu" UI.
    @Test
    public void test04() {
        Intents.init();
        onView(withId(R.id.MainMenuJobOfferButton)).perform(click());
        intended(hasComponent(JobOffer.class.getName()));
        Intents.release();
    }

    // Check whether the text editing fields on the "Job Offer" UI are blank upon launching.
    @Test
    public void test05() {
        onView(withId(R.id.MainMenuJobOfferButton)).perform(click());
        onView(withId(R.id.JobOfferTitle)).check(matches(withText("")));
        onView(withId(R.id.JobOfferCompany)).check(matches(withText("")));
        onView(withId(R.id.JobOfferLocation)).check(matches(withText("")));
        onView(withId(R.id.JobOfferCostOfLivingIndex)).check(matches(withText("")));
        onView(withId(R.id.JobOfferRemoteWorkTime)).check(matches(withText("")));
        onView(withId(R.id.JobOfferYearlySalary)).check(matches(withText("")));
        onView(withId(R.id.JobOfferYearlyBonus)).check(matches(withText("")));
        onView(withId(R.id.JobOfferRetirementBenefitsPercentage)).check(matches(withText("")));
        onView(withId(R.id.JobOfferLeaveTime)).check(matches(withText("")));
    }

    // Check whether the error texts will appear when trying to save with blank text editing fields on the "Job Offer" UI.
    @Test
    public void test06() {
        onView(withId(R.id.MainMenuJobOfferButton)).perform(click());
        onView(withId(R.id.JobOfferSaveButton)).perform(click());
        onView(withId(R.id.JobOfferTitle)).check(matches(hasErrorText("The title field is empty!")));
        onView(withId(R.id.JobOfferCompany)).check(matches(hasErrorText("The company field is empty!")));
        onView(withId(R.id.JobOfferLocation)).check(matches(hasErrorText("The location field is empty!")));
        onView(withId(R.id.JobOfferCostOfLivingIndex)).check(matches(hasErrorText("The cost of living index field is empty!")));
        onView(withId(R.id.JobOfferRemoteWorkTime)).check(matches(hasErrorText("The remote work time field is empty!")));
        onView(withId(R.id.JobOfferYearlySalary)).check(matches(hasErrorText("The yearly salary field is empty!")));
        onView(withId(R.id.JobOfferYearlyBonus)).check(matches(hasErrorText("The yearly bonus field is empty!")));
        onView(withId(R.id.JobOfferRetirementBenefitsPercentage)).check(matches(hasErrorText("The retirement benefits field is empty!")));
        onView(withId(R.id.JobOfferLeaveTime)).check(matches(hasErrorText("The leave time field is empty!")));
    }

    // Check whether the error texts will appear when trying to save with some invalid text editing fields on the "Job Offer" UI.
    @Test
    public void test07() {
        onView(withId(R.id.MainMenuJobOfferButton)).perform(click());
        onView(withId(R.id.JobOfferCostOfLivingIndex)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.JobOfferRemoteWorkTime)).perform(clearText(), replaceText("6"));
        onView(withId(R.id.JobOfferRetirementBenefitsPercentage)).perform(clearText(), replaceText("2.0"));
        onView(withId(R.id.JobOfferLeaveTime)).perform(clearText(), replaceText("366"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.JobOfferSaveButton)).perform(click());
        onView(withId(R.id.JobOfferCostOfLivingIndex)).check(matches(hasErrorText("The cost of living index must be a positive integer!")));
        onView(withId(R.id.JobOfferRemoteWorkTime)).check(matches(hasErrorText("Remote work time must be an integer from 1 to 5!")));
        onView(withId(R.id.JobOfferRetirementBenefitsPercentage)).check(matches(hasErrorText("The retirement benefits should be a decimal numeral between 0 and 1!")));
        onView(withId(R.id.JobOfferLeaveTime)).check(matches(hasErrorText("The leave time must be an integer less or equal to 365!")));
    }

    // Check whether the "Job Offer Utility" UI is launched after clicking the "Save" button on the "Job Offer" UI with valid values from job offer 1 in text editing fields.
    @Test
    public void test08() {
        onView(withId(R.id.MainMenuJobOfferButton)).perform(click());
        onView(withId(R.id.JobOfferTitle)).perform(clearText(), replaceText(offerTitle1));
        onView(withId(R.id.JobOfferCompany)).perform(clearText(), replaceText(offerCompany1));
        onView(withId(R.id.JobOfferLocation)).perform(clearText(), replaceText(offerLocation1));
        onView(withId(R.id.JobOfferCostOfLivingIndex)).perform(clearText(), replaceText(offerCostOfLivingIndex1));
        onView(withId(R.id.JobOfferRemoteWorkTime)).perform(clearText(), replaceText(offerRemoteWorkTime1));
        onView(withId(R.id.JobOfferYearlySalary)).perform(clearText(), replaceText(offerYearlySalary1));
        onView(withId(R.id.JobOfferYearlyBonus)).perform(clearText(), replaceText(offerYearlyBonus1));
        onView(withId(R.id.JobOfferRetirementBenefitsPercentage)).perform(clearText(), replaceText(offerRetirementBenefitsPercentage1));
        onView(withId(R.id.JobOfferLeaveTime)).perform(clearText(), replaceText(offerLeaveTime1));
        Espresso.closeSoftKeyboard();
        Intents.init();
        onView(withId(R.id.JobOfferSaveButton)).perform(click());
        intended(hasComponent(JobOfferUtil.class.getName()));
        Intents.release();
    }

    // Check whether the "Main Menu" UI is launched after clicking the "Cancel" button on the "Job Offer" UI.
    @Test
    public void test09() {
        onView(withId(R.id.MainMenuJobOfferButton)).perform(click());
        Intents.init();
        onView(withId(R.id.JobOfferCancelButton)).perform(click());
        intended(hasComponent(MainMenu.class.getName()));
        Intents.release();
    }

    // Check whether the "Compare Jobs" button on the "Main Menu" UI is disabled when there is one job.
    @Test
    public void test10() {
        onView(withId(R.id.MainMenuCompareJobsButton)).check(matches(not(isEnabled())));
    }

    // Check whether the "Compare With Current Job" button on the "Job Offer Utility" UI is disabled when there is no current job, adding job offer 2 in the process.
    @Test
    public void test11() {
        onView(withId(R.id.MainMenuJobOfferButton)).perform(click());
        onView(withId(R.id.JobOfferTitle)).perform(clearText(), replaceText(offerTitle2));
        onView(withId(R.id.JobOfferCompany)).perform(clearText(), replaceText(offerCompany2));
        onView(withId(R.id.JobOfferLocation)).perform(clearText(), replaceText(offerLocation2));
        onView(withId(R.id.JobOfferCostOfLivingIndex)).perform(clearText(), replaceText(offerCostOfLivingIndex2));
        onView(withId(R.id.JobOfferRemoteWorkTime)).perform(clearText(), replaceText(offerRemoteWorkTime2));
        onView(withId(R.id.JobOfferYearlySalary)).perform(clearText(), replaceText(offerYearlySalary2));
        onView(withId(R.id.JobOfferYearlyBonus)).perform(clearText(), replaceText(offerYearlyBonus2));
        onView(withId(R.id.JobOfferRetirementBenefitsPercentage)).perform(clearText(), replaceText(offerRetirementBenefitsPercentage2));
        onView(withId(R.id.JobOfferLeaveTime)).perform(clearText(), replaceText(offerLeaveTime2));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.JobOfferSaveButton)).perform(click());
        onView(withId(R.id.JobOfferUtilCompareWithCurrentButton)).check(matches(not(isEnabled())));
    }

    // Check whether the "Compare Jobs" button on the "Main Menu" UI is enabled when there are two jobs.
    @Test
    public void test12() {
        onView(withId(R.id.MainMenuCompareJobsButton)).check(matches(isEnabled()));
    }

    // Check whether the "Job Offer" UI is launched after clicking the "Add Another Offer" button on the "Job Offer Utility" UI, adding job offer 3 in the process.
    @Test
    public void test13() {
        onView(withId(R.id.MainMenuJobOfferButton)).perform(click());
        onView(withId(R.id.JobOfferTitle)).perform(clearText(), replaceText(offerTitle3));
        onView(withId(R.id.JobOfferCompany)).perform(clearText(), replaceText(offerCompany3));
        onView(withId(R.id.JobOfferLocation)).perform(clearText(), replaceText(offerLocation3));
        onView(withId(R.id.JobOfferCostOfLivingIndex)).perform(clearText(), replaceText(offerCostOfLivingIndex3));
        onView(withId(R.id.JobOfferRemoteWorkTime)).perform(clearText(), replaceText(offerRemoteWorkTime3));
        onView(withId(R.id.JobOfferYearlySalary)).perform(clearText(), replaceText(offerYearlySalary3));
        onView(withId(R.id.JobOfferYearlyBonus)).perform(clearText(), replaceText(offerYearlyBonus3));
        onView(withId(R.id.JobOfferRetirementBenefitsPercentage)).perform(clearText(), replaceText(offerRetirementBenefitsPercentage3));
        onView(withId(R.id.JobOfferLeaveTime)).perform(clearText(), replaceText(offerLeaveTime3));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.JobOfferSaveButton)).perform(click());
        Intents.init();
        onView(withId(R.id.JobOfferUtilAddJobOfferButton)).perform(click());
        intended(hasComponent(JobOffer.class.getName()));
        Intents.release();
    }

    // Check whether the "Main Menu" UI is launched after clicking the "Return To Main Menu" button on the "Job Offer Utility" UI, adding job offer 4 in the process.
    @Test
    public void test14() {
        onView(withId(R.id.MainMenuJobOfferButton)).perform(click());
        onView(withId(R.id.JobOfferTitle)).perform(clearText(), replaceText(offerTitle4));
        onView(withId(R.id.JobOfferCompany)).perform(clearText(), replaceText(offerCompany4));
        onView(withId(R.id.JobOfferLocation)).perform(clearText(), replaceText(offerLocation4));
        onView(withId(R.id.JobOfferCostOfLivingIndex)).perform(clearText(), replaceText(offerCostOfLivingIndex4));
        onView(withId(R.id.JobOfferRemoteWorkTime)).perform(clearText(), replaceText(offerRemoteWorkTime4));
        onView(withId(R.id.JobOfferYearlySalary)).perform(clearText(), replaceText(offerYearlySalary4));
        onView(withId(R.id.JobOfferYearlyBonus)).perform(clearText(), replaceText(offerYearlyBonus4));
        onView(withId(R.id.JobOfferRetirementBenefitsPercentage)).perform(clearText(), replaceText(offerRetirementBenefitsPercentage4));
        onView(withId(R.id.JobOfferLeaveTime)).perform(clearText(), replaceText(offerLeaveTime4));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.JobOfferSaveButton)).perform(click());
        Intents.init();
        onView(withId(R.id.JobOfferUtilReturnMainMenuButton)).perform(click());
        intended(hasComponent(MainMenu.class.getName()));
        Intents.release();
    }

    // Check whether the "Current Job" UI is launched after clicking the "Current Job" button on the "Main Menu" UI.
    @Test
    public void test15() {
        Intents.init();
        onView(withId(R.id.MainMenuCurrentJobButton)).perform(click());
        intended(hasComponent(CurrentJob.class.getName()));
        Intents.release();
    }

    // Check whether the text editing fields on the "Current Job" UI are blank when there is no current job.
    @Test
    public void test16() {
        onView(withId(R.id.MainMenuCurrentJobButton)).perform(click());
        onView(withId(R.id.CurrentJobTitle)).check(matches(withText("")));
        onView(withId(R.id.CurrentJobCompany)).check(matches(withText("")));
        onView(withId(R.id.CurrentJobLocation)).check(matches(withText("")));
        onView(withId(R.id.CurrentJobCostOfLivingIndex)).check(matches(withText("")));
        onView(withId(R.id.CurrentJobRemoteWorkTime)).check(matches(withText("")));
        onView(withId(R.id.CurrentJobYearlySalary)).check(matches(withText("")));
        onView(withId(R.id.CurrentJobYearlyBonus)).check(matches(withText("")));
        onView(withId(R.id.CurrentJobRetirementBenefitsPercentage)).check(matches(withText("")));
        onView(withId(R.id.CurrentJobLeaveTime)).check(matches(withText("")));
    }

    // Check whether the error texts will appear when trying to save with blank text editing fields on the "Current Job" UI.
    @Test
    public void test17() {
        onView(withId(R.id.MainMenuCurrentJobButton)).perform(click());
        onView(withId(R.id.CurrentJobSaveButton)).perform(click());
        onView(withId(R.id.CurrentJobTitle)).check(matches(hasErrorText("The title field is empty!")));
        onView(withId(R.id.CurrentJobCompany)).check(matches(hasErrorText("The company field is empty!")));
        onView(withId(R.id.CurrentJobLocation)).check(matches(hasErrorText("The location field is empty!")));
        onView(withId(R.id.CurrentJobCostOfLivingIndex)).check(matches(hasErrorText("The cost of living index field is empty!")));
        onView(withId(R.id.CurrentJobRemoteWorkTime)).check(matches(hasErrorText("The remote work time field is empty!")));
        onView(withId(R.id.CurrentJobYearlySalary)).check(matches(hasErrorText("The yearly salary field is empty!")));
        onView(withId(R.id.CurrentJobYearlyBonus)).check(matches(hasErrorText("The yearly bonus field is empty!")));
        onView(withId(R.id.CurrentJobRetirementBenefitsPercentage)).check(matches(hasErrorText("The retirement benefits field is empty!")));
        onView(withId(R.id.CurrentJobLeaveTime)).check(matches(hasErrorText("The leave time field is empty!")));
    }

    // Check whether the error texts will appear when trying to save with some invalid text editing fields on the "Current Job" UI.
    @Test
    public void test18() {
        onView(withId(R.id.MainMenuCurrentJobButton)).perform(click());
        onView(withId(R.id.CurrentJobCostOfLivingIndex)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.CurrentJobRemoteWorkTime)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.CurrentJobRetirementBenefitsPercentage)).perform(clearText(), replaceText("1.1"));
        onView(withId(R.id.CurrentJobLeaveTime)).perform(clearText(), replaceText("400"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.CurrentJobSaveButton)).perform(click());
        onView(withId(R.id.CurrentJobCostOfLivingIndex)).check(matches(hasErrorText("The cost of living index must be a positive integer!")));
        onView(withId(R.id.CurrentJobRemoteWorkTime)).check(matches(hasErrorText("Remote work time must be an integer from 1 to 5!")));
        onView(withId(R.id.CurrentJobRetirementBenefitsPercentage)).check(matches(hasErrorText("The retirement benefits should be a decimal numeral between 0 and 1!")));
        onView(withId(R.id.CurrentJobLeaveTime)).check(matches(hasErrorText("The leave time must be an integer less or equal to 365!")));
    }

    // Check whether the "Main Menu" UI is launched after clicking the "Save" button on the "Current Job" UI with valid values from placeholder current job in text editing fields.
    @Test
    public void test19() {
        onView(withId(R.id.MainMenuCurrentJobButton)).perform(click());
        onView(withId(R.id.CurrentJobTitle)).perform(clearText(), replaceText(placeholderCurrentTitle));
        onView(withId(R.id.CurrentJobCompany)).perform(clearText(), replaceText(placeholderCurrentCompany));
        onView(withId(R.id.CurrentJobLocation)).perform(clearText(), replaceText(placeholderCurrentLocation));
        onView(withId(R.id.CurrentJobCostOfLivingIndex)).perform(clearText(), replaceText(placeholderCurrentCostOfLivingIndex));
        onView(withId(R.id.CurrentJobRemoteWorkTime)).perform(clearText(), replaceText(placeholderCurrentRemoteWorkTime));
        onView(withId(R.id.CurrentJobYearlySalary)).perform(clearText(), replaceText(placeholderCurrentYearlySalary));
        onView(withId(R.id.CurrentJobYearlyBonus)).perform(clearText(), replaceText(placeholderCurrentYearlyBonus));
        onView(withId(R.id.CurrentJobRetirementBenefitsPercentage)).perform(clearText(), replaceText(placeholderCurrentRetirementBenefitsPercentage));
        onView(withId(R.id.CurrentJobLeaveTime)).perform(clearText(), replaceText(placeholderCurrentLeaveTime));
        Espresso.closeSoftKeyboard();
        Intents.init();
        onView(withId(R.id.CurrentJobSaveButton)).perform(click());
        intended(hasComponent(MainMenu.class.getName()));
        Intents.release();
    }

    // Check whether the "Main Menu" UI is launched after clicking the "Cancel" button on the "Current Job" UI.
    @Test
    public void test20() {
        onView(withId(R.id.MainMenuCurrentJobButton)).perform(click());
        Intents.init();
        onView(withId(R.id.CurrentJobCancelButton)).perform(click());
        intended(hasComponent(MainMenu.class.getName()));
        Intents.release();
    }

    // Check whether the placeholder current job is saved and appears on the "Current Job" UI.
    @Test
    public void test21() {
        onView(withId(R.id.MainMenuCurrentJobButton)).perform(click());
        onView(withId(R.id.CurrentJobTitle)).check(matches(withText(placeholderCurrentTitle)));
        onView(withId(R.id.CurrentJobCompany)).check(matches(withText(placeholderCurrentCompany)));
        onView(withId(R.id.CurrentJobLocation)).check(matches(withText(placeholderCurrentLocation)));
        onView(withId(R.id.CurrentJobCostOfLivingIndex)).check(matches(withText(placeholderCurrentCostOfLivingIndex)));
        onView(withId(R.id.CurrentJobRemoteWorkTime)).check(matches(withText(placeholderCurrentRemoteWorkTime)));
        onView(withId(R.id.CurrentJobYearlySalary)).check(matches(withText(placeholderCurrentYearlySalary)));
        onView(withId(R.id.CurrentJobYearlyBonus)).check(matches(withText(placeholderCurrentYearlyBonus)));
        onView(withId(R.id.CurrentJobRetirementBenefitsPercentage)).check(matches(withText(placeholderCurrentRetirementBenefitsPercentage)));
        onView(withId(R.id.CurrentJobLeaveTime)).check(matches(withText(placeholderCurrentLeaveTime)));
    }

    // Check whether the placeholder current job's values can be replaced by current job's values and successfully saved on the "Current Job" UI.
    @Test
    public void test22() {
        onView(withId(R.id.MainMenuCurrentJobButton)).perform(click());
        onView(withId(R.id.CurrentJobTitle)).perform(clearText(), replaceText(currentTitle));
        onView(withId(R.id.CurrentJobCompany)).perform(clearText(), replaceText(currentCompany));
        onView(withId(R.id.CurrentJobLocation)).perform(clearText(), replaceText(currentLocation));
        onView(withId(R.id.CurrentJobCostOfLivingIndex)).perform(clearText(), replaceText(currentCostOfLivingIndex));
        onView(withId(R.id.CurrentJobRemoteWorkTime)).perform(clearText(), replaceText(currentRemoteWorkTime));
        onView(withId(R.id.CurrentJobYearlySalary)).perform(clearText(), replaceText(currentYearlySalary));
        onView(withId(R.id.CurrentJobYearlyBonus)).perform(clearText(), replaceText(currentYearlyBonus));
        onView(withId(R.id.CurrentJobRetirementBenefitsPercentage)).perform(clearText(), replaceText(currentRetirementBenefitsPercentage));
        onView(withId(R.id.CurrentJobLeaveTime)).perform(clearText(), replaceText(currentLeaveTime));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.CurrentJobSaveButton)).perform(click());
        onView(withId(R.id.MainMenuCurrentJobButton)).perform(click());
        onView(withId(R.id.CurrentJobTitle)).check(matches(withText(currentTitle)));
        onView(withId(R.id.CurrentJobCompany)).check(matches(withText(currentCompany)));
        onView(withId(R.id.CurrentJobLocation)).check(matches(withText(currentLocation)));
        onView(withId(R.id.CurrentJobCostOfLivingIndex)).check(matches(withText(currentCostOfLivingIndex)));
        onView(withId(R.id.CurrentJobRemoteWorkTime)).check(matches(withText(currentRemoteWorkTime)));
        onView(withId(R.id.CurrentJobYearlySalary)).check(matches(withText(currentYearlySalary)));
        onView(withId(R.id.CurrentJobYearlyBonus)).check(matches(withText(currentYearlyBonus)));
        onView(withId(R.id.CurrentJobRetirementBenefitsPercentage)).check(matches(withText(currentRetirementBenefitsPercentage)));
        onView(withId(R.id.CurrentJobLeaveTime)).check(matches(withText(currentLeaveTime)));
    }

    // Check whether the "Compare With Current Job" button on the "Job Offer Utility" UI is enabled when a current job exist,
    // and whether the "Comparison Result" UI is launched after clicking the "Compare With Current Job" button on the "Job Offer Utility" UI, adding job offer 5 in the process.
    // Then check whether the entries of the table comparing job offer 5 and current job is correctly displayed on the "Comparison Result" UI.
    @Test
    public void test23() {
        onView(withId(R.id.MainMenuJobOfferButton)).perform(click());
        onView(withId(R.id.JobOfferTitle)).perform(clearText(), replaceText(offerTitle5));
        onView(withId(R.id.JobOfferCompany)).perform(clearText(), replaceText(offerCompany5));
        onView(withId(R.id.JobOfferLocation)).perform(clearText(), replaceText(offerLocation5));
        onView(withId(R.id.JobOfferCostOfLivingIndex)).perform(clearText(), replaceText(offerCostOfLivingIndex5));
        onView(withId(R.id.JobOfferRemoteWorkTime)).perform(clearText(), replaceText(offerRemoteWorkTime5));
        onView(withId(R.id.JobOfferYearlySalary)).perform(clearText(), replaceText(offerYearlySalary5));
        onView(withId(R.id.JobOfferYearlyBonus)).perform(clearText(), replaceText(offerYearlyBonus5));
        onView(withId(R.id.JobOfferRetirementBenefitsPercentage)).perform(clearText(), replaceText(offerRetirementBenefitsPercentage5));
        onView(withId(R.id.JobOfferLeaveTime)).perform(clearText(), replaceText(offerLeaveTime5));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.JobOfferSaveButton)).perform(click());
        onView(withId(R.id.JobOfferUtilCompareWithCurrentButton)).check(matches(isEnabled()));
        Intents.init();
        onView(withId(R.id.JobOfferUtilCompareWithCurrentButton)).perform(click());
        intended(hasComponent(ComparisonResult.class.getName()));
        Intents.release();
        onView(withId(R.id.ComparisonResultTitle1)).check(matches(withSubstring(offerTitle5)));
        onView(withId(R.id.ComparisonResultCompany1)).check(matches(withSubstring(offerCompany5)));
        onView(withId(R.id.ComparisonResultLocation1)).check(matches(withSubstring(offerLocation5)));
        onView(withId(R.id.ComparisonResultAdjustedYearlySalary1)).check(matches(withSubstring(offerAdjustedYearlySalary5)));
        onView(withId(R.id.ComparisonResultAdjustedYearlyBonus1)).check(matches(withSubstring(offerAdjustedYearlyBonus5)));
        onView(withId(R.id.ComparisonResultRetirementBenefitsPercentage1)).check(matches(withSubstring(offerRetirementBenefitsPercentage5)));
        onView(withId(R.id.ComparisonResultLeaveTime1)).check(matches(withSubstring(offerLeaveTime5)));
        onView(withId(R.id.ComparisonResultTitle2)).check(matches(withSubstring("*"+currentTitle)));
        onView(withId(R.id.ComparisonResultCompany2)).check(matches(withSubstring(currentCompany)));
        onView(withId(R.id.ComparisonResultLocation2)).check(matches(withSubstring(currentLocation)));
        onView(withId(R.id.ComparisonResultAdjustedYearlySalary2)).check(matches(withSubstring(currentAdjustedYearlySalary)));
        onView(withId(R.id.ComparisonResultAdjustedYearlyBonus2)).check(matches(withSubstring(currentAdjustedYearlyBonus)));
        onView(withId(R.id.ComparisonResultRetirementBenefitsPercentage2)).check(matches(withSubstring(currentRetirementBenefitsPercentage)));
        onView(withId(R.id.ComparisonResultLeaveTime2)).check(matches(withSubstring(currentLeaveTime)));
    }

    // Check whether the "Comparison Settings" UI is launched after clicking the "Comparison Settings" button on the "Main Menu" UI.
    @Test
    public void test24() {
        Intents.init();
        onView(withId(R.id.MainMenuComparisonSettingsButton)).perform(click());
        intended(hasComponent(ComparisonSettings.class.getName()));
        Intents.release();
    }

    // Check whether the five initial weights are all assigned as 1.
    @Test
    public void test25() {
        onView(withId(R.id.MainMenuComparisonSettingsButton)).perform(click());
        onView(withId(R.id.ComparisonSettingsAYSWeight)).check(matches(withText("1")));
        onView(withId(R.id.ComparisonSettingsAYBWeight)).check(matches(withText("1")));
        onView(withId(R.id.ComparisonSettingsRBPWeight)).check(matches(withText("1")));
        onView(withId(R.id.ComparisonSettingsLTWeight)).check(matches(withText("1")));
        onView(withId(R.id.ComparisonSettingsRWTWeight)).check(matches(withText("1")));
    }

    // Check whether the error texts will appear when trying to save with blank text editing fields on the "Comparison Settings" UI.
    @Test
    public void test26() {
        onView(withId(R.id.MainMenuComparisonSettingsButton)).perform(click());
        onView(withId(R.id.ComparisonSettingsAYSWeight)).perform(clearText());
        onView(withId(R.id.ComparisonSettingsAYBWeight)).perform(clearText());
        onView(withId(R.id.ComparisonSettingsRBPWeight)).perform(clearText());
        onView(withId(R.id.ComparisonSettingsLTWeight)).perform(clearText());
        onView(withId(R.id.ComparisonSettingsRWTWeight)).perform(clearText());
        onView(withId(R.id.ComparisonSettingsSaveButton)).perform(click());
        onView(withId(R.id.ComparisonSettingsAYSWeight)).check(matches(hasErrorText("The AYS Weight field is empty!")));
        onView(withId(R.id.ComparisonSettingsAYBWeight)).check(matches(hasErrorText("The AYB Weight field is empty!")));
        onView(withId(R.id.ComparisonSettingsRBPWeight)).check(matches(hasErrorText("The RBP Weight field is empty!")));
        onView(withId(R.id.ComparisonSettingsLTWeight)).check(matches(hasErrorText("The LT Weight field is empty!")));
        onView(withId(R.id.ComparisonSettingsRWTWeight)).check(matches(hasErrorText("The RWT Weight field is empty!")));
    }

    // Check whether the error texts will appear when trying to save with some invalid text editing fields on the "Comparison Settings" UI.
    @Test
    public void test27() {
        onView(withId(R.id.MainMenuComparisonSettingsButton)).perform(click());
        onView(withId(R.id.ComparisonSettingsAYSWeight)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.ComparisonSettingsAYBWeight)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.ComparisonSettingsRBPWeight)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.ComparisonSettingsLTWeight)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.ComparisonSettingsRWTWeight)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.ComparisonSettingsSaveButton)).perform(click());
        onView(withId(R.id.ComparisonSettingsAYSWeight)).check(matches(hasErrorText("The AYS Weight must be a positive integer!")));
        onView(withId(R.id.ComparisonSettingsAYBWeight)).check(matches(hasErrorText("The AYB Weight must be a positive integer!")));
        onView(withId(R.id.ComparisonSettingsRBPWeight)).check(matches(hasErrorText("The RBP Weight must be a positive integer!")));
        onView(withId(R.id.ComparisonSettingsLTWeight)).check(matches(hasErrorText("The LT Weight must be a positive integer!")));
        onView(withId(R.id.ComparisonSettingsRWTWeight)).check(matches(hasErrorText("The RWT Weight must be a positive integer!")));
    }

    // Check whether the "Main Menu" UI is launched after clicking the "Cancel" button on the "Comparison Settings" UI.
    @Test
    public void test28() {
        onView(withId(R.id.MainMenuComparisonSettingsButton)).perform(click());
        Intents.init();
        onView(withId(R.id.ComparisonSettingsCancelButton)).perform(click());
        intended(hasComponent(MainMenu.class.getName()));
        Intents.release();
    }

    // Check whether the default weight values can be replaced by modified weight values and successfully saved on the "Comparison Settings" UI.
    @Test
    public void test29() {
        onView(withId(R.id.MainMenuComparisonSettingsButton)).perform(click());
        onView(withId(R.id.ComparisonSettingsAYSWeight)).perform(clearText(), replaceText(AYSWeightModified));
        onView(withId(R.id.ComparisonSettingsAYBWeight)).perform(clearText(), replaceText(AYBWeightModified));
        onView(withId(R.id.ComparisonSettingsRBPWeight)).perform(clearText(), replaceText(RBPWeightModified));
        onView(withId(R.id.ComparisonSettingsLTWeight)).perform(clearText(), replaceText(LTWeightModified));
        onView(withId(R.id.ComparisonSettingsRWTWeight)).perform(clearText(), replaceText(RWTWeightModified));
        onView(withId(R.id.ComparisonSettingsSaveButton)).perform(click());
        onView(withId(R.id.ComparisonSettingsCancelButton)).perform(click());
        onView(withId(R.id.MainMenuComparisonSettingsButton)).perform(click());
        onView(withId(R.id.ComparisonSettingsAYSWeight)).check(matches(withText(AYSWeightModified)));
        onView(withId(R.id.ComparisonSettingsAYBWeight)).check(matches(withText(AYBWeightModified)));
        onView(withId(R.id.ComparisonSettingsRBPWeight)).check(matches(withText(RBPWeightModified)));
        onView(withId(R.id.ComparisonSettingsLTWeight)).check(matches(withText(LTWeightModified)));
        onView(withId(R.id.ComparisonSettingsRWTWeight)).check(matches(withText(RWTWeightModified)));
    }

    // Check whether the "Compare Jobs" UI is launched after clicking the "Compare Jobs" button on the "Main Menu" UI.
    @Test
    public void test30() {
        Intents.init();
        onView(withId(R.id.MainMenuCompareJobsButton)).perform(click());
        intended(hasComponent(CompareJobs.class.getName()));
        Intents.release();
    }

    // Check whether the "Main Menu" UI is launched after clicking the "Cancel" button on the "Compare Jobs" UI.
    @Test
    public void test31() {
        onView(withId(R.id.MainMenuCompareJobsButton)).perform(click());
        Intents.init();
        onView(withId(R.id.CompareJobsCancelButton)).perform(click());
        intended(hasComponent(MainMenu.class.getName()));
        Intents.release();
    }

    // Check whether the list of jobs on the "Compare Jobs" UI is ranked according to correctly calculated job scores,
    // and the current job is clearly indicated with an "*" in front of its job title.
    @Test
    public void test32() {
        onView(withId(R.id.MainMenuCompareJobsButton)).perform(click());
        onData(instanceOf(Job.class)).atPosition(0).check(matches(withSubstring("*"+currentTitle)));
        onData(instanceOf(Job.class)).atPosition(0).check(matches(withSubstring(currentJobScoreModifiedWeight)));
        onData(instanceOf(Job.class)).atPosition(1).check(matches(withSubstring(offerTitle4)));
        onData(instanceOf(Job.class)).atPosition(1).check(matches(withSubstring(offerJobScoreModifiedWeight4)));
        onData(instanceOf(Job.class)).atPosition(2).check(matches(withSubstring(offerTitle2)));
        onData(instanceOf(Job.class)).atPosition(2).check(matches(withSubstring(offerJobScoreModifiedWeight2)));
        onData(instanceOf(Job.class)).atPosition(3).check(matches(withSubstring(offerTitle3)));
        onData(instanceOf(Job.class)).atPosition(3).check(matches(withSubstring(offerJobScoreModifiedWeight3)));
        onData(instanceOf(Job.class)).atPosition(4).check(matches(withSubstring(offerTitle5)));
        onData(instanceOf(Job.class)).atPosition(4).check(matches(withSubstring(offerJobScoreModifiedWeight5)));
        onData(instanceOf(Job.class)).atPosition(5).check(matches(withSubstring(offerTitle1)));
        onData(instanceOf(Job.class)).atPosition(5).check(matches(withSubstring(offerJobScoreModifiedWeight1)));
    }

    // Check whether the "Compare" button on the "Compare Jobs" UI is only enabled when there are exactly two jobs selected.
    @Test
    public void test33() {
        onView(withId(R.id.MainMenuCompareJobsButton)).perform(click());
        onView(withId(R.id.CompareJobsCompareButton)).check(matches(not(isEnabled())));
        onData(instanceOf(Job.class)).atPosition(0).perform(click());
        onView(withId(R.id.CompareJobsCompareButton)).check(matches(not(isEnabled())));
        onData(instanceOf(Job.class)).atPosition(1).perform(click());
        onView(withId(R.id.CompareJobsCompareButton)).check(matches(isEnabled()));
        onData(instanceOf(Job.class)).atPosition(2).perform(click());
        onView(withId(R.id.CompareJobsCompareButton)).check(matches(not(isEnabled())));
        onData(instanceOf(Job.class)).atPosition(1).perform(click());
        onView(withId(R.id.CompareJobsCompareButton)).check(matches(isEnabled()));
    }

    // Check whether the "Comparison Result" UI is launched after choosing the first two jobs and clicking the "Compare" button on the "Compare Jobs" UI.
    @Test
    public void test34() {
        onView(withId(R.id.MainMenuCompareJobsButton)).perform(click());
        onData(instanceOf(Job.class)).atPosition(0).perform(click());
        onData(instanceOf(Job.class)).atPosition(1).perform(click());
        Intents.init();
        onView(withId(R.id.CompareJobsCompareButton)).perform(click());
        intended(hasComponent(ComparisonResult.class.getName()));
        Intents.release();
    }

    // Check whether the "Compare Jobs" UI is launched after clicking the "Perform Another Comparison" button on the "Comparison Result" UI.
    @Test
    public void test35() {
        onView(withId(R.id.MainMenuCompareJobsButton)).perform(click());
        onData(instanceOf(Job.class)).atPosition(0).perform(click());
        onData(instanceOf(Job.class)).atPosition(1).perform(click());
        onView(withId(R.id.CompareJobsCompareButton)).perform(click());
        Intents.init();
        onView(withId(R.id.ComparisonResultPerformAnotherComparisonButton)).perform(click());
        intended(hasComponent(CompareJobs.class.getName()));
        Intents.release();
    }

    // Check whether the "Main Menu" UI is launched after clicking the "Return To Main Menu" button on the "Comparison Result" UI.
    @Test
    public void test36() {
        onView(withId(R.id.MainMenuCompareJobsButton)).perform(click());
        onData(instanceOf(Job.class)).atPosition(0).perform(click());
        onData(instanceOf(Job.class)).atPosition(1).perform(click());
        onView(withId(R.id.CompareJobsCompareButton)).perform(click());
        Intents.init();
        onView(withId(R.id.ComparisonResultReturnMainMenuButton)).perform(click());
        intended(hasComponent(MainMenu.class.getName()));
        Intents.release();
    }

    // Check whether the entries of the table comparing current job and job offer 1 is correctly displayed on the "Comparison Result" UI.
    @Test
    public void test37() {
        onView(withId(R.id.MainMenuCompareJobsButton)).perform(click());
        onData(instanceOf(Job.class)).atPosition(0).perform(click());
        onData(instanceOf(Job.class)).atPosition(5).perform(click());
        onView(withId(R.id.CompareJobsCompareButton)).perform(click());
        onView(withId(R.id.ComparisonResultTitle1)).check(matches(withSubstring("*"+currentTitle)));
        onView(withId(R.id.ComparisonResultCompany1)).check(matches(withSubstring(currentCompany)));
        onView(withId(R.id.ComparisonResultLocation1)).check(matches(withSubstring(currentLocation)));
        onView(withId(R.id.ComparisonResultAdjustedYearlySalary1)).check(matches(withSubstring(currentAdjustedYearlySalary)));
        onView(withId(R.id.ComparisonResultAdjustedYearlyBonus1)).check(matches(withSubstring(currentAdjustedYearlyBonus)));
        onView(withId(R.id.ComparisonResultRetirementBenefitsPercentage1)).check(matches(withSubstring(currentRetirementBenefitsPercentage)));
        onView(withId(R.id.ComparisonResultLeaveTime1)).check(matches(withSubstring(currentLeaveTime)));
        onView(withId(R.id.ComparisonResultTitle2)).check(matches(withSubstring(offerTitle1)));
        onView(withId(R.id.ComparisonResultCompany2)).check(matches(withSubstring(offerCompany1)));
        onView(withId(R.id.ComparisonResultLocation2)).check(matches(withSubstring(offerLocation1)));
        onView(withId(R.id.ComparisonResultAdjustedYearlySalary2)).check(matches(withSubstring(offerAdjustedYearlySalary1)));
        onView(withId(R.id.ComparisonResultAdjustedYearlyBonus2)).check(matches(withSubstring(offerAdjustedYearlyBonus1)));
        onView(withId(R.id.ComparisonResultRetirementBenefitsPercentage2)).check(matches(withSubstring(offerRetirementBenefitsPercentage1)));
        onView(withId(R.id.ComparisonResultLeaveTime2)).check(matches(withSubstring(offerLeaveTime1)));
    }

    // Check whether the entries of the table comparing job offer 2 and job offer 3 is correctly displayed on the "Comparison Result" UI.
    @Test
    public void test38() {
        onView(withId(R.id.MainMenuCompareJobsButton)).perform(click());
        onData(instanceOf(Job.class)).atPosition(2).perform(click());
        onData(instanceOf(Job.class)).atPosition(3).perform(click());
        onView(withId(R.id.CompareJobsCompareButton)).perform(click());
        onView(withId(R.id.ComparisonResultTitle1)).check(matches(withSubstring(offerTitle2)));
        onView(withId(R.id.ComparisonResultCompany1)).check(matches(withSubstring(offerCompany2)));
        onView(withId(R.id.ComparisonResultLocation1)).check(matches(withSubstring(offerLocation2)));
        onView(withId(R.id.ComparisonResultAdjustedYearlySalary1)).check(matches(withSubstring(offerAdjustedYearlySalary2)));
        onView(withId(R.id.ComparisonResultAdjustedYearlyBonus1)).check(matches(withSubstring(offerAdjustedYearlyBonus2)));
        onView(withId(R.id.ComparisonResultRetirementBenefitsPercentage1)).check(matches(withSubstring(offerRetirementBenefitsPercentage2)));
        onView(withId(R.id.ComparisonResultLeaveTime1)).check(matches(withSubstring(offerLeaveTime2)));
        onView(withId(R.id.ComparisonResultTitle2)).check(matches(withSubstring(offerTitle3)));
        onView(withId(R.id.ComparisonResultCompany2)).check(matches(withSubstring(offerCompany3)));
        onView(withId(R.id.ComparisonResultLocation2)).check(matches(withSubstring(offerLocation3)));
        onView(withId(R.id.ComparisonResultAdjustedYearlySalary2)).check(matches(withSubstring(offerAdjustedYearlySalary3)));
        onView(withId(R.id.ComparisonResultAdjustedYearlyBonus2)).check(matches(withSubstring(offerAdjustedYearlyBonus3)));
        onView(withId(R.id.ComparisonResultRetirementBenefitsPercentage2)).check(matches(withSubstring(offerRetirementBenefitsPercentage3)));
        onView(withId(R.id.ComparisonResultLeaveTime2)).check(matches(withSubstring(offerLeaveTime3)));
    }

    // Check whether the entries of the table comparing job offer 4 and job offer 5 is correctly displayed on the "Comparison Result" UI.
    @Test
    public void test39() {
        onView(withId(R.id.MainMenuCompareJobsButton)).perform(click());
        onData(instanceOf(Job.class)).atPosition(1).perform(click());
        onData(instanceOf(Job.class)).atPosition(4).perform(click());
        onView(withId(R.id.CompareJobsCompareButton)).perform(click());
        onView(withId(R.id.ComparisonResultTitle1)).check(matches(withSubstring(offerTitle4)));
        onView(withId(R.id.ComparisonResultCompany1)).check(matches(withSubstring(offerCompany4)));
        onView(withId(R.id.ComparisonResultLocation1)).check(matches(withSubstring(offerLocation4)));
        onView(withId(R.id.ComparisonResultAdjustedYearlySalary1)).check(matches(withSubstring(offerAdjustedYearlySalary4)));
        onView(withId(R.id.ComparisonResultAdjustedYearlyBonus1)).check(matches(withSubstring(offerAdjustedYearlyBonus4)));
        onView(withId(R.id.ComparisonResultRetirementBenefitsPercentage1)).check(matches(withSubstring(offerRetirementBenefitsPercentage4)));
        onView(withId(R.id.ComparisonResultLeaveTime1)).check(matches(withSubstring(offerLeaveTime4)));
        onView(withId(R.id.ComparisonResultTitle2)).check(matches(withSubstring(offerTitle5)));
        onView(withId(R.id.ComparisonResultCompany2)).check(matches(withSubstring(offerCompany5)));
        onView(withId(R.id.ComparisonResultLocation2)).check(matches(withSubstring(offerLocation5)));
        onView(withId(R.id.ComparisonResultAdjustedYearlySalary2)).check(matches(withSubstring(offerAdjustedYearlySalary5)));
        onView(withId(R.id.ComparisonResultAdjustedYearlyBonus2)).check(matches(withSubstring(offerAdjustedYearlyBonus5)));
        onView(withId(R.id.ComparisonResultRetirementBenefitsPercentage2)).check(matches(withSubstring(offerRetirementBenefitsPercentage5)));
        onView(withId(R.id.ComparisonResultLeaveTime2)).check(matches(withSubstring(offerLeaveTime5)));
    }

}