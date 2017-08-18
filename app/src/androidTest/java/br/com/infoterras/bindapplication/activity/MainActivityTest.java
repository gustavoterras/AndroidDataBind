package br.com.infoterras.bindapplication.activity;


import android.content.ComponentName;
import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import junit.framework.Assert;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import br.com.infoterras.bindapplication.R;
import br.com.infoterras.bindapplication.model.Repository;
import br.com.infoterras.bindapplication.model.User;
import br.com.infoterras.bindapplication.viewModel.UserViewModel;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private ArrayList<Repository> repositories;
    private User user;

    @Before
    public void setup() {
        user = mockUser();
        repositories = mockRepository();
    }

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class, true, false);


    @Test
    public void mainActivityTest() {

        Intents.init();

        mainActivityTestRule.launchActivity(createIntentWithUser(user));

        intended(hasComponent(new ComponentName(getTargetContext(), MainActivity.class)));

        ViewInteraction imgUserAvatar = onView(withId(R.id.img_user_avatar));
        imgUserAvatar.check(matches(isDisplayed()));

        ViewInteraction tvUserName = onView(withId(R.id.tv_userName));
        tvUserName.check(matches(isDisplayed()));
        tvUserName.check(matches(withText(user.getName())));

        ViewInteraction tvUserBio = onView(withId(R.id.tv_userBio));
        tvUserBio.check(matches(isDisplayed()));
        tvUserBio.check(matches(withText(user.getBio())));

        UserViewModel userViewModel = mock(UserViewModel.class);

        Assert.assertNotNull(userViewModel);

        //userViewModel.onSuccess(repositories, -1);

        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction recyclerView = onView(withId(R.id.recycler_view));
        recyclerView.check(matches(isDisplayed()));
        recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        intended(hasComponent(new ComponentName(getTargetContext(), ContentActivity.class)));

    }

    private static User mockUser(){

        User user = new User();

        user.setName("Gustavo Terras");
        user.setLogin("gustavoterras");
        user.setPublic_repos("24");
        user.setLocation("Brazil, Canoas - RS");
        user.setFollowers("9");
        user.setFollowing("27");
        user.setEmail("gustavoterras@gmail.com");
        user.setBlog("http://gustavoterras.github.io");
        user.setBio("Mobile Application Developer");
        user.setAvatar_url("https://avatars2.githubusercontent.com/u/8082012?v=4");

        Assert.assertNotNull(user);

        return user;
    }

    private static ArrayList<Repository> mockRepository(){

        Repository repository1 = new Repository();
        repository1.setName("Teste1");

        Repository repository2 = new Repository();
        repository2.setName("Teste2");

        ArrayList<Repository> repositories = new ArrayList<>();

        repositories.add(repository1);
        repositories.add(repository2);

        return repositories;
    }

    private static Intent createIntentWithUser(User user){
        Intent intent = new Intent(getTargetContext(), MainActivity.class);
        intent.putExtra("extra", user);
        return intent;
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
