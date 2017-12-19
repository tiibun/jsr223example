package jsr223example

import javax.script.Compilable
import javax.script.ScriptEngineManager

class Main {
    fun example1() {
        val engine = ScriptEngineManager().getEngineByExtension("kts")
//        val engine = ScriptEngineManager().getEngineByName("kotlin")
        engine.eval("println(\"Hello Kotlin\")")
        val route2 = engine.eval("kotlin.math.sqrt(2.0)")
        println(route2)
    }

    fun example2() {
        val engine = ScriptEngineManager().getEngineByExtension("kts")
        val bindings = engine.createBindings()
        bindings["x"] = "Hello!"
        engine.eval("""
            val xxx = bindings["x"]
            println(xxx)
            """, bindings)
        engine.eval("""
            val xxx = bindings["x"] as String
            println(xxx.substring(1))
            """, bindings)
    }

    fun example3() {
        val engine = ScriptEngineManager().getEngineByExtension("kts")
        val compiled = (engine as Compilable).compile("""
            val xxx = bindings["x"]
            println(xxx)
            """)
        val bindings = engine.createBindings()
        bindings["x"] = "Hello!"
        compiled.eval(bindings)
    }

    fun example4() {
        val engine = ScriptEngineManager().getEngineByExtension("kts")
        engine.eval("""
            val x = 1
            val y = eval("${'$'}x + 1")
            println(y)
        """)
    }

    fun example5() {
        val engine = ScriptEngineManager().getEngineByExtension("kts")
        val compiledScript = (engine as Compilable).compile(this::class.java.classLoader.getResource("template.kts").readText())
        val bindings = engine.createBindings()
        bindings["name"] = "Kotlin"
        bindings["users"] = listOf(User("ことりん"), User("コトリン"))
        val str = compiledScript.eval(bindings)
        println(str)
    }
}

fun main(args: Array<String>) {
    Main().example1()
    Main().example2()
    Main().example3()
    Main().example4()
    Main().example5()
}
