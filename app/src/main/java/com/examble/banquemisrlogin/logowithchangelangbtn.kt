package com.examble.banquemisrlogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun Logo(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isArabic = LocaleHelper.getCurrentLanguage() == "ar"
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(R.drawable.bm_icon),
            contentDescription = "logo banque misr"
        )
        TextButton(
            onClick = {
                val newLang = if (isArabic) "ar" else "en"
                LocaleHelper.saveLanguage(context, newLang)
                LocaleHelper.setLocale(newLang)
            }
        ) {
            Text(
                text = if (isArabic) "العربية" else "En",
                color = Color.Red,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.cairo_semibold))
            )
        }
    }

}

@Preview(showSystemUi = true, showBackground = false)
@Composable
private fun LogoPreview() {
    Logo()
}