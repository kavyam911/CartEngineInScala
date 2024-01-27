import scala.util.matching.Regex

object RegexMethods extends App{
//Finds the first occurrence of the regex pattern in the input string.
  val pattern = "[0-9]+".r
  val firstMatchOption = pattern.findFirstIn("abc 123 xyz")
  val firstMatch = firstMatchOption.getOrElse("No match")

  val allMatchesIterator = pattern.findAllIn("1221 addsc 232 vfv")
  val allMatches = allMatchesIterator.mkString(", ")

  //Checks if the beginning of the input string matches the regex pattern
  val prefixMatch = pattern.findPrefixOf("123 abc")
  val prefixMatchResult = prefixMatch.mkString(",")

 // Replaces all occurrences of the regex pattern in the input string with a replacement string.
  val replaced = pattern.replaceAllIn("123 abc 456", "number")

  val parts = pattern.split("123 abc 456")
  val partsResult = parts.mkString(",")
  println(s"firstMatch: $firstMatch, allMatches:$allMatches, prefixMatchResult:$prefixMatchResult, " +
    s"replaced:$replaced, partsResult:$partsResult")


  //unanchored allows the regex pattern to match a substring of text
    val datePattern: Regex = """(\d{4})-(\d{2})-(\d{2})""".r.unanchored
    val text = "The date today is 2024-01-07."
    // Pattern matching to extract date components
    text match {
      case datePattern(year,month,day)=> println(s"Year: $year, Month: $month, Day: $day")
    }

  }
