import org.jsoup.Jsoup
import java.io.File
import java.lang.StringBuilder

fun main(args : Array<String>) {

    val years = arrayOf("2018-2019", "2017-2018", "2016-2017", "2015-2016")

    val headers = arrayListOf(
        "R",
        "S",
        "G",
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

    for (year in years) {

        val doc = Jsoup.connect("https://www.mondofantacalcio.com/statistiche-fantacalcio/$year/").get()
        println("Retrieving ${doc.title()} ..." )

        val csvToSave = StringBuilder()

        csvToSave.append(headers.joinToString(";"))
        csvToSave.append("\n")

        val players = doc.select("table tbody tr")
        for (player in players) {
            for (i in 0 until player.children().size) {
                if (i != 14) {
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

    println("DONE")
}