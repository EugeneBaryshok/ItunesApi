package com.example.mi.albumlibrary.screen;

import com.example.mi.albumlibrary.screen.main.MainPresenter;
import com.example.mi.albumlibrary.screen.main.MainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.times;

@RunWith(JUnit4.class)
public class MainActivityPresenterTest {

    private MainView mMainView;
    private MainPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
              mPresenter = new MainPresenter(mMainView);
    }

    @Test
    public void testCreated() throws Exception {
        assertNotNull(mPresenter);
    }
}
