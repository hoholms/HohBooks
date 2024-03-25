package com.hoholms.book.hohbooks.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hoholms.book.hohbooks.R
import com.hoholms.book.hohbooks.model.Book

@Composable
fun BookListItem(
    book: Book,
    trailingButton: @Composable (() -> Unit)? = null
) {
    ListItem(
        headlineContent = { Text(book.title) },
        supportingContent = {
            Text(book.author)
        },
        leadingContent = {
            Image(
                modifier = Modifier
                    .size(69.dp)
                    .clip(RoundedCornerShape(12.dp)),
                painter = painterResource(id = book.cover),
                contentDescription = book.title,
                contentScale = ContentScale.Crop,
            )
        },
        trailingContent = trailingButton
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewBookListItem() {
    val book = Book(
        cover = R.drawable.harry_potter_and_the_sorcerers_stone,
        title = "Sample Book Title",
        author = "John Doe",
        description = "A sample book description for preview purposes. This is a long description that provides more information about the book."
    )

    BookListItem(book = book)
}
