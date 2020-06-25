package com.hitesh.todopod.component

import com.hitesh.todopod.activities.AddNoteActivity
import com.hitesh.todopod.activities.EditNoteActivity
import dagger.Component

@Component
interface DateComponent {

    fun inject(addNoteActivity: AddNoteActivity)
    fun inject(editNoteActivity: EditNoteActivity)
}