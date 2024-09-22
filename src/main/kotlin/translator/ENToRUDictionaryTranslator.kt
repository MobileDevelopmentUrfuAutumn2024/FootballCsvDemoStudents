package translator

class ENToRUDictionaryTranslator : ITranslator {

    val enToRuDictionary: Map<String, String> = mapOf(
        "DEFENDER" to "ЗАЩИТНИК",
        "MIDFIELD" to "ПОЛУЗАЩИТНИК",
        "FORWARD" to "НАПАДАЮЩИЙ",
        "GOALKEEPER" to "ВРАТАРЬ",
    )

    override fun translate(text: String): String? {
        return enToRuDictionary[text]
    }
}