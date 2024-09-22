package parser

import model.Player
import model.Team
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.lang.RuntimeException
import java.math.BigDecimal
import java.util.*
import kotlin.collections.HashMap
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.KType
import kotlin.reflect.jvm.jvmErasure

fun main(args: Array<String>) {
    val parser: CsvParser = CsvParser()
    val players = parser.parse(
        FileInputStream(File("D:\\projects\\FootballCsvDemoStudents\\src\\main\\resources\\fakePlayers.csv")),
        Player::class
    )

    for (player in players) {
        player.team.players.add(player)
    }


    print(players)
}
class CsvParser {

    val separator = ";"

    val teams: HashMap<String, Team> = HashMap();

    public fun <T : Any> parse(inputStream: InputStream, entityType: KClass<T>): List<T> {
        val scanner = Scanner(inputStream)

        val rows = mutableListOf<List<String>>()
        while (scanner.hasNext()) {
            val line = scanner.nextLine();
            rows.add(parseLine(line));
        }

        val names = rows.removeFirst().map { name -> name.lowercase() }
        val constructor = chooseConstructor(names, entityType) ?: throw RuntimeException(Messages.NO_SUITABLE_CONSTRUCTOR.name)

        return rows.map { row -> constructor.callBy(buildConstructorParametersMap(row, names, constructor)) as T }
    }

    private fun parseLine(line: String): List<String> {
        return line.split(separator)
    }

    private fun buildConstructorParametersMap(values: List<String>,
                                              valuesNames :List<String>,
                                              constructor: KFunction<*>): HashMap<KParameter, Any?> {
        val valuesNames = normalizeNames(valuesNames)
        val map = HashMap<KParameter, Any?>()


        for (parameter in constructor.parameters) {
            val paramName = parameter.name!!.lowercase()
            if (valuesNames.contains(paramName)) {
                val paramType = parameter.type
                val valueIndex = valuesNames.indexOf(paramName)
                val parsed = convert(values[valueIndex], paramType);
                map[parameter] = parsed

            } else {
                map[parameter] = null
            }
        }

        return map

    }

    private fun chooseConstructor(valuesNames :List<String>, entityType: KClass<*>): KFunction<*>? {
        val valuesNames = normalizeNames(valuesNames)
        var maxKAcceptable = 0
        var maxAcceptable: KFunction<*>? = null
        for (constructor in entityType.constructors) {
            var isAcceptable = true;
            var kAcceptable = 0;
            for (parameter in constructor.parameters) {
                if (!valuesNames.contains(parameter.name!!.lowercase())
                    && !parameter.type.isMarkedNullable) {
                    isAcceptable = false
                    break
                } else {
                    kAcceptable++
                }
            }
            if (isAcceptable && kAcceptable > maxKAcceptable) {
                maxAcceptable = constructor
                maxKAcceptable = kAcceptable
            }
        }
        return maxAcceptable
    }


    private fun normalizeNames(names :List<String>): List<String> {
        return names.map { name -> name.lowercase()
            .replace(" ", "")
            .replace(".", "")
            .replace("_","")
            .replace("-", "")
        }
    }

    private fun convert(str: String, type: KType): Any {
        return when (type.jvmErasure) {
            Team::class -> {
                if (!teams.containsKey(str)) {
                    teams.put(str, Team(str, mutableListOf()))
                }
                teams[str]!!
            }
            Int::class -> str.toInt()
            Long::class -> str.toLong()
            Float::class -> str.toFloat()
            Double::class -> str.toDouble()
            BigDecimal::class -> str.toBigDecimal()
            String::class -> str
            else -> throw IllegalArgumentException("'$str' cannot be converted to $type")
        }
    }
}