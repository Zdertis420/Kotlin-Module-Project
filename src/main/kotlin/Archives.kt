object Archives {
    private val archives: MutableList<Archive> = mutableListOf()

    fun addArchive(name: String): Boolean {
        for (archive in archives) {
            if (name == archive.name) {
                println("Archive with name $name already exists\n")
                return false
            }
        }

        archives.add(Archive(name))
        println("New archive created successfully\n")
        return true
    }

    fun removeArchiveByName(name: String): Boolean {
        for (archive in archives) {
            if (name == archive.name) {
                archives.removeAt(archives.indexOf(archive))
                println("Archive $name removed successfully")
                return true
            }
        }

        println("There's no archive with name $name\n")
        return false
    }

    fun removeArchiveByIndex(index: Int): Boolean {
        try {
            archives.removeAt(index-1)
            println("Archive $index removed successfully")
            return true
        } catch (e: IndexOutOfBoundsException) {
            println("There's no archive with index $index\n")
            return false
        }
    }

    fun renameArchive(name: String, newName: String): Boolean {
        for (archive in archives) {
            if (name == archive.name) {
                archives[archives.indexOf(archive)].name = newName
                return true
            }
        }

        println("There's no archive with name $name\n")
        return false
    }

    fun copyArchive(name: String): Boolean {
        for (archive in archives) {
            if (name == archive.name) {
                archives.add(Archive("$name copy"))
                println("Archive $name copied successfully\n")
                return true
            }
        }

        println("There's no archive with name $name\n")
        return false
    }

    fun printAllArchives() {
        if (archives.isEmpty()) {
            println("No archives created yet\n")
            return
        }

        println("All created archives:")
        for (i in 0 until archives.size) {
            println("${i + 1}: ${archives[i]}")
        }

        println()
    }

    fun printArchive(name: String) {
        println(Thread.currentThread().stackTrace[2])

        for (archive in archives) {
            if (name == archive.name) {
                println(archives[archives.indexOf(archive)])
                return
            }
        }

        println("There's no archive with name $name\n")
    }

    operator fun get(index: Int): Archive {
        return archives[index]
    }

    operator fun get(name: String): Archive {
        for (archive in archives) {
            if (name == archive.name) {
                return archive
            }
        }

        throw IndexOutOfBoundsException()
    }

    override fun toString(): String {
        return archives.joinToString(", ")
    }
}