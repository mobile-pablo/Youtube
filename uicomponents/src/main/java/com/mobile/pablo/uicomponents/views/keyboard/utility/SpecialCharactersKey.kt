package com.mobile.pablo.uicomponents.views.keyboard.utility

import com.mobile.pablo.uicomponents.views.keyboard.data.model.Key

/**
 *  Copied from https://github.com/UmairKhalid786/ComposeTvKeyboard
 */
sealed class SpecialCharactersKey(
    override val text: String,
    override val span: Int = 1
) : Key {

    data object Dot : SpecialCharactersKey(".")

    data object Underscore : SpecialCharactersKey("_")

    data object Dash : SpecialCharactersKey("-")

    data object Dollar : SpecialCharactersKey("$")

    data object Plus : SpecialCharactersKey("+")

    data object And : SpecialCharactersKey("&")

    data object ParenthesesBracketsLeft : SpecialCharactersKey("(")

    data object ParenthesesBracketsRight : SpecialCharactersKey(")")

    data object BackSlash : SpecialCharactersKey("/")

    data object Asterisk : SpecialCharactersKey("*")

    data object Quotes : SpecialCharactersKey("\"")

    data object SingleQuotes : SpecialCharactersKey("\'")

    data object Colon : SpecialCharactersKey(":")

    data object Semicolon : SpecialCharactersKey(";")

    data object Exclamation : SpecialCharactersKey("!")

    data object Question : SpecialCharactersKey("?")

    data object Percent : SpecialCharactersKey("%")

    data object Hash : SpecialCharactersKey("#")

    data object Ampersat : SpecialCharactersKey("@")

    data object Comma : SpecialCharactersKey(",")

    data object Tide : SpecialCharactersKey("~")

    data object Grave : SpecialCharactersKey("`")

    data object Pipe : SpecialCharactersKey("|")

    data object Bullet : SpecialCharactersKey("∙")

    data object Root : SpecialCharactersKey("√")

    data object PI : SpecialCharactersKey("π")

    data object Division : SpecialCharactersKey("÷")

    data object Multiple : SpecialCharactersKey("×")

    data object Paragraph : SpecialCharactersKey("¶")

    data object Triangle : SpecialCharactersKey("△")

    data object Pound : SpecialCharactersKey("£")

    data object Cent : SpecialCharactersKey("¢")

    data object Euro : SpecialCharactersKey("€")

    data object Yen : SpecialCharactersKey("¥")

    data object Caret : SpecialCharactersKey("^")

    data object Degree : SpecialCharactersKey("°")

    data object Equal : SpecialCharactersKey("=")

    data object CurlyBracketLeft : SpecialCharactersKey("{")

    data object CurlyBracketRight : SpecialCharactersKey("}")

    data object Backlash : SpecialCharactersKey("\\")

    data object CopyRight : SpecialCharactersKey("©")

    data object RegisterTrademark : SpecialCharactersKey("®")

    data object CheckMark : SpecialCharactersKey("✓")

    data object BoxBracketLeft : SpecialCharactersKey("[")

    data object BoxBracketRight : SpecialCharactersKey("]")

    data object ArrowLeft : SpecialCharactersKey("<")

    data object ArrowRight : SpecialCharactersKey(">")
}
