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
                var success = false
                while (!success) {
                    println("Enter new archive name or esc to cancel")
                    val userAnswer = scanner.nextLine()
                    if (userAnswer.lowercase() == "esc") {
                        println("Process canceled")
                        break
                    }
                    success = archives.addArchive(scanner.nextLine())
                }
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
                    "4. Copy archive\n" +
                    "5. Display notes in archive\n" +
                    "6. Esc"
        )

        val userInput = scanner.nextLine()

        when (userInput) {
            "1" -> {
                var success = false
                while (!success) {
                    println("Enter new archive name or esc to cancel")
                    val userAnswer = scanner.nextLine()
                    if (userAnswer.lowercase() == "esc") {
                        println("Process canceled")
                        break
                    }
                    success = archives.addArchive(scanner.nextLine())
                }
            }

            "2" -> {
                var success = false
                while (!success) {
                    println("Enter name or index of archive you want to remove or esc to cancel")
                    val archiveToRemove = scanner.nextLine()
                    if (archiveToRemove.lowercase() == "esc") {
                        println("Process canceled")
                        break
                    }
                    println(
                        "You sure you want to remove archive $archiveToRemove?\n" +
                                "Y - yes/N - no"
                    )
                    val userAnswer = scanner.nextLine()
                    if (userAnswer == "Y") {
                        success = if (!archiveToRemove.matches(Regex("\\d+"))) {
                            archives.removeArchiveByName(archiveToRemove)
                        } else {
                            archives.removeArchiveByIndex(archiveToRemove.toInt())
                        }
                    } else if (userAnswer == "N") {
                        println("Process canceled")
                        success = true
                    } else {
                        println("Unresolved reference $userAnswer")
                    }
                }
            }

            "3" -> {
                var success = false
                while (!success) {
                    println("Select archive to rename or esc to cancel")
                    val archiveToRename = scanner.nextLine()
                    if (archiveToRename.lowercase() == "esc") {
                        println("Process canceled")
                        break
                    }
                    println(
                        "You sure you want to rename archive $archiveToRename?\n" +
                                "Y - yes/N - no"
                    )
                    when (val userAnswer = scanner.nextLine()) {
                        "Y" -> {
                            println("Enter new name for archive $archiveToRename")
                            success = archives.renameArchive(archiveToRename, scanner.nextLine())
                        }

                        "N" -> {
                            println("Process canceled")
                            success = true
                        }

                        else -> println("Unresolved reference $userAnswer")

                    }
                }
            }

            "4" -> {
                var success = false
                while (!success) {
                    println("Select archive to copy or esc to cancel")
                    val userAnswer = scanner.nextLine()
                    if (userAnswer.lowercase() == "esc") {
                        println("Process canceled")
                        break
                    }
                    success = archives.copyArchive(userAnswer)
                }
            }

            "5" -> {
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

            "6" -> break
        }
    }
}

fun navigateThroughNotes(archive: Archive) {
    while (true) {
        archives.printArchive(archive.name)

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
                    println("Enter name and note or esc to cancel")
                    val name = scanner.nextLine()
                    if (name.lowercase() == "esc") {
                        println("Process canceled")
                        break
                    }
                    val note = scanner.nextLine()
                    success = archive.addNote(name, note)
                }
            }

            "2" -> {
                var success = false
                while (!success) {
                    println("Select note to remove")
                    val noteToRemove = scanner.nextLine()
                    println(
                        "You sure you want to remove note $noteToRemove?\n" +
                                "Y - yes/N - no"
                    )
                    when (val userAnswer = scanner.nextLine()) {
                        "Y" -> success = archive.removeNote(noteToRemove)
                        "N" -> {
                            println("Process canceled")
                            success = true
                        }

                        else -> println("Unresolved reference $userAnswer")
                    }
                }
            }

            "3" -> {
                var success = false
                while (!success) {
                    println("Select note to rename")
                    val noteToRename = scanner.nextLine()
                    println(
                        "You sure you want to rename note $noteToRename?\n" +
                                "Y - yes/N - no"
                    )

                    when (val userAnswer = scanner.nextLine()) {
                        "Y" -> success = archive.renameNote(noteToRename)
                        "N" -> {
                            println("Process canceled")
                            success = true
                        }

                        else -> println("Unresolved reference $userAnswer")
                    }
                }
            }

            "4" -> {
                var success = false
                while (!success) {
                    println("Select note to copy")
                    success = archive.copyNote(scanner.nextLine())
                }
            }

            "5" -> {
                var success = false
                while (!success) {
                    println("Select note to edit")
                    val name = scanner.nextLine()
                    val note = scanner.nextLine()
                    success = archive.editNote(name, note)
                }
            }

            "6" -> archive.printAllNotes()
            "7" -> {
                println("Select note to display")
                archive.printNote(scanner.nextLine())
            }

            "8" -> break
        }
    }
}

