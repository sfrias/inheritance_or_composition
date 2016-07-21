/*
 * Copyright Txus Ballesteros 2016 (@txusballesteros)
 *
 * This file is part of some open source application.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */
package com.txusballesteros.view.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.txusballesteros.AndroidApplication;
import com.txusballesteros.di.ApplicationComponent;

abstract class AbsFragment extends Fragment {
  public static final int WITHOUT_MENU = 0;
  private Menu menu;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    onRequestInjection(getApplicationComponent());
    setHasOptionsMenu(true);
    initializeToolbar();
  }

  abstract void onRequestInjection(ApplicationComponent applicationComponent);

  public void onInitializeToolbar() { }

  protected ActionBar getToolbar() {
    return ((AppCompatActivity) getActivity()).getSupportActionBar();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater,
                           @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    int layoutResourceId = onRequestLayoutResourceId();
    View result = inflater.inflate(layoutResourceId, container, false);
    return result;
  }

  protected Menu getMenu() {
    return menu;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    this.menu = menu;
    this.menu.clear();
    int menuResourceId = onRequestMenuResourceId();
    if (menuResourceId != WITHOUT_MENU) {
      inflater.inflate(menuResourceId, this.menu);
    }
  }

  @MenuRes
  protected int onRequestMenuResourceId() {
    return WITHOUT_MENU;
  }

  @LayoutRes
  abstract int onRequestLayoutResourceId();

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    doViewInjection(view);
    onViewReady();
    onPresenterShouldBeAttached();
    initializeToolbar();
  }

  private void initializeToolbar() {
    if (getToolbar() != null) {
      onInitializeToolbar();
    }
  }

  private void doViewInjection(View view) {
    ButterKnife.bind(this, view);
  }

  public void onPresenterShouldBeAttached() { }

  public void onViewReady() { }

  private ApplicationComponent getApplicationComponent() {
    return ((AndroidApplication) getActivity().getApplication()).getApplicationComponent();
  }
}
