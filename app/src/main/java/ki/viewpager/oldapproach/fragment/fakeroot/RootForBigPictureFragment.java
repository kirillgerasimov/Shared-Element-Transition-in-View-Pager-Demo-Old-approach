package ki.viewpager.oldapproach.fragment.fakeroot;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ki.viewpager.oldapproach.MainActivity;
import ki.viewpageroldapproach.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class RootForBigPictureFragment extends Fragment {


    public RootForBigPictureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_root_for_big_picture, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        /*
         * When this container fragment is created, we fill it with our first
         * "real" fragment
         */
        MainActivity activity = (MainActivity) getActivity();
        transaction.replace(R.id.rootForBigPicture_frame, activity.big_picture_fragment);
        transaction.commit();

        return view;

    }

}
