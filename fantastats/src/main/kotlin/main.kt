import org.jsoup.Jsoup
import java.io.File
import java.lang.StringBuilder

fun main(args: Array<String>) {

    stats()
    quotations()

    println("DONE")
}

private fun quotations() {
    val years = arrayOf("2019-2020")
    val headers = arrayListOf(
        "R",
        "S",
        "Gi",
        "Q"
    )
    val url = "https://www.mondofantacalcio.com/quotazioni-fantacalcio/%s/0-giornata/"
    parse(years, url, headers)
}

private fun stats() {
    val years = arrayOf("2018-2019", "2017-2018", "2016-2017", "2015-2016")
    val headers = arrayListOf(
        "R",
        "S",
        "Gi",
        "IAM",
        "P",
        "MV",
        "FMV",
        "G",
        "As",
        "Am",
        "Es",
        "Au",
        "Gs",
        "RS",
        "RP"
    )
    val url = "https://www.mondofantacalcio.com/statistiche-fantacalcio/%s/"
    parse(years, url, headers)
}

private fun parse(
    years: Array<String>,
    url: String,
    headers: ArrayList<String>
) {
    for (year in years) {

        val doc = Jsoup.connect(String.format(url, year)).get()
        println("Retrieving ${doc.title()} ...")

        val csvToSave = StringBuilder()

        csvToSave.append(headers.joinToString(";"))
        csvToSave.append("\n")

        val players = doc.select("table tbody tr")
        for (player in players) {
            for (i in 0 until player.children().size) {
                if (i != headers.size - 1) {
                    csvToSave.append("${player.child(i).text()};")
                } else {
                    csvToSave.append("${player.child(i).text()}\n")
                }
            }
        }

        File("$year.csv").printWriter().use { out ->
            out.print(csvToSave.toString())
        }
    }
}