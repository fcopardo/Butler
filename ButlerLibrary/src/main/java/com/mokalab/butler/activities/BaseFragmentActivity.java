package com.mokalab.butler.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.mokalab.butler.fragments.BaseFragment;
import com.mokalab.butler.interfaces.IBundleArgs;
import com.mokalab.butler.interfaces.IFragmentHelper;
import com.mokalab.butler.interfaces.IViewHelper;
import com.mokalab.butler.util.BundleArgs;
import com.mokalab.butler.util.FragmentUtils;
import com.mokalab.butler.util.ViewUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Pirdad S on 2014-07-22.
 */
public abstract class BaseFragmentActivity extends FragmentActivity implements IBundleArgs, IFragmentHelper, IViewHelper {

    /**
     * Returns Bundle from getIntent().
     */
    protected Bundle getBundle() {

        Intent intent = getIntent();
        if (intent == null) return null;

        return intent.getExtras();
    }

    /**
     * Get String Type arguments from {@link #getIntent()}.
     */
    @Override
    public String getStringArg(@NotNull String key, String def) {

        return BundleArgs.getStringArg(getBundle(), key, def);
    }

    /**
     * Get Integer Type arguments from {@link #getIntent()}.
     */
    @Override
    public int getIntArg(@NotNull String key, int def) {

        return BundleArgs.getIntArg(getBundle(), key, def);
    }

    /**
     * Get Integer Array Type arguments from {@link #getIntent()}.
     */
    @Override
    public int[] getIntArrayArg(@NotNull String key, int[] def) {

        return BundleArgs.getIntArrayArg(getBundle(), key, def);
    }

    /**
     * Get Boolean Type arguments from {@link #getIntent()}.
     */
    @Override
    public boolean getBooleanArg(@NotNull String key, boolean def) {

        return BundleArgs.getBooleanArg(getBundle(), key, def);
    }

    /**
     * Get Parcelable Type arguments from {@link #getIntent()}.
     */
    @Override
    public <T extends Parcelable> T getParcelableArg(@NotNull String key) {

        return BundleArgs.getParcelableArg(getBundle(), key);
    }

    /**
     * Get Parcelable ArrayList Type arguments from {@link #getIntent()}.
     */
    @Override
    public <T extends Parcelable> ArrayList<T> getParcelableArrayListArg(@NotNull String key) {

        return BundleArgs.getParcelableArrayListArg(getBundle(), key);
    }

    /**
     * Get Serializable Type arguments from {@link #getIntent()}.
     */
    @Override
    public Serializable getSerializableArg(@NotNull String key) {

        return BundleArgs.getSerializableArg(getBundle(), key);
    }

    @Override
    public void replaceFragment(int containerResId, @NotNull BaseFragment fragment, @Nullable String fragmentTag) {

        FragmentManager mgr = getSupportFragmentManager();
        FragmentUtils.replaceFragment(mgr, containerResId, fragment, fragmentTag);
    }

    @Override
    public void replaceFragment(int containerResId, @NotNull BaseFragment fragment, @Nullable String fragmentTag, boolean addToBackStack) {

        FragmentManager mgr = getSupportFragmentManager();
        FragmentUtils.replaceFragment(mgr, containerResId, fragment, fragmentTag, addToBackStack);
    }

    /**
     * {@inheritDoc }
     * If the specified type does not match the found fragment type, it'll throw
     * {@link IllegalArgumentException}.
     */
    @Nullable
    @Override
    public <T extends BaseFragment> T findFragmentByTag(@NotNull String fragmentTag, @NotNull Class<T> returnType) {

        FragmentManager mgr = getSupportFragmentManager();
        return FragmentUtils.findFragmentByTag(mgr, fragmentTag, returnType);
    }

    @Nullable
    @Override
    public <T extends View> T findView(View from, int viewResId) {

        return ViewUtils.findView(from, viewResId);
    }
}