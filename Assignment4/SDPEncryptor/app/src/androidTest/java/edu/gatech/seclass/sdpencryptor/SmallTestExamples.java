package edu.gatech.seclass.sdpencryptor;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * This is a Georgia Tech provided code example for use in assigned private GT repositories. Students and other users of this template
 * code are advised not to share it with other students or to make it available on publicly viewable websites including
 * repositories such as github and gitlab.  Such sharing may be investigated as a GT honor code violation. Created for CS6300.
 */


@RunWith(AndroidJUnit4.class)
public class SmallTestExamples {

    @Rule
    public ActivityTestRule<MainActivity> tActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Test
    public void Screenshot1() {
        onView(withId(R.id.plaintextID)).perform(clearText(), replaceText("Cat & 5 DogS"));
        onView(withId(R.id.alphaKeyID)).perform(clearText(), replaceText("5"));
        onView(withId(R.id.betaKeyID)).perform(clearText(), replaceText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encipherButtonID)).perform(click());
        onView(withId(R.id.ciphertextID)).check(matches(withText("Ndu & 5 SvhP")));
    }

    @Test
    public void Screenshot2() {
        onView(withId(R.id.plaintextID)).perform(clearText(), replaceText("Down by the Bay? Pop!"));
        onView(withId(R.id.alphaKeyID)).perform(clearText(), replaceText("3"));
        onView(withId(R.id.betaKeyID)).perform(clearText(), replaceText("5"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encipherButtonID)).perform(click());
        onView(withId(R.id.ciphertextID)).check(matches(withText("Ovts iz kar Ifz? Yvy!")));
    }

    @Test
    public void Screenshot3() {
        onView(withId(R.id.plaintextID)).perform(clearText(), replaceText("%abcdefg #"));
        onView(withId(R.id.alphaKeyID)).perform(clearText(), replaceText("3"));
        onView(withId(R.id.betaKeyID)).perform(clearText(), replaceText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encipherButtonID)).perform(click());
        onView(withId(R.id.ciphertextID)).check(matches(withText("%behknqt #")));
    }

    @Test
    public void trigger() {
        onView(withId(R.id.plaintextID)).perform(clearText(), replaceText("__trigger__"));
        onView(withId(R.id.alphaKeyID)).perform(clearText(), replaceText("3"));
        onView(withId(R.id.betaKeyID)).perform(clearText(), replaceText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encipherButtonID)).perform(click());
        onView(withId(R.id.ciphertextID)).check(matches(withText("__gazttna__")));
    }

    @Test
    public void errorTest1() {
        onView(withId(R.id.plaintextID)).perform(clearText(), replaceText(""));
        onView(withId(R.id.alphaKeyID)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.betaKeyID)).perform(clearText(), replaceText("0"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encipherButtonID)).perform(click());
        onView(withId(R.id.betaKeyID)).check(matches(hasErrorText("Invalid Beta Key")));
        onView(withId(R.id.plaintextID)).check(matches(hasErrorText("Invalid Plaintext")));
        onView(withId(R.id.alphaKeyID)).check(matches(hasErrorText("Invalid Alpha Key")));
    }

    @Test
    public void gradingTest13() {
        onView(withId(R.id.plaintextID)).perform(clearText(), replaceText("Panda Cat"));
        onView(withId(R.id.alphaKeyID)).perform(clearText(), replaceText("23"));
        onView(withId(R.id.betaKeyID)).perform(clearText(), replaceText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encipherButtonID)).perform(click());
        onView(withId(R.id.ciphertextID)).check(matches(withText("Ibosb Vbw")));
    }
}
