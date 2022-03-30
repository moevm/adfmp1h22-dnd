package com.dwards.a5edpockethelper.unitTests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dwards.a5edpockethelper.adapters.CharacterListAdapter
import com.dwards.a5edpockethelper.dialogs.CharacterListDialog
import com.dwards.a5edpockethelper.model.Character
import io.mockk.MockKAnnotations
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterListAdapterTest {

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    private lateinit var dialog: CharacterListDialog

    @Test
    fun itemsCount() {
        val count = 10
        dialog = mockk()

        assert(::dialog.isInitialized)

        val characters = mutableListOf<Character>()
        for (i in 1..count) {
            characters.add(Character())
        }
        val characterAdapter = CharacterListAdapter(characters, dialog)

        assertEquals(characterAdapter.itemCount, count)
    }

}