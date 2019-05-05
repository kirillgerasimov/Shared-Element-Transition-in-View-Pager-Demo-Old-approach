package ki.viewpager.oldapproach.util;

import android.os.Build;
import android.os.Handler;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Map;


import ki.viewpager.oldapproach.MainActivity;


public class FragmentTransitionUtil {

    private static final long FADE_DEFAULT_TIME = 500;
    private static final long MOVE_DEFAULT_TIME = 1000;

    public static void perform(
            MainActivity activity,
            Fragment previousFragment,
            Fragment nextFragment,
            Map<View, String> sharedElements,
            int nextPage

    ) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (previousFragment != null) {

            // 1. Exit for Previous Fragment
            Fade exitFade = new Fade();
            exitFade.setDuration(FADE_DEFAULT_TIME);
            previousFragment.setExitTransition(exitFade);

            // 2. Shared Elements Transition
            TransitionSet enterTransitionSet = new TransitionSet();
            enterTransitionSet.addTransition(
                    new TransitionSet() {

                        {
                            setOrdering(ORDERING_TOGETHER);
                            addTransition(new ChangeBounds()).
                            addTransition(new ChangeTransform()).
                            addTransition(new ChangeImageTransform());
                        }
                    }
            );

            enterTransitionSet.setDuration(MOVE_DEFAULT_TIME);
            enterTransitionSet.setStartDelay(FADE_DEFAULT_TIME);
            nextFragment.setSharedElementEnterTransition(enterTransitionSet);

            // 3. Enter Transition for New Fragment
            Fade enterFade = new Fade();
            enterFade.setStartDelay(MOVE_DEFAULT_TIME + FADE_DEFAULT_TIME);
            enterFade.setDuration(FADE_DEFAULT_TIME);
            nextFragment.setEnterTransition(enterFade);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (sharedElements != null) {
                    for (Map.Entry<View, String> viewStringEntry : sharedElements.entrySet()) {
                        View view = viewStringEntry.getKey();
                        String transName = viewStringEntry.getValue();
//                        exitFade.excludeTarget(view,true);
                        view.setTransitionName(transName);

                        fragmentTransaction.addSharedElement(
                                view,
                                transName
                        );
                    }
                }
            }

            int fragmentContId = ((ViewGroup) previousFragment.getView().getParent()).getId();
            fragmentTransaction.replace(fragmentContId, nextFragment);
            fragmentTransaction.commit();


            final Handler handler = new Handler();
            handler.postDelayed(
                    () -> {
                        // Stealthy changing page visible to user. He won’t notice!
                        activity.viewPager.setCurrentItem(nextPage, false);
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        // Restore previous fragment. It contains inappropriate view now
                        transaction.replace(fragmentContId, previousFragment);
                        transaction.commitAllowingStateLoss();
                    },
                    FADE_DEFAULT_TIME + MOVE_DEFAULT_TIME + FADE_DEFAULT_TIME
            );


        }
    }

}
