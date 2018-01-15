package com.courses.bogdan.myapplication.presenters;

import android.graphics.Color;

import com.courses.bogdan.myapplication.views.MainView;

import java.util.Random;

/**
 * Created by robert on 19/12/2017.
 */

public class MainPresenterImpl implements MainPresenter {

    private static final int MAX_COLOR_VALUE = 256;

    private MainView view;
    private Random randomColorGenerator;


    public MainPresenterImpl(MainView view) {
        this.view = view;
    }

    @Override
    public void onViewCreated() {
        randomColorGenerator = new Random();

    }

    @Override
    public void onViewStarted() {
        view.setMainLayoutBackgroundColor(generateRandomColor());
    }

    @Override
    public void onColorButtonClicked() {
        view.setMainLayoutBackgroundColor(generateRandomColor());
    }

    private int generateRandomColor() {
        return Color.argb(MAX_COLOR_VALUE - 1,
                randomColorGenerator.nextInt(MAX_COLOR_VALUE),
                randomColorGenerator.nextInt(MAX_COLOR_VALUE),
                randomColorGenerator.nextInt(MAX_COLOR_VALUE));
    }
}
