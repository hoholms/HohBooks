package com.hoholms.book.hohbooks.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hoholms.book.hohbooks.R
import com.hoholms.book.hohbooks.components.BookListItem
import com.hoholms.book.hohbooks.model.Book
import com.hoholms.book.hohbooks.ui.theme.HohBooksTheme
import com.hoholms.book.hohbooks.viewmodel.ThemeViewModel

@Composable
fun ForKidsScreen(navController: NavController, themeViewModel: ThemeViewModel = viewModel()) {
    var isAlertOpen by rememberSaveable { mutableStateOf(false) }
    var selectedBook by remember { mutableStateOf(Book()) }

    val books = listOf(
        Book(
            title = "Charlotte's Web",
            author = "E.B. White",
            description = "A tender story of friendship, love, life, and death that takes place in a barnyard. Charlotte, a savvy spider, hatches a plan to save Wilbur, a young pig, from the butcher's block."
        ),
        Book(
            title = "The Chronicles of Narnia: The Lion, the Witch and the Wardrobe",
            author = "C.S. Lewis",
            description = "Four siblings enter the magical world of Narnia through a wardrobe and find themselves caught up in a struggle between good and evil."
        ),
        Book(
            title = "Matilda",
            author = "Roald Dahl",
            description = "The story of a brilliant and sensitive girl who uses her talents to deal with her uncaring parents and a tyrannical school headmistress."
        ),
        Book(
            title = "Harry Potter and the Sorcerer's Stone",
            author = "J.K. Rowling",
            description = "The first book in the Harry Potter series, introducing Harry Potter, a young wizard, who discovers his magical heritage and attends Hogwarts School of Witchcraft and Wizardry.",
            cover = R.drawable.harry_potter_and_the_sorcerers_stone
        ),
        Book(
            title = "The Tale of Peter Rabbit",
            author = "Beatrix Potter",
            description = "A mischievous and disobedient young rabbit is chased around the garden of Mr. McGregor after he gets into the garden to eat vegetables."
        ),
        Book(
            title = "The Secret Garden",
            author = "Frances Hodgson Burnett",
            description = "An orphaned girl discovers a hidden, neglected garden and, with the help of a few friends, begins to restore it, finding healing and happiness in the process."
        ),
        Book(
            title = "Anne of Green Gables",
            author = "L.M. Montgomery",
            description = "The story follows Anne Shirley, an imaginative, talkative, red-haired orphan who is mistakenly sent to live with a lonely, middle-aged brother and sister on Prince Edward Island."
        ),
        Book(
            title = "Where the Wild Things Are",
            author = "Maurice Sendak",
            description = "A classic tale of a young boy named Max who goes on a wild adventure to an island inhabited by strange creatures and becomes their king."
        ),
        Book(
            title = "A Wrinkle in Time",
            author = "Madeleine L'Engle",
            description = "A science fantasy story about three children's quest through space and time to find their missing father and fight against a cosmic evil."
        ),
        Book(
            title = "Percy Jackson & the Olympians: The Lightning Thief",
            author = "Rick Riordan",
            description = "Percy Jackson discovers he is a demigod, the son of Poseidon, and is accused of stealing Zeus' lightning bolt. He embarks on a journey across the United States to find it and restore order to Olympus."
        )
    )

    HohBooksTheme(themeSetting = themeViewModel.themeSetting.value) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (isAlertOpen) {
                    AlertDialogExample(
                        onDismissRequest = { isAlertOpen = false },
                        onConfirmation = { isAlertOpen = false },
                        dialogTitle = selectedBook.title,
                        dialogText = selectedBook.description,
                        icon = Icons.Filled.Book
                    )
                }

                Text(
                    stringResource(R.string.books_for_kids_title),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
                LazyColumn(Modifier.fillMaxWidth()) {

                    items(books) { book: Book ->
                        BookListItem(
                            book
                        ) {
                            OutlinedButton(onClick = { selectedBook = book; isAlertOpen = true }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = null
                                )
                            }
                        }
                        HorizontalDivider()
                    }
                }
            }

//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .verticalScroll(rememberScrollState())
//                    .padding(15.dp),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                Text(
//                    stringResource(R.string.books_for_kids_title),
//                    style = MaterialTheme.typography.titleLarge,
//                    modifier = Modifier.padding(vertical = 20.dp)
//                )
//
//                if (isAlertOpen) {
//                    AlertDialogExample(
//                        onDismissRequest = { isAlertOpen = false },
//                        onConfirmation = { isAlertOpen = false },
//                        dialogTitle = selectedBook.title,
//                        dialogText = selectedBook.description,
//                        icon = Icons.Filled.Book
//                    )
//                }
//
//                books.forEach { book: Book ->
//                    BookListItem(book
//                    ) {
//                        Button(onClick = { selectedBook = book; isAlertOpen = true }) {
//                            Icon(
//                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
//                                contentDescription = null
//                            )
//                        }
//                    }
//                    HorizontalDivider()
//                }
//
//            }
        }
    }
}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(icon = {
        Icon(icon, contentDescription = "Example Icon")
    }, title = {
        Text(text = dialogTitle)
    }, text = {
        Text(text = dialogText)
    }, onDismissRequest = {
        onDismissRequest()
    }, confirmButton = {
        TextButton(onClick = {
            onConfirmation()
        }) {
            Text("Confirm")
        }
    }, dismissButton = {
        TextButton(onClick = {
            onDismissRequest()
        }) {
            Text("Dismiss")
        }
    })
}