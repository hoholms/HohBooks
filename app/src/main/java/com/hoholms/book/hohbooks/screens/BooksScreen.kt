package com.hoholms.book.hohbooks.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hoholms.book.hohbooks.R
import com.hoholms.book.hohbooks.model.Book
import com.hoholms.book.hohbooks.model.BookCard
import com.hoholms.book.hohbooks.ui.theme.HohBooksTheme
import com.hoholms.book.hohbooks.viewmodel.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksScreen(navController: NavController, themeViewModel: ThemeViewModel = viewModel()) {
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var selectedBook by remember { mutableStateOf(Book()) }

    HohBooksTheme(themeSetting = themeViewModel.themeSetting.value) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    stringResource(R.string.books_for_adults_title),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 20.dp)
                )

                if (isSheetOpen) {
//                    AlertDialogExample(
//                        onDismissRequest = { showBottomSheet = false },
//                        onConfirmation = { showBottomSheet = false },
//                        dialogTitle = "Congratulations",
//                        dialogText = "You pressed this button!",
//                        icon = Icons.Filled.Celebration
//                    )

                    ModalBottomSheet(
                        sheetState = sheetState,
                        onDismissRequest = { isSheetOpen = false }
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .height(500.dp)
                        ) {
                            Text(
                                text = selectedBook.title,
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = selectedBook.author,
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = selectedBook.description,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(bottom = 24.dp)
                            )
                            Share(text = selectedBook.toString(), context = LocalContext.current)
                        }
                    }
                }

                val books = listOf(
                    Book(
                        title = "The Alchemist",
                        author = "Paulo Coelho",
                        description = "A mystical story about an Andalusian shepherd boy named Santiago who travels from his homeland in Spain to the Egyptian pyramids in search of a worldly treasure."
                    ), Book(
                        title = "To Kill a Mockingbird",
                        author = "Harper Lee",
                        description = "An American novel and classic work of Southern Gothic literature that was first published in 1960. It was Harper Lee's first and only published novel."
                    ), Book(
                        title = "Pride and Prejudice",
                        author = "Jane Austen",
                        description = "A romantic novel of manners by Jane Austen, first published in 1813 under the title 'First Impressions'. The story follows the main character Elizabeth Bennet as she deals with issues of manners, upbringing, morality, education and marriage."
                    ), Book(
                        title = "One Hundred Years of Solitude",
                        author = "Gabriel Garcia Marquez",
                        description = "The novel tells the multi-generational story of the Buendía family's decadence under the tropical sun of Macondo,"
                    ), Book(
                        title = "Jane Eyre",
                        author = "Charlotte Bronte",
                        description = "An English novel by Charlotte Brontë, published on 16 October 1847 under the pseudonym 'Currer Bell'. The first person narrative follows the experiences of its heroine, Jane Eyre, from her childhood through to adulthood."
                    ), Book(
                        title = "The Great Gatsby",
                        author = "F. Scott Fitzgerald",
                        description = "An American novel by F. Scott Fitzgerald published in 1925, set in the summer of 1922. The story is narrated by Nick Carraway."
                    ), Book(
                        title = "Brave New World",
                        author = "Aldous Huxley",
                        description = "A dystopian novel written in 1931 and published in 1932, set in a futuristic society where people are produced in an industrial environment."
                    ), Book(
                        title = "The Catcher in the Rye",
                        author = "J.D. Salinger",
                        description = "A first-person narrative told by Holden Caulfield, who is an antihero seeking moral clarity and understanding between adolescence and adulthood as he faces the harsh realities of the world."
                    ), Book(
                        title = "Beloved",
                        author = "Toni Morrison",
                        description = "A novel by American author Toni Morrison. It was published in 1987, for which she won the Pulitzer Prize and National Book Critics Circle Award."
                    ), Book(
                        title = "The Hobbit",
                        author = "J.R.R Tolkien",
                        description = "An epic fantasy novel by J. R. R. Tolkien that was published in September 1937 to unprecedented critical acclaim."
                    )
                )
                books.forEach { book: Book ->
                    BookCard(
                        book = book,
                        onClick1 = { isSheetOpen = true; selectedBook = book })
                }

            }
        }
    }
}

@Composable
fun Share(text: String, context: Context) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)


    FilledTonalButton(onClick = {
        startActivity(context, shareIntent, null)
    }) {
        Icon(imageVector = Icons.Default.Share, contentDescription = null)
        Text("Share", modifier = Modifier.padding(start = 8.dp))
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