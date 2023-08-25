import spock.lang.Specification

class CalculatorNativeSpec extends Specification {

    CalculatorNative calculator = new CalculatorNative()

    def "test addition"() {
        expect:
        calculator.evaluate('2+2') == 4
        calculator.evaluate('5+10') == 15
        calculator.evaluate('12+0') == 12
    }

    def "test subtraction"() {
        expect:
        calculator.evaluate('5-3') == 2
        calculator.evaluate('10-5') == 5
        calculator.evaluate('10-10') == 0
    }

    def "test multiplication"() {
        expect:
        calculator.evaluate('3*3') == 9
        calculator.evaluate('5*2') == 10
        calculator.evaluate('0*10') == 0
    }

    def "test division"() {
        expect:
        calculator.evaluate('9/3') == 3
        calculator.evaluate('10/2') == 5
        calculator.evaluate('0/10') == 0
    }

    def "test parentheses"() {
        expect:
        calculator.evaluate('(2+2)*3') == 12
        calculator.evaluate('2+(2*3)') == 8
        calculator.evaluate('(5+5)*(2+3)') == 50
    }

    def "test addition Given|When|Then"() {
        given: "an expression to be evaluated"
        String expression = "5+3"

        when: "the expression is evaluated"
        def result = calculator.evaluate(expression)

        then: "the result should be the sum"
        result == 8
    }

    def "test subtraction with negative result"() {
        given: "an expression involving subtraction"
        String expression = "5-8"

        when: "the expression is evaluated"
        def result = calculator.evaluate(expression)

        then: "the result should be negative"
        result == -3
    }

    def "test multiplication Given|When|Then"() {
        given: "an expression with multiplication"
        String expression = "4*4"

        when: "the expression is evaluated"
        def result = calculator.evaluate(expression)

        then: "the result should be the product"
        result == 16
    }

    def "test division Given|When|Then"() {
        given: "an expression involving division"
        String expression = "9/3"

        when: "the expression is evaluated"
        def result = calculator.evaluate(expression)

        then: "the result should be the quotient"
        result == 3
    }

    def "test parentheses Given|When|Then"() {
        given: "an expression with parentheses"
        String expression = "(3+2)*4"

        when: "the expression is evaluated"
        def result = calculator.evaluate(expression)

        then: "the result should consider the parentheses"
        result == 20
    }

}