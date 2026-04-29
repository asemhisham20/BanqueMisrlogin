package com.examble.banquemisrlogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.examble.banquemisrlogin.data.DataSource
import com.examble.banquemisrlogin.model.ServiceItem
import com.examble.banquemisrlogin.ui.theme.BanqueMisrLoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val savedLang = LocaleHelper.getSavedLanguage(this)
        LocaleHelper.setLocale(savedLang)
        enableEdgeToEdge()
        setContent {
            val isArabic = LocaleHelper.getCurrentLanguage() == "ar"
            val layoutDirection = if (isArabic) LayoutDirection.Ltr else LayoutDirection.Rtl

            CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                BanqueMisrLoginTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        SignIn(
                            modifier = Modifier
                                .padding(innerPadding)
                                .padding(horizontal = 8.dp)
                                .padding(top = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SignIn(modifier: Modifier = Modifier) {
    val usernameState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()
    val isLoginEnabled = usernameState.text.isNotEmpty() &&
            passwordState.text.isNotEmpty()


          Column(modifier = modifier.fillMaxSize()) {
            Logo()
            TextFieldLabel(
                keyboardType = KeyboardType.Email,
                stringResource(R.string.username),
                modifier = Modifier.fillMaxWidth(),
                state = usernameState
            )
            TextFieldLabel(
                keyboardType = KeyboardType.Password,
                text = stringResource(R.string.password),
                modifier = Modifier.fillMaxWidth(),
                state = passwordState
            )
            Text(
                text = stringResource(R.string.forgot_email_password),
                fontFamily = FontFamily(Font(R.font.cairo_medium)),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .padding(top = 16.dp)

            )
            SignInBtn(
                {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                isEnabled = isLoginEnabled

            )
            Text(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(start = 8.dp),
                text = buildAnnotatedString {
                    append(stringResource(R.string.needhelp))
                    withLink(
                        LinkAnnotation.Url(
                            url = "",
                            styles = TextLinkStyles(
                                style = SpanStyle(
                                    color = Color.Red,
                                    fontFamily = FontFamily(Font(R.font.cairo_regular)),
                                    textDecoration = TextDecoration.Underline
                                )

                            ),


                            )
                    ) {
                        append(stringResource(R.string.contact_us))
                    }


                }

            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 24.dp),

                thickness = 1.dp,
                color = Color.Gray
            )
            AllServices()
        }
    }


@Composable
fun ServieListItem(serviceItem: ServiceItem, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(83.dp),
    ) {
        Image(
            painter = painterResource(serviceItem.image),
            contentDescription = serviceItem.name,
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
        )
        Text(
            text = serviceItem.name,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.cairo_regular)),
            fontSize = 18.sp,

            )
    }
}

@Composable
fun AllServices() {
    val services = DataSource().getServicesData()
    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 24.dp)
        .padding(horizontal = 16.dp)) {
        items(services) {
            ServieListItem(it)
        }

    }
}

@Composable
fun SignInBtn(onClick: () -> Unit, modifier: Modifier = Modifier,
              isEnabled: Boolean) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFC0392B),
            contentColor = Color.White,
            disabledContainerColor = Color(0xFFE8A0A0), // disabled: light pink
            disabledContentColor = Color.White
        ),
        enabled = isEnabled
    ) {
        Text(
            text = "Login",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.cairo_semibold))
        )
    }

}

@Preview(showSystemUi = true, showBackground = false, device = "spec:width=411dp,height=891dp")
@Composable
private fun SignInPreview() {
    SignIn()
}

@Composable
fun TextFieldLabel(
    keyboardType: KeyboardType,
    text: String,
    modifier: Modifier = Modifier,
    state: TextFieldState
) {

    OutlinedTextField(

        state = state,
        label = { Text(text = text) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        lineLimits = TextFieldLineLimits.SingleLine,
        modifier = modifier
            .padding(horizontal = 8.dp)
            .padding(top = 16.dp),
    )

}

@Preview(showSystemUi = true, showBackground = false)
@Composable
private fun TextlabelPreview() {
    TextFieldLabel(KeyboardType.Email, "Username",state= TextFieldState())
}

