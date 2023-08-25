
class CalculatorSimple {
    // Overload the call method to evaluate expressions
    def call(String expr) {
        evaluate(expr)
    }

    private evaluate(String expr) {
        def shell = new GroovyShell()
        shell.evaluate("return ${expr}")
    }

}

