package skin.support.animator.SingleAnimator.hide;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import skin.support.animator.Action;
import skin.support.animator.SingleAnimator.ViewAnimatorImpl;
import skin.support.animator.SkinAnimator;

/**
 * Created by erfli on 2/25/17.
 */

public class TranslationRotationHideAnimator2 extends ViewAnimatorImpl {

    private ObjectAnimator animator;

    private TranslationRotationHideAnimator2() {
    }


    public static TranslationRotationHideAnimator2 getInstance() {
        return new TranslationRotationHideAnimator2();
    }

    @Override
    public SkinAnimator apply(@NonNull final View view, @Nullable final Action action) {
        animator = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("alpha", 1, 0),
                PropertyValuesHolder.ofFloat("rotation", 0, 360),
                PropertyValuesHolder.ofFloat("translationY", 0, view.getHeight()),
                PropertyValuesHolder.ofFloat("translationX", 0, view.getWidth()));
        animator.setDuration(5 * PRE_DURATION);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                resetView(view);
                if (action != null) {
                    action.action();
                }
            }
        });
        return this;
    }

    @Override
    public void start() {
        animator.start();
    }
}
