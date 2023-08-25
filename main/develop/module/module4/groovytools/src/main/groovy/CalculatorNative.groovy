
class CalculatorNative {
    private String expression
    private int pos = 0

    // Defining operation closures
    def add = { a, b -> a + b }
    def subtract = { a, b -> a - b }
    def multiply = { a, b -> a * b }
    def divide = { a, b -> a / b }

    def evaluate(String expr) {
        expression = expr
        pos = 0
        return calculate()
    }

    private def calculate() {
        def result = term()

        while (pos < expression.length()) {
            if (expression[pos] == '+') {
                pos++
                result = add(result, term())
            } else if (expression[pos] == '-') {
                pos++
                result = subtract(result, term())
            } else {
                break
            }
        }

        return result
    }

    private def term() {
        def result = factor()

        while (pos < expression.length()) {
            if (expression[pos] == '*') {
                pos++
                result = multiply(result, factor())
            } else if (expression[pos] == '/') {
                pos++
                result = divide(result, factor())
            } else {
                break
            }
        }

        return result
    }

    private def factor() {
        if (expression[pos] == '(') {
            pos++
            def result = calculate()
            if (expression[pos] == ')') {
                pos++
                return result
            }
        }
        return number()
    }

    private def number() {
        int start = pos
        while (pos < expression.length() && Character.isDigit(expression[pos] as char)) {
            pos++
        }
        return expression.substring(start, pos).toInteger()
    }

    // Operator overloading
    def call(String expr) {
        return evaluate(expr)
    }
}

