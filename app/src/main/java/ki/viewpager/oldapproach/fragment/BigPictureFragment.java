package ki.viewpager.oldapproach.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import ki.viewpager.oldapproach.MainActivity;
import ki.viewpageroldapproach.R;
import ki.viewpager.oldapproach.util.FragmentTransitionUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class BigPictureFragment extends Fragment {

    public ImageView bigCatImageView;
    public TextView bigCatLabel;
    public BigPictureFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_big_picture, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();
        super.onViewCreated(view, savedInstanceState);

        bigCatImageView = view.findViewById(R.id.bigPic_image_cat);
        bigCatLabel = view.findViewById(R.id.bigPic_text_label);

        bigCatImageView.setOnClickListener(v -> {
            HashMap<View, String> sharedElements = new HashMap<>();
            sharedElements.put(bigCatImageView, "shared_cat");
//            sharedElements.put(bigCatLabel, "shared_label");
            FragmentTransitionUtil.perform(
                    activity,
                    activity.big_picture_fragment,
                    activity.small_picture_fragment_fake,
                    sharedElements,
                    MainActivity.PAGE_SMALL_CAT
            );
        });

    }
}
