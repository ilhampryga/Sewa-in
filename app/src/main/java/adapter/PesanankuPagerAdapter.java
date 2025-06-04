package adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import fragment.TabDipesanFragment;
import fragment.TabDisewaFragment;
import fragment.TabSelesaiFragment;

public class PesanankuPagerAdapter extends FragmentStateAdapter {

    private Bundle dataBundle;

    public PesanankuPagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    public void setDataBundle(Bundle bundle) {
        this.dataBundle = bundle;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position) {
            case 0: fragment = new TabDipesanFragment(); break;
            case 1: fragment = new TabDisewaFragment(); break;
            case 2: fragment = new TabSelesaiFragment(); break;
            default: fragment = new TabDipesanFragment(); break;
        }
        if (dataBundle != null) {
            fragment.setArguments(dataBundle);
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

