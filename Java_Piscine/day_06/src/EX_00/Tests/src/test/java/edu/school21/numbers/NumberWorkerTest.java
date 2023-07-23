package edu.school21.numbers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NumberWorkerTest {

    private NumberWorker numberWorker;

    @BeforeEach
    void createNumberWorker(){
        numberWorker = new NumberWorker();
    }

    @ParameterizedTest(name = "{index} - {0} is a negative")
    @ValueSource(ints = {0, 1, -200})
    void isPrimeForIncorrectNumbers(final int number) {
        assertThrows(IllegalNumberException.class, () -> numberWorker.isPrime(number));
    }


    @ParameterizedTest
    @ValueSource(ints = {7,19,11})
    void isPrimeForPrime(final int number) throws IllegalNumberException {
        assertTrue(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {4,6,8})
    void isPrimeForNotPrime(final int number) throws IllegalNumberException{
        assertFalse(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 0)
    void digitSumTest(int number, int sum){
        assertEquals(sum,numberWorker.digitsSum(number));
    }
}