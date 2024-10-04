import java.util.Scanner

val scanner = Scanner(System.`in`)
val archives = Archives

fun main() {
    while (true) {
        println(
            "Select one option:\n" +
                    "1. Display all archives\n" +
                    "2. Display one archive\n" +
                    "3. Add new archive\n" +
                    "4. Esc"
        )

        val userInput = scanner.nextLine()

        when (userInput) {
            "1" -> navigateThroughArchives()
            "2" -> {
                var success = false
                while (!success) {
                    println("Select archive to display")
                    try {
                        navigateThroughNotes(archives[scanner.nextLine()])
                        success = true
                    } catch (e: IndexOutOfBoundsException) {
                        println("Archive does not exist")
                    }
                }
            }
            "3" -> {
                println("Enter new archive name")
                archives.addArchive(scanner.nextLine())
            }
            "4" -> break
        }
    }
}

fun navigateThroughArchives() {
    while (true) {
        archives.printAllArchives()

        println(
            "Select one option:\n" +
                    "1. Add new archive\n" +
                    "2. Remove archive\n" +
                    "3. Rename archive\n" +
                    "4. Display notes in archive\n" +
                    "5. Esc"
        )

        val userInput = scanner.nextLine()

        when (userInput) {
            "1" -> {
                var success = false
                while (!success){
                    println("Enter name of new archive")
                    success = archives.addArchive(scanner.nextLine())

                }
            }

            "2" -> {
                var success = false
                while (!success) {
                    println("Enter name or index of archive you want to remove")
                    val archiveToRemove = scanner.nextLine()
                    println(
                        "You sure you want to remove archive $archiveToRemove?\n" +
                                "Y - yes/N - no"
                    )
                    val answer = scanner.nextLine()
                    if (answer == "Y") {
                        success = if (!archiveToRemove.matches(Regex("\\d+"))) {
                            archives.removeArchiveByName(archiveToRemove)
                        } else {
                            archives.removeArchiveByIndex(archiveToRemove.toInt())
                        }
                    } else if (answer == "N") {
                        success = true
                    }
                }
            }

            "3" -> {
                var success = false
                while (!success) {
                    println("Select archive to rename")
                    val archiveToRename = scanner.nextLine()
                    println("You sure you want to rename archive $archiveToRename?")
                    if (scanner.nextLine() == "Y") {
                        println("Enter new name for archive $archiveToRename")
                        success = archives.renameArchive(archiveToRename, scanner.nextLine())
                    }
                }
            }

            "4" -> {
                var success = false
                while (!success) {
                    println("Select archive to display")
                    try {
                        navigateThroughNotes(archives[scanner.nextLine()])
                        success = true
                    } catch (e: IndexOutOfBoundsException) {
                        println("Archive does not exist")
                    }
                }
            }

            "5" -> break
        }
    }
}

fun navigateThroughNotes(archive: Archive) {
    while (true) {
        archives.printArchive(archive.toString())
//        println(archives.toString())

        println(
            "Select one option:\n" +
                    "1. Add note\n" +
                    "2. Remove note\n" +
                    "3. Rename note\n" +
                    "4. Copy note\n" +
                    "5. Edit note\n" +
                    "6. Display all notes\n" +
                    "7. Display one note\n" +
                    "8. Esc"
        )

        val userInput = scanner.nextLine()

        when (userInput) {
            "1" -> {
                var success = false
                while (!success) {
                    println("Enter name and note")
                    success = archive.addNote(scanner.nextLine(), scanner.nextLine())
                }
            }
            "2" -> {

            }
            "6" -> break
        }
    }
}

