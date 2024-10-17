package com.example.coleramsey_comp304_402_handsontest1_f24

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class ContactsViewModel : ViewModel() {
    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> = _contacts

    init {
        // hardcoded contacts
        _contacts.value = listOf(
            Contact("Amari Cooper", "416-880-9991", "amari@WR.com", isFriend = true, isFamily = false, isWork = false),
            Contact("Mo Salah", "319-755-8534", "mo@liverpool.co", isFriend = false, isFamily = true, isWork = false),
            Contact("John Smith", "999-123-1233", "jsmith@gmail.com", isFriend = false, isFamily = false, isWork = true)
        )
    }

    fun addContact(contact: Contact) {
        _contacts.value = _contacts.value + contact
    }
}