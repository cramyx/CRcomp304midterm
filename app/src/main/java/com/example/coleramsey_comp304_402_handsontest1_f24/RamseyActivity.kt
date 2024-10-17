package com.example.coleramsey_comp304_402_handsontest1_f24
// COLE RAMSEY 301333287


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coleramsey_comp304_402_handsontest1_f24.ui.theme.ColeRamsey_COMP304_402_HandsOnTest1_F24Theme
import kotlinx.coroutines.launch

class RamseyActivity : ComponentActivity() {
    private val contactsViewModel: ContactsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColeRamsey_COMP304_402_HandsOnTest1_F24Theme {
                Scaffold { innerPadding ->
                    AddContactScreen(
                        contactsViewModel = contactsViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactScreen(
    contactsViewModel: ContactsViewModel,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isFriend by remember { mutableStateOf(false) }
    var isFamily by remember { mutableStateOf(false) }
    var isWork by remember { mutableStateOf(false) }

    val contactList by contactsViewModel.contacts.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text("Contact Type", color = MaterialTheme.colorScheme.onBackground)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isFriend,
                onCheckedChange = { isFriend = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.onBackground,
                    checkmarkColor = MaterialTheme.colorScheme.onPrimary
                )
            )
            Text("Friend", color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.width(16.dp))
            Checkbox(
                checked = isFamily,
                onCheckedChange = { isFamily = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.onBackground,
                    checkmarkColor = MaterialTheme.colorScheme.onPrimary
                )
            )
            Text("Family", color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.width(16.dp))
            Checkbox(
                checked = isWork,
                onCheckedChange = { isWork = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.onBackground,
                    checkmarkColor = MaterialTheme.colorScheme.onPrimary
                )
            )
            Text("Work", color = MaterialTheme.colorScheme.onBackground)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (name.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty()) {
                    val newContact = Contact(name, phone, email, isFriend, isFamily, isWork)
                    contactsViewModel.addContact(newContact)

                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Contact Added: $name")
                    }

                    name = ""
                    phone = ""
                    email = ""
                    isFriend = false
                    isFamily = false
                    isWork = false
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Add Contact")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(contactList) { contact ->
                ContactItem(contact = contact)
            }
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${contact.name}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Phone: ${contact.phone}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Email: ${contact.email}", style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "Type: " +
                        (if (contact.isFriend) "Friend " else "") +
                        (if (contact.isFamily) "Family " else "") +
                        (if (contact.isWork) "Work" else ""),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddContactScreenPreview() {
    val mockContactsViewModel = object : ContactsViewModel() {
        init {
            addContact(Contact("test", "123-456-7890", "testing@tester.com", true, false, false))
        }
    }

    ColeRamsey_COMP304_402_HandsOnTest1_F24Theme {
        AddContactScreen(contactsViewModel = mockContactsViewModel)
    }
}