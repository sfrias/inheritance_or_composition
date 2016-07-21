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
package com.txusballesteros.navigation.command;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.txusballesteros.domain.model.Note;
import com.txusballesteros.view.activity.NoteDetailActivity;

public class NoteDetailNavigationCommand extends NavigationCommand {
  private final Note note;

  public NoteDetailNavigationCommand(Context context, Note note) {
    super(context);
    this.note = note;
  }

  @NonNull @Override
  Intent onRequestIntent(Context context) {
    final Intent intent = new Intent(context, NoteDetailActivity.class);
    intent.putExtra(NoteDetailActivity.EXTRA_NOTE_TYPE, note.getType().ordinal());
    intent.putExtra(NoteDetailActivity.EXTRA_NOTE_ID, note.getId());
    return intent;
  }
}
