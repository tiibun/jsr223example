import jsr223example.*

val name = bindings["name"] as String
@Suppress("UNCHECKED_CAST")
val users = bindings["users"] as List<User>
val condition = true

// ここからテンプレート
"""
Hello! ${name}

${if (condition) {
"""
Hello!
"""
} else ""}

${users.map {
"""
Hello! ${it.name}
"""}.joinToString("\n")}


""" // ここまで
