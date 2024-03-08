//mvn jacoco:prepare-agent test install jacoco:report

package bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


//HAY PRUEBAS QUE ESTÁN COMENTADAS, LAS HE COMENTADO YA QUE SON PRUEBAS QUE FALLARÁN CON EL FIN DE PONER
//A PRUEBA EL CÓDIGO

class BankAccountTest {

    BankAccount bankAccount;

    @BeforeEach
    void setup() {
    }

    @Test
    @DisplayName("el objeto se crea correctamente")
    public void constructorCorrect(){
        bankAccount = new BankAccount(1000);
        int resultado = 1000;
        int valorObtenido = bankAccount.getBalance();
        assertEquals(resultado, valorObtenido);
    }


    @Test
    @DisplayName("se realiza un deposito correctamente")
    public void depositIsCorrect(){
        bankAccount = new BankAccount(0) ;
        int valor = 100;
        bankAccount.deposit(valor);
        assertEquals(valor, bankAccount.getBalance());
    }

    @Test
    @DisplayName("se realiza un deposito incorrectamente")
    public void depositIsNotCorrect(){
        bankAccount = new BankAccount(0) ;
        int valor = (-100);
        assertThrows(IllegalArgumentException.class,()->{
            bankAccount.deposit(valor);
        });
    }

    @Test
    @DisplayName("se realiza una retirada correctamente")
    public void withdrawIsCorrect(){
        bankAccount = new BankAccount(100) ;
        int valor = 100;
        assertTrue(bankAccount.withdraw(valor));
    }

    @Test
    @DisplayName("se realiza una retirada con valor negativo")
    public void withdrawIsNegative(){
        bankAccount = new BankAccount(100) ;
        int valor = -100;
          assertThrows(IllegalArgumentException.class,()->{
              bankAccount.withdraw(valor);
              });    
    }

    @Test
    @DisplayName("se realiza una retirada incorrectamente")
    public void withdrawIsNotCorrect(){
        bankAccount = new BankAccount(100) ;
        int valor = (200);
        assertFalse(bankAccount.withdraw(valor));
    }

    @Test
    @DisplayName("se calcula un pago por mes para un préstamo correctamente")
    public void loanCalculateIsCorrect(){
        bankAccount = new BankAccount(0);
        double total_amount = 10000;
        double interest = 0.001;
        int npayments = 12;
        double resultado = 838.7599255697181;
        double valorObtenido = bankAccount.payment(total_amount, interest, npayments);
        assertEquals(resultado,valorObtenido);
    }

   
    @Test
    @DisplayName("resultado de pago por mes para un total negativo es incorrecto")
    public void loanCalculateLowerTotalZero(){
        bankAccount = new BankAccount(0);
        int meses = 12;
        double interes = 0.0001;
        double total = -10000;
             assertThrows(IllegalArgumentException.class,()->{
                 bankAccount.payment(total,interes,meses);
             });    
    }

    @Test
    @DisplayName("resultado de pago por mes para un préstamo de cero meses incorrecto")
    public void loanCalculateZeroMonths(){
        bankAccount = new BankAccount(0);
        int meses = 0;
        double interes = 0.0001;
        double total = 10000;
             assertThrows(IllegalArgumentException.class,()->{
                 bankAccount.payment(total,interes,meses);
             });    
    }

    @Test
    @DisplayName("resultado de pago por mes para un préstamo de menos de cero meses es incorrecto")
    public void loanCalculateLowerZeroMonths(){
        bankAccount = new BankAccount(0);
        int meses = -1;
        double interes = 0.0001;
        double total = 10000;
             assertThrows(IllegalArgumentException.class,()->{
                 bankAccount.payment(total,interes,meses);
             });    
    }

    @Test
    @DisplayName("resultado de pago por mes para un total de cero euros es cero")
    public void loanCalculateTotalIsZero(){
        bankAccount = new BankAccount(0);
        int meses = 12;
        double interes = 0.0001;
        double total = 0;
        double valorObtenido = bankAccount.payment(total,interes,meses);
        double resultado = 0;
            assertEquals(resultado,valorObtenido);   
    }

    @Test
    @DisplayName("resultado de pago por mes para un interes de cero porciento")
    public void loanCalculateZeroInteres(){
        bankAccount = new BankAccount(0);
        int meses = 12;
        double interes = 0;
        double total = 1200;
        double valorObtenido = bankAccount.payment(total,interes,meses);
        double resultado = total / meses;
            assertEquals(resultado,valorObtenido);   
    }

    @Test
    @DisplayName("resultado de pago por mes para un interes negativo")
    public void loanCalculateNegativeInteres(){
        bankAccount = new BankAccount(0);
        int meses = 12;
        double interes = -0.0001;
        double total = 10000;
             assertThrows(IllegalArgumentException.class,()->{
                 bankAccount.payment(total,interes,meses);
             });    
    }

    @Test
    @DisplayName("se calcula el resto pendiente a pagar del préstamo correctamente")
    public void pendingRestIsCorrect(){
        bankAccount = new BankAccount(0);
        double amount = 10000;
        double inte = 0.001;
        int npayments = 12;
        int mont = 2;
        double resultado = 8341.651388934994;
        double valorObtenido = bankAccount.pending(amount, inte, npayments, mont);
        assertEquals(resultado,valorObtenido);
    }

    @Test
    @DisplayName("se calcula el resto pendiente a pagar del préstamo con menos de 0 meses pagados")
    public void pendingMonthtIsLessZero(){
        bankAccount = new BankAccount(0);
        double amount = 10000;
        double inte = 0.001;
        int npayments = 12;
        int mont = -2;
        assertThrows(IllegalArgumentException.class,()->{
             bankAccount.pending(amount, inte, npayments, mont);
        });    
    }
    
    @Test
    @DisplayName("se calcula el resto pendiente a pagar del préstamo de total negativo")
    public void pendingTotalIsLessZero(){
        bankAccount = new BankAccount(0);
        double amount = -10000;
        double inte = 0.001;
        int npayments = 12;
        int mont = 1;
        assertThrows(IllegalArgumentException.class,()->{
             bankAccount.pending(amount, inte, npayments, mont);
        });    
    }

    @Test
    @DisplayName("se calcula el resto pendiente a pagar del préstamo con interes negativo")
    public void pendingInteresIsLessZero(){
        bankAccount = new BankAccount(0);
        double amount = 10000;
        double inte = -0.001;
        int npayments = 12;
        int mont = 1;
        assertThrows(IllegalArgumentException.class,()->{
             bankAccount.pending(amount, inte, npayments, mont);
        });    
    }

    @Test
    @DisplayName("se calcula el resto pendiente a pagar del préstamo con numero de pagos negativo")
    public void pendingNPaymentsIsLessZero(){
        bankAccount = new BankAccount(0);
        double amount = 10000;
        double inte = 0.001;
        int npayments = -12;
        int mont = 1;
        assertThrows(IllegalArgumentException.class,()->{
             bankAccount.pending(amount, inte, npayments, mont);
        });    
    }

    @Test
    @DisplayName("se calcula el resto pendiente a pagar del préstamo con 0 meses pagados")
    public void pendingMonthtIsZero(){
        bankAccount = new BankAccount(0);
        double amount = 10000;
        double inte = 0.001;
        int npayments = 12;
        int mont = 0;
        double resultado = 10000;
        double valorObtenido = bankAccount.pending(amount, inte, npayments, mont);
        assertEquals(resultado,valorObtenido);
    }
}
