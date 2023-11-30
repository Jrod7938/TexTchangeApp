/*
 * BSD 3-Clause License
 *
 * Copyright (c) 2023, Jrod7938, Khang-ALe, jesma14, Holesum
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.jrod7938.textchangeapp.screens.saved

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.jrod7938.textchangeapp.navigation.AppScreens

@Composable
fun SavedBooksScreen(
    navController: NavHostController,
    viewModel: SavedBooksScreenViewModel = viewModel()
) {
    val loading by viewModel.loading.observeAsState(initial = false)
    val errorMessage by viewModel.errorMessage.collectAsState(initial = null)
    val savedBooks by viewModel.savedBooks.observeAsState(initial = listOf())
    val reloadInterface by viewModel.reloadInterface.collectAsState(initial = false)

    Column(
        modifier = Modifier.fillMaxSize().padding(top = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if (loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            Text(
                text = "Saved Books",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
            )
            if (!errorMessage.isNullOrEmpty()) {
                Text(
                    text = errorMessage.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp).fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                if(reloadInterface) {
                    Button(
                        onClick = {
                            navController
                                .navigate(
                                    "${AppScreens.LoadingScreen.name}/${AppScreens.SavedBooksScreen.name}"
                                )
                        },
                        content = {
                            Text(
                                text = "Reload Page",
                                fontSize = 14.sp
                            )
                        }
                    )
                }
            }
            if(savedBooks.isEmpty()) {
                Text(
                    text = "This page is empty. Add some books to your saved list to get started.",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(15.dp),
                    fontWeight = FontWeight.Bold,

                    )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                items(savedBooks.size) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onBackground
                        ),
                        // border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(150.dp)
                                    .padding(end = 16.dp),
                                painter = rememberAsyncImagePainter(
                                    model = savedBooks[it].imageURL
                                ),
                                contentDescription = "Image of ${savedBooks[it].title} book"
                            )
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = buildAnnotatedString {
                                         withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                                             append("Title: ")
                                         }
                                        append(savedBooks[it].title)
                                    },
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontSize = 13.sp
                                )
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                                            append("Author: ")
                                        }
                                        append(savedBooks[it].author)
                                    },
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontSize = 13.sp
                                )
                                Text(
                                    text =buildAnnotatedString {
                                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                                            append("Price: ")
                                        }
                                        append(savedBooks[it].price.toString())
                                    },
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontSize = 13.sp
                                )
                                Button(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = { navController.navigate("${AppScreens.BookInfoScreen.name}/${savedBooks[it].bookID}") }
                                ) {
                                    Text(text = "View Info")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
